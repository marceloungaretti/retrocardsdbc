package com.dbc.retrocards.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmailDTO extends EmailCreateDTO {
    @ApiModelProperty(value = "ID do email")
    private  Integer idEmail;
    @NotNull
    private  RetrospectivaDTO retrospectivaDTO;
}
