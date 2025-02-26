package community.whatever.onembackendjava;

import community.whatever.onembackendjava.dto.CreateShortenUrlRequest;
import community.whatever.onembackendjava.dto.CreateShortenUrlResponse;
import community.whatever.onembackendjava.dto.SearchShortenUrlRequest;
import community.whatever.onembackendjava.dto.SearchShortenUrlResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class UrlShortenController {

    private final Map<String, String> shortenUrls = new HashMap<>();

    @PostMapping("/shorten-url/search")
    public SearchShortenUrlResponse shortenUrlSearch(@RequestBody SearchShortenUrlRequest searchShortenUrlRequest) {
        if (!shortenUrls.containsKey(searchShortenUrlRequest.key())) {
            throw new IllegalArgumentException("Invalid key");
        }
        return new SearchShortenUrlResponse(shortenUrls.get(searchShortenUrlRequest.key()));
    }

    @PostMapping("/shorten-url/create")
    public CreateShortenUrlResponse shortenUrlCreate(@RequestBody CreateShortenUrlRequest createShortenUrlRequest) {
        String randomKey = String.valueOf(new Random().nextInt(10000));
        shortenUrls.put(randomKey, createShortenUrlRequest.originUrl());
        return new CreateShortenUrlResponse(randomKey);
    }
}
