package com.dbc.retrocards.service;

import com.dbc.retrocards.dto.ItemDeRetrospectivaDTO;
import com.dbc.retrocards.dto.RetrospectivaCreateDTO;
import com.dbc.retrocards.dto.RetrospectivaDTO;
import com.dbc.retrocards.entity.RetrospectivaEntity;
import com.dbc.retrocards.entity.SprintEntity;
import com.dbc.retrocards.entity.StatusKudoBoxEntity;
import com.dbc.retrocards.entity.StatusRetrospectivaEntity;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.RetrospectivaRepository;
import com.dbc.retrocards.repository.SprintRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        entity.setStatusRetrospectivaEntity(StatusRetrospectivaEntity.CRIADA);
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

//    public List<ItemDeRetrospectivaDTO> getById(Integer idRetrospectiva) throws RegraDeNegocioException {
//        return repository.findByIdRetro(idRetrospectiva).stream().map(item ->{
//            ItemDeRetrospectivaDTO itemDeRetrospectivaDTO = objectMapper.convertValue(item, ItemDeRetrospectivaDTO.class);
//
//            return itemDeRetrospectivaDTO;
//        }).collect(Collectors.toList());
//    }

    public List<RetrospectivaDTO> getByIdSprint(Integer id) throws RegraDeNegocioException {
        return repository.findByIdSprint(id).stream()
                .map(retrospectivaEntity -> {
                    RetrospectivaDTO retrospectivaDTO = objectMapper.convertValue(retrospectivaEntity, RetrospectivaDTO.class);
                    retrospectivaDTO.setItemDeRetrospectivaDTO(retrospectivaEntity.getItens().stream().map(retro -> objectMapper.convertValue(retro, ItemDeRetrospectivaDTO.class)).collect(Collectors.toList()));
                    return retrospectivaDTO;
                })
                .collect(Collectors.toList());
    }

    public List<RetrospectivaDTO> getByIdRetro(Integer id) throws RegraDeNegocioException {
        return repository.findByIdRetro(id).stream()
                .map(retrospectivaEntity -> {
                    RetrospectivaDTO retrospectivaDTO = objectMapper.convertValue(retrospectivaEntity, RetrospectivaDTO.class);
                    retrospectivaDTO.setItemDeRetrospectivaDTO(retrospectivaEntity.getItens().stream().map(retro -> objectMapper.convertValue(retro, ItemDeRetrospectivaDTO.class)).collect(Collectors.toList()));
                    return retrospectivaDTO;
                })
                .collect(Collectors.toList());
    }

    public RetrospectivaDTO update(Integer id, RetrospectivaCreateDTO retrospectivaCreateDTO) throws RegraDeNegocioException {
        findById(id);
        RetrospectivaEntity entity = objectMapper.convertValue(retrospectivaCreateDTO, RetrospectivaEntity.class);
//        entity.setIdTitulo(id);
        RetrospectivaEntity update = repository.save(entity);
        RetrospectivaDTO dto = objectMapper.convertValue(update, RetrospectivaDTO.class);
        return dto;
    }

    public RetrospectivaDTO updateStatus(Integer idRetrospectiva, StatusRetrospectivaEntity status) throws RegraDeNegocioException {
        Optional<RetrospectivaEntity> entity = repository.findById(idRetrospectiva);
        SprintEntity sprintEntity = entity.get().getSprintEntity();
        for (RetrospectivaEntity entity2 : sprintEntity.getRetrospectivaEntityList()) {
            if (entity2.getStatusRetrospectivaEntity() == StatusRetrospectivaEntity.EM_ANDAMENTO) {
                throw new RegraDeNegocioException("Não é possivel iniciar. Status em andamento em uso");
            }
        }
            RetrospectivaEntity entity2 = findById(idRetrospectiva);
            entity2.setStatusRetrospectivaEntity(status);
            RetrospectivaEntity update = repository.save(entity2);
            return objectMapper.convertValue(update, RetrospectivaDTO.class);
        }



//    if (entity.getKudoBox().getStatusKudoBoxEntity() == StatusKudoBoxEntity.EM_ANDAMENTO) {
//        kudoCardRepository.delete(entity);
//    } else {
//        throw new RegraDeNegocioException("Não é possivel deletar. Status Incorreto.");
//    }

    public void delete(Integer idRetrospectiva) throws RegraDeNegocioException {
        RetrospectivaEntity entity = findById(idRetrospectiva);
        repository.delete(entity);

    }
}
