package community.whatever.onembackendjava.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ShortenUrlRepository {

    private final Map<String, String> storage = new ConcurrentHashMap<>();

    public String read(String id) throws IllegalArgumentException {
        if (!storage.containsKey(id)) {
            throw new IllegalArgumentException("Invalid key");
        }

        return storage.get(id);
    }

    public String store(String originUrl) {
        String id = String.valueOf(new Random().nextInt(10000));
        storage.put(id, originUrl);
        return id;
    }

}
