create table if not exists players (
    player_id           varchar(255) PRIMARY KEY,
    username            varchar(255) NOT NULL,
    password            varchar(255) NOT NULL,
    email               varchar(255) NOT NULL,
    image_url           varchar(255)
);

create table if not exists guesses(
    guess_id        varchar(255) PRIMARY KEY,
    guess_place     varchar(255),
    image_url       varchar(255)
);

create table if not exists games(
    game_id                    SERIAL PRIMARY KEY,
    player_id                  varchar(255) NOT NULL,
    guess_id                   varchar(255) ,
    game_type                  varchar(255) NOT NULL,
    started_at                 timestamp ,
    ended_at                   timestamp ,
    total_points               integer NOT NULL,
    FOREIGN KEY (player_id) references players(player_id),
    FOREIGN KEY (guess_id) REFERENCES guesses(guess_id)
);


create table if not exists leaderboard_records (
    leaderboard_record_id           varchar(255) PRIMARY KEY,
    player_id                       varchar(255) NOT NULL,
    time_started                    timestamp NOT NULL,
    time_completed                  timestamp NOT NULL,
    profile_picture_url             varchar(255),
    total_score                     integer NOT NULL,
    FOREIGN KEY (player_id) REFERENCES players(player_id)
);

