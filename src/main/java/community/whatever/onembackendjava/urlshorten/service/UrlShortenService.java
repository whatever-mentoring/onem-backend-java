package community.whatever.onembackendjava.urlshorten.service;

import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.common.exception.custom.NotFoundException;
import community.whatever.onembackendjava.urlshorten.component.UrlShortener;
import community.whatever.onembackendjava.urlshorten.component.UrlShortenValidator;
import community.whatever.onembackendjava.urlshorten.repository.UrlShortenRepository;
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
        urlShortenRepository.save(shortenUrlKey, originUrl);
        return shortenUrlKey;
    }

    public String getOriginUrlByShortenUrlKey(String shortenUrlKey) {
        return urlShortenRepository.findByShortenUrlKey(shortenUrlKey)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_SHORTEN_URL));
    }

}
