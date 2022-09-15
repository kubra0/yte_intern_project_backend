package yte.intern.spring.application.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.spring.application.authentication.entity.Authority;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAuthority(String authority);

    boolean existsByAuthority(String authority);
}
