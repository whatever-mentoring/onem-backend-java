package community.whatever.onembackendjava.shortenurl.properties;

import java.time.Duration;
import java.util.Set;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "shorten-url")
public class ShortenUrlProperties {

    private Duration expiredDuration;
    private Set<String> blacklist;

    public Duration getExpiredDuration() {
        return expiredDuration;
    }

    public void setExpiredDuration(Duration expiredDuration) {
        this.expiredDuration = expiredDuration;
    }

    public Set<String> getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(Set<String> blacklist) {
        this.blacklist = blacklist;
    }

}
