package com.clicknam.api.mapper;

import com.clicknam.api.dao.RestauranteEntity;
import com.clicknam.api.model.RestauranteModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RestauranteMapper {
    RestauranteMapper INSTANCE = Mappers.getMapper(RestauranteMapper.class);

    RestauranteEntity modelToEntity(RestauranteModel restauranteModel);

    RestauranteModel entityToModel(RestauranteEntity restauranteEntity);

    List<RestauranteModel> entityListToModelList(List<RestauranteEntity> list);
}
