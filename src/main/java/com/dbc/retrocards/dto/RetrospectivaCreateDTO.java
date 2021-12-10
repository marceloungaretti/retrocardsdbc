package com.dbc.retrocards.dto;

import com.dbc.retrocards.entity.TipoStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RetrospectivaCreateDTO {


    private  Integer idretrospectiva;

    private  int titulo;
    @ApiModelProperty(value = "Data de criação do reunião")
    private LocalDateTime dataQueOcorreu;
    @ApiModelProperty(value = "1 - CRIADO   2 - ANDAMENTO")
    private TipoStatus tipoStatus;
    private String descricao;
    private Integer itensApontados;
}