package community.whatever.onembackendjava.shortenurl.repository;

import community.whatever.onembackendjava.shortenurl.entity.ShortenUrl;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class ShortenUrlRepositoryImpl implements ShortenUrlRepository {

    private final JpaShortenUrlRepository jpaShortenUrlRepository;

    public ShortenUrlRepositoryImpl(JpaShortenUrlRepository jpaShortenUrlRepository) {
        this.jpaShortenUrlRepository = jpaShortenUrlRepository;
    }

    @Override
    public void save(ShortenUrl shortenUrl) {
        jpaShortenUrlRepository.save(shortenUrl);
    }

    @Override
    public Optional<ShortenUrl> findByShortenUrlKey(String shortenUrlKey) {
        return jpaShortenUrlRepository.findByShortenUrlKey(shortenUrlKey);
    }
}
