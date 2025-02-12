package community.whatever.onembackendjava.url;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlShortenService {

    private  UrlShortenRepository urlShortenRepository ;

    public UrlShortenService(UrlShortenRepository urlShortenRepository ) {
        this.urlShortenRepository = urlShortenRepository;
    }


    public String getUrlByKey(String key) {
        if(urlShortenRepository.existKey(key)){
            return  urlShortenRepository.getUrl(key) ;
        }else{
            throw new IllegalArgumentException("Invalid key");
        }
    }



    public String createKey(String url) {

        String urlKey = urlShortenRepository.createKey(url);
        return urlKey ;
    }
}
