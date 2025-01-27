package community.whatever.onembackendjava.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlShortenController {


    private  final UrlShortenService urlShortenService ;

    @Autowired
    private UrlShortenController(UrlShortenService urlShortenService){
        this.urlShortenService = urlShortenService ;
    }

    @PostMapping("/shorten-url/search")
    public String shortenUrlSearch(@RequestBody RequestVO requestVO ) {
        String result ;
        result = urlShortenService.searchUrl(requestVO.getKey()) ;

        return result ;
    }


    @PostMapping("/shorten-url/create")
    public String shortenUrlCreate(@RequestBody RequestVO requestVO ) {
        String result ;
        result = urlShortenService.createKey(requestVO.getUrl()) ;
        return result;


    }

}
