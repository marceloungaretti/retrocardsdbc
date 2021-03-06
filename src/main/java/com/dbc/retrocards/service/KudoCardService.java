package com.dbc.retrocards.service;

import com.dbc.retrocards.dto.*;
import com.dbc.retrocards.entity.KudoCardEntity;
import com.dbc.retrocards.entity.StatusKudoBoxEntity;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.KudoBoxRepository;
import com.dbc.retrocards.repository.KudoCardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KudoCardService {
    private final KudoCardRepository kudoCardRepository;
    private final ObjectMapper objectMapper;
    private final KudoBoxRepository kudoBoxRepository;
    private final UsuarioService usuarioService;

    private KudoCardEntity findById(Integer id) throws RegraDeNegocioException {
        KudoCardEntity entity = kudoCardRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("KudoCard não encontrado."));
        return entity;
    }

    public KudoCardDTO create(Integer id, KudoCardCreateDTO kudoCardCreateDTO) throws RegraDeNegocioException {
        KudoCardEntity entity = objectMapper.convertValue(kudoCardCreateDTO, KudoCardEntity.class);
        entity.setIdCriador(usuarioService.retrieveUser().getIdUsuario());
        entity.setKudoBox(kudoBoxRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("KudoBox não encontrada")));
        KudoCardEntity kudoCardCriado = kudoCardRepository.save(entity);
        KudoCardDTO kudoCardDTO = objectMapper.convertValue(kudoCardCriado, KudoCardDTO.class);
        kudoCardDTO.setKudoBoxDTO(objectMapper.convertValue(kudoCardCriado.getKudoBox(), KudoBoxDTO.class));
        return kudoCardDTO;
    }

    public List<KudoCardDTO> list() {
        return kudoCardRepository.findAll()
                .stream()
                .map(kudocard -> objectMapper.convertValue(kudocard, KudoCardDTO.class))


                .collect(Collectors.toList());
    }

    public KudoCardDTO getById(Integer id) throws RegraDeNegocioException {
        KudoCardEntity entity = findById(id);
        KudoCardDTO dto = objectMapper.convertValue(entity, KudoCardDTO.class);
        return dto;
    }

    public KudoCardDTO update(Integer id, KudoCardCreateDTO kudoCardCreateDTO) throws RegraDeNegocioException {
        findById(id);
        KudoCardEntity entity = objectMapper.convertValue(kudoCardCreateDTO, KudoCardEntity.class);
        entity.setIdKudoCard(id);
        KudoCardEntity update = kudoCardRepository.save(entity);
        KudoCardDTO dto = objectMapper.convertValue(update, KudoCardDTO.class);
        return dto;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        KudoCardEntity entity = findById(id);
        if (entity.getIdCriador().equals(usuarioService.retrieveUser().getIdUsuario())) {
            if (entity.getKudoBox().getStatusKudoBoxEntity() == StatusKudoBoxEntity.EM_ANDAMENTO) {
                kudoCardRepository.delete(entity);
            } else {
                throw new RegraDeNegocioException("Não é possivel deletar. Status Incorreto.");
            }
        } else {
            throw new RegraDeNegocioException("Você não é o criador deste kudo card");
        }
    }

    public List<KudoCardDTO> listarPorCriador() {
        List<KudoCardEntity> listaTodos = kudoCardRepository.findAll();
        List<KudoCardEntity> listaUser = new ArrayList<>();
        for(KudoCardEntity card : listaTodos) {
            if(card.getIdCriador().equals(usuarioService.retrieveUser().getIdUsuario())){
                listaUser.add(card);
            }
        }
        List<KudoCardDTO> listaDTO = new ArrayList<>();
        for (KudoCardEntity card : listaUser){
            KudoCardDTO dto = objectMapper.convertValue(card, KudoCardDTO.class);
            dto.setKudoBoxDTO(objectMapper.convertValue(card.getKudoBox(), KudoBoxDTO.class));
            listaDTO.add(dto);
        }
        return listaDTO;
    }

    public List<ListarKudoCardDTO> listar() {
        return kudoCardRepository.listarKudoCardPorDataAsc()
                .stream()
                .map(kudoCard -> {
                    ListarKudoCardDTO listarKudoCardDTO = objectMapper.convertValue(kudoCard, ListarKudoCardDTO.class);
                    return listarKudoCardDTO;
                }).collect(Collectors.toList());
    }

    public List<KudoCardDTO> listarPorBox(Integer idKudoBox) throws RegraDeNegocioException {
        return kudoCardRepository.findByIdBox(idKudoBox).stream()
                .map(card -> {
                    KudoCardDTO cardDTO = objectMapper.convertValue(card, KudoCardDTO.class);
                    cardDTO.setKudoBoxDTO(objectMapper.convertValue(card.getKudoBox(),KudoBoxDTO.class));
                    return cardDTO;
                })
                .collect(Collectors.toList());
    }

    public List<KudoCardDTO> findBySprint() throws RegraDeNegocioException {
        return kudoCardRepository.findBySprint().stream()
                .map(card -> {
                    KudoCardDTO cardDTO = objectMapper.convertValue(card, KudoCardDTO.class);
                    cardDTO.setKudoBoxDTO(objectMapper.convertValue(card.getKudoBox(),KudoBoxDTO.class));
                    cardDTO.setSprintDTO(objectMapper.convertValue(card.getKudoBox().getSprintEntity(),SprintDTO.class));
                    return cardDTO;
                })
                .collect(Collectors.toList());
    }

    public List<KudoCardDTO> findByIdSprint(Integer idSprint) throws RegraDeNegocioException {
        return kudoCardRepository.findByIdSprint(idSprint).stream()
                .map(card -> {
                    KudoCardDTO cardDTO = objectMapper.convertValue(card, KudoCardDTO.class);
                    cardDTO.setKudoBoxDTO(objectMapper.convertValue(card.getKudoBox(),KudoBoxDTO.class));
                    cardDTO.setSprintDTO(objectMapper.convertValue(card.getKudoBox().getSprintEntity(),SprintDTO.class));
                    return cardDTO;
                })
                .collect(Collectors.toList());
    }

}
