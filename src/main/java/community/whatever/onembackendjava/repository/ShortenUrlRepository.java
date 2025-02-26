package community.whatever.onembackendjava.repository;

import community.whatever.onembackendjava.entity.ShortenUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortenUrlRepository extends JpaRepository<ShortenUrl, Long> {
    
    Optional<ShortenUrl> findByShortenKey(String shortenKey);
    
    boolean existsByShortenKey(String shortenKey);
}
