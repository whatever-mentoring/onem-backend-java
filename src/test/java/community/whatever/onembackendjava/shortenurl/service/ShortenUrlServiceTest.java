package community.whatever.onembackendjava.shortenurl.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.common.exception.custom.NotFoundException;
import community.whatever.onembackendjava.shortenurl.component.ShortenUrlKeyGenerator;
import community.whatever.onembackendjava.shortenurl.component.ShortenUrlValidator;
import community.whatever.onembackendjava.shortenurl.properties.ShortenUrlProperties;
import community.whatever.onembackendjava.shortenurl.repository.ShortenUrlRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ShortenUrlServiceTest {

    @InjectMocks
    private ShortenUrlService shortenUrlService;

    @Mock
    private ShortenUrlProperties shortenUrlProperties;

    @Mock
    private ShortenUrlValidator shortenUrlValidator;

    @Mock
    private ShortenUrlKeyGenerator shortenUrlKeyGenerator;

    @Mock
    private ShortenUrlRepository shortenUrlRepository;

    @Test
    void origin_url이_존재하지_않을_경우_예외가_발생한다() {
        String shortenUrlKey = "dev-";

        assertThatThrownBy(() -> shortenUrlService.getOriginUrlByShortenUrlKey(shortenUrlKey))
            .isInstanceOf(NotFoundException .class)
            .hasMessage(ErrorCode.NOT_FOUND_SHORTEN_URL.getMessage());

    }


}
