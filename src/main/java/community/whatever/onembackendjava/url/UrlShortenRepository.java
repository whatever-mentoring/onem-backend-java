package community.whatever.onembackendjava.url;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Repository
public class UrlShortenRepository {
    private final Map<String, String> shortenUrlMap = new HashMap<>();
    private static final Random random = new Random() ;

    boolean existKey(String key){
        return shortenUrlMap.containsKey(key) ;
    }
    public String keyCreate(String url){
        String uniqueKey = generateKey();
        shortenUrlMap.put(uniqueKey, url) ;
        return uniqueKey ;
    }

    public String searchUrl(String key){
        return shortenUrlMap.get(key) ;
    }

    // test용
    public void testInsertValue(String key , String Url){
        shortenUrlMap.put(key , Url ) ;
    }

    private static String generateKey(){
        return  String.valueOf(random.nextInt(10000));
    }
}
