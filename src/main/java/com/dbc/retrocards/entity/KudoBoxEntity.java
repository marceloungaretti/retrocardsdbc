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
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "KUDOBOX")
public class KudoBoxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KUDOBOX_SEQ")
    @SequenceGenerator(name = "KUDOBOX_SEQ", sequenceName = "seq_kudobox", allocationSize = 1)
    @Column(name = "id_kudobox")
    private Integer idKudoBox;


    @Column(name = "titulo")
    private String titulo;

    @Column(name = "data_leitura")
    private LocalDate dataLeitura;

    @Column(name = "status")
    private StatusKudoBox status;

    @JsonIgnore
    @OneToMany(mappedBy = "kudoBox", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KudoCardEntity> kudocards;
}
