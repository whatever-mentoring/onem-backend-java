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

    public String shortenUrlSearch(String key) {
        return urlShortenRepository.findByKey(key)
            .orElseThrow(() -> new IllegalArgumentException("Invalid key"));
    }

    public String shortenUrlCreate(String originUrl) {
        String randomKey = String.valueOf(new Random().nextInt(10000));
        urlShortenRepository.save(randomKey, originUrl);
        return randomKey;
    }

}
