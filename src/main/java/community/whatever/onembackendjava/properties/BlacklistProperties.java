package community.whatever.onembackendjava.properties;


import java.util.Set;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("blacklist")
public record BlacklistProperties(
    Set<String> domains
) {

}
