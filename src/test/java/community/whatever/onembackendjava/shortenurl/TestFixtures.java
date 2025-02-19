package community.whatever.onembackendjava.shortenurl;

import community.whatever.onembackendjava.shortenurl.component.ShortenUrlValidator;
import community.whatever.onembackendjava.shortenurl.mock.FakeShortenUrlRepository;
import community.whatever.onembackendjava.shortenurl.mock.StubShortenUrlKeyGenerator;
import community.whatever.onembackendjava.shortenurl.mock.StubShortenUrlValidator;
import community.whatever.onembackendjava.shortenurl.properties.ShortenUrlProperties;
import community.whatever.onembackendjava.shortenurl.service.ShortenUrlService;
import java.time.Duration;
import java.util.Set;

public class TestFixtures {

    static final FakeShortenUrlRepository shortenUrlRepository = new FakeShortenUrlRepository();

    public static ShortenUrlService createShortenUrlService() {
        ShortenUrlProperties shortenUrlProperties = new ShortenUrlProperties();
        shortenUrlProperties.setExpiredDuration(Duration.ofMinutes(1));

        StubShortenUrlValidator shortenUrlValidator = new StubShortenUrlValidator();
        StubShortenUrlKeyGenerator snowflakeKeyGenerator = new StubShortenUrlKeyGenerator();

        return new ShortenUrlService(
            shortenUrlProperties,
            shortenUrlValidator,
            snowflakeKeyGenerator,
            shortenUrlRepository
        );
    }

    public static FakeShortenUrlRepository getShortenUrlRepository() {
        return shortenUrlRepository;
    }

    public static ShortenUrlValidator createShortenUrlValidator() {
        ShortenUrlProperties shortenUrlProperties = new ShortenUrlProperties();
        shortenUrlProperties.setBlockedDomains(Set.of("www.example.com"));

        return new ShortenUrlValidator(shortenUrlProperties);
    }
}
