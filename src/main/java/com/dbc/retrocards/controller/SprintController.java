package com.dbc.retrocards.controller;

import com.dbc.retrocards.dto.ListarSprintDTO;
import com.dbc.retrocards.dto.SprintCreateDTO;
import com.dbc.retrocards.dto.SprintDTO;
import com.dbc.retrocards.service.SprintService;
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
@RequestMapping("/sprint")
@Validated
@Slf4j
@RequiredArgsConstructor
public class SprintController {
    private final SprintService sprintService;


    @PostMapping
    @ApiOperation(value = "Cria nova Sprint")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sprint criada com sucesso"),
            @ApiResponse(code = 400, message = "Dados do sprint inconsistentes"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public SprintDTO create(@RequestBody @Valid SprintCreateDTO sprintCreateDTO) throws Exception {
        log.info("Criando sprint...");
        SprintDTO sprintDTO = sprintService.create(sprintCreateDTO);
        log.info("Sprint criada com sucesso.");
        return sprintDTO;
    }

    @GetMapping("/listar-sprint")
    @ApiOperation(value = "listar-sprint")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sprints listadas com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public List<ListarSprintDTO> procurarPessoasComEndereco (){
        return sprintService.listar();

    }
}
