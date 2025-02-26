package community.whatever.onembackendjava;

import community.whatever.onembackendjava.dto.CreateShortenUrlRequest;
import community.whatever.onembackendjava.dto.CreateShortenUrlResponse;
import community.whatever.onembackendjava.dto.SearchShortenUrlRequest;
import community.whatever.onembackendjava.dto.SearchShortenUrlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UrlShortenController {

    private final UrlShortenService urlShortenService;

    @PostMapping("/shorten-url/search")
    public SearchShortenUrlResponse shortenUrlSearch(@RequestBody SearchShortenUrlRequest request) {
        return urlShortenService.searchShortenUrl(request);
    }

    @PostMapping("/shorten-url/create")
    public CreateShortenUrlResponse shortenUrlCreate(@RequestBody CreateShortenUrlRequest request) {
        return urlShortenService.createShortenUrl(request);
    }
}
