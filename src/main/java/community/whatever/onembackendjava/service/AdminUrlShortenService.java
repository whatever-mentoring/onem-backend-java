package community.whatever.onembackendjava.service;

import community.whatever.onembackendjava.UrlMappingManager;
import community.whatever.onembackendjava.dto.ShortenUrlsMapResponse;
import community.whatever.onembackendjava.dto.PostShortenUrlsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUrlShortenService {
    
    private final UrlMappingManager urlMappingManager;
    
    public ShortenUrlsMapResponse getAllShortenUrls() {
        return new ShortenUrlsMapResponse(urlMappingManager.findAll());
    }
    
    public String addToShortenUrls(PostShortenUrlsRequest request) {
        if (request.shortenUrls() != null) {
            request.shortenUrls().forEach((key, url) -> 
                urlMappingManager.putIfAbsent(key, url));
        }
        return "Success";
    }
}
