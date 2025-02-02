package community.whatever.onembackendjava.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import community.whatever.onembackendjava.common.exception.custom.BusinessException;
import community.whatever.onembackendjava.common.exception.custom.NotFoundException;
import community.whatever.onembackendjava.service.UrlShortenService;
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
    void 존재하지_않는_shorten_url_key로_조회하면_예외가_발생한다() {
        String nonExistingKey = "nonExistingKey";

        assertThatThrownBy(() -> urlShortenService.getOriginUrlByShortenUrlKey(nonExistingKey))
            .isInstanceOf(NotFoundException.class)
            .hasMessage("단축 URL을 찾을 수 없습니다.");

    }

}