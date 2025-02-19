package community.whatever.onembackendjava.shortenurl.component;

import com.github.f4b6a3.ulid.Ulid;
import community.whatever.onembackendjava.common.utils.Base62Encoder;
import org.springframework.stereotype.Component;

@Component
public class ShortenUrlKeyGenerator {

    private final ProfileProvider profileProvider;

    public ShortenUrlKeyGenerator(ProfileProvider profileProvider) {
        this.profileProvider = profileProvider;
    }

    public String generate() {
        String profile = profileProvider.getActiveProfile();
        String encodedKey = Base62Encoder.encode(Ulid.fast().getTime());
        return profile + "-" + encodedKey;
    }

}
