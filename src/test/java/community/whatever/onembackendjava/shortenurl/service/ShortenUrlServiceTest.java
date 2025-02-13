package community.whatever.onembackendjava.shortenurl.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.common.exception.custom.NotFoundException;
import community.whatever.onembackendjava.common.exception.custom.ExpiredUrlException;
import community.whatever.onembackendjava.common.exception.custom.ValidationException;
import community.whatever.onembackendjava.shortenurl.entity.ShortenUrl;
import community.whatever.onembackendjava.shortenurl.repository.ShortenUrlRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShortenUrlServiceTest {

    @Autowired
    private ShortenUrlService shortenUrlService;

    @Autowired
    private ShortenUrlRepository shortenUrlRepository;

    @Test
    void shorten_url을_생성하고_조회한다() {
        String expectedOriginUrl = "https://www.google.com";
        String key = shortenUrlService.createShortenUrl(expectedOriginUrl);
        String originUrl = shortenUrlService.getOriginUrlByShortenUrlKey(key);

        assertThat(originUrl).isEqualTo(expectedOriginUrl);
    }

    @ParameterizedTest
    @CsvSource({
        "''",
        "'https ://google.com'",
        "'://google.com'",
        "'htp://example.com'"
    })
    void 잘못된_입력값으로_요청하면_예외가_발생한다(String originUrl) {
        assertThatThrownBy(() -> shortenUrlService.createShortenUrl(originUrl))
            .isInstanceOf(ValidationException.class)
            .hasMessage(ErrorCode.INVALID_URL_FORMAT.getMessage());
    }

    @Test
    void 사용_불가능한_url일_경우_예외가_발생한다() {
        String originUrl = "https://www.example.com";

        assertThatThrownBy(() -> shortenUrlService.createShortenUrl(originUrl))
            .isInstanceOf(ValidationException.class)
            .hasMessage(ErrorCode.BLOCKED_URL.getMessage());
    }

    @Test
    void 존재하지_않는_shorten_url_key로_조회하면_예외가_발생한다() {
        String nonExistingKey = "nonExistingKey";

        assertThatThrownBy(() -> shortenUrlService.getOriginUrlByShortenUrlKey(nonExistingKey))
            .isInstanceOf(NotFoundException.class)
            .hasMessage(ErrorCode.NOT_FOUND_SHORTEN_URL.getMessage());

    }

    @Test
    void 만료된_shorten_url을_조회하면_예외가_발생한다() {
        String expiredKey = "expiredKey";
        ShortenUrl expiredUrl = new ShortenUrl( "https://google.com", expiredKey, LocalDateTime.now().minusMinutes(1));
        shortenUrlRepository.save(expiredUrl);

        assertThatThrownBy(() -> shortenUrlService.getOriginUrlByShortenUrlKey(expiredKey))
            .isInstanceOf(ExpiredUrlException.class)
            .hasMessage(ErrorCode.EXPIRED_SHORTEN_URL.getMessage());
    }

}
