package com.clicknam.api.repository;

import com.clicknam.api.dao.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {

    //Buscar reserva que tiene un restaurante
    @Query("SELECT r FROM ReservaEntity r WHERE r.mesa.restaurante.id = :id")
    Optional<List<ReservaEntity>> findByRestauranteId(Long id);

}

