package community.whatever.onembackendjava.shortenurl.service;

import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.common.exception.custom.ExpiredUrlException;
import community.whatever.onembackendjava.common.exception.custom.NotFoundException;
import community.whatever.onembackendjava.shortenurl.component.ShortenUrlKeyGenerator;
import community.whatever.onembackendjava.shortenurl.component.ShortenUrlValidator;
import community.whatever.onembackendjava.shortenurl.entity.ShortenUrl;
import community.whatever.onembackendjava.shortenurl.properties.ShortenUrlProperties;
import community.whatever.onembackendjava.shortenurl.repository.ShortenUrlRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ShortenUrlService {

    private final ShortenUrlProperties shortenUrlProperties;
    private final ShortenUrlValidator shortenUrlValidator;
    private final ShortenUrlKeyGenerator shortenUrlKeyGenerator;
    private final ShortenUrlRepository shortenUrlRepository;

    public ShortenUrlService(ShortenUrlProperties shortenUrlProperties,
        ShortenUrlValidator shortenUrlValidator, ShortenUrlKeyGenerator shortenUrlKeyGenerator,
        ShortenUrlRepository shortenUrlRepository) {
        this.shortenUrlProperties = shortenUrlProperties;
        this.shortenUrlValidator = shortenUrlValidator;
        this.shortenUrlKeyGenerator = shortenUrlKeyGenerator;
        this.shortenUrlRepository = shortenUrlRepository;
    }

    public String createShortenUrl(String originUrl) {
        shortenUrlValidator.validate(originUrl);

        ShortenUrl shortenUrl = saveShortenUrl(originUrl);
        return shortenUrl.getShortenUrlKey();
    }


    public String getOriginUrlByShortenUrlKey(String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findByShortenUrlKey(shortenUrlKey)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_SHORTEN_URL));

        validateNotExpired(shortenUrl);
        return shortenUrl.getOriginUrl();
    }

    private ShortenUrl saveShortenUrl(String originUrl) {
        long uniqueId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

        String shortenUrlKey = shortenUrlKeyGenerator.generate(uniqueId);
        LocalDateTime expirationTime = LocalDateTime.now().plus(shortenUrlProperties.getExpiredDuration());

        ShortenUrl shortenUrl = new ShortenUrl(originUrl, shortenUrlKey, expirationTime);
        shortenUrlRepository.save(shortenUrl);

        return shortenUrl;
    }

    private void validateNotExpired(ShortenUrl shortenUrl) {
        if (shortenUrl.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new ExpiredUrlException(ErrorCode.EXPIRED_SHORTEN_URL);
        }
    }
}
