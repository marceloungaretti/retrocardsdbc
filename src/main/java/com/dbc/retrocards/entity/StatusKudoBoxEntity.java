package com.dbc.retrocards.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public enum StatusKudoBoxEntity {
    EM_ANDAMENTO(0),
    ENCERRADO(1);

    private Integer status;
}
