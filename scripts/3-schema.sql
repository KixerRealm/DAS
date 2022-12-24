create table if not exists accounts
(
    id        varchar(255) PRIMARY KEY,
    image_url varchar(255)
);

create table if not exists games
(
    id           SERIAL PRIMARY KEY,
    account_id   varchar(255) NOT NULL,
    game_type    varchar(20)  NOT NULL,
    started_at   timestamp default current_timestamp,
    ended_at     timestamp,
    total_points integer      NOT NULL default 0,
    FOREIGN KEY (account_id) references accounts (id)
);
