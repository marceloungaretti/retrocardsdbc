package com.dbc.retrocards.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public enum StatusRetrospectivaEntity {


    CRIADA(0),
    EM_ANDAMENTO(1),
    ENCERRADA(2);

    private Integer tipo;

}

