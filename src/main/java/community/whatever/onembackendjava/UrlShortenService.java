package community.whatever.onembackendjava;

import community.whatever.onembackendjava.dto.CreateShortenUrlRequest;
import community.whatever.onembackendjava.dto.CreateShortenUrlResponse;
import community.whatever.onembackendjava.dto.SearchShortenUrlRequest;
import community.whatever.onembackendjava.dto.SearchShortenUrlResponse;
import community.whatever.onembackendjava.entity.ShortenUrl;
import community.whatever.onembackendjava.repository.ShortenUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UrlShortenService {
    
    private final ShortenUrlRepository shortenUrlRepository;
    private final Random random = new Random();

    @Transactional(readOnly = true)
    public SearchShortenUrlResponse searchShortenUrl(SearchShortenUrlRequest request) {
        ShortenUrl shortenUrl = shortenUrlRepository.findByShortenKey(request.key())
                .orElseThrow(() -> new IllegalArgumentException("Invalid key"));
        
        shortenUrl.setLastAccessedAt(LocalDateTime.now());
        shortenUrlRepository.save(shortenUrl);
        
        return new SearchShortenUrlResponse(shortenUrl.getOriginalUrl());
    }

    @Transactional
    public CreateShortenUrlResponse createShortenUrl(CreateShortenUrlRequest request) {

        String randomKey;
        do {
            randomKey = String.valueOf(random.nextInt(10000));
        } while (shortenUrlRepository.existsByShortenKey(randomKey));
        
        ShortenUrl shortenUrl = new ShortenUrl(randomKey, request.originUrl());
        shortenUrlRepository.save(shortenUrl);
        
        return new CreateShortenUrlResponse(randomKey);
    }
}
