package community.whatever.onembackendjava.controller;

import community.whatever.onembackendjava.service.UrlShortenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url-shorten")
@AllArgsConstructor
public class UrlShortenController {

    private final UrlShortenService urlShortenService;

    @GetMapping("/{urlKey}")
    public String getShortUrl(@PathVariable("urlKey") String urlKey) {
        return urlShortenService.getShortUrl(urlKey);
    }

    @PostMapping
    public String createShortUrl(@RequestBody String originUrl) {
        return urlShortenService.createShortUrl(originUrl);
    }
}
