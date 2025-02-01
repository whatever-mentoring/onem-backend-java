package community.whatever.onembackendjava.service;

import community.whatever.onembackendjava.common.exception.BusinessException;
import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.component.UrlShortener;
import community.whatever.onembackendjava.repository.UrlShortenRepository;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenService {

    private final UrlShortenRepository urlShortenRepository;

    public UrlShortenService(UrlShortenRepository urlShortenRepository) {
        this.urlShortenRepository = urlShortenRepository;
    }

    public String createShortenUrl(String originUrl) {
        String shortenUrlKey = UrlShortener.shorten();
        urlShortenRepository.save(shortenUrlKey, originUrl);
        return shortenUrlKey;
    }

    public String getOriginUrlByShortenUrlKey(String shortenUrlKey) {
        return urlShortenRepository.findByShortenUrlKey(shortenUrlKey)
            .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
    }

}
