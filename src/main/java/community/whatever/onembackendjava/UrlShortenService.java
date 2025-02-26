package community.whatever.onembackendjava;

import community.whatever.onembackendjava.dto.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UrlShortenService {
    private final Map<String, String> shortenUrls = new ConcurrentHashMap<>();
    
    public SearchShortenUrlResponse searchShortenUrl(SearchShortenUrlRequest request) {
        String url = shortenUrls.get(request.key());
        if (url == null) {
            throw new IllegalArgumentException("Invalid key");
        }
        return new SearchShortenUrlResponse(url);
    }

    public CreateShortenUrlResponse createShortenUrl(CreateShortenUrlRequest request) {
        String randomKey;
        String originUrl = request.originUrl();
        
        do {
            randomKey = generateRandomKey();
        } while (shortenUrls.putIfAbsent(randomKey, originUrl) != null);
        
        return new CreateShortenUrlResponse(randomKey);
    }

    private String generateRandomKey() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(10000));
    }

    public HashMapResponse getAllShortenUrls() {
        return new HashMapResponse(new HashMap<>(shortenUrls));
    }

    public String addToShortenUrls(PostShortenUrlsRequest request) {
        if (request.shortenUrls() != null) {
            request.shortenUrls().forEach(shortenUrls::putIfAbsent);
        }
        return "Success";
    }
}