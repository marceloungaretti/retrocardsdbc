package com.dbc.retrocards.service;

import com.dbc.retrocards.entity.*;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.RetrospectivaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.Valid;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RetrospectivaServiceTest {

    @Mock
    private RetrospectivaRepository retrospectivaRepository;

    @InjectMocks
    private RetrospectivaService retrospectivaService;

    @Test(expected = RegraDeNegocioException.class)
    public void naoDeveriaTrocarStatusCasoHajaUmaRetrospectivaEmAndamento() throws RegraDeNegocioException {
//        SprintEntity sprintEntity = new SprintEntity();
//
//        RetrospectivaEntity retrospectiva1 = new RetrospectivaEntity();
//        retrospectiva1.setIdRetrospectiva(1);
//        retrospectiva1.setSprintEntity(sprintEntity);
//
//        RetrospectivaEntity retrospectiva2 = new RetrospectivaEntity();
//        retrospectiva2.setIdRetrospectiva(2);
//        retrospectiva2.setSprintEntity(sprintEntity);
//
//        retrospectiva1.setStatusRetrospectivaEntity(StatusRetrospectivaEntity.EM_ANDAMENTO);
//        retrospectiva2.setStatusRetrospectivaEntity(StatusRetrospectivaEntity.CRIADA);
//        doReturn(Optional.of(retrospectiva1)).when(retrospectivaRepository).save(retrospectiva1);
//
//        retrospectivaService.updateStatus(2, StatusRetrospectivaEntity.EM_ANDAMENTO);
//        verify(retrospectivaRepository, times(1)).save(retrospectiva2);

    }

//    KudoCardEntity entity = new KudoCardEntity();
//    KudoBoxEntity box = new KudoBoxEntity();
//        entity.setKudoBox(box);
//        box.setStatusKudoBoxEntity(StatusKudoBoxEntity.ENCERRADO);
//    doReturn(Optional.of(entity)).when(kudoCardRepository).findById(1);
//        kudoCardService.delete(1);
//    verify(kudoCardRepository, times(1)).delete(entity);

}