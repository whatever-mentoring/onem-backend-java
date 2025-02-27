package community.whatever.onembackendjava;

import community.whatever.onembackendjava.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UrlShortenController {

    private final UrlShortenService urlShortenService;

    @PostMapping("/shorten-url/search")
    public ResponseEntity<SearchShortenUrlResponse> shortenUrlSearch(@RequestBody SearchShortenUrlRequest request) {
        return ResponseEntity.ok(urlShortenService.searchShortenUrl(request));
    }

    @PostMapping("/shorten-url/create")
    public ResponseEntity<CreateShortenUrlResponse> shortenUrlCreate(@Valid @RequestBody CreateShortenUrlRequest request) {
        return ResponseEntity.ok(urlShortenService.createShortenUrl(request));
    }

    @GetMapping("/admin/shorten-urls")
    public ResponseEntity<HashMapResponse> getAllShortenUrls() {
        return ResponseEntity.ok(urlShortenService.getAllShortenUrls());
    }

    @PostMapping("/admin/shorten-urls")
    public ResponseEntity<String> postShortenUrls(@RequestBody PostShortenUrlsRequest request) {
        return ResponseEntity.ok(urlShortenService.addToShortenUrls(request));
    }
    
    @GetMapping("/{code}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String code) {
        String originalUrl = urlShortenService.getOriginalUrl(code);
        if (originalUrl == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header("Location", originalUrl)
                .build();
    }
}
