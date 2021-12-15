package com.dbc.retrocards.service;

import com.dbc.retrocards.entity.*;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.ItemDeRetrospectivaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ItemDeRetrospectivaServiceTest {

    @Mock
    private ItemDeRetrospectivaRepository itemDeRetrospectivaRepository;

    @InjectMocks
    private ItemDeRetrospectivaService itemDeRetrospectivaService;

    @Test
    public void deveriaRetornarExcessaoAoProcurarPorIdNaoExistente () {

        int id = 100;
        when(itemDeRetrospectivaRepository.findById(id)).thenReturn(Optional.empty());
        Assert.assertThrows(RegraDeNegocioException.class, () -> itemDeRetrospectivaService.getById(id)
        );
    }

    @Test
    public void deveriaRetornarCardCorretamenteAoBuscarPorId() {
        ItemDeRetrospectivaEntity entity = new ItemDeRetrospectivaEntity();
        entity.setIdItemRetrospectiva(1);
        when(itemDeRetrospectivaRepository.findById(entity.getIdItemRetrospectiva())).thenReturn(Optional.of(entity));
        Assert.assertEquals(entity.getIdItemRetrospectiva(), 1, 0);
    }

    @Test(expected = RegraDeNegocioException.class)
    public void deveriaDarErroAoTentarDeletarItemComStatusEncerrado() throws RegraDeNegocioException {
        ItemDeRetrospectivaEntity itemDeRetrospectivaEntity = new ItemDeRetrospectivaEntity();
        RetrospectivaEntity retrospectivaEntity = new RetrospectivaEntity();
        itemDeRetrospectivaEntity.setRetrospectivaEntity(retrospectivaEntity);
        retrospectivaEntity.setStatusRetrospectivaEntity(StatusRetrospectivaEntity.ENCERRADA);
        doReturn(Optional.of(itemDeRetrospectivaEntity)).when(itemDeRetrospectivaRepository).findById(1);
        itemDeRetrospectivaService.delete(1);
        verify(itemDeRetrospectivaRepository, times(1)).delete(itemDeRetrospectivaEntity);
    }

    @Test
    public void deveriaDeletarItemComStatusEmAndamento() throws RegraDeNegocioException {
        ItemDeRetrospectivaEntity itemDeRetrospectivaEntity = new ItemDeRetrospectivaEntity();
        RetrospectivaEntity retrospectivaEntity = new RetrospectivaEntity();
        itemDeRetrospectivaEntity.setRetrospectivaEntity(retrospectivaEntity);
        retrospectivaEntity.setStatusRetrospectivaEntity(StatusRetrospectivaEntity.EM_ANDAMENTO);
        doReturn(Optional.of(itemDeRetrospectivaEntity)).when(itemDeRetrospectivaRepository).findById(1);
        itemDeRetrospectivaService.delete(1);
        verify(itemDeRetrospectivaRepository, times(1)).delete(itemDeRetrospectivaEntity);
    }
}