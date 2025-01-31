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

    @GetMapping("/shorten-url/{shortenUrlKey}")
    public String getOriginUrlByShortenUrlKey(@PathVariable String shortenUrlKey) {
        return urlShortenService.getOriginUrlByShortenUrlKey(shortenUrlKey);
    }

    @PostMapping("/shorten-url")
    public String createShortenUrl(@RequestBody String originUrl) {
        return urlShortenService.createShortenUrl(originUrl);
    }
}
