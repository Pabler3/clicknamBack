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
    @PutMapping() // Endpoint para actualizar una mesa
    public ResponseEntity<MesaModel> updateMesa(@RequestBody  MesaModel mesaModel){
        MesaModel mesa = mesaService.updateMesa(mesaModel);
        if(null != mesa){
            return new ResponseEntity<>(mesa, HttpStatus.OK);
        }
        return new ResponseEntity<>(mesa,HttpStatus.CONFLICT);
    }

    @GetMapping("/restaurante/{id}") // Endpoint para traer una lista de mesas por la id de de restaurante
    public ResponseEntity<List<MesaModel>> findMesaListByRestauranteId(@PathVariable long id){
        List<MesaModel> mesaModelList = mesaService.findMesaByRestauranteId(id);
        return new ResponseEntity<>(mesaModelList,HttpStatus.OK);
    }
    @GetMapping("/mesaDisponible")
    public ResponseEntity<MesaModel> findPrimeraMesaDisponible(
            @RequestParam String ciudad,
            @RequestParam Integer capacidad,
            @RequestParam Integer dia,
            @RequestParam Integer mes,
            @RequestParam Integer ano,
            @RequestParam String hora,
            @RequestParam Long id
    ){
        MesaModel mesa = mesaService.findPrimeraMesaDisponible(ciudad, capacidad, dia, mes, ano, hora, id);
        if(null != mesa){
            return new ResponseEntity<>(mesa,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    @PutMapping("/mesa") // Endpoint para borrar una mesa
    public ResponseEntity<MesaModel> deleteMesa(@RequestBody MesaModel mesaModel){
        int resultado = mesaService.deleteMese(mesaModel.getId());
        if(resultado > 0){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
