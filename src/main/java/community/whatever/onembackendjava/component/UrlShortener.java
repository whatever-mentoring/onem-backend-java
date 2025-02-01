package community.whatever.onembackendjava.component;

import java.util.Random;
import org.springframework.stereotype.Component;

public class UrlShortener {

    private static final Random RANDOM = new Random();

    public static String shorten() {
        return String.valueOf(RANDOM.nextInt(10000));
    }

}
