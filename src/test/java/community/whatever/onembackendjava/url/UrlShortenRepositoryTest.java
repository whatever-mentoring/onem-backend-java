package community.whatever.onembackendjava.url;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class UrlShortenRepositoryTest {

    static UrlShortenRepository urlShortenRepository ;

    @BeforeAll
    public static void setUp(){
        urlShortenRepository = new UrlShortenRepository() ;
        // 테스트용
      //  urlShortenRepository.testInsertValue("1234" , "https://docs.oracle.com");
      //  urlShortenRepository.createKey("https://www.daum.net");
    }

    @Test
    @DisplayName("url 검색")
    public void searchUrl(){
        ShortenUrl shortenUrl = urlShortenRepository.getShotenUrl("1234");
        assertThat(shortenUrl.originUrl()).isNotEmpty() ;
    }

}
