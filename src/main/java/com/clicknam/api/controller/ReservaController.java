package com.clicknam.api.controller;

import com.clicknam.api.model.ReservaModel;
import com.clicknam.api.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping()
    public ResponseEntity<ReservaModel> createReserva(@RequestBody ReservaModel reservaModel){
        ReservaModel reserva = reservaService.createReserva(reservaModel);
        return new ResponseEntity<>(reserva, HttpStatus.OK);
    }
}
