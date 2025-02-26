package community.whatever.onembackendjava.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "shorten_urls")
@Getter
@NoArgsConstructor
public class ShortenUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String shortenKey;

    @Column(nullable = false, length = 32767)
    private String originalUrl;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Setter
    @Column
    private LocalDateTime lastAccessedAt;

    public ShortenUrl(String shortenKey, String originalUrl) {
        this.shortenKey = shortenKey;
        this.originalUrl = originalUrl;
        this.createdAt = LocalDateTime.now();
    }
}
