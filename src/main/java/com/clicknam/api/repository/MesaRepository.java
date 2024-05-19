package com.clicknam.api.repository;

import com.clicknam.api.dao.MesaEntity;
import com.clicknam.api.dao.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MesaRepository extends JpaRepository<MesaEntity, Long> {

    @Query("SELECT r FROM MesaEntity r WHERE r.restaurante.id = :id")
    Optional<List<MesaEntity>> findByRestauranteId(long id);

}
