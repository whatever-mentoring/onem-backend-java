package community.whatever.onembackendjava.expiration;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

public class FakeExpirationChecker implements ExpirationChecker {
    private final boolean expired;

    public FakeExpirationChecker(boolean expired) {
        this.expired = expired;
    }

    @Override
    public boolean isExpired(LocalDateTime expirationTime) {
        return expired;
    }

    @Override
    public boolean isNotExpired(LocalDateTime expirationTime) {
        return expired;
    }
}
