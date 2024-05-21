package com.clicknam.api.repository;

import com.clicknam.api.dao.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    // buscar por email
    Optional<UsuarioEntity> findByEmail(String email);

    //comprobar si existe el email
    boolean existsByEmail(String email);

    //verifica si el email es unico excepto el suyo
    @Query("SELECT COUNT(u) = 0 FROM UsuarioEntity u WHERE u.email = :email AND u.id != :userId")
    boolean isEmailUniqueExceptMine(String email, Long userId);
}
