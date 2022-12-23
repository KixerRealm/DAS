create table if not exists players (
    player_id           varchar(255) PRIMARY KEY,
    username            varchar(255) NOT NULL,
    password            varchar(255) NOT NULL,
    email               varchar(255) NOT NULL,
    image_url           varchar(255)
);

create table if not exists games(
    game_id             varchar(255) PRIMARY KEY,
    player_id           varchar(255) NOT NULL,
    type                varchar(255) NOT NULL,
    started_at          timestamp NOT NULL,
    ended_at            timestamp NOT NULL,
    score               integer NOT NULL,
    FOREIGN KEY (player_id) references player(player_id)
);

create table if not exists leaderboard_records (
    leaderboard_record_id           varchar(255) PRIMARY KEY,
    player_id                       varchar(255) NOT NULL,
    time_started                    timestamp NOT NULL,
    time_completed                  timestamp NOT NULL,
    profile_picture_url             varchar(255),
    total_score                     integer NOT NULL,
    FOREIGN KEY (player_id) REFERENCES player(player_id)
);

create table if not exists guess(
    guess_id        varchar(255) PRIMARY KEY,
    guess_place     varchar(255),
    image_url       varchar(255)
);