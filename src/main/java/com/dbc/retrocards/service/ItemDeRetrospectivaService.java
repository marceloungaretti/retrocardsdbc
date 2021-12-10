package com.dbc.retrocards.service;

import com.dbc.retrocards.dto.ItemDeRetrospectivaCreateDTO;
import com.dbc.retrocards.dto.ItemDeRetrospectivaDTO;
import com.dbc.retrocards.entity.ItemDeRetrospectivaEntity;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.ItemDeRetrospectivaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemDeRetrospectivaService {
    private final ItemDeRetrospectivaRepository itemDeRetrospectivaRepository;
    private final ObjectMapper objectMapper;

    private ItemDeRetrospectivaEntity findById(Integer id) throws RegraDeNegocioException {
        ItemDeRetrospectivaEntity entity = itemDeRetrospectivaRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Item de Retrospectiva não encontrado"));
        return entity;
    }

    public ItemDeRetrospectivaDTO create(ItemDeRetrospectivaCreateDTO dto) throws Exception {
        ItemDeRetrospectivaEntity entity = objectMapper.convertValue(dto, ItemDeRetrospectivaEntity.class);
        ItemDeRetrospectivaEntity itemCriado = itemDeRetrospectivaRepository.save(entity);
        ItemDeRetrospectivaDTO itemDeRetrospectivaDTO = objectMapper.convertValue(itemCriado, ItemDeRetrospectivaDTO.class);
        return itemDeRetrospectivaDTO;
    }

    public List<ItemDeRetrospectivaDTO> list() {
        return itemDeRetrospectivaRepository.findAll()
                .stream()
                .map(item -> objectMapper.convertValue(item, ItemDeRetrospectivaDTO.class))
                .collect(Collectors.toList());
    }

    public ItemDeRetrospectivaDTO getById(Integer id) throws RegraDeNegocioException {
        ItemDeRetrospectivaEntity entity = findById(id);
        ItemDeRetrospectivaDTO dto = objectMapper.convertValue(entity, ItemDeRetrospectivaDTO.class);
        return dto;
    }

    public ItemDeRetrospectivaDTO update(Integer id, ItemDeRetrospectivaCreateDTO itemDeRetrospectivaCreateDTO) throws RegraDeNegocioException {
        findById(id);
        ItemDeRetrospectivaEntity entity = objectMapper.convertValue(itemDeRetrospectivaCreateDTO, ItemDeRetrospectivaEntity.class);
        entity.setIdItemRetrospectiva(id);
        ItemDeRetrospectivaEntity update = itemDeRetrospectivaRepository.save(entity);
        ItemDeRetrospectivaDTO dto = objectMapper.convertValue(update, ItemDeRetrospectivaDTO.class);
        return dto;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        ItemDeRetrospectivaEntity entity = findById(id);
        itemDeRetrospectivaRepository.delete(entity);
    }
}