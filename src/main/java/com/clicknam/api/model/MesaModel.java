package com.clicknam.api.model;

import com.clicknam.api.dao.ReservaEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MesaModel implements Serializable {
    private Long id;
    private Integer capacidad;
    private String nombreMesa;
    private String infoMesa;
    private Integer horaMaxima;
    private RestauranteModel restaurante;
    private Boolean activa;
    //private List<ReservaModel> reserva;

}
