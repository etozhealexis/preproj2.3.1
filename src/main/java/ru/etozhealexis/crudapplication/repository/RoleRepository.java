package ru.etozhealexis.crudapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.etozhealexis.crudapplication.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
