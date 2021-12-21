package com.dbc.retrocards.controller;

import com.dbc.retrocards.dto.UsuarioCreateDTO;
import com.dbc.retrocards.dto.UsuarioDTO;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.service.UsuarioService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@Validated
@Slf4j
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @ApiOperation(value = "Retorna todos usuarios")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Lista gerada com sucesso"),
            @ApiResponse(code = 400, message = "Algum dado inconsistente"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    public List<UsuarioDTO> list() {return usuarioService.list();
    }

    @ApiOperation(value = "Retorna usuario Pelo ID")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Usuario encontrado  sucesso"),
            @ApiResponse(code = 400, message = "Algum dado inconsistente"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{idUsuario}")
    public UsuarioDTO listById(@PathVariable("idUsuario") Integer idUsuario) throws  RegraDeNegocioException {
        return usuarioService.getPorId(idUsuario);
    }

    @ApiOperation(value = "Cria um novo usuario")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Usuario criado com sucesso"),
            @ApiResponse(code = 400, message = "Algum dado inconsistente"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    public UsuarioDTO create(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException{
        log.info("Criando usuario");
        UsuarioDTO usuarioDTO = usuarioService.create(usuarioCreateDTO);
        log.info("Usuario criado com sucesso");
        return usuarioDTO;
    }

    @ApiOperation(value = "Atualizar um usuario")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Usuario atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Algum dado inconsistente"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/{idUsuario}")
    public UsuarioDTO update(@PathVariable("idUsuario") Integer id, @RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException {
        log.info("Atualizar usuario");
        UsuarioDTO usuarioDTO = usuarioService.update(id, usuarioCreateDTO);
        log.info("Usuario atualizado");
        return usuarioDTO;
    }

    @ApiOperation(value = "Deletar um usuario")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Usuario deletado com sucesso"),
            @ApiResponse(code = 400, message = "Algum dado inconsistente"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/{idUsuario}")
    public void delete(@PathVariable("idUsuario") @NotNull Integer id) throws RegraDeNegocioException {
        log.info("Deletando usuario");
        usuarioService.delete(id);
        log.info("Usuario deletado com sucesso");
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupera cadastrador"),
            @ApiResponse(code = 400, message = "Você não tem permissão para acessar esse recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    @ApiOperation("Recupera o usuário")
    @GetMapping("/recuperar")
    public UsuarioDTO retrieveUser(){
        return usuarioService.retrieveUser();
    }

    @GetMapping("/listar-por-grupo")
    public List<UsuarioDTO> listarUsuarioPorGrupo(Integer idGrupo){
        return usuarioService.listarUsuarioPorGrupo(idGrupo);
    }
}
