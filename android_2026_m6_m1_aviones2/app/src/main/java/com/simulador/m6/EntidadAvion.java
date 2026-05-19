package com.simulador.m6;

public class EntidadAvion {

    public int x;

    public int y;

    public DireccionMovimiento direccion;

    public EntidadAvion(
            int x,
            int y,
            DireccionMovimiento direccion
    ) {

        this.x = x;
        this.y = y;
        this.direccion = direccion;
    }

    public void mover() {

        switch (direccion) {

            case NORTE:
                y--;
                break;

            case SUR:
                y++;
                break;

            case ESTE:
                x++;
                break;

            case OESTE:
                x--;
                break;
        }
    }
}