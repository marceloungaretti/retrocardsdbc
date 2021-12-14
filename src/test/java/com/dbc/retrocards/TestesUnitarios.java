package com.dbc.retrocards;

import com.dbc.retrocards.entity.RetrospectivaEntity;
import com.dbc.retrocards.entity.SprintEntity;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.RetrospectivaRepository;
import com.dbc.retrocards.service.RetrospectivaService;
import com.dbc.retrocards.service.SprintService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.validator.constraints.ru.INN;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestesUnitarios {

    @InjectMocks
    private RetrospectivaService retrospectivaService;


    @InjectMocks
    private RetrospectivaRepository retrospectivaRepository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
    }
 @Test
    public void deleteRetro() throws RegraDeNegocioException, JsonProcessingException {
        RetrospectivaEntity retro = new RetrospectivaEntity();
        doReturn(Optional.of(retro)).when(retrospectivaRepository).findById(1);
        retrospectivaService.delete(1);
        verify(retrospectivaRepository, times(1)).delete(retro);
    }
}
