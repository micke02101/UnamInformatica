package com.simulador.m6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridAdapter
        extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private int filas = 10;

    private int columnas = 10;

    private EstadoSimulacion estado;

    public void setEstado(
            EstadoSimulacion estado
    ) {

        this.estado = estado;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return filas * columnas;
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position
    ) {

        int x = position % columnas;

        int y = position / columnas;

        holder.img.setRotation(0);

        holder.img.setImageResource(
                R.drawable.celda_vacia
        );

        // aviones
        if (estado != null) {

            for (EntidadAvion avion : estado.aviones) {

                if (avion.x == x && avion.y == y) {

                    holder.img.setImageResource(R.drawable.avion);

                    switch (avion.direccion) {

                        case NORTE:

                            holder.img.setRotation(270);

                            break;

                        case SUR:

                            holder.img.setRotation(90);

                            break;

                        case ESTE:

                            holder.img.setRotation(0);

                            break;

                        case OESTE:

                            holder.img.setRotation(180);

                            break;
                    }
                }
            }

            // colisiones
            for (EntidadColision colision
                    : estado.colisiones) {

                if (colision.x == x
                        && colision.y == y) {

                    holder.img.setRotation(0);
                    holder.img.setImageResource(
                            R.drawable.colision
                    );
                }
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(
                        R.layout.celda_item,
                        parent,
                        false
                );

        return new ViewHolder(view);
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        ImageView img;

        public ViewHolder(
                @NonNull View itemView
        ) {

            super(itemView);

            img = itemView.findViewById(
                    R.id.imgCelda
            );
        }
    }
}