CREATE TABLE shorten_url (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             origin_url VARCHAR(255) NOT NULL,
                             shorten_url_key VARCHAR(255) NOT NULL UNIQUE,
                             expired_at DATETIME NOT NULL
);

INSERT INTO shorten_url (origin_url, shorten_url_key, expired_at)
VALUES ('https://www.google.com', 'dev-NzdzaHhtbjlNWnU', '2025-12-31 23:59:59');

INSERT INTO shorten_url (origin_url, shorten_url_key, expired_at)
VALUES ('https://www.google.com', 'dev-NzdzaHhtbjlNWnz', '2025-01-01 00:00:00');
