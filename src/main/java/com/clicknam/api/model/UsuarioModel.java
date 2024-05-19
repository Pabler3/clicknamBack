package com.clicknam.api.model;

import com.clicknam.api.dao.ReservaEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UsuarioModel implements Serializable {
    private Long id;
    private String email;
    private String password;
    private String nombre;
    private String apellido;
    private String telefono;
    private String rol;
    private List<ReservaEntity> reserva;
}
