package com.example.android_2026_m6_03;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button btnCargar;
    TextView txtDatos;

    String URL = "https://jsonplaceholder.typicode.com/users/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
            );

            return insets;
        });

        btnCargar = findViewById(R.id.btnCargar);
        txtDatos = findViewById(R.id.txtDatos);

        btnCargar.setOnClickListener(v -> consumirAPI());
    }

    private void consumirAPI() {

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,

                response -> {

                    try {

                        String nombre = response.getString("name");
                        String usuario = response.getString("username");
                        String email = response.getString("email");

                        txtDatos.setText(
                                "Nombre: " + nombre +
                                        "\n\nUsuario: " + usuario +
                                        "\n\nEmail: " + email
                        );

                    } catch (Exception e) {

                        txtDatos.setText("Error al procesar JSON");

                    }

                },

                error -> {

                    txtDatos.setText(
                            "Error de conexión:\n\n" + error.toString()
                    );

                }
        );

        queue.add(request);
    }
}