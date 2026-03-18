package com.example.android_2026_m5_02_calorias;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CALORIAS_APP";

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
        calcularCaloriasMaximas();
    }

    private void calcularCaloriasMaximas() {
        int maxCalorias = 0;
        int caloriasActual = 0;


        try {
            InputStream is = getResources().openRawResource(R.raw.calorias);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String linea;

            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    if (caloriasActual > maxCalorias) {
                        maxCalorias = caloriasActual;
                    }
                    caloriasActual = 0;
                } else {
                    caloriasActual += Integer.parseInt(linea.trim());
                }
            }
            if (caloriasActual > maxCalorias) {
                maxCalorias = caloriasActual;
            }
            Log.d(TAG, "El máximo de calorías es: " + maxCalorias);
        } catch (Exception e) {
            Log.e(TAG, "Error al leer archivo", e);
        }
    }
}