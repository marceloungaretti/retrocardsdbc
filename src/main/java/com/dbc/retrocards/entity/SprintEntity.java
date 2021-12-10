package com.dbc.retrocards.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name="SPRINT")
public class SprintEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPRINT_SEQUENCIA")
    @SequenceGenerator(name = "SPRINT_SEQUENCIA", sequenceName = "SEQ_SPRINT", allocationSize = 1)
    @Column(name = "id_sprint")
    private Integer idSprint;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "data_inicio")
    private LocalDate dataInicio;
    @Column(name = "data_conclusao")
    private LocalDate dataConclusao;
}
