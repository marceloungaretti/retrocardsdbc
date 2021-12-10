package com.dbc.retrocards.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "KUDOCARD")
public class KudoCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KUDOCARD_SEQ")
    @SequenceGenerator(name = "KUDOCARD_SEQ", sequenceName = "seq_kudocard", allocationSize = 1)
    @Column(name = "id_kudocard")
    private Integer idKudoCard;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @Column(name = "de")
    private String de;

    @Column(name = "para")
    private String para;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_kudobox", referencedColumnName = "id_kudobox")
    private KudoBoxEntity kudoBox;
}
