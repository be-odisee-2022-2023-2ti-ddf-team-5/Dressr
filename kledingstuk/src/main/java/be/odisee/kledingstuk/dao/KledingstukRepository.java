package be.odisee.kledingstuk.dao;

import be.odisee.kledingstuk.domain.Kledingstuk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KledingstukRepository extends JpaRepository<Kledingstuk, Integer> {

}
