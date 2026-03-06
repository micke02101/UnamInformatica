package com.example.m1_03_ramirez_meza_miguelalejandro;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;
import java.util.Arrays;
import java.util.Random;


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
        new Thread(() -> {
            int[] num = new int[1000000];
            Random r = new Random();
            for (int i=0; i<num.length; i++)
            num[i] = r.nextInt(1000001);

            long start = System.currentTimeMillis();
            Arrays.sort(num);
            long end = System.currentTimeMillis();
            long tiempoTotal = end-start;

            Log.i("Titulo","Imprimiendo los números ordenados");
            Log.i("tiempo","Tiempo de ejecución "+tiempoTotal+" en milisegundos");

            Log.i("Muestra","Imprimiendo los primeros y últimos 5 números");
            Log.i("0","Primer número: "+num[0]);
            Log.i("1","Segundo número: "+num[1]);
            Log.i("2","Tercero número: "+num[2]);
            Log.i("3","Cuarto número: "+num[3]);
            Log.i("4","Quinto número: "+num[4]);

            Log.i("999996","999996 número: "+num[num.length-5]);
            Log.i("999997","999997 número: "+num[num.length-4]);
            Log.i("999998","999998 número: "+num[num.length-3]);
            Log.i("999999","999999 número: "+num[num.length-2]);
            Log.i("1000000","1000000 número: "+num[num.length-1]);



        }).start();
    }
}
