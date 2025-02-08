package community.whatever.onembackendjava.urlshorten.component;

import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.common.exception.custom.ValidationException;
import community.whatever.onembackendjava.urlshorten.properties.UrlShortenProperties;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.stereotype.Component;

@Component
public class UrlShortenValidator {

    private final UrlShortenProperties urlShortenProperties;

    public UrlShortenValidator(UrlShortenProperties urlShortenProperties) {
        this.urlShortenProperties = urlShortenProperties;
    }

    public void validateUrl(String url) {
        String domain = extractDomain(url);

        if (urlShortenProperties.getBlacklist().contains(domain)) {
            throw new ValidationException(ErrorCode.BLOCKED_URL);
        }
    }

    private String extractDomain(String url) {
        try {
            URL parsedUrl = new URL(url);
            return parsedUrl.getHost();
        } catch (MalformedURLException e) {
            throw new ValidationException(ErrorCode.INVALID_URL_FORMAT);
        }
    }
}
