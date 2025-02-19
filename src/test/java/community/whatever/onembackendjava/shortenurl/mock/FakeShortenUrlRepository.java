package community.whatever.onembackendjava.shortenurl.mock;

import community.whatever.onembackendjava.shortenurl.entity.ShortenUrl;
import community.whatever.onembackendjava.shortenurl.repository.ShortenUrlRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeShortenUrlRepository implements ShortenUrlRepository {
    private final Map<String, ShortenUrl> storage = new HashMap<>();

    @Override
    public Optional<ShortenUrl> findByShortenUrlKey(String shortenUrlKey) {
        return Optional.ofNullable(storage.get(shortenUrlKey));
    }

    @Override
    public void save(ShortenUrl shortenUrl) {
        storage.put(shortenUrl.getShortenUrlKey(), shortenUrl);
    }
}
