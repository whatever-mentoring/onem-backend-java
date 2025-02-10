package community.whatever.onembackendjava.urlshorten.component;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ShortenUrlKeyGenerator {

    private static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final Environment environment;

    public ShortenUrlKeyGenerator(Environment environment) {
        this.environment = environment;
    }

    public String generate(long number) {
        String profile = environment.getProperty("spring.profiles.active");
        String generatedKey = encode(number);
        return profile + "-" + generatedKey;
    }

    private String encode(long number) {
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            final long remainder = number % 62;
            sb.insert(0, CHARS.charAt((int) remainder));
            number /= 62;
        }

        while (sb.length() < 7) {
            sb.insert(0, "0");
        }

        return sb.toString();
    }
}
