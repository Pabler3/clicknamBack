package com.clicknam.api.repository;

import com.clicknam.api.dao.MesaEntity;
import com.clicknam.api.dao.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MesaRepository extends JpaRepository<MesaEntity, Long> {

    //Buscar mesas por ID de restaurante
    @Query("SELECT m FROM MesaEntity m WHERE m.restaurante.id = :id AND m.activa = true")

    Optional<List<MesaEntity>> findByRestauranteId(long id);

    //Devuelve la lista de mesas disponibles según filtros de busqueda
    @Query("SELECT m FROM RestauranteEntity r " +
            "JOIN r.mesas m " +
            "LEFT JOIN ReservaEntity res ON res.mesa = m " +
            "WHERE LOWER(r.poblacion) = LOWER(:ciudad) " +
            "AND r.id = :id " +
            "AND m.capacidad >= :capacidad " +
            "AND m.activa = true "+
            "AND (res IS NULL OR NOT (res.dia = :dia " +
            "AND res.mes = :mes " +
            "AND res.ano = :ano " +
            "AND :hora BETWEEN res.horaInicio AND res.horaFin)) " +
            "ORDER BY m.capacidad ASC")
    Optional<List<MesaEntity>> findByBusqueda(String ciudad,Integer capacidad, Integer dia, Integer mes, Integer ano, String hora, Long id);

    @Modifying // modifica el estado de la mesa
    @Query("UPDATE MesaEntity m SET m.activa = false WHERE m.id = :id")
    int borrarMesa(Long id);
}
