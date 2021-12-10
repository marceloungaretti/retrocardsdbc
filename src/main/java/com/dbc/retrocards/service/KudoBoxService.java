package com.dbc.retrocards.service;

import com.dbc.retrocards.dto.KudoBoxCreateDTO;
import com.dbc.retrocards.dto.KudoBoxDTO;
import com.dbc.retrocards.entity.KudoBoxEntity;
import com.dbc.retrocards.entity.StatusKudoBox;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.KudoBoxRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KudoBoxService {
    private final KudoBoxRepository kudoBoxRepository;
    private final ObjectMapper objectMapper;

    private KudoBoxEntity findById(Integer id) throws RegraDeNegocioException {
        KudoBoxEntity entity = kudoBoxRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("KudoBox não encontrado."));
        return entity;
    }

    public KudoBoxDTO create(KudoBoxCreateDTO kudoBoxCreateDTO) throws Exception {
        KudoBoxEntity entity = objectMapper.convertValue(kudoBoxCreateDTO, KudoBoxEntity.class);
        entity.setStatus(StatusKudoBox.EM_ANDAMENTO);
        KudoBoxEntity kudoBoxCriado = kudoBoxRepository.save(entity);
        KudoBoxDTO kudoBoxDTO = objectMapper.convertValue(kudoBoxCriado, KudoBoxDTO.class);
        return kudoBoxDTO;
    }

    public List<KudoBoxDTO> list() {
        return kudoBoxRepository.findAll()
                .stream()
                .map(kudobox -> objectMapper.convertValue(kudobox, KudoBoxDTO.class))
                .collect(Collectors.toList());
    }

    public KudoBoxDTO getById(Integer id) throws RegraDeNegocioException {
        KudoBoxEntity entity = findById(id);
        KudoBoxDTO dto = objectMapper.convertValue(entity, KudoBoxDTO.class);
        return dto;
    }

    public KudoBoxDTO update(Integer id, KudoBoxCreateDTO kudoBoxCreateDTO) throws RegraDeNegocioException{
        findById(id);
        KudoBoxEntity entity = objectMapper.convertValue(kudoBoxCreateDTO, KudoBoxEntity.class);
        entity.setIdKudoBox(id);
        KudoBoxEntity update = kudoBoxRepository.save(entity);
        KudoBoxDTO dto = objectMapper.convertValue(update, KudoBoxDTO.class);
        return dto;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        KudoBoxEntity entity = findById(id);
        kudoBoxRepository.delete(entity);
    }
}
