package community.whatever.onembackendjava.dto.req;

import community.whatever.onembackendjava.controller.customValidator.ValidURL;

public record ShortenedURLCreateRequest(
	@ValidURL
	String originURL

) {
}
