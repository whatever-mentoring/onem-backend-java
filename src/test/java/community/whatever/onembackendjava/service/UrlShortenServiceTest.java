package community.whatever.onembackendjava.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UrlShortenServiceTest {

    private UrlShortenService urlShortenService;
    @BeforeEach
    void setUp(){
        urlShortenService = new UrlShortenService();
    }

    @Test
    void ShortenUrl_을_생성하고_조회한다(){
        String originUrl = "https://example.com";

        String randomKey = urlShortenService.createShortUrl(originUrl);

        String storedUrl = urlShortenService.getShortUrl(randomKey);
        assertEquals(originUrl, storedUrl);
    }

    @Test
    void 존재하지_않는_UrlKey_로_검색하면_애러가_발생한다(){
        String invalidKey = "1234";

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            urlShortenService.getShortUrl(invalidKey)
        );

        assertEquals("Invalid Key: " + invalidKey, ex.getMessage());
    }
}
