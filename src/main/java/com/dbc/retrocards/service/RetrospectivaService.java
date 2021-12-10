package com.dbc.retrocards.service;

import com.dbc.retrocards.dto.KudoCardDTO;
import com.dbc.retrocards.dto.RetrospectivaCreateDTO;
import com.dbc.retrocards.dto.RetrospectivaDTO;
import com.dbc.retrocards.entity.KudoCardEntity;
import com.dbc.retrocards.entity.RetrospectivaEntity;
import com.dbc.retrocards.entity.TipoStatus;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.RetrospectivaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RetrospectivaService {
    private final ObjectMapper objectMapper;
    private final RetrospectivaRepository repository;

    public RetrospectivaCreateDTO create(RetrospectivaCreateDTO retrospectivaCreateDTO) throws Exception {

        RetrospectivaEntity retroCriada = objectMapper.convertValue(retrospectivaCreateDTO, RetrospectivaEntity.class);
        repository.save(retroCriada);
        RetrospectivaCreateDTO retroDTO = objectMapper.convertValue(retroCriada, RetrospectivaCreateDTO.class);
        return retroDTO;
    }


    public RetrospectivaEntity findById(Integer id) throws RegraDeNegocioException {
        RetrospectivaEntity entity = repository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Titulo não encontrado não encontrado."));
        return entity;
    }
    
    public List<RetrospectivaCreateDTO> list() {
        return repository.findAll()
                .stream()
                .map(retro -> objectMapper.convertValue(retro, RetrospectivaCreateDTO.class))
                .collect(Collectors.toList());
    }

    public RetrospectivaDTO getById(Integer id) throws RegraDeNegocioException {
        RetrospectivaEntity entity = findById(id);
        RetrospectivaDTO dto = objectMapper.convertValue(entity, RetrospectivaDTO.class);
        return dto;
    }

    public RetrospectivaDTO update(Integer id, RetrospectivaCreateDTO retrospectivaCreateDTO) throws RegraDeNegocioException {
        findById(id);
        RetrospectivaEntity entity = objectMapper.convertValue(retrospectivaCreateDTO, RetrospectivaEntity.class);
        entity.setIdTitulo(id);
        RetrospectivaEntity update = repository.save(entity);
        RetrospectivaDTO dto = objectMapper.convertValue(update, RetrospectivaDTO.class);
        return dto;
    }


    public void delete(Integer idTitulo) throws RegraDeNegocioException {
        RetrospectivaEntity Entity = repository.findById(idTitulo).orElseThrow(() -> new RegraDeNegocioException("Totulo de Retrispectiva  não encontrado!"));

        Entity.setTipoStatus(TipoStatus.ANDAMENTO);

        repository.save(Entity);


    }
}
