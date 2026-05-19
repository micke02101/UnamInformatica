package com.simulador.m6;

import java.util.ArrayList;

public class EstadoSimulacion {

    public int paso;

    public int totalColisiones;

    public ArrayList<EntidadAvion> aviones;

    public ArrayList<EntidadColision> colisiones;

    public EstadoSimulacion(
            int paso,
            int totalColisiones,
            ArrayList<EntidadAvion> aviones,
            ArrayList<EntidadColision> colisiones
    ) {

        this.paso = paso;

        this.totalColisiones = totalColisiones;

        this.aviones = aviones;

        this.colisiones = colisiones;
    }

    public int getNumeroColisiones() {

        return totalColisiones;
    }
}