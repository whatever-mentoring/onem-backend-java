package community.whatever.onembackendjava.shortenurl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.Builder;

@Entity
public class ShortenUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String originUrl;

    @Column(nullable = false, unique = true)
    private String shortenUrlKey;

    @Column(nullable = false, unique = false)
    private LocalDateTime expiredAt;

    protected ShortenUrl() {

    }

    @Builder
    public ShortenUrl(String originUrl, String shortenUrlKey, Duration duration) {
        this.originUrl = originUrl;
        this.shortenUrlKey = shortenUrlKey;
        this.expiredAt = LocalDateTime.now().plus(duration);
    }

    public Long getId() {
        return id;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public String getShortenUrlKey() {
        return shortenUrlKey;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public boolean isExpired() {
        return expiredAt.isBefore(LocalDateTime.now());
    }

}
