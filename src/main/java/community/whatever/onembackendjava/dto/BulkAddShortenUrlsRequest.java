package community.whatever.onembackendjava.dto;

import java.util.Map;

public record BulkAddShortenUrlsRequest(Map<String, String> shortenUrls) {
}
