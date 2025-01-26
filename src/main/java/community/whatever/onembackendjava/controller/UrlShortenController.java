package community.whatever.onembackendjava.controller;

import community.whatever.onembackendjava.service.UrlShorteningService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UrlShortenController {

    private final UrlShorteningService urlShorteningService;

    @PostMapping("/shorten-url/search")
    public String shortenUrlSearch(@RequestParam String key) {
        return urlShorteningService.searchOriginUri(key);
    }

    @PostMapping("/shorten-url/create")
    public String shortenUrlCreate(@RequestParam String originUrl) {
        return urlShorteningService.createShortenUri(originUrl);
    }
}
