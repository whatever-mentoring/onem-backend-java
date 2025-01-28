package community.whatever.onembackendjava;

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
    public String shortenUrlSearch(@RequestBody String key) {
        if (!shortenUrls.containsKey(key)) {
            throw new IllegalArgumentException("Invalid key");
        }
        return shortenUrls.get(key);
    }

    @PostMapping("/shorten-url/create")
    public String shortenUrlCreate(@RequestBody String originUrl) {
        String randomKey = String.valueOf(new Random().nextInt(10000));
        shortenUrls.put(randomKey, originUrl);
        return randomKey;
    }
}
