package community.whatever.onembackendjava.urlshorten.controller;

import community.whatever.onembackendjava.urlshorten.dto.OriginUrlResponse;
import community.whatever.onembackendjava.urlshorten.dto.ShortenUrlRequest;
import community.whatever.onembackendjava.urlshorten.dto.ShortenUrlResponse;
import community.whatever.onembackendjava.urlshorten.service.ShortenUrlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenUrlController {

    private final ShortenUrlService shortenUrlService;

    public ShortenUrlController(ShortenUrlService shortenUrlService) {
        this.shortenUrlService = shortenUrlService;
    }

    @PostMapping("/shorten-url")
    public ShortenUrlResponse createShortenUrl(@RequestBody ShortenUrlRequest request) {
        String shortenUrl = shortenUrlService.createShortenUrl(request.originUrl());
        return new ShortenUrlResponse(shortenUrl);
    }

    @GetMapping("/shorten-url/{shortenUrlKey}")
    public OriginUrlResponse getOriginUrlByShortenUrlKey(@PathVariable String shortenUrlKey) {
        String originUrl = shortenUrlService.getOriginUrlByShortenUrlKey(shortenUrlKey);
        return new OriginUrlResponse(originUrl);
    }

}
