package community.whatever.onembackendjava.urlshorten.service;

import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.common.exception.custom.ValidationException;
import community.whatever.onembackendjava.urlshorten.properties.BlacklistProperties;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.stereotype.Service;

@Service
public class ValidateUrlShortenService {

    private final BlacklistProperties blacklistProperties;

    public ValidateUrlShortenService(BlacklistProperties blacklistProperties) {
        this.blacklistProperties = blacklistProperties;
    }

    public void validateUrl(String url) {
        String domain = extractDomain(url);

        if (blacklistProperties.domains().contains(domain)) {
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
