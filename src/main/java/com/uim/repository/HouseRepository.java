package com.uim.repository;

import com.uim.models.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Radhouene Rouached
 */
@Repository("houseRepository")
@EnableJpaRepositories
public interface HouseRepository extends JpaRepository<House, Long> {

    List<House> findByOwnerName(String owername);
}