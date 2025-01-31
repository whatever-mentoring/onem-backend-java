package community.whatever.onembackendjava.application;

import community.whatever.onembackendjava.infrastructure.UrlShortenRepository;
import java.util.NoSuchElementException;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenService {

    private final UrlShortenRepository urlShortenRepository;

    private static final Random RANDOM = new Random();

    public UrlShortenService(UrlShortenRepository urlShortenRepository) {
        this.urlShortenRepository = urlShortenRepository;
    }

    public String getOriginUrlByShortenUrlKey(String shortenUrlKey) {
        return urlShortenRepository.findByShortenUrlKey(shortenUrlKey)
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 key입니다."));
    }

    public String createShortenUrl(String originUrl) {
        String shortenUrlKey = generateKey();
        urlShortenRepository.save(shortenUrlKey, originUrl);
        return shortenUrlKey;
    }

    private String generateKey() {
        return String.valueOf(RANDOM.nextInt(10000));
    }

}
