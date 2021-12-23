package com.dbc.retrocards.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity(name = "GRUPO")
public class GrupoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_grupo")
    @SequenceGenerator(name = "seq_grupo", sequenceName = "seq_grupo", allocationSize = 1)
    @Column(name = "id_grupo")
    private Integer idGrupo;

    private String nome;

    private String descricao;
    @JsonIgnore
    @ManyToMany(mappedBy = "grupos")
    private List<UsuarioEntity> usuarios;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "GRUPO_REGRA",
            joinColumns = @JoinColumn(name = "id_grupo"),
            inverseJoinColumns = @JoinColumn(name = "id_regra")
    )
    //FIXME Pode deixar sem o caminho, já está no pacote
    private List<com.dbc.retrocards.entity.RegraEntity> regras;

}
