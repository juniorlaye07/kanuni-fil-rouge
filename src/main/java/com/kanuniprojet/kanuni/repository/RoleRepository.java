package com.kanuniprojet.kanuni.repository;

import com.kanuniprojet.kanuni.modele.Role;
import com.kanuniprojet.kanuni.modele.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
