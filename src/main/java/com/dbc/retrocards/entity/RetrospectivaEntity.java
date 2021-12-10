package com.dbc.retrocards.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Retrospectiva")
public class RetrospectivaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RETROSPECTIVA_SEQ")
    @SequenceGenerator(name = "RETROSPECTIVA_SEQ", sequenceName = "SEQ_RETROSPECTIVA", allocationSize = 1)
    @Column(name = "id_titulo")
    private  Integer idTitulo;
    @Column(name = "status")
    private TipoStatus tipoStatus;
    @Column(name = "DESCRICAO")
    private  String descricao;

}
