
CREATE TABLE IF NOT EXISTS color
(
    id BIGSERIAL PRIMARY KEY ,
    name  text
);


CREATE TABLE IF NOT EXISTS wine
(
    id BIGSERIAL PRIMARY KEY ,
    title  text,
    anons text,
    full_text text,
	alcohol INT,
	year INT,
	idcolor INT REFERENCES color (id)
);
