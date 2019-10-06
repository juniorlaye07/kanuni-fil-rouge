package com.kanuniprojet.kanuni.repository;

import com.kanuniprojet.kanuni.modele.ComptBancaire;
import com.kanuniprojet.kanuni.modele.Partenaire;
import com.kanuniprojet.kanuni.modele.Role;
import com.kanuniprojet.kanuni.modele.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartenaireRepository extends JpaRepository<Partenaire, Long> {
    Partenaire findOneByNinea(String ninea);
    Optional<Partenaire>findById(long id);
}
