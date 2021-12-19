package com.dbc.retrocards.controller;

import com.dbc.retrocards.dto.KudoCardCreateDTO;
import com.dbc.retrocards.dto.KudoCardDTO;
import com.dbc.retrocards.dto.ListarKudoCardDTO;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.service.KudoCardService;
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
@RequestMapping("/kudocard")
@Validated
@RequiredArgsConstructor
@Slf4j
public class KudoCardController {

    private final KudoCardService kudoCardService;

    @ApiOperation(value = "Cria um novo Kudo Card")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Kudo Card criado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um exceção")
    })
    @PostMapping
    public KudoCardDTO create(Integer id,@RequestBody @Valid KudoCardCreateDTO kudoCardCreateDTO) throws Exception {
        KudoCardDTO kudoCardDTO = kudoCardService.create(id,kudoCardCreateDTO);
        return kudoCardDTO;
    }


    @ApiOperation(value = "Retorna uma lista de Kudo Cards")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de Kudo Cards"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping
    public List<KudoCardDTO> list() {
        return kudoCardService.list();
    }


    @ApiOperation(value = "Retorna um Kudo Card pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Kudo Card com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/{idKudoCard}")
    public KudoCardDTO getById(@RequestParam("idKudoCard") Integer idKudoCard) throws Exception {
        return kudoCardService.getById(idKudoCard);
    }


    @ApiOperation(value = "Atualiza um Kudo Card pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Kudo Card atualizado com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping("/{idKudoCard}")
    public KudoCardDTO update(@PathVariable("idKudoCard") Integer idKudoCard,
                              @RequestBody @Valid KudoCardCreateDTO kudoCardCreateDTO) throws Exception {
        return kudoCardService.update(idKudoCard, kudoCardCreateDTO);
    }


    @ApiOperation(value = "Deleta um Kudo Card pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Kudo Card deletado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um exceção")
    })
    @DeleteMapping("/{idKudoCard}")
    public void delete(@PathVariable("idKudoCard") Integer idKudoCard) throws Exception {
        kudoCardService.delete(idKudoCard);
    }

    @ApiOperation(value = "Lista um Kudo Card ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Kudo Card listado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um exceção")
    })
    @GetMapping("/listar-kudo-card")
    public List<ListarKudoCardDTO> listarKudoCard() {
        return kudoCardService.listar();
    }

    @ApiOperation(value = "Lista um Kudo Card pelo ID do kudobox")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Kudo Card com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um exceção")
    })
    @GetMapping("/list-por-box")
    public List<KudoCardDTO> listarPorBox (@RequestParam("idKudoBox") Integer idKudoBox) throws RegraDeNegocioException {
        return kudoCardService.listarPorBox(idKudoBox);
    }

    @ApiOperation(value = "Lista todos Kudo Cards com status encerrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornou a lista com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um exceção")
    })
    @GetMapping("/find-by-sprint")
    public List<KudoCardDTO> findBySprint () throws RegraDeNegocioException {
        return kudoCardService.findBySprint();
    }

    @ApiOperation(value = "Lista os Kudo Cards com status encerrado pelo id da sprint")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornou a lista com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um exceção")
    })
    @GetMapping("/find-by-id-sprint")
    public List<KudoCardDTO> findByIdSprint (@RequestParam Integer idSprint) throws RegraDeNegocioException {
        return kudoCardService.findByIdSprint(idSprint);
    }

    @ApiOperation(value = "Lista os Kudo Cards do usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornou a lista com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um exceção")
    })
    @GetMapping("/find-by-user")
    public List<KudoCardDTO> listarPorCriador() throws RegraDeNegocioException {
        return kudoCardService.listarPorCriador();
    }
}
