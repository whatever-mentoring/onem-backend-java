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

    // O(N)이라서 키 같을때 어떻게 처리 할지 방법 고민할 예정
    ShortenUrl existUrl(String url) {
        for (ShortenUrl shortenUrl : shortenUrlMap.values()) {
            if (shortenUrl.originUrl().equals(url)) {
                return shortenUrl ;
            }
        }
        return null ;
    }

    public void saveKey(ShortenUrl shortenUrl){
        shortenUrlMap.put(shortenUrl.urlKey(), shortenUrl) ;
    }

    public ShortenUrl getShotenUrl(String key){
        return shortenUrlMap.get(key) ;
    }

}
