package com.dbc.retrocards.service;

import com.dbc.retrocards.dto.KudoCardCreateDTO;
import com.dbc.retrocards.dto.KudoCardDTO;
import com.dbc.retrocards.entity.KudoCardEntity;
import com.dbc.retrocards.entity.StatusKudoBox;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.KudoCardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KudoCardService {
    private final KudoCardRepository kudoCardRepository;
    private final ObjectMapper objectMapper;

    private KudoCardEntity findById(Integer id) throws RegraDeNegocioException {
        KudoCardEntity entity = kudoCardRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("KudoCard não encontrado."));
        return entity;
    }

    public KudoCardDTO create(KudoCardCreateDTO kudoCardCreateDTO) throws Exception {
        KudoCardEntity entity = objectMapper.convertValue(kudoCardCreateDTO, KudoCardEntity.class);
        KudoCardEntity kudoCardCriado = kudoCardRepository.save(entity);
        KudoCardDTO kudoCardDTO = objectMapper.convertValue(kudoCardCriado, KudoCardDTO.class);
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
        if (entity.getKudoBox().getStatus() == StatusKudoBox.EM_ANDAMENTO) {
            kudoCardRepository.delete(entity);
        }
    }
}
