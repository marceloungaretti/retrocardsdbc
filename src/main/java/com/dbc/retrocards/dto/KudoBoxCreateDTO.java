package com.dbc.retrocards.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class KudoBoxCreateDTO {

    @NotEmpty
    @NotBlank
    @ApiModelProperty(value = "Título do KudoBox")
    private String titulo;

    @NotNull
    @ApiModelProperty(value = "Data de leitura dos KudoCards(encerramento dos cadastros)")
    private LocalDate dataLeitura;
}
