package community.whatever.onembackendjava.shortenurl.component;

import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.common.exception.custom.ValidationException;
import community.whatever.onembackendjava.shortenurl.properties.ShortenUrlProperties;
import org.springframework.stereotype.Component;

@Component
public class ShortenUrlValidator {

    private final ShortenUrlProperties shortenUrlProperties;

    public ShortenUrlValidator(ShortenUrlProperties shortenUrlProperties) {
        this.shortenUrlProperties = shortenUrlProperties;
    }

    public void validate(String url) {
        String domain = UrlParser.extractDomain(url);

        if (shortenUrlProperties.getBlockedDomains().contains(domain)) {
            throw new ValidationException(ErrorCode.BLOCKED_URL);
        }
    }

}
