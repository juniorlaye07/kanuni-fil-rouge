package com.kanuniprojet.kanuni.repository;

import com.kanuniprojet.kanuni.modele.ComptBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ComptBancaireRepository extends JpaRepository<ComptBancaire, Long> {

    ComptBancaire findComptBancaireByNumCompte(String numCompt);

}
