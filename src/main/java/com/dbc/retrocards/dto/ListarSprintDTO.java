package com.dbc.retrocards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListarSprintDTO {
    private Integer idSprint;
    private String titulo;
    private LocalDate dataConclusao;
}
