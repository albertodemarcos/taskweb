package es.taskweb.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.taskweb.app.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
