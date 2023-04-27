package be.odisee.brainstorm2023wa3sb.dao;

import be.odisee.brainstorm2023wa3sb.domain.Persoon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersoonRepository extends JpaRepository<Persoon, Integer> {
	Persoon findByEmailadress(String email);
}
