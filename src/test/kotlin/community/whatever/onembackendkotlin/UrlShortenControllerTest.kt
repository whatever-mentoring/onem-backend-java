package community.whatever.onembackendkotlin

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(UrlShortenController::class)
class UrlShortenControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `원본 URL 등록 시 키를 반환한다`() {
        // given
        val originUrl = "https://www.google.com"

        // when
        val result = mockMvc.perform(
            post("/shorten-url/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(originUrl)
        )
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsString

        // then
        Assertions.assertTrue(result.isNotBlank())
    }

    @Test
    fun `키로 검색하면 원본 URL을 반환한다`() {
        // given
        val originUrl = "https://www.google.com"
        val key = mockMvc.perform(
            post("/shorten-url/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(originUrl)
        )
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsString

        // when
        val result = mockMvc.perform(
            post("/shorten-url/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(key)
        )
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsString

        // then
        Assertions.assertEquals(originUrl, result)
    }
}
