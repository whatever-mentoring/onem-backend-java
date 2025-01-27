package community.whatever.onembackendjava.url;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UrlShortenRepositoryTest {

    UrlShortenRepository urlShortenRepository ;

    @BeforeEach
    public void setUp(){
        urlShortenRepository = new UrlShortenRepository() ;
        // 테스트용
        urlShortenRepository.testInsertValue("1234" , "https://docs.oracle.com");
        urlShortenRepository.createKey("https://www.daum.net");
    }


    @Test
    @DisplayName("키 생성")
    public void createKey(){
        String createKey = urlShortenRepository.createKey("https://www.naver.com");
        //System.out.println("createKey = " + createKey);
        Assertions.assertThat(createKey).isNotEmpty() ;
    }


}
