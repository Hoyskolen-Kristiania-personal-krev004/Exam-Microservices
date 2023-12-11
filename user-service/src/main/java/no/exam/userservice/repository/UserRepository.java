package no.exam.userservice.repository;

import no.exam.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(Long userId);
    User findByUsername(String username);
}
