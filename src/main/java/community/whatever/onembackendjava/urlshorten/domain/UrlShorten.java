package community.whatever.onembackendjava.urlshorten.domain;

import java.time.LocalDateTime;

public class UrlShorten {

    private String originUrl;
    private String shortenUrlKey;
    private LocalDateTime expiredAt;

    public UrlShorten(String originUrl, String shortenUrlKey, LocalDateTime expiredAt) {
        this.originUrl = originUrl;
        this.shortenUrlKey = shortenUrlKey;
        this.expiredAt = expiredAt;
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
}
