package community.whatever.onembackendjava.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenServiceImpl implements  UrlShortenService{

    private  UrlShortenRepository urlShortenRepository ;


    @Autowired
    public UrlShortenServiceImpl(UrlShortenRepository urlShortenRepository) {
        this.urlShortenRepository = urlShortenRepository ;
    }

    public Boolean existKey(String key) {

        return urlShortenRepository.existKey(key) ;
    }

    public Boolean existUrl(String url) {
        return urlShortenRepository.existUrl(url) ;
    }

    public String searchKey(String url) {
        return urlShortenRepository.searchKey(url);
    }

    public String createKey(String url) {
        return urlShortenRepository.createKey(url);
    }

    public String searchUrl(String key) {
        return urlShortenRepository.searchUrl(key);
    }
    public int deleteKey(String key) {
        return urlShortenRepository.deleteKey(key);
    }
}
