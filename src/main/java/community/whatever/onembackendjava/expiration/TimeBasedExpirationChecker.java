package community.whatever.onembackendjava.expiration;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimeBasedExpirationChecker implements ExpirationChecker {

    @Override
    public boolean isExpired(LocalDateTime expirationTime) {
        return LocalDateTime.now().isAfter(expirationTime);
    }
}
