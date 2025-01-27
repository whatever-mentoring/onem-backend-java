package community.whatever.onembackendjava;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ShortUrlControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Value("${shortener.endpoint.create}")
    private String createEndpoint;

    @Value("${shortener.endpoint.search}")
    private String searchEndpoint;

    @Value("${shortener.origin}")
    private String originalUrl;

    @Value("${shortener.invalid-key}")
    private String invalidKey;

    @Test
    void createShortUrlTest() {

        ResponseEntity<String> response = restTemplate.postForEntity(
                createEndpoint,
                originalUrl,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        String generatedKey = response.getBody();
        assertThat(generatedKey).isNotBlank();
    }

    @Test
    void getOriginalUrlTest() {

        String generatedKey = restTemplate.postForObject(
                createEndpoint,
                originalUrl,
                String.class
        );

        ResponseEntity<String> response = restTemplate.getForEntity(
                searchEndpoint,
                String.class,
                generatedKey
        );


        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(originalUrl);
    }

    @Test
    void getOriginalUrlWithInvalidKeyTest() {

        ResponseEntity<String> response = restTemplate.getForEntity(
                searchEndpoint,
                String.class,
                invalidKey
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
