package finki.ukim.mk.backendproject.services.impl;

import finki.ukim.mk.backendproject.dtos.AttemptDto;
import finki.ukim.mk.backendproject.dtos.GameDto;
import finki.ukim.mk.backendproject.dtos.LeaderboardRecordDto;
import finki.ukim.mk.backendproject.dtos.UserDto;
import finki.ukim.mk.backendproject.enums.PlaceType;
import finki.ukim.mk.backendproject.mappers.GameMapper;
import finki.ukim.mk.backendproject.models.Account;
import finki.ukim.mk.backendproject.models.Game;
import finki.ukim.mk.backendproject.repository.GameRepository;
import finki.ukim.mk.backendproject.services.GameService;
import finki.ukim.mk.backendproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
	private final GameRepository gameRepository;
	private final UserService userService;

	@Override
	public GameDto startGame(GameDto gameDto, String userId) {
		Game game = new Game();
		Account account = this.userService.getAccountById(userId);
		game.setStartedAt(LocalDateTime.now());
		game.setAccount(account);
		game.setGameType(gameDto.getGameType());
		this.gameRepository.save(game);
		return GameMapper.INSTANCE.toDto(game);
	}

	@Override
	public GameDto submitGame(GameDto game, String userId) {
		Game entity = gameRepository.findById(game.getId()).orElseThrow();
		entity.setTotalPoints(game.getTotalPoints());
		entity.setEndedAt(LocalDateTime.now());
		entity.setEndingPlacement(gameRepository.countByGameTypeAndTotalPointsGreaterThan(entity.getGameType(), game.getTotalPoints()) + 1);
		// TODO: Add async validation of information/guesses
		gameRepository.save(entity);
		return GameMapper.INSTANCE.toDto(entity);
	}

	@Override
	public void cancel(GameDto game, String userId) {
		gameRepository.deleteById(game.getId());
	}

	@Override
	public List<LeaderboardRecordDto> leaderboards(PlaceType placeType) {
		return gameRepository.findAllByGameTypeAndEndedAtIsNotNullOrderByTotalPointsDesc(placeType)
				.stream().limit(15)
				.map(GameMapper.INSTANCE::toDto)
				.map(item -> GameMapper.INSTANCE.toLeaderboardDto(item, userService.getUserById(item.getId())))
				.toList();
	}

	@Override
	public List<AttemptDto> findByUser(String userId) {
		return gameRepository.findAllByAccountIdAndEndedAtIsNotNullOrderByEndedAtDesc(userId)
				.stream().map(GameMapper.INSTANCE::toDto)
				.map(GameMapper.INSTANCE::toAttemptDto)
				.toList();
	}

	@Override
	public List<AttemptDto> findPeakPlacementsByUser(String userId) {
		List<String> peakIds = gameRepository.findPeakPlacementIdsByAccountId(userId);
		return gameRepository.findAllByIdIn(peakIds)
				.stream().map(GameMapper.INSTANCE::toDto)
				.map(GameMapper.INSTANCE::toAttemptDto)
				.toList();
	}

	@Override
	public List<AttemptDto> findLatestPlacementsByUser(String userId) {
		List<String> latestIds = gameRepository.findLatestPlacementIdsByAccountId(userId);
		return gameRepository.findAllByIdIn(latestIds)
				.stream().map(GameMapper.INSTANCE::toDto)
				.map(GameMapper.INSTANCE::toAttemptDto)
				.toList();
	}
}
