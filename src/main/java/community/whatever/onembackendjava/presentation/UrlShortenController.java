package community.whatever.onembackendjava.presentation;

import community.whatever.onembackendjava.application.UrlShortenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortenController {

    private final UrlShortenService urlShortenService;

    public UrlShortenController(UrlShortenService urlShortenService) {
        this.urlShortenService = urlShortenService;
    }

    @PostMapping("/shorten-url/search")
    public String shortenUrlSearch(@RequestBody String key) {
        return urlShortenService.shortenUrlSearch(key);
    }

    @PostMapping("/shorten-url/create")
    public String shortenUrlCreate(@RequestBody String originUrl) {
        return urlShortenService.shortenUrlCreate(originUrl);
    }
}
