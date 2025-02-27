package community.whatever.onembackendkotlin

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

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

    @Test
    fun `유요하지 않은 키로 검색하면 404 에러를 반환한다`() {
        // given
        val invalidKey = "invalid-key"

        // when
        mockMvc.perform(
            post("/shorten-url/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidKey)
        )
            .andExpect(status().isNotFound)
    }

    @Test
    fun `중복된 원본 URL을 등록하면 기존 키를 반환한다`() {
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
            post("/shorten-url/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(originUrl)
        )
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsString

        // then
        Assertions.assertEquals(key, result)
    }

    @Test
    fun `동시에 여러 개의 원본 URL을 등록하더라도 키가 중복되지 않는다`() {
        // given
        val originUrls = (1..100).map { "https://www.google.com/$it" }
        val executor = Executors.newFixedThreadPool(10)

        // when
        val futures = originUrls.map { url ->
            CompletableFuture.supplyAsync({
                synchronized(mockMvc) {
                    mockMvc.perform(
                        post("/shorten-url/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(url)
                    ).andExpect(status().isOk)
                     .andReturn()
                     .response
                     .contentAsString
                }
            }, executor)
        }

        val keys = futures.map { it.get() }

        // then
        Assertions.assertEquals(originUrls.size, keys.distinct().size)
    }
}
