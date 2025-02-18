package community.whatever.onembackendjava.shortenurl.dto;

import community.whatever.onembackendjava.shortenurl.entity.ShortenUrl;
import java.time.format.DateTimeFormatter;

public record ShortenUrlResponse (
    String originUrl,
    String shortenUrlKey,
    String expiredAt

){
    public static ShortenUrlResponse from(ShortenUrl shortenUrl) {
        return new ShortenUrlResponse(
            shortenUrl.getOriginUrl(),
            shortenUrl.getShortenUrlKey(),
            shortenUrl.getExpiredAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"))
        );
    }
}
