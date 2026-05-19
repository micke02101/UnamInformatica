package com.simulador.m6;

import java.util.ArrayList;
import java.util.HashMap;

public class MotorSimulacion {

    private static HashMap<Integer, EstadoSimulacion> memoria
            = new HashMap<>();

    public static TipoEscenario escenarioActual
            = TipoEscenario.ONE_COLLISION;

    public static EstadoSimulacion inicializar() {

        memoria.clear();

        ArrayList<EntidadAvion> aviones
                = new ArrayList<>();

        switch (escenarioActual) {


            case ONE_COLLISION:

                aviones.add(
                        new EntidadAvion(
                                1,
                                2,
                                DireccionMovimiento.ESTE
                        )
                );

                aviones.add(
                        new EntidadAvion(
                                3,
                                2,
                                DireccionMovimiento.OESTE
                        )
                );

                break;


            case CLOSE_ENCOUNTER:

                aviones.add(
                        new EntidadAvion(
                                3,
                                1,
                                DireccionMovimiento.SUR
                        )
                );

                aviones.add(
                        new EntidadAvion(
                                2,
                                3,
                                DireccionMovimiento.ESTE
                        )
                );

                break;


            case CATASTROPHY:

                for (int i = 1; i <= 5; i++) {

                    aviones.add(
                            new EntidadAvion(
                                    i,
                                    1,
                                    DireccionMovimiento.SUR
                            )
                    );

                    aviones.add(
                            new EntidadAvion(
                                    i,
                                    5,
                                    DireccionMovimiento.NORTE
                            )
                    );
                }

                break;


            case CHAINED_REACTION:

                aviones.add(
                        new EntidadAvion(
                                0,
                                4,
                                DireccionMovimiento.ESTE
                        )
                );

                aviones.add(
                        new EntidadAvion(
                                1,
                                4,
                                DireccionMovimiento.ESTE
                        )
                );

                aviones.add(
                        new EntidadAvion(
                                2,
                                4,
                                DireccionMovimiento.ESTE
                        )
                );

                aviones.add(
                        new EntidadAvion(
                                4,
                                0,
                                DireccionMovimiento.SUR
                        )
                );

                aviones.add(
                        new EntidadAvion(
                                4,
                                1,
                                DireccionMovimiento.SUR
                        )
                );

                aviones.add(
                        new EntidadAvion(
                                4,
                                2,
                                DireccionMovimiento.SUR
                        )
                );

                break;
        }

        EstadoSimulacion estadoInicial =
                new EstadoSimulacion(
                        0,
                        0,
                        aviones,
                        new ArrayList<>()
                );

        memoria.put(0, estadoInicial);

        return estadoInicial;
    }

    public static EstadoSimulacion siguiente(
            int paso
    ) {

        // evitar recalcular
        if (memoria.containsKey(paso)) {

            return memoria.get(paso);
        }

        EstadoSimulacion anterior
                = memoria.get(paso - 1);

        // mover aviones
        ArrayList<EntidadAvion> movidos
                = new ArrayList<>();

        for (EntidadAvion avion : anterior.aviones) {

            EntidadAvion copia
                    = new EntidadAvion(
                    avion.x,
                    avion.y,
                    avion.direccion
            );

            copia.mover();

            movidos.add(copia);
        }

        // detectar colisiones
        ArrayList<EntidadColision> colisiones
                = new ArrayList<>();

        boolean[] destruido
                = new boolean[movidos.size()];

        for (int i = 0; i < movidos.size(); i++) {

            for (int j = i + 1;
                 j < movidos.size();
                 j++) {

                EntidadAvion a = movidos.get(i);

                EntidadAvion b = movidos.get(j);

                if (a.x == b.x
                        && a.y == b.y) {

                    colisiones.add(
                            new EntidadColision(
                                    a.x,
                                    a.y
                            )
                    );

                    destruido[i] = true;

                    destruido[j] = true;
                }
            }
        }

        // eliminar destruidos
        ArrayList<EntidadAvion> sobrevivientes
                = new ArrayList<>();

        for (int i = 0; i < movidos.size(); i++) {

            if (!destruido[i]) {

                sobrevivientes.add(
                        movidos.get(i)
                );
            }
        }

        int total =
                anterior.totalColisiones
                        + colisiones.size();

        EstadoSimulacion nuevoEstado =
                new EstadoSimulacion(
                        paso,
                        total,
                        sobrevivientes,
                        colisiones
                );

        memoria.put(paso, nuevoEstado);

        return nuevoEstado;
    }

    public static EstadoSimulacion anterior(
            int paso
    ) {

        return memoria.get(paso - 1);
    }
}