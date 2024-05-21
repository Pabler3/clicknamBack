package com.clicknam.api.dao;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reserva")
public class ReservaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="dia")
    private Integer dia;
    @Column(name="mes")
    private Integer mes;
    @Column(name="anio")
    private Integer ano;
    @Column(name="hora_inicio")
    private String horaInicio;
    @Column(name="hora_fin")
    private String horaFin;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="usuario_id")
    private UsuarioEntity usuario;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="mesa_id")
    private MesaEntity mesa;
}

