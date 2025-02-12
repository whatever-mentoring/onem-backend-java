package community.whatever.onembackendjava.urlshorten.repository;

import community.whatever.onembackendjava.urlshorten.domain.ShortenUrl;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenUrlRepository extends JpaRepository<ShortenUrl, Long> {

    Optional<ShortenUrl> findByShortenUrlKey(String shortenUrlKey);

}
