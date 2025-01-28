package community.whatever.onembackendjava.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenServiceImpl implements UrlShortenService{
    private  UrlShortenRepository urlShortenRepository ;


    @Autowired
    public UrlShortenServiceImpl(UrlShortenRepository urlShortenRepository) {
        this.urlShortenRepository = urlShortenRepository ;
    }
    @Override
    public String urlSearch(String key) {
            if(urlShortenRepository.existKey(key)){
                return  urlShortenRepository.searchUrl(key) ;
            }else{
                throw new IllegalArgumentException("Invalid key");
            }
    }

    @Override
    public String keyCreate(String url) {
        return urlShortenRepository.keyCreate(url);
    }

}
