package com.dbc.retrocards.controller;

import com.dbc.retrocards.dto.RetrospectivaCreateDTO;
import com.dbc.retrocards.dto.RetrospectivaDTO;
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
@Validated
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/retrospectiva")
public class RetrospectivaController {
    private final RetrospectivaService retrospectivaService;


    @PostMapping
    @ApiOperation(value = "Cria uma nova retrospectiva")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrospectiva criada com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema.")
    })
    public RetrospectivaCreateDTO create(Integer id, @RequestBody @Valid RetrospectivaCreateDTO retrospectivaCreateDTO) throws RegraDeNegocioException {
        return retrospectivaService.create(id, retrospectivaCreateDTO);
    }

    @GetMapping("/listar-retrospectiva")
    @ApiOperation(value = "Gera uma lista de retrospectivas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de retrospectivas gerada com sucesso."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema.")
    })
    public List<RetrospectivaDTO> list() {
        return retrospectivaService.list();
    }

    @PutMapping("/{idRetrospectiva}/status")
    public RetrospectivaDTO updateStatus(@PathVariable("idRetrospectiva") Integer idRetrospectiva,
                                         @RequestParam @Valid StatusRetrospectivaEntity status) throws Exception {
        return retrospectivaService.updateStatus(idRetrospectiva, status);
    }

    @DeleteMapping("/delete-por-id")
    @ApiOperation(value = "Deleta uma retrospectiva")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrospectiva  deletada com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 500, message = "Foi gerada um exceção.")
    })
    public void delete(@PathVariable("idRetrospectiva") Integer idRetrospectiva) throws RegraDeNegocioException {
        retrospectivaService.delete(idRetrospectiva);
    }

    @GetMapping("/listar-por-id-sprint")
    @ApiOperation(value = "Listar uma retrospectiva por ID da sprint")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrospectiva  listada com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 500, message = "Foi gerada um exceção.")
    })
    public List<RetrospectivaDTO> listByIdSprint(@Valid @RequestParam("idSprint") Integer id) throws RegraDeNegocioException {
        return retrospectivaService.getByIdSprint(id);
    }

    @GetMapping("/listar-por-id-retro")
    @ApiOperation(value = "Listar uma retrospectiva pelo ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrospectiva  listada com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 500, message = "Foi gerada um exceção.")
    })
    public List<RetrospectivaDTO> listByIdRetro(@Valid @RequestParam("idRetro") Integer id) throws RegraDeNegocioException {
        return retrospectivaService.getByIdRetro(id);
    }


    @ApiOperation(value = "Listar uma retrospectiva por data mais recente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrospectiva  listada com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 500, message = "Foi gerada um exceção.")
    })
    @GetMapping("/find-retro-mais-recente")
    public RetrospectivaDTO findRetroMaisRecente() throws RegraDeNegocioException {
        return retrospectivaService.getRetroMaisRecente();
    }
}



