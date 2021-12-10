package com.dbc.retrocards.dto;

import com.dbc.retrocards.entity.StatusKudoBox;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class KudoBoxDTO extends KudoBoxCreateDTO{

    @ApiModelProperty(value = "ID do KudoBox")
    private Integer id;

    @ApiModelProperty(value = "Status do KudoBox")
    private StatusKudoBox statusKudoBox;
}
