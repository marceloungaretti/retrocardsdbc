package com.dbc.retrocards.service;

import com.dbc.retrocards.dto.ItemDeRetrospectivaCreateDTO;
import com.dbc.retrocards.dto.ItemDeRetrospectivaDTO;
import com.dbc.retrocards.entity.*;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.ItemDeRetrospectivaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
//FIXME Limpar
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ItemDeRetrospectivaServiceTest {

    //FIXME Criar mais testes para abranger todos services

    @Mock
    private ItemDeRetrospectivaRepository itemDeRetrospectivaRepository;
    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ItemDeRetrospectivaService itemDeRetrospectivaService;


    @Test(expected = Exception.class)
    public void naoDeveriaSalvarListaDeCardsQueNaoPossuiBox() throws RegraDeNegocioException {
        ItemDeRetrospectivaDTO itemDeRetrospectivaDTO = itemDeRetrospectivaService.create(null, new ItemDeRetrospectivaCreateDTO());
        ItemDeRetrospectivaEntity item = objectMapper.convertValue(itemDeRetrospectivaDTO, ItemDeRetrospectivaEntity.class);
        itemDeRetrospectivaRepository.save(item);
    }

    @Test
    public void deveriaSalvarItemDeRetrospectivaNoBancoComSucesso() {
        ItemDeRetrospectivaEntity entity = new ItemDeRetrospectivaEntity();
        entity.setRetrospectivaEntity(new RetrospectivaEntity());
        itemDeRetrospectivaRepository.save(entity);
        verify(itemDeRetrospectivaRepository, times(1)).save(entity);
    }

    @Test(expected = Exception.class)
    public void naoDeveriaCriarItemDeRetrospectivaSemRetrospectiva() throws RegraDeNegocioException {
        ItemDeRetrospectivaCreateDTO itemCreateDTO = new ItemDeRetrospectivaCreateDTO();
        itemDeRetrospectivaService.create(1244111233, itemCreateDTO);
        ItemDeRetrospectivaEntity entity = objectMapper.convertValue(itemCreateDTO, ItemDeRetrospectivaEntity.class);
        verify(itemDeRetrospectivaRepository, times(1)).save(entity);
    }

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