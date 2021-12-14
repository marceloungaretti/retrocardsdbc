package com.dbc.retrocards.controller;

import com.dbc.retrocards.dto.KudoBoxCreateDTO;
import com.dbc.retrocards.dto.KudoBoxDTO;
import com.dbc.retrocards.dto.RetrospectivaDTO;
import com.dbc.retrocards.entity.StatusKudoBoxEntity;
import com.dbc.retrocards.entity.StatusRetrospectivaEntity;
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
@RequestMapping("/kudobox")
@Validated
@RequiredArgsConstructor
@Slf4j
public class KudoBoxController {

    private final KudoBoxService kudoBoxService;

    @ApiOperation(value = "Cria um novo Kudo Box")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Kudo Box criado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um exceção")
    })
    @PostMapping
    public KudoBoxDTO create(Integer id,@RequestBody @Valid KudoBoxCreateDTO kudoBoxCreateDTO) throws Exception {
        KudoBoxDTO kudoBoxDTO = kudoBoxService.create(id,kudoBoxCreateDTO);
        return kudoBoxDTO;
    }


    @ApiOperation(value = "Retorna uma lista de Kudo Boxes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de Kudo Boxes"),
            @ApiResponse(code = 403, message = "Você mão tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping
    public List<KudoBoxDTO> list() {
        return kudoBoxService.list();
    }


    @ApiOperation(value = "Retorna um Kudo Box pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Kudo Box sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/{idKudoBox}")
    public KudoBoxDTO getById(@RequestParam("idKudoBox") Integer idKudoBox) throws Exception {
        return kudoBoxService.getById(idKudoBox);
    }

    @PutMapping("/{idKudoBox}/status")
    public KudoBoxDTO updateStatus(@PathVariable("idKudoBox") Integer idKudoBox,
                                         @RequestParam @Valid StatusKudoBoxEntity status) throws Exception {
        return kudoBoxService.updateStatus(idKudoBox, status);
    }


    @ApiOperation(value = "Atualiza um Kudo Box pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Kudo Box atualizado com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping("/{idKudoBox}")
    public KudoBoxDTO update(@PathVariable("idKudoBox") Integer idKudoBox,
                              @RequestBody @Valid KudoBoxCreateDTO kudoBoxCreateDTO) throws Exception {
        return kudoBoxService.update(idKudoBox, kudoBoxCreateDTO);
    }


    @ApiOperation(value = "Deleta um Kudo Box pelo sey ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Kudo Box deletado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um exceção")
    })
    @DeleteMapping("/{idKudoBox}")
    public void delete(@PathVariable("idKudoBox") Integer idKudoBox) throws Exception {
        kudoBoxService.delete(idKudoBox);
    }

    @GetMapping("/id-sprint")
    public List<KudoBoxDTO> listBoxByIdSprint(@Valid @RequestParam("idSprint") Integer id) throws RegraDeNegocioException {
        return kudoBoxService.getBoxByIdSprint(id);
    }
}
