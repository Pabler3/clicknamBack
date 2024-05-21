package com.clicknam.api.controller;

import com.clicknam.api.model.UsuarioModel;
import com.clicknam.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register") //Endpoint para registrar usuario
    public ResponseEntity<Void>registerUser(@RequestBody UsuarioModel usuario){
        if(usuarioService.registerUser(usuario)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    @PutMapping() //Endpoint para actualizar un usuario
    public ResponseEntity<UsuarioModel> updateUser(@RequestBody UsuarioModel usuario){
        UsuarioModel usuarioModel = usuarioService.updateUser(usuario);
        if(usuarioModel!=null){
            return new ResponseEntity<>(usuarioModel,HttpStatus.OK);
        }
        return new ResponseEntity<>(usuario,HttpStatus.CONFLICT);
    }
    @GetMapping("check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email){
        boolean isAvailable = usuarioService.checkEmailVerify(email);
        return new ResponseEntity<>(isAvailable, HttpStatus.OK);
    }

    @PostMapping("/login") // Endpoint para loguear en la aplicacion
    public ResponseEntity<UsuarioModel> loginUser(@RequestBody UsuarioModel usuario){
        UsuarioModel user = usuarioService.loginUser(usuario);
        if(user!=null){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(user,HttpStatus.NOT_FOUND);
        }

    }
}
