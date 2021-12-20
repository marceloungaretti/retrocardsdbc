package com.dbc.retrocards.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EmailDTO extends EmailCreateDTO {
    @ApiModelProperty(value = "ID do email")
    private  Integer idEmail;

    private  RetrospectivaDTO retrospectivaDTO;
}
