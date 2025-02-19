package community.whatever.onembackendjava.expiration;

import java.time.LocalDateTime;

public interface ExpirationChecker {
    boolean isExpired(LocalDateTime expirationTime);
    boolean isNotExpired(LocalDateTime expirationTime);
}
