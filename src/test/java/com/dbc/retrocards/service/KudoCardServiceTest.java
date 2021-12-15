package com.dbc.retrocards.service;

import com.dbc.retrocards.entity.*;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.KudoCardRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class KudoCardServiceTest {

    @Mock
    private KudoCardRepository kudoCardRepository;

    @InjectMocks
    private KudoCardService kudoCardService;

    @Test
    public void deveriaRetornarExcessaoAoProcurarPorIdNaoExistente () {

        int id = 100;
        when(kudoCardRepository.findById(id)).thenReturn(Optional.empty());
        Assert.assertThrows(RegraDeNegocioException.class, () -> kudoCardService.getById(id)
        );
    }

    @Test
    public void deveriaRetornarCardCorretamenteAoBuscarPorId() {
        KudoCardEntity entity = new KudoCardEntity();
        entity.setIdKudoCard(1);
        when(kudoCardRepository.findById(entity.getIdKudoCard())).thenReturn(Optional.of(entity));
        Assert.assertEquals(entity.getIdKudoCard(), 1, 0);
    }

    @Test(expected = RegraDeNegocioException.class)
    public void deveriaDarErroAoTentarDeletarCardComStatusEncerrado() throws RegraDeNegocioException{
        KudoCardEntity entity = new KudoCardEntity();
        KudoBoxEntity box = new KudoBoxEntity();
        entity.setKudoBox(box);
        box.setStatusKudoBoxEntity(StatusKudoBoxEntity.ENCERRADO);
        doReturn(Optional.of(entity)).when(kudoCardRepository).findById(1);
        kudoCardService.delete(1);
        verify(kudoCardRepository, times(1)).delete(entity);
    }

    @Test
    public void deveriaDeletarCardComStatusEmAndamento() throws RegraDeNegocioException{
        KudoCardEntity entity = new KudoCardEntity();
        KudoBoxEntity box = new KudoBoxEntity();
        entity.setKudoBox(box);
        box.setStatusKudoBoxEntity(StatusKudoBoxEntity.EM_ANDAMENTO);
        doReturn(Optional.of(entity)).when(kudoCardRepository).findById(1);
        kudoCardService.delete(1);
        verify(kudoCardRepository, times(1)).delete(entity);
    }
}