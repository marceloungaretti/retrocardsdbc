package com.dbc.retrocards.service;

import com.dbc.retrocards.dto.ListarSprintDTO;
import com.dbc.retrocards.dto.SprintCreateDTO;
import com.dbc.retrocards.dto.SprintDTO;
import com.dbc.retrocards.entity.SprintEntity;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.SprintRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SprintService {
    private final SprintRepository sprintRepository;
    private final ObjectMapper objectMapper;

    public SprintDTO create(SprintCreateDTO sprintCreateDTO) throws RegraDeNegocioException {
        SprintEntity entity = objectMapper.convertValue(sprintCreateDTO, SprintEntity.class);
        if (sprintCreateDTO.getDataInicio().isAfter(sprintCreateDTO.getDataConclusao())){
            throw new RegraDeNegocioException("data errada");
        }
        SprintEntity sprintCriar = sprintRepository.save(entity);
        SprintDTO dto = objectMapper.convertValue(sprintCriar, SprintDTO.class);
        return dto;
    }

    public List<ListarSprintDTO> listar() {
        return sprintRepository.listarSprintPorDataConclusaoDesc().stream()
                .map(sprint -> {
                    ListarSprintDTO listarSprintDTO = objectMapper.convertValue(sprint, ListarSprintDTO.class);
                    return listarSprintDTO;
                }).collect(Collectors.toList());
    }
}
