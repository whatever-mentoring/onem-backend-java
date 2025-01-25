package community.whatever.onembackendjava.application;

import community.whatever.onembackendjava.infrastructure.UrlShortenRepository;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenService {

    private final UrlShortenRepository urlShortenRepository;

    public UrlShortenService(UrlShortenRepository urlShortenRepository) {
        this.urlShortenRepository = urlShortenRepository;
    }

    public String getOriginUrlByKey(String key) {
        return urlShortenRepository.findByKey(key)
            .orElseThrow(() -> new IllegalArgumentException("Invalid key"));
    }

    public String createShortenUrl(String originUrl) {
        String key = generateKey();
        urlShortenRepository.save(key, originUrl);
        return key;
    }

    private String generateKey() {
        Random random = new Random();
        return String.valueOf(random.nextInt(10000));
    }

}
