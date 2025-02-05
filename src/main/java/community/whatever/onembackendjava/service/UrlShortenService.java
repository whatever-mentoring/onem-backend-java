package community.whatever.onembackendjava.service;

import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.common.exception.custom.NotFoundException;
import community.whatever.onembackendjava.component.UrlShortener;
import community.whatever.onembackendjava.repository.UrlShortenRepository;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenService {

    private final ValidateUrlShortenService validateUrlShortenService;
    private final UrlShortenRepository urlShortenRepository;

    public UrlShortenService(ValidateUrlShortenService validateUrlShortenService,
        UrlShortenRepository urlShortenRepository) {
        this.validateUrlShortenService = validateUrlShortenService;
        this.urlShortenRepository = urlShortenRepository;
    }

    public String createShortenUrl(String originUrl) {
        validateUrlShortenService.validateUrl(originUrl);
        String shortenUrlKey = UrlShortener.shorten();
        urlShortenRepository.save(shortenUrlKey, originUrl);
        return shortenUrlKey;
    }

    public String getOriginUrlByShortenUrlKey(String shortenUrlKey) {
        return urlShortenRepository.findByShortenUrlKey(shortenUrlKey)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_SHORTEN_URL));
    }

}
