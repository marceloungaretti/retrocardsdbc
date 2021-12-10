package com.dbc.retrocards.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "retrospectiva")
public class RetrospectivaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RETROSPECTIVA_SEQ")
    @SequenceGenerator(name = "RETROSPECTIVA_SEQ", sequenceName = "SEQ_RETROSPECTIVA", allocationSize = 1)

    @Column(name = "id_retrospectiva")
    private Integer idRetrospectiva;
    @Column(name = "id_titulo")
    private  Integer idTitulo;

    @Column(name = "status")
    private TipoStatus tipoStatus;

    @Column(name = "descricao")
    private  String descricao;

    @Column(name = "data")
    private LocalDateTime dataReuniao;

    @JsonIgnore
    @OneToMany(mappedBy = "retrospectiva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemDeRetrospectivaEntity> itens;

}
