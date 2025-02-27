package community.whatever.onembackendjava;

import community.whatever.onembackendjava.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UrlShortenController {

    private final UrlShortenService urlShortenService;

    @PostMapping("/shorten-url/search")
    public SearchShortenUrlResponse shortenUrlSearch(@RequestBody SearchShortenUrlRequest request) {
        return urlShortenService.searchShortenUrl(request);
    }

    @PostMapping("/shorten-url/create")
    public CreateShortenUrlResponse shortenUrlCreate(@Valid @RequestBody CreateShortenUrlRequest request) {
        return urlShortenService.createShortenUrl(request);
    }

    @GetMapping("/admin/shorten-urls")
    public HashMapResponse getAllShortenUrls() {
        return urlShortenService.getAllShortenUrls();
    }

    @PostMapping("/admin/shorten-urls")
    public ResponseEntity<String> postShortenUrls(@RequestBody PostShortenUrlsRequest request) {
        return ResponseEntity.ok(urlShortenService.addToShortenUrls(request));
    }
    
    @GetMapping("/{code}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String code) {
        String originalUrl = urlShortenService.getOriginalUrl(code);
        if (originalUrl != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", originalUrl);
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
