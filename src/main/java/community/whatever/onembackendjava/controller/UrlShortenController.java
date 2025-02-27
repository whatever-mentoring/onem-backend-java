package community.whatever.onembackendjava.controller;

import community.whatever.onembackendjava.service.UrlShortenService;
import community.whatever.onembackendjava.dto.CreateShortenUrlRequest;
import community.whatever.onembackendjava.dto.CreateShortenUrlResponse;
import community.whatever.onembackendjava.dto.SearchShortenUrlRequest;
import community.whatever.onembackendjava.dto.SearchShortenUrlResponse;
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
