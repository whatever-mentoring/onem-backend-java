package community.whatever.onembackendjava.common;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ProfileProvider {

    private final Environment environment;

    public ProfileProvider(Environment environment) {
        this.environment = environment;
    }

    public Environment getEnvironment() {
        return environment;
    }
    public  String getActiveProfile() {
        String[] activeProfiles = environment.getActiveProfiles();
        return activeProfiles[0] ;
    }
}
