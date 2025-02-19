package community.whatever.onembackendjava.shortenurl.component;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ProfileProvider {

    private final Environment environment;

    public ProfileProvider(Environment environment) {
        this.environment = environment;
    }

    public String getActiveProfile() {
        return environment.getProperty("spring.profiles.active", "default");  // 기본값 설정
    }
}
