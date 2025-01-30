package community.whatever.onembackendjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UrlShortenService {

    //TODO: 추후 DB 연결을 통해 데이터를 관리하도록 변경
    private final Map<String, String> shortenUrls = new HashMap<>();

    public String getShortUrl(String urlKey){
        if (!shortenUrls.containsKey(urlKey)) {
            throw new IllegalArgumentException("Invalid key: " + urlKey);
        }
        return shortenUrls.get(urlKey);
    }

    public String createShortUrl(String originUrl) {
        //TODO: 추후 중복 가능성을 고려하여 UUID 로 변경
        String randomKey = String.valueOf(new Random().nextInt(10000));
        shortenUrls.put(randomKey, originUrl);
        return randomKey;
    }

}
