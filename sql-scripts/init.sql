CREATE TABLE shorten_url (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             origin_url VARCHAR(255) NOT NULL,
                             shorten_url_key VARCHAR(255) NOT NULL UNIQUE,
                             expired_at DATETIME NOT NULL
);

DELIMITER $$

DROP PROCEDURE IF EXISTS loopInsert$$

CREATE PROCEDURE loopInsert()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE batch_count INT DEFAULT 1000;  -- 한 번에 삽입할 데이터 개수
    DECLARE batch_values TEXT DEFAULT '';  -- 배치에 넣을 값들을 저장할 변수

    -- 루프를 돌면서 데이터를 삽입
    WHILE i <= 100000 DO
            -- 랜덤한 8자리 문자열 생성 (영문 + 숫자)
            SET @random_domain = SUBSTRING(CONCAT(MD5(RAND()), MD5(RAND())), 1, 8);

            -- 랜덤한 origin_url 생성 (예: https://www.abcd1234.com)
            SET @origin_url = CONCAT('https://www.', @random_domain, '.com');

            -- shorten_url_key 형식 맞춰서 10자리 랜덤 문자열 생성
            SET @shorten_url_key = CONCAT('dev-',
                                          SUBSTRING(CONCAT(MD5(RAND()), MD5(RAND())), 1, 10));

            -- 만료 날짜를 현재 시간 기준 -12시간 ~ +12시간 사이에서 랜덤 설정
            SET @expired_at = NOW() + INTERVAL (FLOOR(RAND() * 24) - 12) HOUR;

            -- 배치 값 추가 (중복되는 값은 INSERT IGNORE로 무시)
            SET batch_values = CONCAT(batch_values,
                                      '(', QUOTE(@origin_url), ',',
                                      QUOTE(@shorten_url_key), ',',
                                      QUOTE(@expired_at), '),');

            -- 배치 크기 제한 (각 배치의 크기를 4KB로 제한)
            IF LENGTH(batch_values) > 4000 THEN
                -- 마지막 쉼표 제거하고 삽입
                SET batch_values = LEFT(batch_values, LENGTH(batch_values) - 1);

                -- 배치로 데이터 삽입
                SET @insert_query = CONCAT('INSERT IGNORE INTO shorten_url (origin_url, shorten_url_key, expired_at) VALUES ', batch_values);
PREPARE stmt FROM @insert_query;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- 배치 값 초기화
SET batch_values = '';
END IF;

            -- 카운터 증가
            SET i = i + 1;
END WHILE;

    -- 남은 데이터 삽입 (배치가 끝나지 않은 경우)
    IF LENGTH(batch_values) > 0 THEN
        SET batch_values = LEFT(batch_values, LENGTH(batch_values) - 1);
        SET @insert_query = CONCAT('INSERT IGNORE INTO shorten_url (origin_url, shorten_url_key, expired_at) VALUES ', batch_values);
PREPARE stmt FROM @insert_query;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
END IF;
END$$

DELIMITER ;

CALL loopInsert();


INSERT INTO shorten_url (origin_url, shorten_url_key, expired_at)
VALUES ('https://www.14k1h2ak.com', 'dev-ah31k1g2h1k', DATE_ADD(NOW(), INTERVAL 1 DAY));
