package community.whatever.onembackendjava.shortenurl.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.common.exception.custom.ValidationException;
import community.whatever.onembackendjava.shortenurl.properties.ShortenUrlProperties;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ShortenUrlValidatorTest {

    @InjectMocks
    private ShortenUrlValidator shortenUrlValidator;

    @Mock
    private ShortenUrlProperties shortenUrlProperties;

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
        String blockedDomain = "www.example.com";

        when(shortenUrlProperties.getBlacklist()).thenReturn(Set.of(blockedDomain));

        assertThatThrownBy(() -> shortenUrlValidator.validate(url))
            .isInstanceOf(ValidationException.class)
            .hasMessage(ErrorCode.BLOCKED_URL.getMessage());
    }

}
