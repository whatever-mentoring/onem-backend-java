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
class UrlShortenControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Value("${shortener.endpoint.create}")
    private String createEndpoint;

    @Value("${shortener.endpoint.search}")
    private String searchEndpoint;

    @Value("${shortener.origin}")
    private String originUrl;

    @Value("${shortener.invalid-key}")
    private String invalidKey;

    @Test
    void createShortenUrlTest() {
        ResponseEntity<String> response = restTemplate.postForEntity(createEndpoint, originUrl, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        String generatedKey = response.getBody();
        assertThat(generatedKey).isNotBlank();
    }

    @Test
    void searchShortenUrlTest() {
        String generatedKey = restTemplate.postForObject(createEndpoint, originUrl, String.class);

        ResponseEntity<String> response = restTemplate.postForEntity(searchEndpoint, generatedKey, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(originUrl);
    }

    @Test
    void searchShortenUrlInvalidKeyTest() {

        ResponseEntity<String> response = restTemplate.postForEntity(searchEndpoint, invalidKey, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
