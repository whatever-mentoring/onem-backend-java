package community.whatever.onembackendjava.component;

import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.common.exception.custom.ValidationException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlValidator {

    private static final Pattern URL_PATTERN = Pattern.compile("^(?:https?:\\/\\/)?(?:www\\.)?([a-zA-Z0-9./]+)$");
    private static final Set<String> BLOCKED_DOMAINS = new HashSet<>(Arrays.asList("example.com"));

    public static void validateUrl(String url) {
        String domain = extractDomain(url);

        if (BLOCKED_DOMAINS.contains(domain)) {
            throw new ValidationException(ErrorCode.BLOCKED_URL);
        }

    }

    private static String extractDomain(String url) {
        Matcher matcher = URL_PATTERN.matcher(url);

        if (matcher.find()) {
            return matcher.group(1).split("/")[0];
        }

        throw new ValidationException(ErrorCode.INVALID_URL_FORMAT);
    }

}
