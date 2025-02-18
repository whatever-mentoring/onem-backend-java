package community.whatever.onembackendjava.shortenurl.component;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SnowflakeKeyGenerator {

    private static final String BASE56_CHARACTERS = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";
    private static final int BASE56 = BASE56_CHARACTERS.length();
    private static final long EPOCH = 1609459200000L;
    private static final long SERVER_ID_BITS = 5;
    private static final long SEQUENCE_BITS = 12;
    private static final long MAX_SEQUENCE = (1 << SEQUENCE_BITS) - 1;
    private static final int SERVER_ID = 1;
    private static AtomicLong sequence = new AtomicLong(0);
    private static volatile long lastTimestamp = -1L;

    private final Environment environment;

    public SnowflakeKeyGenerator(Environment environment) {
        this.environment = environment;
    }

    public String generate() {
        String profile = environment.getProperty("spring.profiles.active");
        String snowflakeKey = generateSnowflakeKey();
        return profile + "-" + snowflakeKey;
    }

    private String generateSnowflakeKey() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id");
        }

        if (timestamp == lastTimestamp) {
            long currentSequence = sequence.incrementAndGet() & MAX_SEQUENCE;
            if (currentSequence == 0) {
                timestamp = waitUntilNextMillis(lastTimestamp);
            }
        } else {
            sequence.set(0);
        }

        lastTimestamp = timestamp;

        long id = ((timestamp - EPOCH) << (SERVER_ID_BITS + SEQUENCE_BITS))
            | (SERVER_ID << SEQUENCE_BITS)
            | sequence.get();

        return encodeBase56(id);
    }

    private long waitUntilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    private String encodeBase56(long id) {
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            int index = (int) (id % BASE56);
            sb.append(BASE56_CHARACTERS.charAt(index));
            id /= BASE56;
        }
        while (sb.length() < 8) {
            sb.append(BASE56_CHARACTERS.charAt(0));
        }
        return sb.reverse().toString();
    }
}
