package com.example.m3_1_calculadora_miguelalejandro_ramirez_meza;
/**
 * Write a description of interface ICalculadora here.
 * 
 * @author (Miguel Alejandro Ramirez Meza)
 * @version (1)
 */

public interface ICalculadora
{
    void setControl(IControl control);
    void setDisplay(IDisplay display);
    void setProcesamiento(IProcesamiento procesa);
}
