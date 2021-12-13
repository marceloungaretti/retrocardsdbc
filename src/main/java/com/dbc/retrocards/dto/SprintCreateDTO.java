package com.dbc.retrocards.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SprintCreateDTO {
    @NotEmpty
    @NotBlank
    @ApiModelProperty(value = "Título da Sprint")
    private String titulo;
    @NotNull
    @ApiModelProperty(value = "Data de inicio das sprints ")
    private LocalDate dataInicio;
    @NotNull
    @ApiModelProperty(value = "Data de conclusão das sprints")
    private LocalDate dataConclusao;
}
