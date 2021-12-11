package com.dbc.retrocards.entity;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoStatus {


    EM_ANDAMENTO(1),
    ENCERRADA(2);

    private int tipo;

    public int getTipo() {
        return tipo;
    }
}

