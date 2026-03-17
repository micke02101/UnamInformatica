package com.example.android_2026_m5_01_fibonacci;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText etLimite;
    private TextView tvResultado;
    private int posicionActual=0;
    private int limiteMaximo=0;

    private HashMap<Integer, Long> memo = new HashMap<>();

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

        etLimite = findViewById(R.id.et_limite);
        tvResultado = findViewById(R.id.tv_resultado);
        Button btnSet = findViewById(R.id.btn_set_limite);
        Button btnAvanzar = findViewById(R.id.btn_avanzar);
        Button btnRetroceder=findViewById(R.id.btn_retroceder);


            btnSet.setOnClickListener(v-> {
                        String valor = etLimite.getText().toString();
                        if (!valor.isEmpty()) {
                            limiteMaximo = Integer.parseInt(valor);
                            posicionActual = 1;
                            memo.clear();
                            mostrarFibonacci();
                            Toast.makeText(this, "Número límite " + limiteMaximo, Toast.LENGTH_SHORT).show();
                        }
                    });

            btnAvanzar.setOnClickListener(v ->{
              if(posicionActual < limiteMaximo){
                  posicionActual++;
                  mostrarFibonacci();
              }else{
                  Toast.makeText(this, "Límite alcanzado", Toast.LENGTH_SHORT).show();
              }
            });


            btnRetroceder.setOnClickListener(v->{
                if(posicionActual>1){
                    posicionActual--;
                    mostrarFibonacci();
                }
            });
    }
    private void mostrarFibonacci(){
            long resultado = calcularFibonacciRecursivo(posicionActual);
            tvResultado.setText(String.valueOf(resultado));
        }

        private long calcularFibonacciRecursivo(int n){
            if(n<= 2) return 1;

            if(memo.containsKey(n)) {
                return memo.get(n);

            }

                long valor = calcularFibonacciRecursivo(n-1)+calcularFibonacciRecursivo(n-2);

                memo.put(n, valor);
                    return valor;
                }
            }