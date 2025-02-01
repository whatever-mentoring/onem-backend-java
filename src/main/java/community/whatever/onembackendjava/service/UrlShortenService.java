package community.whatever.onembackendjava.service;

import community.whatever.onembackendjava.common.exception.notfound.NotFoundShortenUrlException;
import community.whatever.onembackendjava.component.UrlShortener;
import community.whatever.onembackendjava.repository.UrlShortenRepository;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenService {

    private final UrlShortenRepository urlShortenRepository;
    private final UrlShortener urlShortener;

    public UrlShortenService(UrlShortenRepository urlShortenRepository) {
        this.urlShortenRepository = urlShortenRepository;
        this.urlShortener = new UrlShortener();
    }

    public String createShortenUrl(String originUrl) {
        String shortenUrlKey = urlShortener.shorten();
        urlShortenRepository.save(shortenUrlKey, originUrl);
        return shortenUrlKey;
    }

    public String getOriginUrlByShortenUrlKey(String shortenUrlKey) {
        return urlShortenRepository.findByShortenUrlKey(shortenUrlKey)
            .orElseThrow(NotFoundShortenUrlException::new);
    }

}
