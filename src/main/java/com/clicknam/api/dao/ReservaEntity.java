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
    @Column(name="dia_semana")
    private int diaSemana;
    @Column(name="dia")
    private int dia;
    @Column(name="mes")
    private int mes;
    @Column(name="anio")
    private int ano;
    @Column(name="hora_inicio")
    private String horaInicio;
    @Column(name="hora_fin")
    private String horaFin;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="usuario_id")
    private UsuarioEntity usuario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="mesa_id")
    private MesaEntity mesa;
}

