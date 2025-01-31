package community.whatever.onembackendjava.url;

import org.springframework.stereotype.Service;

@Service
public class UrlShortenService {

    private  UrlShortenRepository urlShortenRepository ;

    public UrlShortenService(UrlShortenRepository urlShortenRepository ) {
        this.urlShortenRepository = urlShortenRepository;
    }


    public String urlSearch(String key) {
        if(urlShortenRepository.existKey(key)){
            return  urlShortenRepository.searchUrl(key) ;
        }else{
            throw new IllegalArgumentException("Invalid key");
        }
    }



    public String keyCreate(String url) {
        return urlShortenRepository.keyCreate(url);
    }
}
