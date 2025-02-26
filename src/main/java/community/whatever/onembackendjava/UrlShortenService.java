package community.whatever.onembackendjava;

import community.whatever.onembackendjava.dto.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UrlShortenService {
    private final Map<String, String> shortenUrls = new HashMap<>();
    private final Random random = new Random();

    public SearchShortenUrlResponse searchShortenUrl(SearchShortenUrlRequest request) {
        if (!shortenUrls.containsKey(request.key())) {
            throw new IllegalArgumentException("Invalid key");
        }
        return new SearchShortenUrlResponse(shortenUrls.get(request.key()));
    }

    public CreateShortenUrlResponse createShortenUrl(CreateShortenUrlRequest request) {
        String randomKey = String.valueOf(random.nextInt(10000));
        shortenUrls.put(randomKey, request.originUrl());
        return new CreateShortenUrlResponse(randomKey);
    }

    public HashMapResponse getHashMapData() {
        return new HashMapResponse(shortenUrls);
    }
}