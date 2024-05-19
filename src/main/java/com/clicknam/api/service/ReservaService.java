package com.clicknam.api.service;

import com.clicknam.api.dao.ReservaEntity;
import com.clicknam.api.mapper.ReservaMapper;
import com.clicknam.api.model.ReservaModel;
import com.clicknam.api.repository.ReservaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservaService {
    private final ReservaRepository reservaRepository;

    @Transactional
    public ReservaModel createReserva(ReservaModel reservaModel){
        ReservaEntity reservaEntity = ReservaMapper.INSTANCE.modelToEntity(reservaModel);
        ReservaEntity reservaCreated = reservaRepository.save(reservaEntity);
        ReservaModel reserva = ReservaMapper.INSTANCE.entityToModel(reservaCreated);
        return reserva;
    }
}
