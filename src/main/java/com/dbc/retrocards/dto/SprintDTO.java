package com.dbc.retrocards.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SprintDTO extends SprintCreateDTO {
    @ApiModelProperty(value = "Id sprint")
    private Integer idSprint;


}
