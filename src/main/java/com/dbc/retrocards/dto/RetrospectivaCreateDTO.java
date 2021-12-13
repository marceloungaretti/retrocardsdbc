package com.dbc.retrocards.dto;

import com.dbc.retrocards.entity.TipoStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RetrospectivaCreateDTO {
    private  String tituloRetrospectiva;
    @ApiModelProperty(value = "Data de criação do reunião")
    private LocalDate dataReuniao;
    @ApiModelProperty(value = "1 - CRIADO   2 - ANDAMENTO")
    private TipoStatus tipoStatus;

}