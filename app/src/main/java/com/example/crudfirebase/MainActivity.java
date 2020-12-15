package com.example.crudfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    EditText etNombre, etAPaterno, etAMaterno, etSexo, etDireccion, etFacebook, etInstagram;
    FloatingActionButton fabGuardar, fabListar;

    ProgressDialog progressDialog;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String updateId, updateNombre, updateApaterno, updateAmaterno, updateSexo, updateDireccion, updateFacebook, updateInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etAPaterno = findViewById(R.id.etAPaterno);
        etAMaterno = findViewById(R.id.etAMaterno);
        etSexo = findViewById(R.id.etSexo);
        etDireccion = findViewById(R.id.etDireccion);
        etFacebook = findViewById(R.id.etFacebook);
        etInstagram = findViewById(R.id.etInstagram);

        fabGuardar = findViewById(R.id.fabGuardar);
        fabListar = findViewById(R.id.fabListar);

        progressDialog = new ProgressDialog(this);

        db = FirebaseFirestore.getInstance();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar registro");


        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            actionBar.setTitle("Actualizar Datos");

            updateId = bundle.getString("updateId");
            updateNombre = bundle.getString("updateNombre");
            updateApaterno = bundle.getString("updateApaterno");
            updateAmaterno = bundle.getString("updateAmaterno");
            updateSexo = bundle.getString("updateSexo");
            updateDireccion = bundle.getString("updateDireccion");
            updateFacebook = bundle.getString("updateFacebook");
            updateInstagram = bundle.getString("updateInstagram");

            etNombre.setText(updateNombre);
            etAPaterno.setText(updateApaterno);
            etAMaterno.setText(updateAmaterno);
            etSexo.setText(updateSexo);
            etDireccion.setText(updateDireccion);
            etFacebook.setText(updateFacebook);
            etInstagram.setText(updateInstagram);

        } else {
            actionBar.setTitle("Agregar");
        }


        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle1 = getIntent().getExtras();
                if (bundle1 != null) {
                    String id = updateId;
                    String marca = etNombre.getText().toString().trim();
                    String modelo = etAPaterno.getText().toString().trim();
                    String precio = etAMaterno.getText().toString().trim();
                    String memoriaRam = etSexo.getText().toString().trim();
                    String procesador = etDireccion.getText().toString().trim();
                    String sistemaoperativo = etFacebook.getText().toString().trim();
                    String lanzamiento = etInstagram.getText().toString().trim();

                    actualizarDatos(id, marca, modelo, precio, memoriaRam, procesador, sistemaoperativo, lanzamiento);

                } else {
                    String marca = etNombre.getText().toString().trim();
                    String modelo = etAPaterno.getText().toString().trim();
                    String precio = etAMaterno.getText().toString().trim();
                    String memoriaRam = etSexo.getText().toString().trim();
                    String procesador = etDireccion.getText().toString().trim();
                    String sistemaoperativo = etFacebook.getText().toString().trim();
                    String lanzamiento = etInstagram.getText().toString().trim();

                    cargarDatos(marca, modelo, precio, memoriaRam, procesador, sistemaoperativo, lanzamiento);
                }
            }
        });


        fabListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListActivityPerson.class));
                finish();
            }
        });

    }


    private void cargarDatos(String marca, String modelo, String precio, String memoriaRam, String procesador, String sistemaoperativo, String lanzamiento) {
        progressDialog.setTitle("Agregar datos");
        progressDialog.show();
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("marca", marca);
        doc.put("modelo", modelo);
        doc.put("precio", precio);
        doc.put("memoriaRam", memoriaRam);
        doc.put("procesador", procesador);
        doc.put("sistemaoperativo", sistemaoperativo);
        doc.put("lanzamiento", lanzamiento);



        db.collection("Documents").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Datos almacenados con éxito...", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Ha ocurrido un error..." + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void actualizarDatos(String id, String marca, String modelo, String precio, String memoriaRam, String procesador, String sistemaoperativo, String lanzamiento) {
        progressDialog.setTitle("Actualizando datos a Firebase");
        progressDialog.show();


        db.collection("Documents")
                .document(id).update(
                "marca", marca,
                "modelo", modelo,
                "precio", precio,
                "memoriaRam", memoriaRam,
                "procesador", procesador,
                "sistemaoperativo", sistemaoperativo,
                "lanzamiento", lanzamiento
        )
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Actualización exitosa...", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Ha ocurrido un error..." + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}