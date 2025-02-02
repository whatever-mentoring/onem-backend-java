package community.whatever.onembackendjava.url;

import org.springframework.web.bind.annotation.*;


@RestController
public class UrlShortenController {

    private  final UrlShortenService urlShortenService ;

    private UrlShortenController(UrlShortenService urlShortenService){
        this.urlShortenService = urlShortenService ;
    }
    @GetMapping("/shorten-url/{key}")
    public String getUrlByKey(@PathVariable String key) {
        return urlShortenService.getUrlByKey(key);
    }

    @PostMapping("/shorten-url")
    public String shortenUrlCreate(@RequestBody ShortenUrlRequest request) {
        return urlShortenService.createKey(request.originUrl());
    }
}
