package be.odisee.kledingstuk.dao;

import be.odisee.kledingstuk.domain.Persoon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersoonRepository extends JpaRepository<Persoon, Integer> {
    Persoon findByEmailadres(String email);
}