package com.dbc.retrocards.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class EmailCreateDTO {


    @NotBlank
    @NotEmpty
    @ApiModelProperty(value = "Campo assunto do email")
    private String assunto;

    @NotNull
    @ApiModelProperty(value = "emails do destinatario")
    private String emailDestinatario;

    @FutureOrPresent
    @NotNull
    @ApiModelProperty(value = "data de envio do email")
    private LocalDate dataEnvio;

}

