package finki.ukim.mk.backendproject.repository;

import finki.ukim.mk.backendproject.models.Account;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
