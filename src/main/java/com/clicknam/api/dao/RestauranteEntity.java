package com.clicknam.api.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "restaurante")
public class RestauranteEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "poblacion")
    private String poblacion;

    @Column(name = "tipo_comida")
    private String tipo_comida;

    @Column(name = "precio_medio")
    private String precio_medio;

    @Column(name = "descripcion_corta")
    private String descripcion_corta;

    @Column(name = "foto_portada", columnDefinition = "mediumtext")
    private String foto_portada;

    @ManyToOne
    @JoinColumn(name="usuario_id")
    private UsuarioEntity administrador;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurante")
    private List<MesaEntity> mesas;


}
