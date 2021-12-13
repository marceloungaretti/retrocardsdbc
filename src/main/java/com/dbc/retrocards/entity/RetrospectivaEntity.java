package com.dbc.retrocards.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(name = "titulo")
    private  String tituloRetrospectiva;

    @Column(name = "status")
    private StatusRetrospectivaEntity statusRetrospectivaEntity;


    @Column(name = "data")
    private LocalDate dataReuniao;

    @JsonIgnore
    @OneToMany(mappedBy = "retrospectivaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemDeRetrospectivaEntity> itens;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_sprint", referencedColumnName = "id_sprint")
    private SprintEntity sprintEntity;

}
