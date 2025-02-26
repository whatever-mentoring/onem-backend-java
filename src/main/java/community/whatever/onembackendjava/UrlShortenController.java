package community.whatever.onembackendjava;

import community.whatever.onembackendjava.repository.URLShortenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequiredArgsConstructor
public class UrlShortenController {

    private final URLShortenRepository repository;

    @PostMapping("/shorten-url/search")
    public String shortenUrlSearch(@RequestBody String key) {
        String shortenURL = repository.findByShortenedURL(key)
                .orElseThrow(RuntimeException::new);
        return shortenURL;
    }

    @PostMapping("/shorten-url/create")
    public String shortenUrlCreate(@RequestBody String originUrl) {
        String randomKey = String.valueOf(new Random().nextInt(10000));
        String shortenURL = repository.create(originUrl, randomKey);
        return shortenURL;
    }
}
