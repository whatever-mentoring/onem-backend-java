package community.whatever.onembackendjava.url;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class UrlShortenRepository {

    private final Map<String, String> shortenUrlMap = new HashMap<>();

    boolean existUrl(String url){
        return shortenUrlMap.containsValue(url) ;
    }
    boolean existKey(String key){
        return shortenUrlMap.containsKey(key) ;
    }

    //  앞으로 고민 사항 일단 Map 커지면 개선이 필요함 어떻게 key와 value값을 효율적으로 관리할것인지 고민해보기
    public String searchKey(String url){
        String resultKey = "" ;
        for (Map.Entry<String, String> entry : shortenUrlMap.entrySet()){
            if (entry.getValue().equals(url)){
                resultKey = entry.getKey() ;
            }
        }
        return resultKey ;
    }
    public String searchUrl(String key){

        return shortenUrlMap.get(key) ;
    }



    public String createKey(String url){
        String uniqueKey = generateKey();

        while (shortenUrlMap.containsKey(uniqueKey)){
            uniqueKey = generateKey();
        }
        shortenUrlMap.put(uniqueKey, url) ;

        return uniqueKey ;
    }

    public int deleteKey(String key){
        if(shortenUrlMap.remove(key) != null){
            return 1 ;
        }else {
            return -1 ;
        }
    }

    // test용
    public void testInsertValue(String key , String Url){
        shortenUrlMap.put(key , Url ) ;
    }
    // key값이 겹칠 수 있으므로 추후에 다른 Key생성 방법 생각하기
    private static String generateKey(){
        return  String.valueOf(new Random().nextInt(10000));
    }

}
