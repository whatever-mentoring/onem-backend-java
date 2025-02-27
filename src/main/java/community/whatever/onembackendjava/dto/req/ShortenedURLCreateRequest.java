package community.whatever.onembackendjava.dto.req;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

public record ShortenedURLCreateRequest(
	@NotBlank(message = "빈 값일 수 없습니다")
	@URL(message = "올바른 URL 형식이 아닙니다") //todo
	String originURL

) {
}
