package com.dbc.retrocards.controller;

import com.dbc.retrocards.dto.ListarSprintDTO;
import com.dbc.retrocards.dto.SprintCreateDTO;
import com.dbc.retrocards.dto.SprintDTO;
import com.dbc.retrocards.service.SprintService;
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
    public SprintDTO create(@RequestBody @Valid SprintCreateDTO sprintCreateDTO) throws Exception {
        log.info("Criando sprint...");
        SprintDTO sprintDTO = sprintService.create(sprintCreateDTO);
        log.info("Sprint criada com sucesso.");
        return sprintDTO;
    }

    @GetMapping("/listar-sprint")
    public List<ListarSprintDTO> procurarPessoasComEndereco (){
        return sprintService.listar();

    }
}
