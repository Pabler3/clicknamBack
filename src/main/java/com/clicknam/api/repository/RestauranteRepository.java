package com.clicknam.api.repository;

import com.clicknam.api.dao.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {

    // Metodo para buscar restaurante por id de usuario
    @Query("SELECT r FROM RestauranteEntity r WHERE r.administrador.id = :id")
    Optional<List<RestauranteEntity>> findByUserId(long id);

    // Método para comprobar no existe el email en la base de datos, excluyendo el mio
    @Query("SELECT COUNT(u) = 0 FROM UsuarioEntity u WHERE u.email = :email AND u.id != :userId")
    boolean isEmailUniqueExceptMine(String email, Long userId);


    @Query("SELECT r FROM RestauranteEntity r " +
            "INNER JOIN r.mesas m " +
            "LEFT JOIN ReservaEntity res ON res.mesa = m " +
            "WHERE LOWER(r.poblacion) = LOWER(:ciudad)" +
            "AND m.capacidad >= :capacidad " +
            "AND (res IS NULL OR NOT (res.dia = :dia " +
            "AND res.mes = :mes " +
            "AND res.ano = :ano " +
            "AND :hora BETWEEN res.horaInicio AND res.horaFin))")
    List<RestauranteEntity> findBySearch(String ciudad,Integer capacidad, Integer dia, Integer mes, Integer ano, String hora);

}
