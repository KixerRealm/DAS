package finki.ukim.mk.backendproject.repository;

import finki.ukim.mk.backendproject.models.LeaderboardRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;
import java.util.Optional;

public interface LeaderboardRecordRepository extends JpaRepository<LeaderboardRecord, String> {
}