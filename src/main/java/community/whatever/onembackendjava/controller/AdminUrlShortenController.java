package community.whatever.onembackendjava.controller;

import community.whatever.onembackendjava.service.AdminUrlShortenService;
import community.whatever.onembackendjava.dto.HashMapResponse;
import community.whatever.onembackendjava.dto.PostShortenUrlsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminUrlShortenController {

    private final AdminUrlShortenService adminUrlShortenService;

    @GetMapping("/admin/shorten-urls")
    public ResponseEntity<HashMapResponse> getAllShortenUrls() {
        return ResponseEntity.ok(adminUrlShortenService.getAllShortenUrls());
    }

    @PostMapping("/admin/shorten-urls")
    public ResponseEntity<String> postShortenUrls(@RequestBody PostShortenUrlsRequest request) {
        return ResponseEntity.ok(adminUrlShortenService.addToShortenUrls(request));
    }
}
