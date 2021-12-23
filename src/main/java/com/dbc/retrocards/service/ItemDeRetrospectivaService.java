package com.dbc.retrocards.service;
//FIXME Limpar
import com.dbc.retrocards.dto.ItemDeRetrospectivaCreateDTO;
import com.dbc.retrocards.dto.ItemDeRetrospectivaDTO;
import com.dbc.retrocards.dto.RetrospectivaDTO;
import com.dbc.retrocards.entity.ItemDeRetrospectivaEntity;
import com.dbc.retrocards.entity.KudoCardEntity;
import com.dbc.retrocards.entity.StatusKudoBoxEntity;
import com.dbc.retrocards.entity.StatusRetrospectivaEntity;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.ItemDeRetrospectivaRepository;
import com.dbc.retrocards.repository.RetrospectivaRepository;
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
    private final RetrospectivaRepository retrospectivaRepository;
    private final UsuarioService usuarioService;

    private ItemDeRetrospectivaEntity findById(Integer id) throws RegraDeNegocioException {
        ItemDeRetrospectivaEntity entity = itemDeRetrospectivaRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Item de Retrospectiva não encontrado"));
        return entity;
    }

    public ItemDeRetrospectivaDTO create(Integer id, ItemDeRetrospectivaCreateDTO itemDeRetrospectivaCreateDTO) throws RegraDeNegocioException {
        ItemDeRetrospectivaEntity entity = objectMapper.convertValue(itemDeRetrospectivaCreateDTO, ItemDeRetrospectivaEntity.class);
        entity.setIdCriador(usuarioService.retrieveUser().getIdUsuario());
        entity.setRetrospectivaEntity(retrospectivaRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Retrospectiva não encontrada")));
        ItemDeRetrospectivaEntity itemCriado = itemDeRetrospectivaRepository.save(entity);
        ItemDeRetrospectivaDTO itemDeRetrospectivaDTO = objectMapper.convertValue(itemCriado, ItemDeRetrospectivaDTO.class);
        itemDeRetrospectivaDTO.setRetrospectivaDTO(objectMapper.convertValue(itemCriado.getRetrospectivaEntity(), RetrospectivaDTO.class));
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

    public List<ItemDeRetrospectivaDTO> getByIdRetro(Integer id) throws RegraDeNegocioException {
        return itemDeRetrospectivaRepository.findByIdRetro(id).stream()
                .map(retrospectivaEntity -> {
                    ItemDeRetrospectivaDTO itemDeRetrospectivaDTO = objectMapper.convertValue(retrospectivaEntity, ItemDeRetrospectivaDTO.class);
                    return itemDeRetrospectivaDTO;
                })
                .collect(Collectors.toList());
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
            if (entity.getRetrospectivaEntity().getStatusRetrospectivaEntity() == StatusRetrospectivaEntity.EM_ANDAMENTO) {
                itemDeRetrospectivaRepository.delete(entity);
            } else {
                throw new RegraDeNegocioException("Não é possivel deletar. Status Incorreto.");
            }
    }

}
