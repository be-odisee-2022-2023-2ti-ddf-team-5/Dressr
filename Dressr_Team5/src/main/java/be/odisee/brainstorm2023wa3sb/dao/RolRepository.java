package be.odisee.brainstorm2023wa3sb.dao;

import be.odisee.brainstorm2023wa3sb.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    Rol findByUsernaam(String usernaam);
}
