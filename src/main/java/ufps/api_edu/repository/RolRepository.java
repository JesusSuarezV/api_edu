package ufps.api_edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufps.api_edu.entity.Rol;
@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
