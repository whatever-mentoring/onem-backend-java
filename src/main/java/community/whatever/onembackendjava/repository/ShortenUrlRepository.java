package community.whatever.onembackendjava.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ShortenUrlRepository {

    /*
        메모리를 저장소로써 활용하기 위해 Map 자료구조를 저장소로 채택. (리팩토링 전의 url 저장 방식을 사용)
        Map의 구현체로 ConcurrentHashMap을 선택함으로써 thread-safe한 자료구조를 선택
        ConcurrentHashMap:
            - 읽기가 쓰기보다 많을 때 가장 적합: 캐시 용도로 적합하다.
            shortenUrl이 1번 생성된 이후 지속적으로 조회 작업 이후 origin-uri로의 리다이렉트와 같이 갱신이나 저장보다도 조회 연산이 많을 것이라 생각합니다.

     */
    private final Map<String, String> storage = new ConcurrentHashMap<>();

    public String read(String id) throws IllegalArgumentException {

        // 저장소에 들어있는 key를 조회하는 로직은 repository 계층에서 수행해서 예외를 반환하는 것이 좋다고 생각합니다.
        // 이후 service 로직에서 예외를 변환해 비즈니스 로직을 처리하는 방향도 생각해볼 수 있습니다.
        if (!storage.containsKey(id)) {
            throw new IllegalArgumentException("Invalid key");
        }

        return storage.get(id);
    }

    public void store(String id, String url) {
        storage.put(id, url);
    }

}
