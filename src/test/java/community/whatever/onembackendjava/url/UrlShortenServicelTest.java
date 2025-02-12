package community.whatever.onembackendjava.url;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class UrlShortenServicelTest {

    private static UrlShortenRepository urlShortenRepository ;
    private static UrlShortenService urlShortenService ;
    private UrlBlockDomainProperties urlBlockDomainProperties ;
    private static String tmpString ;
    @BeforeAll
    public static void setUp(){
        urlShortenService = new UrlShortenService(new UrlShortenRepository(new UrlBlockDomainProperties()) ) ;
    }

    @Test
    @DisplayName("키 생성")
    void keyCreate() {
        String createKey = urlShortenService.createKey("https://www.naver.com");
        assertThat(createKey).isNotEmpty() ;
    }

}