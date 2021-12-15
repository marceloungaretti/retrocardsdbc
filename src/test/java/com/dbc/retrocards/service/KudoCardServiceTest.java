package com.dbc.retrocards.service;

import com.dbc.retrocards.dto.KudoBoxCreateDTO;
import com.dbc.retrocards.dto.KudoBoxDTO;
import com.dbc.retrocards.dto.KudoCardCreateDTO;
import com.dbc.retrocards.dto.KudoCardDTO;
import com.dbc.retrocards.entity.KudoBoxEntity;
import com.dbc.retrocards.entity.KudoCardEntity;
import com.dbc.retrocards.entity.StatusKudoBoxEntity;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.KudoBoxRepository;
import com.dbc.retrocards.repository.KudoCardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KudoCardServiceTest {

    @Mock
    private KudoCardRepository kudoCardRepository;

    @Mock
    private KudoBoxRepository kudoBoxRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private KudoCardService kudoCardService;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() throws RegraDeNegocioException {
//        KudoCardEntity kudoCardEntity = new KudoCardEntity();
//        KudoBoxEntity kudoBoxEntity = new KudoBoxEntity();
//        kudoBoxEntity.setIdKudoBox(1);
//        kudoCardEntity.setKudoBox(kudoBoxEntity);
//        KudoCardDTO kudoCardDTO = new KudoCardDTO();
//        KudoCardCreateDTO kudoCardCreateDTO = new KudoCardCreateDTO();
//
//        when(objectMapper.convertValue(kudoCardCreateDTO, KudoCardEntity.class)).thenReturn(kudoCardEntity);
//        when(kudoCardRepository.save(kudoCardEntity)).thenReturn(kudoCardEntity);
//        when(objectMapper.convertValue(kudoCardEntity, KudoCardDTO.class)).thenReturn(kudoCardDTO);
//
//        kudoCardService.create(1, kudoCardCreateDTO);
//
//        verify(kudoCardRepository, times(1)).save(kudoCardEntity);

    }

    @Test
    void list() {
    }

    @Test
    void getById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() throws RegraDeNegocioException {
        KudoCardEntity entity = new KudoCardEntity();
        KudoBoxEntity box = new KudoBoxEntity();
        entity.setKudoBox(box);
        box.setStatusKudoBoxEntity(StatusKudoBoxEntity.EM_ANDAMENTO);
        doReturn(Optional.of(entity)).when(kudoCardRepository).findById(1);
        kudoCardService.delete(1);
        verify(kudoCardRepository, times(1)).delete(entity);
    }

    @Test
    void listar() {
    }

    @Test
    void listarPorBox() {
    }
}