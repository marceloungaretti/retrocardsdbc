package com.dbc.retrocards.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class KudoCardDTO extends KudoCardCreateDTO{

    @ApiModelProperty(value = "ID do KudoCard")
    private Integer idKudoCard;
    private KudoBoxDTO kudoBoxDTO;
    private SprintDTO sprintDTO;
}
