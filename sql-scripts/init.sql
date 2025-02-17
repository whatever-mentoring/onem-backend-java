CREATE TABLE shorten_url (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             origin_url VARCHAR(255) NOT NULL,
                             shorten_url_key VARCHAR(255) NOT NULL UNIQUE,
                             expired_at DATETIME NOT NULL
);

DROP FUNCTION IF EXISTS random_string;

DELIMITER $$

CREATE FUNCTION random_string(length INT) RETURNS VARCHAR(255)
    DETERMINISTIC
BEGIN
    DECLARE chars VARCHAR(100) DEFAULT 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    DECLARE random_str VARCHAR(255) DEFAULT '';
    DECLARE i INT DEFAULT 0;
    DECLARE rand_index INT;

    WHILE i < length DO
            SET rand_index = FLOOR(1 + (RAND() * LENGTH(chars)));
            SET random_str = CONCAT(random_str, SUBSTRING(chars, rand_index, 1));
            SET i = i + 1;
        END WHILE;

    RETURN random_str;
END$$

DELIMITER ;

INSERT INTO shorten_url (origin_url, shorten_url_key, expired_at)
SELECT
    CONCAT('https://example.com/', random_string(10)) AS originUrl,
    CONCAT('dev-', random_string(12)) AS shortenUrlKey,
    DATE_ADD(NOW(), INTERVAL (FLOOR(RAND() * 336) - 168) HOUR) AS expiredAt
FROM
    (SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1
     UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1) AS temp1,
    (SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1
     UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1) AS temp2,
    (SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1
     UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1) AS temp3,
    (SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1
     UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1) AS temp4,
    (SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1
     UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1 UNION ALL SELECT 1) AS temp5;

INSERT INTO shorten_url (origin_url, shorten_url_key, expired_at)
VALUES ('https://example.com/test', 'dev-abcdefg', DATE_ADD(NOW(), INTERVAL 1 DAY));
