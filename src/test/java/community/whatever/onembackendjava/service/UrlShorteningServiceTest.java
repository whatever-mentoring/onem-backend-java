package community.whatever.onembackendjava.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UrlShorteningServiceTest {

    @Autowired
    private UrlShorteningService urlShorteningService;

    private final String originUri = "https://www.google.com";
    private String randomUri;

    @BeforeEach
    public void setUp() {
        randomUri = urlShorteningService.createShortenUri(originUri);
    }

    @DisplayName("[URL] shorten url을 조회한다.")
    @Test
    void shorten_url을_조회한다() {
        String shortenedUri = urlShorteningService.searchOriginUri(randomUri);
        assertThat(originUri).isEqualTo(shortenedUri);
    }

    @DisplayName("[URL] shorten url을 생성한다.")
    @Test
    void shorten_url을_생성한다() {
        String prevRandomUri = randomUri;
        randomUri = urlShorteningService.createShortenUri("https://www.google.com");
        assertThat(randomUri).isNotEqualTo(prevRandomUri);
    }
}