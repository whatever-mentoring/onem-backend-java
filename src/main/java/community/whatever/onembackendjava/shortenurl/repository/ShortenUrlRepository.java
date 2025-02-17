package community.whatever.onembackendjava.shortenurl.repository;

import community.whatever.onembackendjava.shortenurl.entity.ShortenUrl;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenUrlRepository extends JpaRepository<ShortenUrl, Long> {

    Optional<ShortenUrl> findByShortenUrlKey(String shortenUrlKey);

}
