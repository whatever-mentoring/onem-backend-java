package community.whatever.onembackendjava;

import community.whatever.onembackendjava.dto.ShortenUrlCreateRequest;
import community.whatever.onembackendjava.dto.ShortenUrlCreateResponse;
import community.whatever.onembackendjava.dto.ShortenUrlSearchRequest;
import community.whatever.onembackendjava.dto.ShortenUrlSearchResponse;
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
    public ShortenUrlSearchResponse shortenUrlSearch(@RequestBody ShortenUrlSearchRequest shortenUrlSearchRequest) {
        if (!shortenUrls.containsKey(shortenUrlSearchRequest.key())) {
            throw new IllegalArgumentException("Invalid key");
        }
        return new ShortenUrlSearchResponse(shortenUrls.get(shortenUrlSearchRequest.key()));
    }

    @PostMapping("/shorten-url/create")
    public ShortenUrlCreateResponse shortenUrlCreate(@RequestBody ShortenUrlCreateRequest shortenUrlCreateRequest) {
        String randomKey = String.valueOf(new Random().nextInt(10000));
        shortenUrls.put(randomKey, shortenUrlCreateRequest.originUrl());
        return new ShortenUrlCreateResponse(randomKey);
    }
}
