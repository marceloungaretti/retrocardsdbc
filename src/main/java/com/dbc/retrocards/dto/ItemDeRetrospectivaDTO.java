package com.dbc.retrocards.dto;

import com.dbc.retrocards.repository.RetrospectivaRepository;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ItemDeRetrospectivaDTO extends ItemDeRetrospectivaCreateDTO{

    @ApiModelProperty(value = "ID do Item de Retrospectiva")
    private Integer idItemRetrospectiva;
    private RetrospectivaDTO retrospectivaDTO;
}
