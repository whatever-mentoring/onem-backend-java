package community.whatever.onembackendjava.exception.res;

import java.util.Map;

public record ValidationExceptionResponse(
	Map<String, String> validation
	
) {
}
