package community.whatever.onembackendjava.url;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class UrlShortenRepository {
    private final Map<String, ShortenUrl> shortenUrlMap = new HashMap<>();


    boolean existKey(String key){
        return shortenUrlMap.containsKey(key) ;
    }

    public void saveKey(ShortenUrl shortenUrl){
        shortenUrlMap.put(shortenUrl.urlKey(), shortenUrl) ;
    }

    public ShortenUrl getShotenUrl(String key){
        return shortenUrlMap.get(key) ;
    }

}
