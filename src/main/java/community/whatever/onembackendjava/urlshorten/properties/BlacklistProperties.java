package community.whatever.onembackendjava.urlshorten.properties;


import java.util.Set;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("blacklist")
public class BlacklistProperties {
    private Set<String> domains;

    public Set<String> getDomains() {
        return domains;
    }

    public void setDomains(Set<String> domains) {
        this.domains = domains;
    }
}
