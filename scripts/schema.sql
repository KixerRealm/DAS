create table if not exists games(
    game_id             varchar(255) PRIMARY KEY,
    user_id             varchar(255) NOT NULL,
    type                varchar(255) NOT NULL,
    started_at          timestamp NOT NULL,
    ended_at            timestamp NOT NULL,
    score               integer NOT NULL
);

create table if not exists player (
    player_id           varchar(255) PRIMARY KEY,
    username            varchar(255) NOT NULL,
    password            varchar(255) NOT NULL,
    email               varchar(255) NOT NULL,
    image_url           varchar(255)
);

create table if not exists leaderboard_records (
    id                  varchar(255) PRIMARY KEY,
    user_id             varchar(255) NOT NULL,
    game_id             varchar(255) NOT NULL
);

create table if not exists player_leaderboard_records (
    player_id               varchar(255) NOT NULL,
    leaderboard_record_id   varchar(255) NOT NULL,
    PRIMARY KEY (player_id, leaderboard_record_id),
    FOREIGN KEY (player_id) REFERENCES player(player_id),
    FOREIGN KEY (leaderboard_record_id) REFERENCES leaderboard_records(id)
);

create table if not exists guess(
    guess_id        varchar(255) PRIMARY KEY,
    guess_place     varchar(255),
    image_url       varchar(255)
);