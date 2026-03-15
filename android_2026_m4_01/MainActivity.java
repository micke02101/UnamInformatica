package com.example.android_2026_m4_01;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputOption;
    private Button btnEjecutar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50,150,50,50);

        inputOption = new EditText(this);
        inputOption.setHint("Ingrese opción (1-7)");
        inputOption.setInputType(InputType.TYPE_CLASS_NUMBER);

        btnEjecutar = new Button(this);
        btnEjecutar.setText("Ejecutar");

        layout.addView(inputOption);
        layout.addView(btnEjecutar);

        setContentView(layout);

        btnEjecutar.setOnClickListener(v -> {

            String opcion = inputOption.getText().toString();
            ejecutarMenu(opcion);

        });
    }

    private void ejecutarMenu(String opcion){

        String opcionLimpia = opcion.trim();

        if(opcionLimpia.isEmpty()){
            Toast.makeText(this,"Ingrese una opción",Toast.LENGTH_SHORT).show();
            return;
        }

        int numero;

        try{
            numero = Integer.parseInt(opcionLimpia);
        }catch(Exception e){
            Toast.makeText(this,"Debe ingresar un número",Toast.LENGTH_SHORT).show();
            return;
        }

        Ejecutar accion = null;

        switch(numero){

            case 1:
                accion = new onCreateAction();
                break;

            case 2:
                accion = new onStart();
                break;

            case 3:
                accion = new onResume();
                break;

            case 4:
                accion = new onPause();
                break;

            case 5:
                accion = new onStop();
                break;

            case 6:
                accion = new onRestart();
                break;

            case 7:
                accion = new onDestroy();
                break;

            default:
                Toast.makeText(this,"Opción inválida",Toast.LENGTH_SHORT).show();
                return;
        }

        if(accion != null){
            accion.ejecutar();
            inputOption.setText("");
        }
    }
}