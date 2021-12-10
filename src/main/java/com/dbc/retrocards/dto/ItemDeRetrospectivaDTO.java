package com.dbc.retrocards.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ItemDeRetrospectivaDTO extends ItemDeRetrospectivaCreateDTO{

    @ApiModelProperty(value = "ID do Item de Retrospectiva")
    private Integer id;
}
