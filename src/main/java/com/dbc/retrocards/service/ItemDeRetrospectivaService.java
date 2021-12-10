package com.dbc.retrocards.service;

import com.dbc.retrocards.dto.ItemDeRetrospectivaCreateDTO;
import com.dbc.retrocards.dto.ItemDeRetrospectivaDTO;
import com.dbc.retrocards.dto.KudoCardCreateDTO;
import com.dbc.retrocards.dto.KudoCardDTO;
import com.dbc.retrocards.entity.ItemDeRetrospectivaEntity;
import com.dbc.retrocards.entity.KudoCardEntity;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.ItemDeRetrospectivaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    private ItemDeRetrospectivaDTO create(ItemDeRetrospectivaCreateDTO dto) throws Exception {
        ItemDeRetrospectivaEntity entity = objectMapper.convertValue(dto, ItemDeRetrospectivaEntity.class);
        ItemDeRetrospectivaEntity itemCriado = itemDeRetrospectivaRepository.save(entity);
        ItemDeRetrospectivaDTO itemDeRetrospectivaDTO = objectMapper.convertValue(itemCriado, ItemDeRetrospectivaDTO.class);
        return itemDeRetrospectivaDTO;
    }




}
