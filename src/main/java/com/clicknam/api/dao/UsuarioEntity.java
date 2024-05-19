package com.clicknam.api.dao;

import jakarta.persistence.Table;
import lombok.Data;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import jakarta.persistence.*;


@Entity
@Data
@Table(name = "usuario")
public class UsuarioEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "rol")
    private String rol;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "administrador")
    private List<RestauranteEntity> restaurantes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<ReservaEntity> reserva;
}
