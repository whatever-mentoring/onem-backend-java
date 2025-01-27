package community.whatever.onembackendjava.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class ShortUrlService {

    private final Map<String, String> urlMappings = new HashMap<>();

    public String createShortUrl(String originalUrl) {
        String shortKey = String.valueOf(new Random().nextInt(10000));
        urlMappings.put(shortKey, originalUrl);
        return shortKey;
    }

    public String getOriginalUrl(String shortKey) {
        if (!urlMappings.containsKey(shortKey)) {
            throw new IllegalArgumentException("Invalid key");
        }
        return urlMappings.get(shortKey);
    }
}
