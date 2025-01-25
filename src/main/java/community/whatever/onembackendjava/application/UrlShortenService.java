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

    public String getOriginUrlByKey(String key) {
        return urlShortenRepository.findByKey(key)
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 key입니다."));
    }

    public String createShortenUrl(String originUrl) {
        String key = generateKey();
        urlShortenRepository.save(key, originUrl);
        return key;
    }

    private String generateKey() {
        return String.valueOf(RANDOM.nextInt(10000));
    }

}
