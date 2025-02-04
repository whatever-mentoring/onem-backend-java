package community.whatever.onembackendjava.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class BlacklistService {

    @Value("${blacklist.path}")
    private String blacklistFilePath;

    private Set<String> blacklistDomains;

    @PostConstruct  // 서비스 시작시 초기 로드
    public void init() {
        loadBlacklist();
    }

    public void loadBlacklist(){
        File file = new File(blacklistFilePath);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            blacklistDomains = objectMapper.readValue(file, Set.class);
            log.info("블랙리스트 로드 완료. 항목 수: {}", blacklistDomains.size());
        } catch (IOException e) {
            log.error("블랙리스트 파일 읽기 실패: {}", e.getMessage());
            blacklistDomains = new HashSet<>();
        }
    }

    public boolean isBlocked(String domain){
        return blacklistDomains.contains(domain);
    }

}
