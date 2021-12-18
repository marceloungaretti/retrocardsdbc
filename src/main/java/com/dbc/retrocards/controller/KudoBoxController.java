package com.dbc.retrocards.controller;

import com.dbc.retrocards.dto.KudoBoxCreateDTO;
import com.dbc.retrocards.dto.KudoBoxDTO;
import com.dbc.retrocards.entity.StatusKudoBoxEntity;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.service.KudoBoxService;
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
@RequestMapping("/kudobox")
public class KudoBoxController {
    private final KudoBoxService kudoBoxService;

    @PostMapping
    @ApiOperation(value = "Cria um novo Kudo Box")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Kudo Box criado com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 500, message = "Foi gerada um exceção.")
    })
    public KudoBoxDTO create(Integer id, @RequestBody @Valid KudoBoxCreateDTO kudoBoxCreateDTO) throws RegraDeNegocioException {
        KudoBoxDTO kudoBoxDTO = kudoBoxService.create(id, kudoBoxCreateDTO);
        return kudoBoxDTO;
    }

//    @GetMapping
//    @ApiOperation(value = "Retorna uma lista de Kudo Boxes")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Retorna a lista de Kudo Boxes."),
//            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
//            @ApiResponse(code = 500, message = "Foi gerada uma exceção.")
//    })
//    public List<KudoBoxDTO> list() {
//        return kudoBoxService.list();
//    }

    @GetMapping("/{idKudoBox}")
    @ApiOperation(value = "Retorna um Kudo Box pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Kudo Box sucesso."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção.")
    })
    public KudoBoxDTO getById(@RequestParam("idKudoBox") Integer idKudoBox) throws RegraDeNegocioException {
        return kudoBoxService.getById(idKudoBox);
    }

    @PutMapping("/{idKudoBox}/status")
    @ApiOperation(value = "Atualiza o status de uma Kudo Box")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status atualizado com sucesso."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção.")
    })
    public KudoBoxDTO updateStatus(@PathVariable("idKudoBox") Integer idKudoBox, @RequestParam @Valid StatusKudoBoxEntity status) throws RegraDeNegocioException {
        return kudoBoxService.updateStatus(idKudoBox, status);
    }

    @GetMapping("/id-sprint")
    @ApiOperation(value = "Retorna um Kudo Box pelo id da sprint")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Kudo Box retornado com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 500, message = "Foi gerada um exceção.")
    })
    public List<KudoBoxDTO> listBoxByIdSprint(@Valid @RequestParam("idSprint") Integer id) throws RegraDeNegocioException {
        return kudoBoxService.getBoxByIdSprint(id);
    }

    @GetMapping
    @ApiOperation(value = "Retorna todas Kudo Box com status em andamento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornou os Kudo Boxes com sucesso."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção.")
    })
    public List<KudoBoxDTO> getBoxEmAndamento() throws RegraDeNegocioException {
        return kudoBoxService.getBoxEmAndamento();
    }
}
