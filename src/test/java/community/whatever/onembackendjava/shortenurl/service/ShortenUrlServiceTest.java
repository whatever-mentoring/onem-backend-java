package community.whatever.onembackendjava.shortenurl.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.common.exception.custom.ExpiredUrlException;
import community.whatever.onembackendjava.common.exception.custom.NotFoundException;
import community.whatever.onembackendjava.shortenurl.dto.ShortenUrlResponse;
import community.whatever.onembackendjava.shortenurl.entity.ShortenUrl;
import community.whatever.onembackendjava.shortenurl.mock.FakeShortenUrlRepository;
import community.whatever.onembackendjava.shortenurl.mock.StubShortenUrlValidator;
import community.whatever.onembackendjava.shortenurl.mock.StubSnowflakeKeyGenerator;
import community.whatever.onembackendjava.shortenurl.properties.ShortenUrlProperties;
import java.time.Duration;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShortenUrlServiceTest {

    private ShortenUrlService shortenUrlService;
    private FakeShortenUrlRepository shortenUrlRepository;

    @BeforeEach
    void setUp() {
        ShortenUrlProperties shortenUrlProperties = new ShortenUrlProperties();
        shortenUrlProperties.setExpiredDuration(Duration.ofMinutes(1));
        StubShortenUrlValidator shortenUrlValidator = new StubShortenUrlValidator();
        StubSnowflakeKeyGenerator snowflakeKeyGenerator = new StubSnowflakeKeyGenerator();
        shortenUrlRepository = new FakeShortenUrlRepository();

        shortenUrlService = new ShortenUrlService(
            shortenUrlProperties,
            shortenUrlValidator,
            snowflakeKeyGenerator,
            shortenUrlRepository
        );
    }

    @Test
    void shorten_url을_생성한다() {
        String originUrl = "https://www.google.com";

        ShortenUrlResponse response = shortenUrlService.createShortenUrl(originUrl);

        assertThat(response).isNotNull();
        assertThat(response.originUrl()).isEqualTo(originUrl);
    }

    @Test
    void origin_url을_조회한다() {
        String originUrl = "https://www.google.com";
        ShortenUrl shortenUrl = new ShortenUrl(originUrl, "dev-NzdzaHhtbjlNWnU", LocalDateTime.now().plus(Duration.ofMinutes(1)));
        shortenUrlRepository.save(shortenUrl);

        ShortenUrlResponse response = shortenUrlService.getOriginUrlByShortenUrlKey(shortenUrl.getShortenUrlKey());

        assertThat(response.originUrl()).isEqualTo(originUrl);
    }

    @Test
    void origin_url이_존재하지_않을_경우_예외가_발생한다() {
        String shortenUrlKey = "dev-NzdzaHhtbjlNWnz";

        assertThatThrownBy(() -> shortenUrlService.getOriginUrlByShortenUrlKey(shortenUrlKey))
            .isInstanceOf(NotFoundException .class)
            .hasMessage(ErrorCode.NOT_FOUND_SHORTEN_URL.getMessage());
    }

    @Test
    void origin_url이_만료된_경우_예외가_발생한다() {
        String originUrl = "https://www.google.com";
        ShortenUrl shortenUrl = new ShortenUrl(originUrl, "dev-NzdzaHhtbjlNWnU", LocalDateTime.now().plus(Duration.ZERO));
        shortenUrlRepository.save(shortenUrl);

        assertThatThrownBy(() -> shortenUrlService.getOriginUrlByShortenUrlKey(shortenUrl.getShortenUrlKey()))
            .isInstanceOf(ExpiredUrlException.class)
            .hasMessage(ErrorCode.EXPIRED_SHORTEN_URL.getMessage());
    }
}
