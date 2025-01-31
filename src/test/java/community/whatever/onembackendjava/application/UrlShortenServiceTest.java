package community.whatever.onembackendjava.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import community.whatever.onembackendjava.service.UrlShortenService;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UrlShortenServiceTest {

    @Autowired
    private UrlShortenService urlShortenService;

    @Test
    void shorten_url을_생성하고_조회한다() {
        String expectedOriginUrl = "https://www.google.com";
        String key = urlShortenService.createShortenUrl(expectedOriginUrl);
        String originUrl = urlShortenService.getOriginUrlByShortenUrlKey(key);

        assertThat(originUrl).isEqualTo(expectedOriginUrl);
    }

    @Test
    void 존재하지_않는_key로_조회하면_예외가_발생한다() {
        String nonExistingKey = "nonExistingKey";

        assertThrows(NoSuchElementException.class, () -> {
            urlShortenService.getOriginUrlByShortenUrlKey(nonExistingKey);
        });
    }

}