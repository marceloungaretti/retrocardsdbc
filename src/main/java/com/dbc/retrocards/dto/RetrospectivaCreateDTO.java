package com.dbc.retrocards.dto;

import com.dbc.retrocards.entity.StatusItemEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class RetrospectivaCreateDTO {
    @NotEmpty
    @NotBlank
    @ApiModelProperty(value = "Título da Retrospectiva")
    private  String tituloRetrospectiva;
    @ApiModelProperty(value = "Data de criação do reunião")
    private LocalDate dataReuniao;
    @ApiModelProperty(value = "1 - CRIADO   2 - ANDAMENTO")
    private StatusItemEntity statusItemEntity;

}