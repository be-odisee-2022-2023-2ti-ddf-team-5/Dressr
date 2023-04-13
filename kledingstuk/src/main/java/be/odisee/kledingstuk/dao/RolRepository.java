package be.odisee.kledingstuk.dao;


import be.odisee.kledingstuk.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    Rol findByUsernaam(String usernaam);
}
