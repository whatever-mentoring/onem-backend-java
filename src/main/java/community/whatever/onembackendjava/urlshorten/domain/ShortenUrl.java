package community.whatever.onembackendjava.urlshorten.domain;

import java.time.LocalDateTime;

public record ShortenUrl(
    String originUrl,
    String shortenUrlKey,
    LocalDateTime expiredAt
) {
}
