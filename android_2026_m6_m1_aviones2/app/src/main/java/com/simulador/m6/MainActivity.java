package com.simulador.m6;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    int paso = 0;

    EstadoSimulacion estado;

    GridAdapter adapter;

    TextView txtPaso;
    TextView txtColisiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        // Ajuste de bordes del sistema
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

        txtPaso = findViewById(R.id.txtPaso);
        txtColisiones = findViewById(R.id.txtColisiones);

        RecyclerView grid = findViewById(R.id.grid);

        grid.setLayoutManager(new GridLayoutManager(this, 5));

        adapter = new GridAdapter();

        grid.setAdapter(adapter);

        // Estado inicial
        MotorSimulacion.escenarioActual
                = TipoEscenario.CHAINED_REACTION;

        cargarEscenario(
                TipoEscenario.ONE_COLLISION
        );

        actualizarUI();

        findViewById(R.id.btnOneCollision)
                .setOnClickListener(v -> {

                    cargarEscenario(
                            TipoEscenario.ONE_COLLISION
                    );
                });

        findViewById(R.id.btnCloseEncounter)
                .setOnClickListener(v -> {

                    cargarEscenario(
                            TipoEscenario.CLOSE_ENCOUNTER
                    );
                });

        findViewById(R.id.btnCatastrophy)
                .setOnClickListener(v -> {

                    cargarEscenario(
                            TipoEscenario.CATASTROPHY
                    );
                });

        findViewById(R.id.btnChain)
                .setOnClickListener(v -> {

                    cargarEscenario(
                            TipoEscenario.CHAINED_REACTION
                    );
                });

        // Botón siguiente
        findViewById(R.id.btnNext).setOnClickListener(v -> {

            paso++;

            estado = MotorSimulacion.siguiente(paso);

            actualizarUI();
        });

        // Botón anterior
        findViewById(R.id.btnPrev).setOnClickListener(v -> {

            if (paso > 0) {

                paso--;

                estado = MotorSimulacion.anterior(paso + 1);

                actualizarUI();
            }
        });
    }

    private void actualizarUI() {

        txtPaso.setText("Paso: " + paso);

        txtColisiones.setText(
                "Colisiones: " + estado.getNumeroColisiones()
        );

        adapter.setEstado(estado);
    }

    private void cargarEscenario(
            TipoEscenario tipo
    ) {

        paso = 0;

        MotorSimulacion.escenarioActual = tipo;

        estado = MotorSimulacion.inicializar();

        actualizarUI();
    }
}