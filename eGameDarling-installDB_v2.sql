CREATE TABLE T_Gamer
(
    p_gamer_id   CHAR(10) NOT NULL,
    f_account_id CHAR(10),
    vname        VARCHAR(50),
    nname        VARCHAR(50)
);

CREATE TABLE T_Hobbys
(
    p_hobby_id CHAR(10) NOT NULL,
    hobbyname  VARCHAR(50)
);

CREATE TABLE T_GamerHobbys
(
    pf_gamer_id CHAR(10) NOT NULL,
    pf_hobby_id CHAR(10) NOT NULL
);

CREATE TABLE T_Accounts
(
    p_account_id CHAR(10) NOT NULL,
    username     VARCHAR(50) UNIQUE,
    password     VARCHAR(255),
    nickname     VARCHAR(50),
    avatar       BLOB
);

CREATE TABLE T_Status
(
    p_status_id  CHAR(10) NOT NULL,
    f_account_id CHAR(10),
    zeitpunkt    TIME,
    text         VARCHAR(100)
);

CREATE TABLE T_Spiele
(
    p_spiel_id CHAR(10) NOT NULL,
    spielname  VARCHAR(50)
);

CREATE TABLE T_Zocken
(
    pf_gamer_id CHAR(10) NOT NULL,
    pf_spiel_id CHAR(10) NOT NULL
);


CREATE TABLE T_Genres
(
    p_genre_id CHAR(10) NOT NULL,
    genrename  VARCHAR(30)
);

CREATE TABLE T_SpieleGenres
(
    pf_spiel_id CHAR(10) NOT NULL,
    pf_genre_id CHAR(10) NOT NULL
);



ALTER TABLE T_Gamer
    ADD CONSTRAINT PRIMARY KEY (p_gamer_id);

ALTER TABLE T_Hobbys
    ADD CONSTRAINT PRIMARY KEY (p_hobby_id);

ALTER TABLE T_Accounts
    ADD CONSTRAINT PRIMARY KEY (p_account_id);

ALTER TABLE T_Status
    ADD CONSTRAINT PRIMARY KEY (p_status_id);

ALTER TABLE T_Genres
    ADD CONSTRAINT PRIMARY KEY (p_genre_id);

ALTER TABLE T_Spiele
    ADD CONSTRAINT PRIMARY KEY (p_spiel_id);

ALTER TABLE T_SpieleGenres
    ADD CONSTRAINT PRIMARY KEY (pf_spiel_id, pf_genre_id);

ALTER TABLE T_Zocken
    ADD CONSTRAINT PRIMARY KEY (pf_gamer_id, pf_spiel_id);

ALTER TABLE T_GamerHobbys
    ADD CONSTRAINT PRIMARY KEY (pf_gamer_id, pf_hobby_id);


ALTER TABLE T_Gamer
    ADD Constraint FOREIGN KEY (f_account_id) REFERENCES T_Accounts (p_account_id);

ALTER TABLE T_Status
    ADD Constraint FOREIGN KEY (f_account_id) REFERENCES T_Accounts (p_account_id);

ALTER TABLE T_GamerHobbys
    ADD CONSTRAINT FOREIGN KEY (pf_gamer_id) REFERENCES T_Gamer (p_gamer_id),
    ADD CONSTRAINT FOREIGN KEY (pf_hobby_id) REFERENCES T_Hobbys (p_hobby_id);

ALTER TABLE T_Zocken
    ADD CONSTRAINT FOREIGN KEY (pf_gamer_id) REFERENCES T_Gamer (p_gamer_id),
    ADD CONSTRAINT FOREIGN KEY (pf_spiel_id) REFERENCES T_Spiele (p_spiel_id);

ALTER TABLE T_SpieleGenres
    ADD CONSTRAINT FOREIGN KEY (pf_spiel_id) REFERENCES T_Spiele (p_spiel_id),
    ADD CONSTRAINT FOREIGN KEY (pf_genre_id) REFERENCES T_Genres (p_genre_id);







