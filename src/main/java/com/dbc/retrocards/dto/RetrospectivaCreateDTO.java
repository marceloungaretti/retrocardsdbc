package com.dbc.retrocards.dto;

import com.dbc.retrocards.entity.StatusRetrospectivaEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RetrospectivaCreateDTO {
    private  String tituloRetrospectiva;
    @ApiModelProperty(value = "Data de criação do reunião")
    private LocalDate dataReuniao;
    @ApiModelProperty(value = "1 - CRIADO   2 - ANDAMENTO")
    private StatusRetrospectivaEntity statusRetrospectivaEntity;

}