package com.clicknam.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.clicknam.api.dao.UsuarioEntity;
import com.clicknam.api.mapper.UsuarioMapper;
import com.clicknam.api.model.ResponseModel;
import com.clicknam.api.model.UsuarioModel;
import com.clicknam.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    @Transactional
    //servicio para registrar usuario nuevo pero verificando si el email existe en otro usuario
    public boolean registerUser(UsuarioModel usuario){
        UsuarioEntity usuarioEntity = UsuarioMapper.INSTANCE.modelToEntity(usuario);
        if(!usuarioRepository.existsByEmail(usuario.getEmail())){
            usuarioRepository.save(usuarioEntity);
            return true;
        }else{
            return false;
        }
    }
    @Transactional
    public UsuarioModel updateUser(UsuarioModel usuario){
        UsuarioEntity usuarioEntity = UsuarioMapper.INSTANCE.modelToEntity(usuario);
        if(usuarioRepository.isEmailUniqueExceptMine(usuarioEntity.getEmail(),usuarioEntity.getId())){
            UsuarioEntity usuarioActualizado = usuarioRepository.save(usuarioEntity);
            UsuarioModel usuarioModel = UsuarioMapper.INSTANCE.entityToModel(usuarioActualizado);
            return usuarioModel;
        }
        return null;
    }
    //verificar si el email esta disponible o no
    public boolean checkEmailVerify(String email){
        return !usuarioRepository.existsByEmail(email);
    }

    //servicio de login que verifica si el email y la contraseña proporcionados son correctos
    public UsuarioModel loginUser(UsuarioModel usuario){
        Optional<UsuarioEntity> usuarioEntityOptional = usuarioRepository.findByEmail(usuario.getEmail());
        UsuarioEntity usuarioEntity = usuarioEntityOptional.orElse(null);
        UsuarioModel usuarioModel = UsuarioMapper.INSTANCE.entityToModel(usuarioEntity);
        if (usuarioEntity != null && usuarioEntity.getPassword().equals(usuario.getPassword())){
            return usuarioModel;
        }else {
            return null;
        }
    }

}

