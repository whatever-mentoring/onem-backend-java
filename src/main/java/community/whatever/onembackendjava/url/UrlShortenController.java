package community.whatever.onembackendjava.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlShortenController {


    private  UrlShortenService urlShortenService ;

    @Autowired
    private UrlShortenController(UrlShortenService urlShortenService){
        this.urlShortenService = urlShortenService ;
    }

    @PostMapping("/shorten-url/search")
    public String shortenUrlSearch(@RequestBody RequestVO requestVO ) {
        String result ;
        if(!urlShortenService.existKey(requestVO.getKey())){
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

    // return시 DTO를 쓸지 말지 다음 스탭에서 고민
    @GetMapping("/shorten-url/delete")
    public String shortenUrlDelete(@RequestParam String key){
        if (urlShortenService.existKey(key)){
            urlShortenService.deleteKey(key) ;
            return "삭제되었습니다.";
        }
        return "삭제되지않았습니다." ;
    }



}
