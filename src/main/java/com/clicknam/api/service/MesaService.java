package com.clicknam.api.service;

import com.clicknam.api.dao.MesaEntity;
import com.clicknam.api.dao.RestauranteEntity;
import com.clicknam.api.mapper.MesaMapper;
import com.clicknam.api.mapper.RestauranteMapper;
import com.clicknam.api.model.MesaModel;
import com.clicknam.api.model.RestauranteModel;
import com.clicknam.api.repository.MesaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MesaService {

    private final MesaRepository mesaRepository;
    @Transactional // servicio para crear una mesa
    public MesaModel createMesa(MesaModel mesaModel){
        MesaEntity mesaEntity = MesaMapper.INSTANCE.modelToEntity(mesaModel);
        MesaEntity mesaCreada = mesaRepository.save(mesaEntity);
        MesaModel mesa = MesaMapper.INSTANCE.entityToModel(mesaCreada);
        return mesa;
    }

    @Transactional // servicio para traer una lista de mesas por la id de restaurante
    public List<MesaModel> findMesaByRestauranteId(long id){
        Optional<List<MesaEntity>> mesaEntities = mesaRepository.findByRestauranteId(id);
        if(mesaEntities.isPresent()){
            List<MesaEntity> mesaEntityList = mesaEntities.get();
            List<MesaModel> mesaModelList = MesaMapper.INSTANCE.entityListToModelList(mesaEntityList);
            return mesaModelList;
        }
        return new ArrayList<MesaModel>();
    }
}
