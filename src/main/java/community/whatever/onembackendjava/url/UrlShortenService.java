package community.whatever.onembackendjava.url;

import community.whatever.onembackendjava.exception.ExpiredException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlShortenService {

    private  UrlShortenRepository urlShortenRepository ;

    public UrlShortenService(UrlShortenRepository urlShortenRepository ) {
        this.urlShortenRepository = urlShortenRepository;
    }


    public String getUrlByKey(String key) {
        if(!urlShortenRepository.existKey(key)){
            throw new IllegalArgumentException("Invalid key");
        }

        ShortenUrl shortenUrl = urlShortenRepository.getShotenUrl(key) ;
        if ( LocalDateTime.now().isAfter(shortenUrl.expirationTime())) {
            throw new ExpiredException("currentKey expired");
        }

        return shortenUrl.originUrl() ;


    }



    public String createKey(String url) {
        return  urlShortenRepository.createKey(url);
    }
}
