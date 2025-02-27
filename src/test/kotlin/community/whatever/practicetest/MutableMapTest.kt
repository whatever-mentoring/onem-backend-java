package community.whatever.practicetest

import org.junit.jupiter.api.Test

class MutableMapTest {

    // JVM Heap size를 제한하고 테스트를 실행하여야 한다.
    // 예시:
    // -Xmx128m (최대 힙 크기 128MB)
    // -XX:MaxRAMPercentage=75 (최대 힙 크기를 사용 가능한 물리 메모리의 75%로 제한)
    @Test
    fun `MutableMap의 최대 크기를 확인한다`() {
        // given
        val mutableMap = mutableMapOf<String, String>()
        var count = 0

        // when
        try {
            while (true) {
                mutableMap["key$count"] = "value$count"
                count++
            }
        } catch (e: OutOfMemoryError) {
            println("Out of memory at size: $count")
        }
    }

}
