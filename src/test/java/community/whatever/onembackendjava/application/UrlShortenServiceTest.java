package community.whatever.onembackendjava.application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UrlShortenServiceTest {

    @Autowired
    private UrlShortenService urlShortenService;

    @Test
    @DisplayName("shorten-url을 생성하고 조회한다.")
    void shortenUrlCreateAndSearch() {
        String expectedOriginUrl = "https://www.google.com";
        String key = urlShortenService.shortenUrlCreate(expectedOriginUrl);
        String originUrl = urlShortenService.shortenUrlSearch(key);

        assertTrue(originUrl.equals(expectedOriginUrl));
    }

    @Test
    @DisplayName("유효하지 않은 key로 조회하면 예외가 발생한다.")
    void shortenUrlSearchWithInvalidKey() {
        String invalidKey = "invalidKey";

        assertThrows(IllegalArgumentException.class, () -> {
            urlShortenService.shortenUrlSearch(invalidKey);
        });
    }

}