package community.whatever.onembackendjava.urlshorten.service;

import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.common.exception.custom.ExpiredUrlException;
import community.whatever.onembackendjava.common.exception.custom.NotFoundException;
import community.whatever.onembackendjava.urlshorten.component.UrlShortenValidator;
import community.whatever.onembackendjava.urlshorten.component.UrlShortener;
import community.whatever.onembackendjava.urlshorten.domain.UrlShorten;
import community.whatever.onembackendjava.urlshorten.properties.UrlShortenProperties;
import community.whatever.onembackendjava.urlshorten.repository.UrlShortenRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenService {

    private final UrlShortenValidator urlShortenValidator;
    private final UrlShortenRepository urlShortenRepository;
    private final UrlShortenProperties urlShortenProperties;

    public UrlShortenService(UrlShortenValidator urlShortenValidator,
        UrlShortenRepository urlShortenRepository,
        UrlShortenProperties urlShortenProperties) {
        this.urlShortenValidator = urlShortenValidator;
        this.urlShortenRepository = urlShortenRepository;
        this.urlShortenProperties = urlShortenProperties;
    }

    public String createShortenUrl(String originUrl) {
        urlShortenValidator.validateUrl(originUrl);
        String shortenUrlKey = UrlShortener.shorten();

        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(urlShortenProperties.getExpirationTime());
        UrlShorten urlShorten = new UrlShorten(originUrl, shortenUrlKey, expirationTime);

        urlShortenRepository.save(urlShorten);
        return shortenUrlKey;
    }

    public String getOriginUrlByShortenUrlKey(String shortenUrlKey) {
        UrlShorten urlShorten = urlShortenRepository.findByShortenUrlKey(shortenUrlKey)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_SHORTEN_URL));

        if (urlShorten.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new ExpiredUrlException(ErrorCode.EXPIRED_SHORTEN_URL);
        }

        return urlShorten.getOriginUrl();
    }
}
