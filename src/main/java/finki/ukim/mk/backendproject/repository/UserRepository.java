package finki.ukim.mk.backendproject.repository;

import finki.ukim.mk.backendproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}