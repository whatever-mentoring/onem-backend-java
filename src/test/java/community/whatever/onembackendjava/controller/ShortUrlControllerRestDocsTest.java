
package community.whatever.onembackendjava.controller;

import community.whatever.onembackendjava.service.ShortUrlService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

@WebMvcTest(ShortUrlController.class)
@AutoConfigureRestDocs
class ShortUrlControllerRestDocsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShortUrlService shortUrlService;

    @Test
    void createShortUrl_Docs() throws Exception {
        when(shortUrlService.createShortUrl(anyString()))
                .thenReturn("1234");

        mockMvc.perform(RestDocumentationRequestBuilders.post("/short-urls")
                        .content("https://www.google.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("1234"))
                .andDo(document("create-short-url"));
    }

    @Test
    void getOriginalUrl_Docs() throws Exception {
        when(shortUrlService.getOriginalUrl("1234"))
                .thenReturn("https://www.google.com");

        mockMvc.perform(RestDocumentationRequestBuilders.get("/short-urls/{shortKey}", "1234"))
                .andExpect(status().isOk())
                .andExpect(content().string("https://www.google.com"))
                .andDo(document("get-original-url"));
    }

    @Test
    void getOriginalUrl_invalidKey_Docs() throws Exception {
        // 모든 문자열에 대해 예외를 던지도록 설정
        when(shortUrlService.getOriginalUrl(Mockito.anyString()))
                .thenThrow(new IllegalArgumentException("Invalid key"));

        mockMvc.perform(RestDocumentationRequestBuilders.get("/short-urls/{shortKey}", "9999"))
                // 404(Not Found) 상태 반환
                .andExpect(status().isNotFound())
                .andDo(document("get-original-url-invalid-key"));
    }

}
