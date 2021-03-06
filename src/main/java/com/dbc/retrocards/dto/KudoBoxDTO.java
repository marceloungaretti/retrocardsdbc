package com.dbc.retrocards.dto;

//FIXME Limpar
import com.dbc.retrocards.entity.SprintEntity;
import com.dbc.retrocards.entity.StatusKudoBoxEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class KudoBoxDTO extends KudoBoxCreateDTO{

    @ApiModelProperty(value = "ID do KudoBox")
    private Integer idKudoBox;;

    @ApiModelProperty(value = "Status do KudoBox")
    private StatusKudoBoxEntity statusKudoBoxEntity;

    private SprintDTO sprintDTO;
}
