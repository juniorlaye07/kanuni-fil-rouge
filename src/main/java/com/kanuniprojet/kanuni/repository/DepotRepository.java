package com.kanuniprojet.kanuni.repository;

import com.kanuniprojet.kanuni.modele.ComptBancaire;
import com.kanuniprojet.kanuni.modele.Depot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepotRepository extends JpaRepository<Depot, Long> {
}
