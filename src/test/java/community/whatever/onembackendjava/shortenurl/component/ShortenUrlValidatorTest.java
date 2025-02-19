package community.whatever.onembackendjava.shortenurl.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.common.exception.custom.ValidationException;
import community.whatever.onembackendjava.shortenurl.properties.ShortenUrlProperties;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ShortenUrlValidatorTest {

    private ShortenUrlValidator shortenUrlValidator;

    @BeforeEach
    void setUp() {
        ShortenUrlProperties shortenUrlProperties = new ShortenUrlProperties();
        shortenUrlProperties.setBlockedDomains(Set.of("www.example.com"));

        shortenUrlValidator = new ShortenUrlValidator(shortenUrlProperties);
    }

    @ParameterizedTest
    @CsvSource({
        "''",
        "'https ://google.com'",
        "'://google.com'",
        "'htp://example.com'"
    })
    void origin_url이_잘못된_형식일_경우_예외가_발생한다(String url) {
        assertThatThrownBy(() -> shortenUrlValidator.validate(url))
            .isInstanceOf(ValidationException.class)
            .hasMessage(ErrorCode.INVALID_URL_FORMAT.getMessage());
    }

    @Test
    void 차단된_url일_경우_예외가_발생한다() {
        String url = "https://www.example.com";

        assertThatThrownBy(() -> shortenUrlValidator.validate(url))
            .isInstanceOf(ValidationException.class)
            .hasMessage(ErrorCode.BLOCKED_URL.getMessage());
    }

}
