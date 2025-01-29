package community.whatever.onembackendjava.url;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UrlShortenRepositoryTest {

    static UrlShortenRepository urlShortenRepository ;

    @BeforeAll
    public static void setUp(){
        urlShortenRepository = new UrlShortenRepository() ;
        // 테스트용
        urlShortenRepository.testInsertValue("1234" , "https://docs.oracle.com");
        urlShortenRepository.keyCreate("https://www.daum.net");
    }



    @Test
    @DisplayName("키 생성")
    public void createKey(){
        String createKey = urlShortenRepository.keyCreate("https://www.naver.com");
        //System.out.println("createKey = " + createKey);
        Assertions.assertThat(createKey).isNotEmpty() ;
    }

    @Test
    @DisplayName("url 검색")
    public void searchUrl(){
        String result = urlShortenRepository.searchUrl("1234");
        Assertions.assertThat(result).isNotEmpty() ;
    }

}
