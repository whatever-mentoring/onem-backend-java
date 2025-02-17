package community.whatever.onembackendjava.shortenurl.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import community.whatever.onembackendjava.MysqlTestContainer;
import community.whatever.onembackendjava.common.exception.ErrorCode;
import community.whatever.onembackendjava.shortenurl.dto.ShortenUrlRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@AutoConfigureMockMvc
class ShortenUrlIntegrationTest extends MysqlTestContainer {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shorten_url을_생성한다() throws Exception {
        String originUrl = "https://www.google.com";
        ShortenUrlRequest request = new ShortenUrlRequest(originUrl);

        ResultActions resultActions =
            mockMvc.perform(post("/shorten-url")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());
    }

    @ParameterizedTest
    @CsvSource({
        "''",
        "'https ://google.com'",
        "'://google.com'",
        "'htp://example.com'"
    })
    void origin_url이_잘못된_형식일_경우_예외가_발생한다(String originUrl) throws Exception {
        ShortenUrlRequest request = new ShortenUrlRequest(originUrl);

        ResultActions resultActions = mockMvc.perform(post("/shorten-url")
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON));

        resultActions
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(ErrorCode.INVALID_URL_FORMAT.name()))
            .andExpect(jsonPath("$.message").value(ErrorCode.INVALID_URL_FORMAT.getMessage()));
    }

    @Test
    void origin_url이_차단된_도메인일_경우_예외가_발생한다() throws Exception {
        String originUrl = "https://www.example.com";
        ShortenUrlRequest request = new ShortenUrlRequest(originUrl);

        ResultActions resultActions = mockMvc.perform(post("/shorten-url")
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON));

        resultActions
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(ErrorCode.BLOCKED_URL.name()))
            .andExpect(jsonPath("$.message").value(ErrorCode.BLOCKED_URL.getMessage()));
    }

    @Test
    void origin_url을_조회한다() throws Exception {
        String originUrl = "https://www.google.com";
        String shortenUrlKey = "dev-abcdefg";

        ResultActions resultActions = mockMvc.perform(get("/shorten-url/{shortenUrlKey}", shortenUrlKey));

        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.originUrl").value(originUrl));
    }

    @Test
    void origin_url이_존재하지_않을_경우_예외가_발생한다() throws Exception {
        String shortenUrlKey = "dev-";

        ResultActions resultActions = mockMvc.perform(get("/shorten-url/{shortenUrlKey}", shortenUrlKey));

        resultActions
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value(ErrorCode.NOT_FOUND_SHORTEN_URL.name()))
            .andExpect(jsonPath("$.message").value(ErrorCode.NOT_FOUND_SHORTEN_URL.getMessage()));
    }

    @Test
    void origin_url이_만료된_경우_예외가_발생한다() throws Exception {
        String shortenUrlKey = "dev-expired";

        ResultActions resultActions = mockMvc.perform(get("/shorten-url/{shortenUrlKey}", shortenUrlKey));

        resultActions
            .andExpect(status().isGone())
            .andExpect(jsonPath("$.code").value(ErrorCode.EXPIRED_SHORTEN_URL.name()))
            .andExpect(jsonPath("$.message").value(ErrorCode.EXPIRED_SHORTEN_URL.getMessage()));
    }

}
