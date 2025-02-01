package community.whatever.onembackendjava.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UrlShortenRepository {

    private final Map<String, String> shortenUrls = new HashMap<>();

    public Optional<String> findByShortenUrlKey(String shortenUrlKey) {
        return Optional.ofNullable(shortenUrls.get(shortenUrlKey));
    }

    public void save(String shortenUrlKey, String originUrl) {
        shortenUrls.put(shortenUrlKey, originUrl);
    }
}
