package community.whatever.onembackendjava.url;

import org.springframework.stereotype.Service;

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
        return urlShortenRepository.createKey(url);
    }
}
