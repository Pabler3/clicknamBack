package com.clicknam.api.service;

import com.clicknam.api.dao.UsuarioEntity;
import com.clicknam.api.exceptions.newException;
import com.clicknam.api.mapper.RestauranteMapper;
import com.clicknam.api.dao.RestauranteEntity;
import com.clicknam.api.model.RestauranteModel;
import com.clicknam.api.repository.RestauranteRepository;
import com.clicknam.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.juli.logging.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    private final UsuarioRepository usuarioRepository;

    // servicio para devolver una lista de restaurantes con los campos de busqueda
    public List<RestauranteModel> getRestaurantesByBusqueda(String ciudad,Integer capacidad,Integer dia,Integer mes,Integer ano,String hora){
        List<RestauranteEntity> restauranteEntities = restauranteRepository.findBySearch( ciudad,capacidad,dia,mes,ano,hora);
        List<RestauranteModel> restaurantes = RestauranteMapper.INSTANCE.entityListToModelList(restauranteEntities);
        return restaurantes;

    }

    // servicio para devolver la lista de restaurantes
    public List<RestauranteModel> getRestaurantes(){
        List<RestauranteEntity> restaurantesList = restauranteRepository.findAll();
        List<RestauranteModel> restauranteModelList = RestauranteMapper.INSTANCE.entityListToModelList(restaurantesList);
        return restauranteModelList;
    }

    // servicio para devolver un restaurante por ID
    public RestauranteModel getRestauranteById(Long id) throws newException {
        Optional<RestauranteEntity> restauranteEntity = restauranteRepository.findById(id);
        if (restauranteEntity.isPresent()) {
            return RestauranteMapper.INSTANCE.entityToModel(restauranteEntity.get());
        } else {
            throw new newException("Restaurante no encontrado con ID: " + id);
        }
    }
    @Transactional // servicio para crear un restaurante
    public RestauranteModel createRestaurante(RestauranteModel restauranteModel, Long userId) {
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(userId);
        if (usuarioOptional.isPresent()) {
            UsuarioEntity usuario = usuarioOptional.get();
            RestauranteEntity restauranteEntity = RestauranteMapper.INSTANCE.modelToEntity(restauranteModel);
            restauranteEntity.setAdministrador(usuario);
            RestauranteEntity savedRestauranteEntity = restauranteRepository.save(restauranteEntity);
            RestauranteModel restaurante = RestauranteMapper.INSTANCE.entityToModel(savedRestauranteEntity);
            return restaurante;
        } else {
            return null;
        }
    }
    @Transactional // servicio para actualizar un restaurante
    public RestauranteModel updateRestaurante(RestauranteModel restauranteModel) {
        RestauranteEntity restauranteEntity = RestauranteMapper.INSTANCE.modelToEntity(restauranteModel);
        RestauranteEntity savedRestauranteEntity = restauranteRepository.save(restauranteEntity);
        RestauranteModel restaurante = RestauranteMapper.INSTANCE.entityToModel(savedRestauranteEntity);
        return restaurante;

    }

    @Transactional // servicio para traer una lista de restaurantes por la id de usuario
    public List<RestauranteModel> findRestaurantByUserId(long id){
        Optional<List<RestauranteEntity>> restauranteEntities = restauranteRepository.findByUserId(id);
        if(restauranteEntities.isPresent()){
            List<RestauranteEntity> restauranteEntityList = restauranteEntities.get();
            List<RestauranteModel> restauranteModelList = RestauranteMapper.INSTANCE.entityListToModelList(restauranteEntityList);
            return restauranteModelList;
        }
        return new ArrayList<RestauranteModel>();
    }


}
