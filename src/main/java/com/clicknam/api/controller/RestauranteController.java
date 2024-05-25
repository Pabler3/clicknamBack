package com.clicknam.api.controller;

import com.clicknam.api.exceptions.newException;
import com.clicknam.api.service.RestauranteService;

import com.clicknam.api.model.RestauranteModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;
    @GetMapping("/busqueda") //Endpoint que retorna una lista con los restaurante segun los filtros
    public ResponseEntity<List<RestauranteModel>> findByBusqueda(
            @RequestParam String ciudad,
            @RequestParam Integer capacidad,
            @RequestParam Integer dia,
            @RequestParam Integer mes,
            @RequestParam Integer ano,
            @RequestParam String hora
    ){
        List<RestauranteModel> listaRestaurantes = restauranteService.getRestaurantesByBusqueda(ciudad,capacidad,dia,mes,ano,hora);
        if(listaRestaurantes.isEmpty()){
            return new ResponseEntity<>(listaRestaurantes,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listaRestaurantes, HttpStatus.OK);
    }
    @GetMapping("/all") //Endpoint para devolver la lista de restaurantes
    public ResponseEntity<List<RestauranteModel>> findAll() {
        List<RestauranteModel> listaRestaurantes = restauranteService.getRestaurantes();
        if (listaRestaurantes.isEmpty()){
            return new ResponseEntity<>(listaRestaurantes,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaRestaurantes, HttpStatus.OK);
    }

    @PostMapping("/create") //Endpoint para registrar un restaurante
    public ResponseEntity<RestauranteModel> createRestaurante(@RequestBody RestauranteModel restauranteModel, @RequestParam("userId") Long userId) {
        RestauranteModel restaurante = restauranteService.createRestaurante(restauranteModel,userId);
        if(restaurante!=null){
            return new ResponseEntity<>(restaurante,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(restaurante,HttpStatus.CONFLICT);
    }
    @PutMapping("/update") //Endpoint para editar restaurante
    public ResponseEntity<RestauranteModel> updateRestaurant(@RequestBody RestauranteModel restauranteModel){
        RestauranteModel restaurante = restauranteService.updateRestaurante(restauranteModel);
        if(restaurante!=null){
            return new ResponseEntity<>(restaurante,HttpStatus.OK);
        }
        return new ResponseEntity<>(restaurante,HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/{id}") //Endpoint para traer restaurantes por ID
    public ResponseEntity<RestauranteModel> findRestauranteById(@PathVariable Long id) {
        try {
            RestauranteModel restaurante = restauranteService.getRestauranteById(id);
            return new ResponseEntity<>(restaurante, HttpStatus.OK);
        } catch (newException e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/user/{id}") // Endpoint para traer una lista de restaurantes por la id de usuario
    public ResponseEntity<List<RestauranteModel>> findRestaurantListByUserId(@PathVariable long id){
        List<RestauranteModel> restauranteModelList = restauranteService.findRestaurantByUserId(id);
        return new ResponseEntity<>(restauranteModelList,HttpStatus.OK);
    }
}
