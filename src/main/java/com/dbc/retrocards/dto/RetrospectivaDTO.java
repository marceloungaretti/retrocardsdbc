package com.dbc.retrocards.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RetrospectivaDTO extends RetrospectivaCreateDTO {
    @ApiModelProperty(value = "ID DO TITULO")
    private Integer idRetrospectiva;
    private List<ItemDeRetrospectivaDTO> itemDeRetrospectivaDTO;
}
