package com.dbc.retrocards.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "item")
public class ItemDeRetrospectivaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_SEQ")
    @SequenceGenerator(name = "ITEM_SEQ", sequenceName = "seq_item", allocationSize = 1)
    @Column(name = "id_item")
    private Integer idItemRetrospectiva;

    @Column(name = "id_criador")
    private Integer idCriador;

    @Column(name = "TIPO")
    private String tipo;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_retrospectiva", referencedColumnName = "id_retrospectiva")
    private RetrospectivaEntity retrospectivaEntity;
}
