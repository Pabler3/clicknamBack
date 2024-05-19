package com.clicknam.api.controller;

import com.clicknam.api.model.MesaModel;
import com.clicknam.api.model.RestauranteModel;
import com.clicknam.api.service.MesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping("/mesas")
public class MesaController {
    private final MesaService mesaService;

    @PostMapping() // Endpoint para crear una mesa
    public ResponseEntity<MesaModel> createMesa(@RequestBody  MesaModel mesaModel){
        MesaModel mesa = mesaService.createMesa(mesaModel);
        return new ResponseEntity<>(mesa, HttpStatus.OK);
    }

    @GetMapping("/restaurante/{id}") // Endpoint para traer una lista de mesas por la id de de restaurante
    public ResponseEntity<List<MesaModel>> findMesaListByRestauranteId(@PathVariable long id){
        List<MesaModel> mesaModelList = mesaService.findMesaByRestauranteId(id);
        return new ResponseEntity<>(mesaModelList,HttpStatus.OK);
    }
}
