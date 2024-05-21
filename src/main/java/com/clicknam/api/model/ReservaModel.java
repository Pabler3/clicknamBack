package com.clicknam.api.model;

import com.clicknam.api.dao.MesaEntity;
import com.clicknam.api.dao.UsuarioEntity;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ReservaModel {
    private Long id;
    private Integer dia;
    private Integer mes;
    private Integer ano;
    private String horaInicio;
    private String horaFin;
    private UsuarioModel usuario;
    private MesaModel mesa;
}
