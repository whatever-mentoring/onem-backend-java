package community.whatever.onembackendjava.url;

import community.whatever.onembackendjava.common.ShortenUrlKeyGenerator;
import community.whatever.onembackendjava.exception.ExpiredException;
import community.whatever.onembackendjava.expiration.ExpirationChecker;
import community.whatever.onembackendjava.expiration.DefaultExpirationChecker;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UrlShortenService {

    private  UrlShortenRepository urlShortenRepository ;
    private  UrlShortenProperties urlShortenProperties ;
    private  ShortenUrlKeyGenerator shortenUrlKeyGenerator ;
    private ExpirationChecker expirationCheker;

    public UrlShortenService(UrlShortenRepository urlShortenRepository , UrlShortenProperties urlShortenProperties
            , ShortenUrlKeyGenerator shortenUrlKeyGenerator , ExpirationChecker timeExpirationChecker) {
        this.urlShortenRepository = urlShortenRepository;
        this.shortenUrlKeyGenerator = shortenUrlKeyGenerator ;
        this.urlShortenProperties =  urlShortenProperties ;
        this.expirationCheker = timeExpirationChecker ;

    }

    public String getUrlByKey(String key) {
        if(!urlShortenRepository.existKey(key)){
            throw new IllegalArgumentException("Invalid key");
        }
        ShortenUrl shortenUrl = urlShortenRepository.getShotenUrl(key) ;
        if (expirationCheker.isExpired(shortenUrl.expirationTime())) {
            throw new ExpiredException("currentKey expired");
        }
        return shortenUrl.originUrl() ;
    }

    public String createKey(String url) {

        if (isblockedDomains(url)) {
            throw new IllegalArgumentException("blocked Domain");
        }

        ShortenUrl existingShortenUrl = urlShortenRepository.existUrl(url) ;
        // 존재하고, 만료 안되었으면 존재하던 key 리턴
        if(existingShortenUrl!= null && !expirationCheker.isExpired(existingShortenUrl.expirationTime())) {
                return existingShortenUrl.urlKey() ;
            }

        // 만료시간 세팅
        LocalDateTime settingTime =LocalDateTime.now().plusMinutes(urlShortenProperties.getExpiredMinute());

        String uniqueKey = shortenUrlKeyGenerator.generateKey();
        ShortenUrl shortenUrl = new ShortenUrl(uniqueKey,url, settingTime)  ;
        urlShortenRepository.saveKey(shortenUrl);
        return  uniqueKey;
    }

    public boolean isblockedDomains(String url){
        List<String> blockedDomains = urlShortenProperties.getBlockedDomains() ;
        return blockedDomains.contains(url) ;
    }

}
