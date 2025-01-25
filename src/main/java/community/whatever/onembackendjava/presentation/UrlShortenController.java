package community.whatever.onembackendjava.presentation;

import community.whatever.onembackendjava.application.UrlShortenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortenController {

    private final UrlShortenService urlShortenService;

    public UrlShortenController(UrlShortenService urlShortenService) {
        this.urlShortenService = urlShortenService;
    }

    @GetMapping("/shorten-url/{key}")
    public String getOriginUrlByKey(@PathVariable String key) {
        return urlShortenService.getOriginUrlByKey(key);
    }

    @PostMapping("/shorten-url")
    public String createShortenUrl(@RequestBody String originUrl) {
        return urlShortenService.createShortenUrl(originUrl);
    }
}
