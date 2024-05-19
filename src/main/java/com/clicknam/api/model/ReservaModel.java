package com.clicknam.api.model;

import com.clicknam.api.dao.MesaEntity;
import com.clicknam.api.dao.UsuarioEntity;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ReservaModel {
    private Long id;
    private int diaSemana;
    private int dia;
    private int mes;
    private int ano;
    private String horaInicio;
    private String horaFin;
    private UsuarioEntity usuarioEntity;
    private MesaEntity mesaEntity;
}
