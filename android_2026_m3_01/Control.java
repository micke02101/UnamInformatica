package com.example.m3_1_calculadora_miguelalejandro_ramirez_meza;

/**
 * Write a description of class Control here.
 * 
 * @author (Miguel Alejandro Ramirez Meza)
 * @version (1)
 */
public class Control implements IControl {
    private float x, y;
    private Operacion op;

    @Override public void introduceValorX(int x) { this.x = (float)x; }
    @Override public void introduceValorY(int y) { this.y = (float)y; }
    @Override public void introduceOperacion(Operacion op) { this.op = op; }
    @Override public float igual() {
        if (op == Operacion.SUM) return x + y;
        if (op == Operacion.RES) return x - y;
        if (op == Operacion.MUL) return x * y;
        if (op == Operacion.DIV) return (y == 0) ? Float.NaN : x / y;
        return 0;
    }
}