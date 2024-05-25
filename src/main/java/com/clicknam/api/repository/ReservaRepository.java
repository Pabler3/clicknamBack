package com.clicknam.api.repository;

import com.clicknam.api.dao.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {

    //Buscar reserva que tiene un restaurante
    @Query("SELECT r FROM ReservaEntity r WHERE r.mesa.restaurante.id = :id")
    Optional<List<ReservaEntity>> findByRestId(Long id);

    //Busca reservas  por ID de usuario
    @Query("SELECT r FROM ReservaEntity r WHERE r.usuario.id = :id AND " +
            "(r.ano > :anoActual OR (r.ano = :anoActual AND r.mes > :mesActual) OR " +
            "(r.ano = :anoActual AND r.mes = :mesActual AND r.dia >= :diaActual)) "+
            "ORDER BY r.ano, r.mes, r.dia, r.horaInicio")
    Optional<List<ReservaEntity>> findByUserId(Long id, Integer anoActual, Integer mesActual, Integer diaActual);

    //Borrar reservas por ID
    @Modifying
    @Query("DELETE FROM ReservaEntity r WHERE r.id = :id")
    int deleteReserva(Long id);

}

