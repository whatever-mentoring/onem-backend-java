package community.whatever.onembackendjava.shortenurl.component;

import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.common.exception.custom.ValidationException;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlParser {

    public static String extractDomain(String url) {
        try {
            return new URL(url).getHost();
        } catch (MalformedURLException e) {
            throw new ValidationException(ErrorCode.INVALID_URL_FORMAT);
        }
    }

}
