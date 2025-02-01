package community.whatever.onembackendjava.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

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

    @PostMapping("/shorten-url/create")
    public String shortenUrlCreate(@RequestBody String originUrl) {
        return urlShortenService.createKey(originUrl);
    }
}
