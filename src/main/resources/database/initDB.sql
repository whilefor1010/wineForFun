CREATE TABLE IF NOT EXISTS wine
(
    id BIGSERIAL PRIMARY KEY ,
    title  text,
    anons text,
    full_text text,
	alcohol INT
);