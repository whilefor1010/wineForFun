CREATE TABLE IF NOT EXISTS wine
(
    id BIGSERIAL PRIMARY KEY ,
    title  text,
    anons text,
    full_text text,
	alcohol INT,
	year INT
);

ALTER TABLE wine ADD COLUMN IF NOT EXISTS year INT DEFAULT 0;