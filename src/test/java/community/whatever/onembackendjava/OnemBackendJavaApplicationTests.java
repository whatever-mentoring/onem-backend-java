package community.whatever.onembackendjava;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UrlShortenControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void createShortenUrlTest() {

        String originUrl = "https://www.google.com";

        ResponseEntity<String> response = restTemplate.postForEntity("/shorten-url/create", originUrl, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        String generatedKey = response.getBody();
        assertThat(generatedKey).isNotBlank();
    }

    @Test
    void searchShortenUrlTest() {
        String originUrl = "https://www.google.com";

        String generatedKey = restTemplate.postForObject("/shorten-url/create", originUrl, String.class);

        ResponseEntity<String> response = restTemplate.postForEntity("/shorten-url/search", generatedKey, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(originUrl);
    }

    @Test
    void searchShortenUrlInvalidKeyTest() {
        String invalidKey = "9999";

        ResponseEntity<String> response = restTemplate.postForEntity("/shorten-url/search", invalidKey, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
