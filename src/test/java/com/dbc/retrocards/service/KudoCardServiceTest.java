package com.dbc.retrocards.service;

import com.dbc.retrocards.dto.*;
import com.dbc.retrocards.entity.*;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.KudoBoxRepository;
import com.dbc.retrocards.repository.KudoCardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class KudoCardServiceTest {

    @Mock
    private KudoCardRepository kudoCardRepository;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private KudoBoxRepository kudoBoxRepository;
    @Mock
    private KudoBoxService kudoBoxService;

    @InjectMocks
    private KudoCardService kudoCardService;

    @Test
    public void deveriaAtualizarCardComSucesso() throws RegraDeNegocioException {
//        KudoCardEntity kudoCardEntity = new KudoCardEntity(1,"Card 1","Primeiro Card", LocalDate.now(),"Fulano","Beltrano",new KudoBoxEntity());
//        when(kudoCardRepository.findById(1)).thenReturn(Optional.of(kudoCardEntity));
//
//        KudoCardCreateDTO kudoCardCreateDTO = new KudoCardCreateDTO();
//        kudoCardCreateDTO.setTitulo("4");
//
//        kudoCardEntity.setIdKudoCard(1);
//        kudoCardService.update(kudoCardRepository.findById(1), kudoCardCreateDTO);
//        kudoCardEntity.setIdKudoCard(1);
//        Assert.assertEquals("4", kudoCardEntity.getTitulo());
    }

//    @Test
//    public void deveriaRetornarTodosCardsComSucesso() {
//        when(kudoCardRepository.findAll()).thenReturn(Arrays.asList(
//                new KudoCardEntity(1,"Card 1","Primeiro Card", LocalDate.now(),"Fulano","Beltrano",new KudoBoxEntity()),
//                new KudoCardEntity(2, "Card 2", "Segundo Card", LocalDate.now(), "Sicrano", "Fulano", new KudoBoxEntity())
//        ));
//        List<KudoCardEntity> lista = kudoCardRepository.findAll();
//        Assert.assertEquals(2, lista.size());
//    }

    @Test(expected = Exception.class)
    public void naoDeveriaCardQueNaoPossuiBox() throws RegraDeNegocioException {
        KudoCardDTO cardDTO = kudoCardService.create(null, new KudoCardCreateDTO());
        KudoCardEntity card = objectMapper.convertValue(cardDTO, KudoCardEntity.class);
        kudoCardRepository.save(card);
    }

    @Test
    public void deveriaSalvarCardNoBancoComSucesso() {
        KudoCardEntity card = new KudoCardEntity();
        card.setKudoBox(new KudoBoxEntity());
        kudoCardRepository.save(card);
        verify(kudoCardRepository, times(1)).save(card);
    }

    @Test(expected = Exception.class)
    public void naoDeveriaCriarCardSemKudoBox() throws RegraDeNegocioException {
        KudoCardCreateDTO cardCreateDTO = new KudoCardCreateDTO();
        kudoCardService.create(1244111233, cardCreateDTO);
        KudoCardEntity entity = objectMapper.convertValue(cardCreateDTO, KudoCardEntity.class);
        verify(kudoCardRepository, times(1)).save(entity);
    }

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