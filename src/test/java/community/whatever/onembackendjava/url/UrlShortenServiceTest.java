package community.whatever.onembackendjava.url;

import community.whatever.onembackendjava.common.ShortenUrlKeyGenerator;
import community.whatever.onembackendjava.exception.ExpiredException;
import community.whatever.onembackendjava.expiration.ExpirationChecker;
import community.whatever.onembackendjava.expiration.FakeExpirationChecker;
import community.whatever.onembackendjava.expiration.DefaultExpirationChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UrlShortenServiceTest {

    @Autowired
    private UrlShortenRepository urlShortenRepository;

    @Autowired
    private UrlShortenProperties urlShortenProperties;

    @Autowired
    private ShortenUrlKeyGenerator shortenUrlKeyGenerator;

    private UrlShortenService urlShortenService;
    private UrlShortenService urlShortenService2;

    String  setUpKey ;

    @BeforeEach
    void setUp(){
        ExpirationChecker allwaysTrueExpirationChecker  = new FakeExpirationChecker(true);
        ExpirationChecker expirationCheker = new DefaultExpirationChecker();

        urlShortenService = new UrlShortenService(urlShortenRepository, urlShortenProperties, shortenUrlKeyGenerator, expirationCheker);
        urlShortenService2 = new UrlShortenService(urlShortenRepository, urlShortenProperties, shortenUrlKeyGenerator,  allwaysTrueExpirationChecker );

        setUpKey = urlShortenService.createKey("https://github.com");
    }

    @Test
    @DisplayName("키 생성")
    void keyCreate() {
        String createKey = urlShortenService.createKey("https://www.naver.com");
        assertThat(createKey).isNotEmpty() ;
    }

    @Test
    @DisplayName("조회")
    void getUrlByKey() {
        assertThat( urlShortenService.getUrlByKey(setUpKey)).isEqualTo("https://github.com") ;
    }

    @Test
    @DisplayName("차단 도메인")
    void getBlocked() {
        assertThrows( IllegalArgumentException.class,
                ()->urlShortenService.createKey("https://papago.naver.com") );

    }
    @Test
    @DisplayName(" 만료 테스트 ")
    void getExpiration() {
        assertThrows( ExpiredException.class,
                ()-> urlShortenService2.getUrlByKey(setUpKey) );

    }

}