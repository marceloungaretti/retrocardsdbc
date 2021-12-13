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
   CRIADO (0),
    EM_ANDAMENTO(1),
    ENCERRADO(2);

    private Integer status;
}
