package com.clicknam.api.model;

import com.clicknam.api.dao.UsuarioEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RestauranteModel implements Serializable {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String poblacion;
    private String tipo_comida;
    private String precio_medio;
    private String descripcion_corta;
    private String foto_portada;
    //private List<MesaModel> mesas;
    private UsuarioModel administrador;


}
