package community.whatever.onembackendjava.common;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;

@Component
public class ShortenUrlKeyGenerator {
    private ProfileProvider profileProvider ;

    public ShortenUrlKeyGenerator(ProfileProvider profileProvider) {
        this.profileProvider = profileProvider;
    }

    private static final Random random = new Random() ;

    public String generateKey(){
        return  profileProvider.getActiveProfile()+"_"+String.valueOf(random.nextInt(10000));
    }



}
