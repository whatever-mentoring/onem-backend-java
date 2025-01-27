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
    @DisplayName("키 존재")
    public void existUrl(){
        boolean result = urlShortenRepository.existUrl("https://www.daum.net");
        Assertions.assertThat(result).isEqualTo(true) ;
    }

    @Test
    @DisplayName("존재하지 않는 url 조회")
    public void existUrlX(){
        boolean result = urlShortenRepository.existUrl("https://www.youtube.com");
        Assertions.assertThat(result).isEqualTo(false) ;
    }

    @Test
    @DisplayName("키 생성")
    public void createKey(){
        String createKey = urlShortenRepository.createKey("https://www.naver.com");
        //System.out.println("createKey = " + createKey);
        Assertions.assertThat(createKey).isNotEmpty() ;
    }

    @Test
    @DisplayName("키 검색")
    public void searchKey(){
        String result = urlShortenRepository.searchKey("https://www.daum.net");
        Assertions.assertThat(result).isNotEmpty() ;
    }

    @Test
    @DisplayName("키 검색 X ")
    public void searchKeyX(){
        String result = urlShortenRepository.searchKey("https://www.daaaaum.net");
        Assertions.assertThat(result).isEmpty() ;
    }

}
