package com.dbc.retrocards.service;

import com.dbc.retrocards.dto.SprintCreateDTO;
import com.dbc.retrocards.dto.SprintDTO;
import com.dbc.retrocards.entity.SprintEntity;
import com.dbc.retrocards.repository.SprintRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SprintService {
    private final ObjectMapper objectMapper;
    private final SprintRepository sprintRepository;

    public SprintDTO create(SprintCreateDTO sprintCreateDTO) throws Exception{
        SprintEntity sprintEntity = objectMapper.convertValue(sprintCreateDTO, SprintEntity.class);
        SprintEntity sprintCriada = sprintRepository.save(sprintEntity);
        SprintDTO sprintDTO = objectMapper.convertValue(sprintCriada, SprintDTO.class);
        return sprintDTO;
    }
}
