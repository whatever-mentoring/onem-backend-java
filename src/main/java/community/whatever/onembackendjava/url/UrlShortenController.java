package community.whatever.onembackendjava.url;

import org.springframework.web.bind.annotation.*;


@RestController
public class UrlShortenController {

    private  final UrlShortenService urlShortenService ;

    private UrlShortenController(UrlShortenService urlShortenService){
        this.urlShortenService = urlShortenService ;
    }
    @GetMapping("/shortenUrl/{key}")
    public String getUrlByKey(@PathVariable String key) {
        return urlShortenService.getUrlByKey(key);
    }

    @PostMapping("/shortenUrl/create")
    public String shortenUrlCreate(@RequestBody requestVO request) {
        return urlShortenService.createKey(request.getUrl());
    }
}
