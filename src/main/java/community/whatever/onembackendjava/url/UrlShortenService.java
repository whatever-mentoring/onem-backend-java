package community.whatever.onembackendjava.url;

public interface UrlShortenService {

     Boolean existKey(String key) ;

     Boolean existUrl(String url) ;

     String searchKey(String url) ;

     String createKey(String url) ;

     String searchUrl(String key) ;

     int deleteKey(String key) ;
}
