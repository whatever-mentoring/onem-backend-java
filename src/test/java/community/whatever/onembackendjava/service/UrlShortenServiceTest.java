package community.whatever.onembackendjava.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UrlShortenServiceTest {

    @Mock
    private BlacklistService blacklistService;

    @InjectMocks
    private UrlShortenService urlShortenService;

    @Test
    void ShortenUrl_을_생성하고_조회한다(){
        String originUrl = "https://example.com";

        String randomKey = urlShortenService.createShortUrl(originUrl);
        String storedUrl = urlShortenService.getShortUrl(randomKey);

        assertThat(storedUrl).isEqualTo(originUrl);
    }

    @Test
    void 존재하지_않는_UrlKey_로_검색하면_애러가_발생한다(){
        String invalidKey = "1234";

        assertThatThrownBy(() -> urlShortenService.getShortUrl(invalidKey))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid key: " + invalidKey);
    }

    @Test
    void 블랙리스트로_등록된_URL_은_예외가_발생한다(){
        String blockedUrl = "https://blocked.com";
        when(blacklistService.isBlocked(blockedUrl)).thenReturn(true);

        assertThatThrownBy(() -> urlShortenService.createShortUrl(blockedUrl))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Blocked URL: " + blockedUrl);
    }
}
