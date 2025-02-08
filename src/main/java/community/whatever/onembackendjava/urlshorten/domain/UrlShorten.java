package community.whatever.onembackendjava.urlshorten.domain;

import java.time.LocalDateTime;

public record UrlShorten(
    String originUrl,
    String shortenUrlKey,
    LocalDateTime expiredAt
) {
}
