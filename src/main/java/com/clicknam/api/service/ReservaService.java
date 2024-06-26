package com.clicknam.api.service;

import com.clicknam.api.dao.ReservaEntity;
import com.clicknam.api.exceptions.newException;
import com.clicknam.api.mapper.ReservaMapper;
import com.clicknam.api.model.MesaModel;
import com.clicknam.api.model.ReservaModel;
import com.clicknam.api.repository.ReservaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final MesaService mesaService;

    @Transactional //servicio para crear una reserva
    public ReservaModel createReserva(ReservaModel reservaModel){
        ReservaEntity reservaEntity = ReservaMapper.INSTANCE.modelToEntity(reservaModel);
        ReservaEntity reservaCreated = reservaRepository.save(reservaEntity);
        ReservaModel reserva = ReservaMapper.INSTANCE.entityToModel(reservaCreated);
        return reserva;

    }

    //servicio para crear una reserva previa segun los filtros de busqueda
    public ReservaModel reservaPrevia(String ciudad,Integer capacidad, Integer dia, Integer mes, Integer ano, String hora, Long id){
        ReservaModel reservaModel = null;
        MesaModel mesa = mesaService.findPrimeraMesaDisponible(ciudad, capacidad, dia, mes, ano, hora, id);
        if(null != mesa){
            reservaModel = new ReservaModel();
            reservaModel.setDia(dia);
            reservaModel.setMes(mes);
            reservaModel.setAno(ano);
            reservaModel.setHoraInicio(hora);
            reservaModel.setHoraFin(calcularHoraFin(hora,mesa.getHoraMaxima()));
            reservaModel.setMesa(mesa);
        }
        return reservaModel;
    }

    //calculo de hora fin de reserva en base a la hora de inicio
    public String calcularHoraFin(String horaInicio, Integer maxTiempo){
        String[] hora = horaInicio.split(":");
        Integer horaInt = Integer.valueOf(hora[0]);
        Integer horaFin = horaInt+maxTiempo;
        if(horaFin>24){
            horaFin -=24;
        } else if (horaFin == 24) {
            horaFin = 0;
        }
        String horaFinString = horaFin+":"+hora[1];
        return horaFinString;
    }

    @Transactional // servicio para traer una lista de reservas por id de restaurante
    public List<ReservaModel> findReservaByIdRestaurante(long id) throws newException {
        Optional<List<ReservaEntity>> reservaEntities = reservaRepository.findByRestId(id);
        if(!reservaEntities.isEmpty()){
            return ReservaMapper.INSTANCE.entityListToModelList(reservaEntities.get());
        } else {
            throw new newException("Reservas no encontradas con ID: " + id);
        }
    }

    @Transactional // servicio para traer una lista de reserva por la id de usuario
    public List<ReservaModel> findReservaByUserId(long id) throws newException {
        LocalDate today = LocalDate.now();
        int anoActual = today.getYear();
        int mesActual = today.getMonthValue();
        int diaActual = today.getDayOfMonth();
        Optional<List<ReservaEntity>> reservaEntities = reservaRepository.findByUserId(id,anoActual,mesActual,diaActual);
        if(reservaEntities.isPresent()){
            List<ReservaEntity> reservaEntityList = reservaEntities.get();
            List<ReservaModel> reservaModelList = ReservaMapper.INSTANCE.entityListToModelList(reservaEntityList);
            return reservaModelList;
        }else{
            throw new newException("Reservas no encontradas con ID: " + id);
        }

    }

    @Transactional // servicio para borrar una reserva
    public int deleteReserva(Long id){
        return reservaRepository.deleteReserva(id);
    }
}
