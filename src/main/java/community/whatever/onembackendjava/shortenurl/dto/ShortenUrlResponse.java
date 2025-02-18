package community.whatever.onembackendjava.shortenurl.dto;

import community.whatever.onembackendjava.shortenurl.entity.ShortenUrl;
import java.time.LocalDateTime;

public record ShortenUrlResponse (
    String originUrl,
    String shortenUrlKey,
    LocalDateTime expiredAt

){
    public static ShortenUrlResponse from(ShortenUrl shortenUrl) {
        return new ShortenUrlResponse(
            shortenUrl.getOriginUrl(),
            shortenUrl.getShortenUrlKey(),
            shortenUrl.getExpiredAt()
        );
    }
}
