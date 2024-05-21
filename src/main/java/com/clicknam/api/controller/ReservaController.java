package com.clicknam.api.controller;

import com.clicknam.api.exceptions.newException;
import com.clicknam.api.model.ReservaModel;
import com.clicknam.api.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping() //Endpoint para crear una reserva
    public ResponseEntity<ReservaModel> createReserva(@RequestBody ReservaModel reservaModel){
        ReservaModel reserva = reservaService.createReserva(reservaModel);
        if(null != reserva){
            return new ResponseEntity<>(reserva, HttpStatus.OK);
        }
        return new ResponseEntity<>(reserva,HttpStatus.CONFLICT);
    }

    @GetMapping("/reservaPrevia") // Endpointe para devolver una reserva previa
    public ResponseEntity<ReservaModel> reservaPrevia(
            @RequestParam String ciudad,
            @RequestParam Integer capacidad,
            @RequestParam Integer dia,
            @RequestParam Integer mes,
            @RequestParam Integer ano,
            @RequestParam String hora,
            @RequestParam Long id
    ){
        ReservaModel reservaModel = reservaService.reservaPrevia(ciudad, capacidad, dia, mes, ano, hora, id);
        if (null != reservaModel){
            return new ResponseEntity<>(reservaModel,HttpStatus.OK);
        }
        return new ResponseEntity<>(reservaModel,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/restaurante/{id}") //Endpoint para traer reserva de un restaurante por ID
    public ResponseEntity<List<ReservaModel>> findReservaByIdRestaurante(@PathVariable Long id) {
        try {
            List<ReservaModel> reservaModelList = reservaService.findReservaByIdRestaurante(id);
            return new ResponseEntity<>(reservaModelList, HttpStatus.OK);
        } catch (newException e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }


}
