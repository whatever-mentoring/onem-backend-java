package community.whatever.onembackendjava.dto.req;

import jakarta.validation.constraints.NotBlank;

public record ShortenedURLCreateRequest(
	@NotBlank(message = "빈 값일 수 없습니다") //todo
	String originURL

) {
}
