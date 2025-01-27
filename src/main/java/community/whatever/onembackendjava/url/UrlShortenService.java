package community.whatever.onembackendjava.url;

public interface UrlShortenService {


     String createKey(String url) ;

     String searchUrl(String key);
}
