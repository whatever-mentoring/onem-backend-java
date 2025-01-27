package community.whatever.onembackendjava.url;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class UrlShortenRepository {

    private final Map<String, String> shortenUrlMap = new HashMap<>();
    private static final Random random = new Random() ;

    public String searchUrl(String key){
        if (shortenUrlMap.containsKey(key)) {
            return shortenUrlMap.get(key) ;
        }else {
            throw new IllegalArgumentException("Invalid key");
        }

    }

    public String createKey(String url){
        String uniqueKey = generateKey();

        while (shortenUrlMap.containsKey(uniqueKey)){
            uniqueKey = generateKey();
        }
        shortenUrlMap.put(uniqueKey, url) ;

        return uniqueKey ;
    }


    // test용
    public void testInsertValue(String key , String Url){
        shortenUrlMap.put(key , Url ) ;
    }
    // key값이 겹칠 수 있으므로 추후에 다른 Key생성 방법 생각하기
    private static String generateKey(){
        return  String.valueOf(random.nextInt(10000));
    }

}
