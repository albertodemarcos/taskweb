package es.taskweb.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.taskweb.app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	 Optional<User> findByUsername(String username);
}
