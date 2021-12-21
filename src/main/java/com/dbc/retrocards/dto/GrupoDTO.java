package com.dbc.retrocards.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GrupoDTO {

    private Integer idGrupo;

    private String nome;

    private String descricao;

    private List<UsuarioDTO> usuarioDTO;


}

