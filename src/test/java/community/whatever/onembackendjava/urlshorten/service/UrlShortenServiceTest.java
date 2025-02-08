package community.whatever.onembackendjava.urlshorten.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.common.exception.custom.NotFoundException;
import community.whatever.onembackendjava.common.exception.custom.ExpiredUrlException;
import community.whatever.onembackendjava.common.exception.custom.ValidationException;
import community.whatever.onembackendjava.urlshorten.domain.UrlShorten;
import community.whatever.onembackendjava.urlshorten.repository.UrlShortenRepository;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UrlShortenServiceTest {

    @Autowired
    private UrlShortenService urlShortenService;

    @Autowired
    private UrlShortenRepository urlShortenRepository;

    @Test
    void shorten_url을_생성하고_조회한다() {
        String expectedOriginUrl = "https://www.google.com";
        String key = urlShortenService.createShortenUrl(expectedOriginUrl);
        String originUrl = urlShortenService.getOriginUrlByShortenUrlKey(key);

        assertThat(originUrl).isEqualTo(expectedOriginUrl);
    }

    @ParameterizedTest
    @MethodSource("invalidUrlProvider")
    void 잘못된_입력값으로_요청하면_예외가_발생한다(String originUrl, Class<? extends RuntimeException> expectedException, String expectedMessage) {
        assertThatThrownBy(() -> urlShortenService.createShortenUrl(originUrl))
            .isInstanceOf(expectedException)
            .hasMessage(expectedMessage);
    }

    private static Stream<Arguments> invalidUrlProvider() {
        return Stream.of(
            Arguments.of(Named.of("빈 문자열", ""), ValidationException.class, ErrorCode.INVALID_URL_FORMAT.getMessage()),
            Arguments.of(Named.of("공백이 포함된 URL", "https ://google.com"), ValidationException.class, ErrorCode.INVALID_URL_FORMAT.getMessage()),
            Arguments.of(Named.of("http가 누락된 URL", "://google.com"), ValidationException.class, ErrorCode.INVALID_URL_FORMAT.getMessage()),
            Arguments.of(Named.of("잘못된 프로토콜", "htp://example.com"), ValidationException.class, ErrorCode.INVALID_URL_FORMAT.getMessage()),
            Arguments.of(Named.of("차단된 URL", "https://www.example.com"), ValidationException.class, ErrorCode.BLOCKED_URL.getMessage())
            );
    }

    @Test
    void 존재하지_않는_shorten_url_key로_조회하면_예외가_발생한다() {
        String nonExistingKey = "nonExistingKey";

        assertThatThrownBy(() -> urlShortenService.getOriginUrlByShortenUrlKey(nonExistingKey))
            .isInstanceOf(NotFoundException.class)
            .hasMessage(ErrorCode.NOT_FOUND_SHORTEN_URL.getMessage());

    }

    @Test
    void 만료된_shorten_url을_조회하면_예외가_발생한다() {
        String expiredKey = "expiredKey";
        UrlShorten expiredUrl = new UrlShorten("https://google.com", expiredKey, LocalDateTime.now().minusMinutes(1));
        urlShortenRepository.save(expiredUrl);

        assertThatThrownBy(() -> urlShortenService.getOriginUrlByShortenUrlKey(expiredKey))
            .isInstanceOf(ExpiredUrlException.class)
            .hasMessage(ErrorCode.EXPIRED_SHORTEN_URL.getMessage());
    }

}
