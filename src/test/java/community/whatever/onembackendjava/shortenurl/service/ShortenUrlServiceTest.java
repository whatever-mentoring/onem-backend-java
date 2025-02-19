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
import community.whatever.onembackendjava.shortenurl.mock.StubShortenUrlKeyGenerator;
import community.whatever.onembackendjava.shortenurl.properties.ShortenUrlProperties;
import java.time.Duration;
import java.time.LocalDateTime;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShortenUrlServiceTest {

    private ShortenUrlService shortenUrlService;
    private FakeShortenUrlRepository shortenUrlRepository;

    private Faker faker;


    @BeforeEach
    void setUp() {
        ShortenUrlProperties shortenUrlProperties = new ShortenUrlProperties();
        shortenUrlProperties.setExpiredDuration(Duration.ofMinutes(1));
        StubShortenUrlValidator shortenUrlValidator = new StubShortenUrlValidator();
        StubShortenUrlKeyGenerator snowflakeKeyGenerator = new StubShortenUrlKeyGenerator();
        shortenUrlRepository = new FakeShortenUrlRepository();
        faker = new Faker();

        shortenUrlService = new ShortenUrlService(
            shortenUrlProperties,
            shortenUrlValidator,
            snowflakeKeyGenerator,
            shortenUrlRepository
        );
    }

    @Test
    void shorten_url을_생성한다() {
        String originUrl = faker.internet().url();
        ShortenUrlResponse response = shortenUrlService.createShortenUrl(originUrl);
        assertThat(response).isNotNull();
        assertThat(response.originUrl()).isEqualTo(originUrl);
    }

    @Test
    void origin_url을_조회한다() {
        ShortenUrl shortenUrl = saveShortenUrl(faker.internet().url(), "dev-" + faker.regexify("[a-zA-Z0-9]{8}"), Duration.ofMinutes(1));
        ShortenUrlResponse response = shortenUrlService.getOriginUrlByShortenUrlKey(shortenUrl.getShortenUrlKey());
        assertThat(response.originUrl()).isEqualTo(shortenUrl.getOriginUrl());
    }

    @Test
    void origin_url이_존재하지_않을_경우_예외가_발생한다() {
        assertThatThrownBy(() -> shortenUrlService.getOriginUrlByShortenUrlKey("dev-NzdzaHhtbjlNWnz"))
            .isInstanceOf(NotFoundException.class)
            .hasMessage(ErrorCode.NOT_FOUND_SHORTEN_URL.getMessage());
    }

    @Test
    void origin_url이_만료된_경우_예외가_발생한다() {
        ShortenUrl shortenUrl = saveShortenUrl(faker.internet().url(), "dev-" + faker.regexify("[a-zA-Z0-9]{8}"), Duration.ZERO);
        assertThatThrownBy(() -> shortenUrlService.getOriginUrlByShortenUrlKey(shortenUrl.getShortenUrlKey()))
            .isInstanceOf(ExpiredUrlException.class)
            .hasMessage(ErrorCode.EXPIRED_SHORTEN_URL.getMessage());
    }

    private ShortenUrl saveShortenUrl(String originUrl, String shortenUrlKey, Duration duration) {
        ShortenUrl shortenUrl = new ShortenUrl(originUrl, shortenUrlKey, LocalDateTime.now().plus(duration));
        shortenUrlRepository.save(shortenUrl);
        return shortenUrl;
    }
}
