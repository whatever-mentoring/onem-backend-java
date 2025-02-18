package community.whatever.onembackendjava.shortenurl.properties;

import java.time.Duration;
import java.util.Set;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "shorten-url")
public class ShortenUrlProperties {

    private Duration expiredDuration;
    private Set<String> blockedDomain;

    public Duration getExpiredDuration() {
        return expiredDuration;
    }

    public void setExpiredDuration(Duration expiredDuration) {
        this.expiredDuration = expiredDuration;
    }

    public Set<String> getBlockedDomain() {
        return blockedDomain;
    }

    public void setBlockedDomain(Set<String> blockedDomain) {
        this.blockedDomain = blockedDomain;
    }

}
