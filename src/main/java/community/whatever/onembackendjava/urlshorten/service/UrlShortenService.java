package community.whatever.onembackendjava.urlshorten.service;

import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.common.exception.custom.NotFoundException;
import community.whatever.onembackendjava.urlshorten.component.UrlShortenValidator;
import community.whatever.onembackendjava.urlshorten.component.UrlShortener;
import community.whatever.onembackendjava.urlshorten.domain.UrlShorten;
import community.whatever.onembackendjava.urlshorten.repository.UrlShortenRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenService {

    private final UrlShortenValidator urlShortenValidator;
    private final UrlShortenRepository urlShortenRepository;

    public UrlShortenService(UrlShortenValidator urlShortenValidator,
        UrlShortenRepository urlShortenRepository) {
        this.urlShortenValidator = urlShortenValidator;
        this.urlShortenRepository = urlShortenRepository;
    }

    public String createShortenUrl(String originUrl) {
        urlShortenValidator.validateUrl(originUrl);
        String shortenUrlKey = UrlShortener.shorten();

        UrlShorten urlShorten = new UrlShorten(originUrl, shortenUrlKey, LocalDateTime.now().plusMinutes(1));
        urlShortenRepository.save(urlShorten);

        return shortenUrlKey;
    }

    public String getOriginUrlByShortenUrlKey(String shortenUrlKey) {
        UrlShorten urlShorten = urlShortenRepository.findByShortenUrlKey(shortenUrlKey)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_SHORTEN_URL));

        if (urlShorten.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("만료된 URL입니다.");
        }

        return urlShorten.getOriginUrl();
    }

}
