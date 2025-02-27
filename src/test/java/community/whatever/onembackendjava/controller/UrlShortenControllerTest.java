package community.whatever.onembackendjava.controller;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import community.whatever.onembackendjava.dto.req.ShortenedURLCreateRequest;
import community.whatever.onembackendjava.service.ShortenURLService;

@WebMvcTest(controllers = {UrlShortenController.class})
class UrlShortenControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockitoBean
	private ShortenURLService shortenURLService;

	@DisplayName("단축 URL 생성 성공")
	@Test
	void test0() throws Exception {
		//given
		ShortenedURLCreateRequest req = new ShortenedURLCreateRequest("https://www.google.com");

		//when then
		mockMvc.perform(post("/shorten-url")
				.content(objectMapper.writeValueAsString(req))
				.contentType(APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isOk());
	}

	@DisplayName("단축 URL 생성 시, 주어지는 원본 URL은 유효한 형식이여야 한다.")
	@Test
	void test1() throws Exception {
		//given
		ShortenedURLCreateRequest req = new ShortenedURLCreateRequest("why_today_is.thursday");

		//when then
		mockMvc.perform(post("/shorten-url")
				.content(objectMapper.writeValueAsString(req))
				.contentType(APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isBadRequest());
	}

	@DisplayName("단축 URL 생성 시, 주어지는 원본 URL은 필수이다")
	@Test
	void test3() throws Exception {
		//given
		//when then
		mockMvc.perform(post("/shorten-url")
				.content(objectMapper.writeValueAsString(new HashMap<>()))
				.contentType(APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isBadRequest());
	}

}