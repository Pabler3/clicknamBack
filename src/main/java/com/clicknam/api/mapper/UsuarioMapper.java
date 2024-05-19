package com.clicknam.api.mapper;

import com.clicknam.api.dao.UsuarioEntity;
import com.clicknam.api.model.UsuarioModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioModel entityToModel(UsuarioEntity usuarioEntity);

    UsuarioEntity modelToEntity(UsuarioModel usuarioModel);

    List<UsuarioModel> entityListToModelList(List<UsuarioEntity> list);

}
