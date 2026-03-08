package com.example.m3_1_calculadora_miguelalejandro_ramirez_meza;
/**
 * Write a description of interface IControl here.
 * 
 * @author (Miguel Alejandro Ramirez Meza)
 * @version (1)
 */

public interface IControl
{
    void introduceValorX(int x);
    void introduceValorY(int y);
    void introduceOperacion(Operacion op);
    float igual();
}
