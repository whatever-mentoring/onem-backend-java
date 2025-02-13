package community.whatever.onembackendjava.url;

import community.whatever.onembackendjava.exception.ExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
@Slf4j
@Repository
public class UrlShortenRepository {
    private final Map<String, ShortenUrl> shortenUrlMap = new HashMap<>();

    private static final Random random = new Random() ;

    private UrlShortenProperties urlShortenProperties ;

    public UrlShortenRepository(UrlShortenProperties urlShortenProperties){
        this.urlShortenProperties = urlShortenProperties ;
    }

    boolean existKey(String key){
        return shortenUrlMap.containsKey(key) ;
    }
    public String createKey(String url){

        if(isblockedDomains(url)){
            throw new IllegalArgumentException("blocked Domain");
        }else{
            String uniqueKey = generateKey();
            ShortenUrl shortenUrl = new ShortenUrl(uniqueKey,url, LocalDateTime.now())  ;
            shortenUrlMap.put(uniqueKey, shortenUrl) ;
            return uniqueKey ;
        }

    }

    public String getUrl(String key){

        // 현재 시간
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between((shortenUrlMap.get(key)).regDate(), now);

        // 제한 시간보다 , duration이 크면 에러
        if(urlShortenProperties.getExpiredTime() <= duration.toMinutes() ){
            throw new ExpiredException("currentKey expired");
        }
        return (shortenUrlMap.get(key)).urlKey() ;
    }


/*    // test용
    public void testInsertValue(String key , String Url){
        shortenUrlMap.put(key , Url ) ;
    }*/

    public String generateKey(){
        return  String.valueOf(random.nextInt(10000));
    }

    public boolean isblockedDomains(String url){
        List<String> blockedDomains = urlShortenProperties.getBlockedDomains() ;
        return blockedDomains.contains(url) ;
    }

}
