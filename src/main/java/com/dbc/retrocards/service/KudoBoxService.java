package com.dbc.retrocards.service;

import com.dbc.retrocards.dto.*;
import com.dbc.retrocards.entity.*;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.KudoBoxRepository;
import com.dbc.retrocards.repository.KudoCardRepository;
import com.dbc.retrocards.repository.SprintRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KudoBoxService {
    private final KudoBoxRepository kudoBoxRepository;
    private final ObjectMapper objectMapper;
    private final SprintRepository sprintRepository;

    private KudoBoxEntity findById(Integer id) throws RegraDeNegocioException {
        KudoBoxEntity entity = kudoBoxRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("KudoBox não encontrado."));
        return entity;
    }

    public KudoBoxDTO create(Integer id , KudoBoxCreateDTO kudoBoxCreateDTO) throws RegraDeNegocioException {
        KudoBoxEntity entity = objectMapper.convertValue(kudoBoxCreateDTO, KudoBoxEntity.class);
        entity.setSprintEntity(sprintRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("KudoBox não encontrada")));
        entity.setStatusKudoBoxEntity(StatusKudoBoxEntity.CRIADO);
        KudoBoxEntity kudoBoxCriado = kudoBoxRepository.save(entity);
        KudoBoxDTO kudoBoxDTO = objectMapper.convertValue(kudoBoxCriado, KudoBoxDTO.class);
        kudoBoxDTO.setSprintDTO(objectMapper.convertValue(kudoBoxCriado.getSprintEntity(), SprintDTO.class));
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

//    public RetrospectivaDTO updateStatus(Integer idRetrospectiva, StatusRetrospectivaEntity status) throws RegraDeNegocioException {
//        Optional<RetrospectivaEntity> entity = repository.findById(idRetrospectiva);
//        SprintEntity sprintEntity = entity.get().getSprintEntity();
//        for (RetrospectivaEntity entity2 : sprintEntity.getRetrospectivaEntityList()) {
//            if (entity2.getStatusRetrospectivaEntity() == StatusRetrospectivaEntity.EM_ANDAMENTO) {
//                if (status == StatusRetrospectivaEntity.EM_ANDAMENTO) {
//                    throw new RegraDeNegocioException("Não é possivel iniciar. Status em andamento em uso");
//                }
//            }
//        }
//        RetrospectivaEntity entity2 = findById(idRetrospectiva);
//        entity2.setStatusRetrospectivaEntity(status);
//        RetrospectivaEntity update = repository.save(entity2);
//        return objectMapper.convertValue(update, RetrospectivaDTO.class);
//    }

    public KudoBoxDTO updateStatus(Integer idKudoBox, StatusKudoBoxEntity status) throws RegraDeNegocioException {
        Optional<KudoBoxEntity> entity = kudoBoxRepository.findById(idKudoBox);
        SprintEntity sprintEntity = entity.get().getSprintEntity();
        for (KudoBoxEntity entity2 : sprintEntity.getKudoBoxEntityList()) {
            if (entity2.getStatusKudoBoxEntity() == StatusKudoBoxEntity.EM_ANDAMENTO) {
                if (status == StatusKudoBoxEntity.EM_ANDAMENTO) {
                    throw new RegraDeNegocioException("Não é possivel iniciar. Status em andamento em uso");
                }
            }
        }
        KudoBoxEntity entity2 = findById(idKudoBox);
        entity2.setStatusKudoBoxEntity(status);
        KudoBoxEntity update = kudoBoxRepository.save(entity2);
        return objectMapper.convertValue(update, KudoBoxDTO.class);
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        KudoBoxEntity entity = findById(id);
        kudoBoxRepository.delete(entity);
    }

    public List<KudoBoxDTO> getBoxByIdSprint(Integer id) throws RegraDeNegocioException {
        return kudoBoxRepository.findBoxByIdSprint(id).stream()
                .map(kudoBoxEntity -> {
                    KudoBoxDTO kudoBoxDTO = objectMapper.convertValue(kudoBoxEntity, KudoBoxDTO.class);
//                    kudoBoxDTO.(retrospectivaEntity.getItens().stream().map(retro -> objectMapper.convertValue(retro, ItemDeRetrospectivaDTO.class)).collect(Collectors.toList()));
                    return kudoBoxDTO;
                })
                .collect(Collectors.toList());
    }
}
