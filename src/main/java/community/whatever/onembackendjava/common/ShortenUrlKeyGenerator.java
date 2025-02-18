package community.whatever.onembackendjava.common;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ShortenUrlKeyGenerator {
    private ProfileUtil profileUtil ;

    public ShortenUrlKeyGenerator(ProfileUtil profileUtil) {
        this.profileUtil = profileUtil;
    }

    private static final Random random = new Random() ;

    public String generateKey(){
        return  String.valueOf(random.nextInt(10000));
    }



}
