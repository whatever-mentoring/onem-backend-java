package community.whatever.onembackendjava.url;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "shortener")
public class UrlShortenProperties {
    private List<String> blockedDomains;
    private long expiredTime ;
}

