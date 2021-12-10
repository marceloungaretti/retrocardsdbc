package com.dbc.retrocards.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RetrospectivaDTO extends RetrospectivaCreateDTO {
    @ApiModelProperty(value = "ID DO TITULO")
    private Integer id;
}
