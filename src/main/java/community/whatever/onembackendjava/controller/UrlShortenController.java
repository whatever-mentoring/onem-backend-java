package community.whatever.onembackendjava.controller;

import community.whatever.onembackendjava.controller.response.GetOriginUrlResponse;
import community.whatever.onembackendjava.controller.request.ShortenUrlRequest;
import community.whatever.onembackendjava.controller.response.ShortenUrlResponse;
import community.whatever.onembackendjava.service.UrlShortenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortenController {

    private final UrlShortenService urlShortenService;

    public UrlShortenController(UrlShortenService urlShortenService) {
        this.urlShortenService = urlShortenService;
    }

    @PostMapping("/shorten-url")
    public ShortenUrlResponse createShortenUrl(@RequestBody ShortenUrlRequest request) {
        String shortenUrl = urlShortenService.createShortenUrl(request.originUrl());
        return new ShortenUrlResponse(shortenUrl);
    }

    @GetMapping("/shorten-url/{shortenUrlKey}")
    public GetOriginUrlResponse getOriginUrlByShortenUrlKey(@PathVariable String shortenUrlKey) {
        String originUrl = urlShortenService.getOriginUrlByShortenUrlKey(shortenUrlKey);
        return new GetOriginUrlResponse(originUrl);
    }

}
