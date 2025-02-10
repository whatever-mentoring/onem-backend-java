package community.whatever.onembackendjava.urlshorten.repository;

import community.whatever.onembackendjava.urlshorten.domain.ShortenUrl;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class ShortenUrlRepository {

    private final Map<String, ShortenUrl> shortenUrls = new HashMap<>();

    public void save(ShortenUrl shortenUrl) {
        shortenUrls.put(shortenUrl.shortenUrlKey(), shortenUrl);
    }

    public Optional<ShortenUrl> findByShortenUrlKey(String shortenUrlKey) {
        return Optional.ofNullable(shortenUrls.get(shortenUrlKey));
    }

}
