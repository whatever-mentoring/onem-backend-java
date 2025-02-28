package community.whatever.onembackendjava;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UrlMappingManager {
    private final Map<String, String> shortenUrls = new ConcurrentHashMap<>();

    public String find(String key) {
        return shortenUrls.get(key);
    }

    public boolean putIfAbsent(String key, String url) {
        return shortenUrls.putIfAbsent(key, url) == null;
    }

    public Map<String, String> findAll() {
        return new HashMap<>(shortenUrls);
    }
}
