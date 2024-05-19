package com.clicknam.api.mapper;

import com.clicknam.api.dao.MesaEntity;
import com.clicknam.api.dao.RestauranteEntity;
import com.clicknam.api.model.MesaModel;
import com.clicknam.api.model.RestauranteModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MesaMapper {
    MesaMapper INSTANCE = Mappers.getMapper(MesaMapper.class);

    MesaEntity modelToEntity(MesaModel mesaModel);

    MesaModel entityToModel(MesaEntity mesaEntity);

    List<MesaModel> entityListToModelList(List<MesaEntity> list);
}
