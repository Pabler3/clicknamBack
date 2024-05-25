package com.clicknam.api.controller;

import com.clicknam.api.exceptions.newException;
import com.clicknam.api.model.ReservaModel;
import com.clicknam.api.service.EmailService;
import com.clicknam.api.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final EmailService emailService;

    @PostMapping() //Endpoint para crear una reserva y enviar un email con los datos
    public ResponseEntity<ReservaModel> createReserva(@RequestBody ReservaModel reservaModel){
        ReservaModel reserva = reservaService.createReserva(reservaModel);
        if(null != reserva){
            emailService.sendDatosReserva(reserva);
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

    @GetMapping("/usuario/{id}") // Endpoint para traer reservas de un usuario por id
    public ResponseEntity<List<ReservaModel>> findReservaByIdUsuario(@PathVariable Long id){
        try{
            List<ReservaModel> reservaModelList = reservaService.findReservaByUserId(id);
            if(reservaModelList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(reservaModelList, HttpStatus.OK);
        }catch (newException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{id}") // Borrar reservas por id
    public ResponseEntity<ReservaModel> deleteById(@PathVariable Long id){
        int borrado = reservaService.deleteReserva(id);
        if(borrado > 0){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
