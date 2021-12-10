package com.dbc.retrocards.controller;


import com.dbc.retrocards.dto.RetrospectivaCreateDTO;
import com.dbc.retrocards.dto.RetrospectivaDTO;
import com.dbc.retrocards.entity.RetrospectivaEntity;
import com.dbc.retrocards.service.RetrospectivaService;
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
@RequestMapping("/Retrospectiva")
@Validated
@RequiredArgsConstructor
@Slf4j
public class RetrospectivaController {
        private final RetrospectivaService retrospectivaService;



    @PostMapping
    @ApiOperation(value = "Cria nova Retrospectiva")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrospectiva criado com sucesso"),
            @ApiResponse(code = 400, message = "Dados do produto inconsistentes"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public RetrospectivaCreateDTO create(@RequestBody @Valid RetrospectivaCreateDTO retrospectivaCreateDTO) throws Exception {
        return retrospectivaService.create(retrospectivaCreateDTO);
    }


    @GetMapping()
    @ApiOperation(value = "Lista as retrospectivas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrospectivas listadas com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public List<RetrospectivaCreateDTO> list() {
        return retrospectivaService.list();
    }

    @GetMapping("/{idTitulo}")
    public RetrospectivaEntity getById(@RequestParam("idTitulo") Integer idTitulo) throws Exception {
        return retrospectivaService.findById(idTitulo);
    }

    @ApiOperation(value = "Atualiza umA Retrospectiva pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna retrospectiva atualizado com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping("/{idTitulo}")
    public RetrospectivaDTO update(@PathVariable("idTitulo") Integer id,
                                   @RequestBody @Valid RetrospectivaCreateDTO retroCreateDTO) throws Exception {
        return retrospectivaService.update(id, retroCreateDTO);
    }



    @ApiOperation(value = "Deleta um Kudo Card pelo sey ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Kudo Card deletado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um exceção")
    })
    @DeleteMapping("/{idTitulo}")
    public void delete(@PathVariable("idTitulo") Integer id) throws Exception {
        retrospectivaService.delete(id);
    }

    }



