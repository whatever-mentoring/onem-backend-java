package community.whatever.onembackendjava.controller;

import community.whatever.onembackendjava.service.UrlShorteningService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UrlShortenController {

    private final UrlShorteningService urlShorteningService;

    @PostMapping("/shorten-url/search")
    public String shortenUrlSearch(@RequestBody String key) {
        return urlShorteningService.searchOriginUri(key);
    }

    @PostMapping("/shorten-url/create")
    public String shortenUrlCreate(@RequestBody String originUrl) {
        return urlShorteningService.createShortenUri(originUrl);
    }
}