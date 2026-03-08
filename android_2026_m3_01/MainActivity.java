package com.example.m3_1_calculadora_miguelalejandro_ramirez_meza;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // 1. Instanciamos los componentes (Implementación de interfaces)
        Control miControl = new Control();
        Display miDisplay = new Display();

        // 2. Realizamos las 4 operaciones básicas para probar la lógica

        // PRUEBA DE SUMA: 10 + 5
        miControl.introduceValorX(10);
        miControl.introduceValorY(5);
        miControl.introduceOperacion(Operacion.SUM);
        miDisplay.muestraResultado(miControl.igual());

        // PRUEBA DE RESTA: 20 - 8
        miControl.introduceValorX(20);
        miControl.introduceValorY(8);
        miControl.introduceOperacion(Operacion.RES);
        miDisplay.muestraResultado(miControl.igual());

        // PRUEBA DE MULTIPLICACIÓN: 6 * 7
        miControl.introduceValorX(6);
        miControl.introduceValorY(7);
        miControl.introduceOperacion(Operacion.MUL);
        miDisplay.muestraResultado(miControl.igual());

        // PRUEBA DE DIVISIÓN (Validación de NaN): 10 / 0
        miControl.introduceValorX(10);
        miControl.introduceValorY(0);
        miControl.introduceOperacion(Operacion.DIV);

        float resultadoDiv = miControl.igual();
        miDisplay.muestraResultado(resultadoDiv);
    }
}