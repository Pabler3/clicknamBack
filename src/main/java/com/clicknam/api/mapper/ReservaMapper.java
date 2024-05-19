package com.clicknam.api.mapper;

import com.clicknam.api.dao.MesaEntity;
import com.clicknam.api.dao.ReservaEntity;
import com.clicknam.api.model.MesaModel;
import com.clicknam.api.model.ReservaModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReservaMapper {
    ReservaMapper INSTANCE = Mappers.getMapper(ReservaMapper.class);

    ReservaEntity modelToEntity(ReservaModel reservaModel);

    ReservaModel entityToModel(ReservaEntity reservaEntity);

    List<ReservaModel> entityListToModelList(List<ReservaEntity> list);
}
