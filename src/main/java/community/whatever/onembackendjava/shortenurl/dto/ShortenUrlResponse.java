package community.whatever.onembackendjava.shortenurl.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import community.whatever.onembackendjava.shortenurl.entity.ShortenUrl;
import java.time.LocalDateTime;

public record ShortenUrlResponse(
    String originUrl,
    String shortenUrlKey,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss")
    LocalDateTime expiredAt
) {
    public static ShortenUrlResponse from(ShortenUrl shortenUrl) {
        return new ShortenUrlResponse(
            shortenUrl.getOriginUrl(),
            shortenUrl.getShortenUrlKey(),
            shortenUrl.getExpiredAt()
        );
    }
}
