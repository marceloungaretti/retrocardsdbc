package com.dbc.retrocards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SprintDTO {
    private Integer idSprint;
    private String titulo;
    private LocalDate dataInicio;
    private LocalDate dataConclusao;
}
