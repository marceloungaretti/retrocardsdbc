package com.dbc.retrocards.entity;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoStatus {


    CRIADA(1),
    FINALIZADA(2);

    private int tipo;

    public int getTipo() {
        return tipo;
    }
}

