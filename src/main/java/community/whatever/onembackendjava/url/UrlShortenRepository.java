package community.whatever.onembackendjava.url;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
@Slf4j
@Repository
public class UrlShortenRepository {
    private final Map<String, String> shortenUrlMap = new HashMap<>();

    private static final Random random = new Random() ;

    private UrlBlockDomainProperties urlBlockDomainProperties ;

    public UrlShortenRepository(UrlBlockDomainProperties urlBlockDomainProperties){
        this.urlBlockDomainProperties = urlBlockDomainProperties ;
    }

    boolean existKey(String key){
        return shortenUrlMap.containsKey(key) ;
    }
    public String createKey(String url){

        if(isblockedDomains(url)){
            throw new IllegalArgumentException("blocked Domain");
        }else{
            String uniqueKey = generateKey();
            shortenUrlMap.put(uniqueKey, url) ;
            return uniqueKey ;
        }

    }

    public String getUrl(String key){
        return shortenUrlMap.get(key) ;
    }

    // test용
    public void testInsertValue(String key , String Url){
        shortenUrlMap.put(key , Url ) ;
    }

    private static String generateKey(){
        return  String.valueOf(random.nextInt(10000));
    }

    public boolean isblockedDomains(String url){
        List<String> blockedDomains = urlBlockDomainProperties.getBlockedDomains() ;
        return blockedDomains.contains(url) ;
    }

}
