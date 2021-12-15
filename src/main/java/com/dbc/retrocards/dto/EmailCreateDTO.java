package com.dbc.retrocards.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class EmailCreateDTO {


    @NotBlank
    @NotEmpty
    @ApiModelProperty(value = "Campo assunto do email")
    private String assunto;

    @NotNull
    @ApiModelProperty(value = "emaisl do destinatario")
    private String emailDestinatario;

    @NotNull
    @ApiModelProperty(value = "data de envio do email")
    private LocalDate dataEnvio;
}

