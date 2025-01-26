package community.whatever.onembackendjava.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UrlShortenService {

    private final Map<String, String> shortenUrls = new HashMap<>();

    public String createShortenUrl(String originUrl) {
        String randomKey = String.valueOf(new Random().nextInt(10000));
        shortenUrls.put(randomKey, originUrl);
        return randomKey;
    }

    public String getOriginUrl(String key) {
        if (!shortenUrls.containsKey(key)) {
            throw new IllegalArgumentException("Invalid key");
        }
        return shortenUrls.get(key);
    }
}
