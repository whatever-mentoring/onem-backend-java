package community.whatever.onembackendjava.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class UrlShortenController {

    private  final UrlShortenService urlShortenService ;

    private UrlShortenController(UrlShortenService urlShortenService){
        this.urlShortenService = urlShortenService ;
    }
    @PostMapping("/shorten-url/search")
    public String shortenUrlSearch(@RequestBody String key) {
        return urlShortenService.urlSearch(key);
    }

    @PostMapping("/shorten-url/create")
    public String shortenUrlCreate(@RequestBody String originUrl) {
        return urlShortenService.keyCreate(originUrl);
    }
}
