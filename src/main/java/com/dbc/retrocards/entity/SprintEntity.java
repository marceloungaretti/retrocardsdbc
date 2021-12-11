package com.dbc.retrocards.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "sprintEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RetrospectivaEntity> retrospectivaEntityList;
}
