package com.dbc.retrocards.dto;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class EmailCreateDTO {

    private String assunto;

    private String emailDestinatario;

    private LocalDate dataEnvio;
}

