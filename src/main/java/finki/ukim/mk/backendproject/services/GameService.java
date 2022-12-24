package finki.ukim.mk.backendproject.services;

import finki.ukim.mk.backendproject.dtos.GameDto;
import finki.ukim.mk.backendproject.dtos.LeaderboardRecordDto;
import finki.ukim.mk.backendproject.enums.PlaceType;
import finki.ukim.mk.backendproject.models.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {
    GameDto startGame(GameDto gameDto, String userId);

    GameDto submitGame(GameDto game, String userId);

    void cancel(GameDto game, String userId);

    List<LeaderboardRecordDto> leaderboards(PlaceType placeType);
}
