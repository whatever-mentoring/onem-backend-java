package community.whatever.onembackendjava.controller;

import community.whatever.onembackendjava.service.UrlShortenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url-shorten")
@AllArgsConstructor
public class UrlShortenController {

    private final UrlShortenService urlShortenService;

}