package community.whatever.onembackendjava.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class UrlShorteningServiceTest {

    @Autowired
    private UrlShorteningService urlShorteningService;

    private String originUri = "https://www.google.com";
    private String randomUri;

    @BeforeEach
    public void setUp() {
        randomUri = urlShorteningService.createShortenUri(originUri);
    }

    @DisplayName("[URL] 유효한 shorten url로 원본 url을 가져올 수 있다.")
    @Test
    void searchOriginUri() {
        String shortenedUri = urlShorteningService.searchOriginUri(randomUri);
        assertEquals(shortenedUri, originUri);
    }

    @DisplayName("[URL] uri 주소를 압축해 유효한 shorten url를 생성할 수 있다.")
    @Test
    void createShortenUri() {
        String prevRandomUri = randomUri;
        randomUri = urlShorteningService.createShortenUri("https://www.google.com");
        assertNotEquals(prevRandomUri, randomUri);
    }
}