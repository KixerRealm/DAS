package finki.ukim.mk.backendproject.repository;

import finki.ukim.mk.backendproject.enums.PlaceType;
import finki.ukim.mk.backendproject.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, String> {
	List<Game> findAllByGameTypeAndEndedAtIsNotNullOrderByTotalPointsDesc(PlaceType placeType);

	List<Game> findAllByAccountIdAndEndedAtIsNotNullOrderByEndedAtDesc(String accountId);

	List<Game> findAllByIdIn(List<String> ids);

	Integer countByGameTypeAndTotalPointsGreaterThan(PlaceType gameType, Integer totalPoints);

	@Query(value =
			"select substring(max(concat(ended_at, '+', id)), strpos(max(concat(ended_at, '+', id)), '+') + 1) " +
					"from games " +
					"where account_id = :accountId " +
					"  and ended_at is not null " +
					"group by game_type order by game_type", nativeQuery = true)
	List<String> findLatestPlacementIdsByAccountId(String accountId);

	@Query(value =
			"select substring(min(concat(ending_placement, ended_at, '+', id)), strpos(min(concat(ending_placement, ended_at, '+', id)), '+') + 1) " +
					"from games " +
					"where account_id = :accountId " +
					"  and ended_at is not null " +
					"group by game_type order by game_type", nativeQuery = true)
	List<String> findPeakPlacementIdsByAccountId(String accountId);
}