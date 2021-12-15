package com.dbc.retrocards.service;

import com.dbc.retrocards.dto.KudoBoxCreateDTO;
import com.dbc.retrocards.dto.KudoBoxDTO;
import com.dbc.retrocards.dto.KudoCardCreateDTO;
import com.dbc.retrocards.dto.KudoCardDTO;
import com.dbc.retrocards.entity.*;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.KudoBoxRepository;
import com.dbc.retrocards.repository.KudoCardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class KudoCardServiceTest {

    @Mock
    private KudoCardRepository kudoCardRepository;

    @InjectMocks
    private KudoCardService kudoCardService;

    @Test(expected = RegraDeNegocioException.class)
    public void deveriaDarErroAoTentarDeletarComStatusEncerrado() throws RegraDeNegocioException {
        KudoCardEntity entity = new KudoCardEntity();
        KudoBoxEntity box = new KudoBoxEntity();
        entity.setKudoBox(box);
        box.setStatusKudoBoxEntity(StatusKudoBoxEntity.ENCERRADO);
        doReturn(Optional.of(entity)).when(kudoCardRepository).findById(1);
        kudoCardService.delete(1);
        verify(kudoCardRepository, times(1)).delete(entity);
    }

    @Test
    public void deveriaDeletarCardComStatusEmAndamento() throws RegraDeNegocioException {
        KudoCardEntity entity = new KudoCardEntity();
        KudoBoxEntity box = new KudoBoxEntity();
        entity.setKudoBox(box);
        box.setStatusKudoBoxEntity(StatusKudoBoxEntity.EM_ANDAMENTO);
        doReturn(Optional.of(entity)).when(kudoCardRepository).findById(1);
        kudoCardService.delete(1);
        verify(kudoCardRepository, times(1)).delete(entity);
    }

}