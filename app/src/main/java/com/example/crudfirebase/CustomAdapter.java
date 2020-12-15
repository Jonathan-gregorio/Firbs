package com.example.crudfirebase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {

    ListActivityPerson listActivityPerson;
    List<PersonModel> mPersonModelList;

    public CustomAdapter(ListActivityPerson listActivity, List<PersonModel> personModelList) {
        this.listActivityPerson = listActivity;
        this.mPersonModelList = personModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.model, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String nombre = mPersonModelList.get(position).getNombre();
                String descripcion = mPersonModelList.get(position).getApaterno();
                String categoria = mPersonModelList.get(position).getAmaterno();
                Toast.makeText(listActivityPerson, nombre + " " + descripcion + " " + categoria, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(listActivityPerson);
                String[] options = {"Actualizar datos", "Eliminar"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            String id = mPersonModelList.get(position).getId();
                            String marca = mPersonModelList.get(position).getNombre();
                            String modelo = mPersonModelList.get(position).getApaterno();
                            String precio = mPersonModelList.get(position).getAmaterno();
                            String memoriaRam = mPersonModelList.get(position).getSexo();
                            String procesador = mPersonModelList.get(position).getDireccion();
                            String sistemaoperativo = mPersonModelList.get(position).getFacebook();
                            String lanzamiento = mPersonModelList.get(position).getInstagram();

                            Intent actualizarDatos = new Intent(listActivityPerson, MainActivity.class);
                            actualizarDatos.putExtra("updateId", id);
                            actualizarDatos.putExtra("updatemarca", marca);
                            actualizarDatos.putExtra("updatemodelo", modelo);
                            actualizarDatos.putExtra("updateprecio", precio);
                            actualizarDatos.putExtra("updatememoriaRam", memoriaRam);
                            actualizarDatos.putExtra("updateprocesador", procesador);
                            actualizarDatos.putExtra("updatesistemaoperativo", sistemaoperativo);
                            actualizarDatos.putExtra("updatelanzamiento", lanzamiento);

                            listActivityPerson.startActivity(actualizarDatos);
                            // String id, String nombre, String apaterno, String amaterno, String sexo, String direccion, String facebook, String instagram
                        }

                        if (which == 1) {
                            listActivityPerson.eliminarRegistro(position);
                        }
                    }
                }).create().show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvNombre.setText(
                mPersonModelList.get(i).getNombre()
                        + " " + mPersonModelList.get(i).getApaterno()
                        + " " + mPersonModelList.get(i).getAmaterno()
        );
    }

    @Override
    public int getItemCount() {
        return mPersonModelList.size();
    }
}
