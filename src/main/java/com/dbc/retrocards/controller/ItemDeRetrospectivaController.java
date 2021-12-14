package com.dbc.retrocards.controller;

import com.dbc.retrocards.dto.ItemDeRetrospectivaCreateDTO;
import com.dbc.retrocards.dto.ItemDeRetrospectivaDTO;
import com.dbc.retrocards.dto.KudoCardCreateDTO;
import com.dbc.retrocards.dto.KudoCardDTO;
import com.dbc.retrocards.service.ItemDeRetrospectivaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/item")
@Validated
@RequiredArgsConstructor
@Slf4j
public class ItemDeRetrospectivaController {

    private final ItemDeRetrospectivaService itemDeRetrospectivaService;

    @ApiOperation(value = "Cria um novo Item de Retrospectiva")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Item de Retrospectiva criado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um exceção")
    })
    @PostMapping
    public ItemDeRetrospectivaDTO create(Integer id, @RequestBody @Valid ItemDeRetrospectivaCreateDTO itemDeRetrospectivaCreateDTO) throws Exception {
        ItemDeRetrospectivaDTO itemDeRetrospectivaDTO = itemDeRetrospectivaService.create(id, itemDeRetrospectivaCreateDTO);
        return itemDeRetrospectivaDTO;
    }


    @ApiOperation(value = "Retorna uma lista de Itens de Retrospectiva")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de Itens de Retrospectiva"),
            @ApiResponse(code = 403, message = "Você mão tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping
    public List<ItemDeRetrospectivaDTO> list() {
        return itemDeRetrospectivaService.list();
    }


    @ApiOperation(value = "Retorna um Item de Retrospectiva pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Item de Retrospectiva com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/{idRetro}")
    public List<ItemDeRetrospectivaDTO> getByIdRetro(@RequestParam("idRetro") Integer idRetro) throws Exception {
        return itemDeRetrospectivaService.getByIdRetro(idRetro);
    }


    @ApiOperation(value = "Atualiza um Item de Retrospectiva pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Item de Retrospectiva atualizado com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping("/{idItem}")
    public ItemDeRetrospectivaDTO update(@PathVariable("idItem") Integer idItem,
                              @RequestBody @Valid ItemDeRetrospectivaCreateDTO itemDeRetrospectivaCreateDTO) throws Exception {
        return itemDeRetrospectivaService.update(idItem, itemDeRetrospectivaCreateDTO);
    }


    @ApiOperation(value = "Deleta um Item de Retrospectiva pelo sey ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Item de Retrospectiva deletado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um exceção")
    })
    @DeleteMapping("/{idItem}")
    public void delete(@PathVariable("idItem") Integer idItem) throws Exception {
        itemDeRetrospectivaService.delete(idItem);
    }
}
