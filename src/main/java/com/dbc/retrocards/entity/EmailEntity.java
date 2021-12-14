package com.dbc.retrocards.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "EMAIL")
public class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_email")
    @SequenceGenerator(name = "seq_email", sequenceName = "seq_email", allocationSize = 1)
    @Column(name = "id_email")
    private  Integer idEmail;
    @Column(name = "assunto")
    private String assunto;
    @Column(name = "email_destinatario")
    private String emailDestinatario;
    @Column(name = "data_envio")
    private LocalDate dataEnvio;
}
