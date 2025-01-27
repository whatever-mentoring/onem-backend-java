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
        if(!urlShortenService.existKey(requestVO.getKey())){
            // todo exception 처리 따로할 예정
            throw new IllegalArgumentException("Invalid key"); // 변경예정
        }else{
            result = urlShortenService.searchUrl(requestVO.getKey()) ;
        }
        return result ;
    }
    @PostMapping("/shorten-url/create")
    public String shortenUrlCreate(@RequestBody RequestVO requestVO ) {
        String result ;
        if(urlShortenService.existUrl(requestVO.getUrl())){
            result = urlShortenService.searchKey(requestVO.getUrl()) ;
            return result;
        }else{
            result = urlShortenService.createKey(requestVO.getUrl()) ;
            return result;
        }

    }

    @GetMapping("/shorten-url/delete/{key}")
    public String shortenUrlDelete(@PathVariable String key){
    //  delete에 대하여 return 메세지 고민 즁
        if (urlShortenService.existKey(key)){
            urlShortenService.deleteKey(key) ;
            return "true";
        }
        return "false" ;
    }
}
