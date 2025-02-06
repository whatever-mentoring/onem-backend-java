package community.whatever.onembackendjava.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class ShortUrlService {

    private final Map<String, String> urlMappings = new HashMap<>();
    private final Random random = new Random();

    @Value("#{'${shortener.blocked-domains}'.split(',')}")
    private List<String> blockedDomains;

    public String createShortUrl(String originalUrl) {

        if (isBlockedDomain(originalUrl)) {
            throw new IllegalArgumentException("Short URL 제공이 거부된 도메인입니다.");
        }
        String shortKey = String.valueOf(random.nextInt(10000));
        urlMappings.put(shortKey, originalUrl);
        return shortKey;
    }

    public String getOriginalUrl(String shortKey) {
        if (!urlMappings.containsKey(shortKey)) {
            throw new IllegalArgumentException("Invalid key");
        }
        return urlMappings.get(shortKey);
    }

    private boolean isBlockedDomain(String originalUrl) {
        try {
            URL url = new URL(originalUrl);
            String host = url.getHost().toLowerCase();
            for (String blocked : blockedDomains) {

                if (host.equals(blocked.trim().toLowerCase()) || host.endsWith("." + blocked.trim().toLowerCase())) {
                    return true;
                }
            }
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid URL format", e);
        }
        return false;
    }
}
