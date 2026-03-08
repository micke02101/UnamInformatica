package com.example.m3_1_calculadora_miguelalejandro_ramirez_meza;

import android.util.Log;

/**
 * Implementación de la interfaz IDisplay para la Unidad 3.
 * @author Miguel Alejandro Ramirez Meza
 */
public class Display implements IDisplay {
    private static final String TAG = "CalculadoraUNAM";

    @Override
    public String muestraResultado(float res) {
        String mensaje = Float.isNaN(res) ? "Resultado: Error (NaN)" : "Resultado: " + res;
        Log.d(TAG, mensaje);
        return mensaje;
    }

    @Override
    public String muestraError(CalculadoraError error) {
        String msg = "Error: " + error.name();
        Log.e(TAG, msg);
        return msg;
    }
}