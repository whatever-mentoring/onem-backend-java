package community.whatever.onembackendjava.urlshorten.service;

import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.common.exception.custom.ExpiredUrlException;
import community.whatever.onembackendjava.common.exception.custom.NotFoundException;
import community.whatever.onembackendjava.urlshorten.component.ShortenUrlValidator;
import community.whatever.onembackendjava.urlshorten.component.ShortenUrlKeyGenerator;
import community.whatever.onembackendjava.urlshorten.domain.ShortenUrl;
import community.whatever.onembackendjava.urlshorten.properties.ShortenUrlProperties;
import community.whatever.onembackendjava.urlshorten.repository.ShortenUrlRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class ShortenUrlService {

    private final ShortenUrlProperties shortenUrlProperties;
    private final ShortenUrlValidator shortenUrlValidator;
    private final ShortenUrlRepository shortenUrlRepository;

    public ShortenUrlService(ShortenUrlProperties shortenUrlProperties,
        ShortenUrlValidator shortenUrlValidator, ShortenUrlRepository shortenUrlRepository) {
        this.shortenUrlProperties = shortenUrlProperties;
        this.shortenUrlValidator = shortenUrlValidator;
        this.shortenUrlRepository = shortenUrlRepository;
    }

    public String createShortenUrl(String originUrl) {
        shortenUrlValidator.validateUrl(originUrl);
        String shortenUrlKey = ShortenUrlKeyGenerator.shorten();

        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(shortenUrlProperties.getExpirationTime());
        ShortenUrl shortenUrl = new ShortenUrl(originUrl, shortenUrlKey, expirationTime);

        shortenUrlRepository.save(shortenUrl);
        return shortenUrlKey;
    }

    public String getOriginUrlByShortenUrlKey(String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findByShortenUrlKey(shortenUrlKey)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_SHORTEN_URL));

        if (shortenUrl.expiredAt().isBefore(LocalDateTime.now())) {
            throw new ExpiredUrlException(ErrorCode.EXPIRED_SHORTEN_URL);
        }

        return shortenUrl.originUrl();
    }
}
