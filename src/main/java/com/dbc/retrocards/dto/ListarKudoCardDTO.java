package com.dbc.retrocards.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ListarKudoCardDTO{

    @ApiModelProperty(value = "ID do KudoCard")
    private Integer idKudoCard;
    private LocalDate dataCriacao;
    private String de;
    private String para;
    private String titulo;
}
