package community.whatever.onembackendjava.urlshorten;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UrlShortenRepository {

    private final Map<String, String> shortenUrls = new HashMap<>();

    public Optional<String> findByKey(String key) {
        return Optional.ofNullable(shortenUrls.get(key));
    }

    public void save(String key, String originUrl) {
        shortenUrls.put(key, originUrl);
    }
}
