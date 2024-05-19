package com.clicknam.api.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Entity
@Data
@Table(name = "mesa")
public class MesaEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "capacidad")
    private Integer capacidad;

    @Column(name = "nombre_mesa")
    private String nombreMesa;

    @Column(name = "info_mesa")
    private String infoMesa;

    @Column(name = "hora_maxima")
    private Integer horaMaxima;

    @ManyToOne
    @JoinColumn(name="restaurante_id")
    private RestauranteEntity restaurante;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mesa")
    private List<ReservaEntity> reserva;

}
