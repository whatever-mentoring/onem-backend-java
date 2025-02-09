package community.whatever.onembackendjava.urlshorten.properties;

import java.util.Set;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "urlshorten")
public class UrlShortenProperties {

    private int expirationTime;
    private Set<String> blacklist;

    public int getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(int expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Set<String> getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(Set<String> blacklist) {
        this.blacklist = blacklist;
    }

}
