package com.dbc.retrocards.service;

import com.dbc.retrocards.dto.ItemDeRetrospectivaDTO;
import com.dbc.retrocards.dto.RetrospectivaCreateDTO;
import com.dbc.retrocards.dto.RetrospectivaDTO;
import com.dbc.retrocards.dto.SprintDTO;
import com.dbc.retrocards.entity.RetrospectivaEntity;
import com.dbc.retrocards.entity.TipoStatus;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.RetrospectivaRepository;
import com.dbc.retrocards.repository.SprintRepository;
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
    private final SprintRepository sprintRepository;

    public RetrospectivaDTO create(Integer id, RetrospectivaCreateDTO retrospectivaCreateDTO) throws RegraDeNegocioException {
        RetrospectivaEntity entity = objectMapper.convertValue(retrospectivaCreateDTO, RetrospectivaEntity.class);
        entity.setSprintEntity(sprintRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("Sprint não encontrada")));
        RetrospectivaEntity retrospectivaCriar = repository.save(entity);
        RetrospectivaDTO retroDTO = objectMapper.convertValue(retrospectivaCriar, RetrospectivaDTO.class);
        return retroDTO;
    }


    public RetrospectivaEntity findById(Integer idRetrospectiva) throws RegraDeNegocioException {
        RetrospectivaEntity entity = repository.findById(idRetrospectiva)
                .orElseThrow(() -> new RegraDeNegocioException("Titulo não encontrado não encontrado."));
        return entity;
    }
    
    public List<RetrospectivaDTO> list() {
        return repository.findAll()
                .stream()
                .map(retro -> objectMapper.convertValue(retro, RetrospectivaDTO.class))
                .collect(Collectors.toList());

    }

    public RetrospectivaDTO getById(Integer idRetrospectiva) throws RegraDeNegocioException {
        RetrospectivaEntity entity = findById(idRetrospectiva);
        RetrospectivaDTO dto = objectMapper.convertValue(entity, RetrospectivaDTO.class);
        if(dto.getTipoStatus() == TipoStatus.EM_ANDAMENTO){
            throw new RegraDeNegocioException("Retrospectiva não encontrada");
        }
        return dto;
    }

    public RetrospectivaDTO update(Integer id, RetrospectivaCreateDTO retrospectivaCreateDTO) throws RegraDeNegocioException {
        findById(id);
        RetrospectivaEntity entity = objectMapper.convertValue(retrospectivaCreateDTO, RetrospectivaEntity.class);
//        entity.setIdTitulo(id);
        RetrospectivaEntity update = repository.save(entity);
        RetrospectivaDTO dto = objectMapper.convertValue(update, RetrospectivaDTO.class);
        return dto;
    }


    public void delete(Integer idRetrospectiva) throws RegraDeNegocioException {
       RetrospectivaEntity entity = findById(idRetrospectiva);
        repository.delete(entity);

    }


    public List<RetrospectivaDTO> getByIdSprint(Integer id) throws RegraDeNegocioException {
        return repository.findByIdSprint(id).stream()
                .map(retrospectivaEntity -> {
                    RetrospectivaDTO retrospectivaDTO = objectMapper.convertValue(retrospectivaEntity, RetrospectivaDTO.class);
                    retrospectivaDTO.setItemDeRetrospectivaDTO(retrospectivaEntity.getItens().stream().map(retro -> objectMapper.convertValue(retro, ItemDeRetrospectivaDTO.class)).collect(Collectors.toList()));
                    return retrospectivaDTO;
                })
                .collect(Collectors.toList());
    }
}
