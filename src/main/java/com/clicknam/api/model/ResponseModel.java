package com.clicknam.api.model;

import lombok.Data;

@Data
public class ResponseModel {
    private String data;
    private boolean succes;
    private String mensaje;

    public ResponseModel() {
    }

    public ResponseModel(String data, boolean succes, String mensaje) {
        this.data = data;
        this.succes = succes;
        this.mensaje = mensaje;
    }


}
