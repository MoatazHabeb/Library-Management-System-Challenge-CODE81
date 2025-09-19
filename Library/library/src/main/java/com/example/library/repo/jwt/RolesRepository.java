package com.example.library.repo.jwt;

import com.example.library.model.usermodel.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findByCode(String code);
}
