package community.whatever.onembackendjava.controller;

import community.whatever.onembackendjava.service.ShortUrlService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/short-urls")
public class ShortUrlController {

    private final ShortUrlService shortUrlService;

    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping
    public String createShortUrl(@RequestBody String originalUrl) {
        return shortUrlService.createShortUrl(originalUrl);
    }

    @GetMapping("/{shortKey}")
    public String getOriginalUrl(@PathVariable String shortKey) {
        return shortUrlService.getOriginalUrl(shortKey);
    }
}
