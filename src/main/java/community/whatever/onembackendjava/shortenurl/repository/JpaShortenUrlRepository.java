package community.whatever.onembackendjava.shortenurl.repository;

import community.whatever.onembackendjava.shortenurl.entity.ShortenUrl;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaShortenUrlRepository extends JpaRepository<ShortenUrl, Long> {
    Optional<ShortenUrl> findByShortenUrlKey(String shortenUrlKey);
}
