package com.dbc.retrocards.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ListarKudoCardDTO{

    @ApiModelProperty(value = "ID do KudoCard")
    private Integer idKudoCard;
    @NotNull
    @ApiModelProperty(value = "Data de inicio)")
    private LocalDate dataCriacao;
    @NotEmpty
    @NotBlank
    @ApiModelProperty(value = "remetente")
    private String de;
    @NotEmpty
    @NotBlank
    @ApiModelProperty(value = "destinatarios")
    private String para;
    @NotEmpty
    @NotBlank
    @ApiModelProperty(value = "Título")
    private String titulo;
}
