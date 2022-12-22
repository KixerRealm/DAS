create table if not exists places(
    place_id           PRIMARY KEY varchar(2500),
    lat                float,
    lng                float,
    name               varchar(2500),
    photo_reference    varchar(2500),
    place_type         varchar(2500)
    );

create table if not exists games(
    game_id         PRIMARY KEY varchar(2500),
    player_id       varchar(2500),
    place_type      varchar(2500),
    started_at      date,
    ended_at        date,
    score           int
);

create table if not exists users(
    player_id       PRIMARY KEY varchar(2500),
    username        varchar(2500),
    password        varchar(2500),
    email           varchar(2500),
    image_url       varchar(2500),
    leaderboard_id  varchar(2500),
    CONSTRAINT FK_record
        FOREIGN KEY (leaderboard_id) REFERENCES leaderboard_records(leaderboard_id)
);

create table if not exists leaderboard_records(
    leaderboard_id   PRIMARY KEY varchar(2500),
    player_id        varchar(2500),
    game_id          varchar(2500),
    CONSTRAINT FK_user
        FOREIGN KEY (player_id) REFERENCES users (player_id)
);

create table if not exists guess(
    guess_id        PRIMARY KEY varchar(2500),
    guess_place     varchar(2500),
    image_url       varchar(2500)
);