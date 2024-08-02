package com.example.demo.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class LogPerformanceTest {

    /**
     * 문자열 연결
     * -- 작동방식
     * 1. log.info() 메소드가 호출되기전에 문자열 연산을 처리
     * 2. 로그 레벨 출력 설정이 WARN이상만 설정을 하여도 문자열 연결 작업을 수행
     *
     * -- 성능에 미치는 영향
     * 1. 불필요한 작업: 로그 레벨이 비활성화되어 있어도 문자열 연결 수행
     * 2. 오버헤드: 위와 같은 작업으로 CPU와 메모리 측면에서 불필요한 오버헤드가 발생
     *
     *
     * 매개변수화된 로깅
     * -- 작동방식
     * 1. 자리 표시자 `{}`를 사용하고 로그 레벨이 활성화되어 있는지 확인할 때까지 문자열 작업 연기
     *
     * -- 성능에 미치는 영향
     * 1. 효율성: 로그 레벨이 활성화되지 않은 경우 불필요한 문자열 작업 방지
     */

    @Test
    @DisplayName("로그 성능 비교 테스트")
    void logPerformance() {
        String test = "sample";
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            log.info("log info: " + test);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("문자열 연결: " + (endTime - startTime) + "ms"); // 112ms

        startTime = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            log.info("log info: {}", test);
        }

        endTime = System.currentTimeMillis();
        System.out.println("매개변수화된 로깅: " + (endTime - startTime) + "ms"); // 12ms
    }
}
