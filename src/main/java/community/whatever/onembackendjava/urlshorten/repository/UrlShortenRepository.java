package community.whatever.onembackendjava.urlshorten.repository;

import community.whatever.onembackendjava.urlshorten.domain.UrlShorten;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UrlShortenRepository {

    private final Map<String, UrlShorten> shortenUrls = new HashMap<>();

    public void save(UrlShorten urlShorten) {
        shortenUrls.put(urlShorten.getShortenUrlKey(), urlShorten);
    }

    public Optional<UrlShorten> findByShortenUrlKey(String shortenUrlKey) {
        return Optional.ofNullable(shortenUrls.get(shortenUrlKey));
    }

    public void deleteByExpiredAtBefore(LocalDateTime now) {
        shortenUrls.values().removeIf(urlShorten -> urlShorten.getExpiredAt().isBefore(now));
    }
}
