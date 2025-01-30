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

    @GetMapping("/search/{urlKey}")
    public ResponseEntity<String> getShortUrl(@PathVariable("urlKey") String urlKey) {
        String originalUrl = urlShortenService.getShortUrl(urlKey);
        return ResponseEntity.ok(originalUrl);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createShortUrl(@RequestBody String originUrl) {
        String shortenUrl = urlShortenService.createShortUrl(originUrl);
        return ResponseEntity.ok(shortenUrl);
    }
}
