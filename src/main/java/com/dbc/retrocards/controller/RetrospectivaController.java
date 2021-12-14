package com.dbc.retrocards.controller;


import com.dbc.retrocards.dto.RetrospectivaCreateDTO;
import com.dbc.retrocards.dto.RetrospectivaDTO;
import com.dbc.retrocards.entity.RetrospectivaEntity;
import com.dbc.retrocards.entity.StatusRetrospectivaEntity;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
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
@RequestMapping("/retrospectiva")
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
    public RetrospectivaCreateDTO create(Integer id,@RequestBody @Valid RetrospectivaCreateDTO retrospectivaCreateDTO) throws Exception {
        return retrospectivaService.create(id,retrospectivaCreateDTO);
    }


    @GetMapping()
    @ApiOperation(value = "Lista as retrospectivas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrospectivas listadas com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public List<RetrospectivaDTO> list() {
        return retrospectivaService.list();
    }

    @GetMapping("/{idRetrospectiva}")
    public RetrospectivaEntity getById(@RequestParam("idTitulo") Integer idRetrospectiva) throws Exception {
        return retrospectivaService.findById(idRetrospectiva);
    }

    @ApiOperation(value = "Atualiza uma Retrospectiva pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna retrospectiva atualizado com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping("/{idRetrospectiva}")
    public RetrospectivaDTO update(@PathVariable("idRetrospectiva") Integer id,
                                   @RequestBody @Valid RetrospectivaCreateDTO retroCreateDTO) throws Exception {
        return retrospectivaService.update(id, retroCreateDTO);
    }

    @PutMapping("/{idRetrospectiva}/status")
    public RetrospectivaDTO updateStatus(@PathVariable("idRetrospectiva") Integer idRetrospectiva,
                                         @RequestParam @Valid StatusRetrospectivaEntity status) throws Exception {
        return retrospectivaService.updateStatus(idRetrospectiva, status);
    }



    @ApiOperation(value = "Deleta uma Retrospectiva  pelo sey ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrospectiva  deletada com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um exceção")
    })
    @DeleteMapping("/{idTitulo}")
    public void delete(@PathVariable("idRetrospectiva") Integer id) throws Exception {
        retrospectivaService.delete(id);
    }
    @GetMapping("/id-sprint")
    public List<RetrospectivaDTO> listByIdSprint(@Valid @RequestParam("idSprint") Integer id) throws RegraDeNegocioException {
        return retrospectivaService.getByIdSprint(id);
    }
    }



