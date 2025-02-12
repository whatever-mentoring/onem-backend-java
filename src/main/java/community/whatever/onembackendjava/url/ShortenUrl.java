package community.whatever.onembackendjava.url;

import java.time.LocalDateTime;
import java.util.Date;

public record ShortenUrl(String urlKey, String originUrl , LocalDateTime regDate) {
}
