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

    @Override
    public String searchUrl(String key) {
        return urlShortenRepository.searchUrl(key);
    }

    @Override
    public String createKey(String url) {
        return urlShortenRepository.createKey(url);
    }

}
