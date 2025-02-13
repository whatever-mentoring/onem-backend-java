package community.whatever.onembackendjava.shortenurl.component;

import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.common.exception.custom.ValidationException;
import community.whatever.onembackendjava.shortenurl.properties.ShortenUrlProperties;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.stereotype.Component;

@Component
public class ShortenUrlValidator {

    private final ShortenUrlProperties shortenUrlProperties;

    public ShortenUrlValidator(ShortenUrlProperties shortenUrlProperties) {
        this.shortenUrlProperties = shortenUrlProperties;
    }

    public void validate(String url) {
        String domain = extractDomain(url);

        if (shortenUrlProperties.getBlacklist().contains(domain)) {
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
