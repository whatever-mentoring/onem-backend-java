package community.whatever.onembackendjava.urlshorten.component;

import community.whatever.onembackendjava.urlshorten.service.UrlShortenService;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableScheduling
public class UrlShortenScheduler {

    private final UrlShortenService urlShortenService;

    public UrlShortenScheduler(UrlShortenService urlShortenService) {
        this.urlShortenService = urlShortenService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredUrls() {
        long start = System.currentTimeMillis();
        String batchName = "Delete Expired Urls";
        log.info("\n==============================");
        log.info("{} - Start Time : {}", batchName, LocalDateTime.now());

        try {
            urlShortenService.deleteByExpiredAtBefore(LocalDateTime.now());
        } catch (Exception e) {
            log.error("{} - Error occurred: {}", batchName, e.getMessage(), e);
        }
        log.info("{} - Elapsed Time : {}ms", batchName, System.currentTimeMillis() - start);
        log.info("{} - End Time : {}", batchName, LocalDateTime.now());
        log.info("==============================\n");
    }

}
