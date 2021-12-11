package com.dbc.retrocards.dto;

import com.dbc.retrocards.entity.KudoBoxEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class KudoCardCreateDTO {

    @NotEmpty
    @NotBlank
    @ApiModelProperty(value = "Título do KudoCard")
    private String titulo;

    @NotNull
    @ApiModelProperty(value = "Descrição")
    private String descricao;

    @NotNull
    @ApiModelProperty(value = "Data de Criação do KudoCard")
    private LocalDate dataCriacao;

    @ApiModelProperty(value = "Usuário que escreveu")
    private String de;

    @NotNull
    @ApiModelProperty(value = "Usuário que recebeu")
    private String para;

}
