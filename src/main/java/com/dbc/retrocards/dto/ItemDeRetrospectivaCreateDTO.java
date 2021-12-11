package com.dbc.retrocards.dto;

import com.dbc.retrocards.entity.RetrospectivaEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ItemDeRetrospectivaCreateDTO {

    @NotEmpty
    @NotBlank
    @ApiModelProperty(value = "Tipo(O que funcionou bem? / O que pode ser melhorado ? / O que faremos no próximo sprint para melhorar?)")
    private String tipo;

    @NotNull
    @ApiModelProperty(value = "Título do Item")
    private String titulo;

    @NotNull
    @ApiModelProperty(value = "Descrição")
    private String descricao;

}
