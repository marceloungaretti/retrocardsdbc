package com.dbc.retrocards.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SprintCreateDTO {
    @NotEmpty
    @NotBlank
    @ApiModelProperty(value = "Título da Sprint")
    private String titulo;

    @FutureOrPresent
    @NotNull
    @ApiModelProperty(value = "Data de inicio das sprints ")
    private LocalDate dataInicio;

    @FutureOrPresent
    @NotNull
    @ApiModelProperty(value = "Data de conclusão das sprints")
    private LocalDate dataConclusao;
}
