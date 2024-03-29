package com.example.milagrosoapp;

import static com.example.milagrosoapp.DatabaseHelper.COL1;
import static com.example.milagrosoapp.DatabaseHelper.COL10;
import static com.example.milagrosoapp.DatabaseHelper.COL11;
import static com.example.milagrosoapp.DatabaseHelper.COL12;
import static com.example.milagrosoapp.DatabaseHelper.COL13;
import static com.example.milagrosoapp.DatabaseHelper.COL14;
import static com.example.milagrosoapp.DatabaseHelper.COL15;
import static com.example.milagrosoapp.DatabaseHelper.COL16;
import static com.example.milagrosoapp.DatabaseHelper.TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputLayout;

public class signosInsuficiencia extends AppCompatActivity {
    private DatabaseHelper myDbs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_signos);
        myDbs = new DatabaseHelper(signosInsuficiencia.this);
        SQLiteDatabase db = myDbs.getReadableDatabase();
        Intent intent = getIntent();
        int valorRecibido = intent.getIntExtra("idUser", 0);

        TextInputLayout  frecuenciaLayout= findViewById(R.id.incardiaca1);
        EditText  editFrecuencia= frecuenciaLayout.getEditText();

        TextInputLayout temperaturaLayout = findViewById(R.id.temperatura1);
        EditText editTemperatura = temperaturaLayout.getEditText();

        TextInputLayout sintomasLayout = findViewById(R.id.sintomas1);
        EditText editSintomas = sintomasLayout.getEditText();

        TextInputLayout actividadLayout = findViewById(R.id.actividad_fisica1);
        EditText editActividad = actividadLayout.getEditText();

        TextInputLayout medicamentosLayout = findViewById(R.id.medicamentos1);
        EditText editMedicamentos = medicamentosLayout.getEditText();

        Button signosButton = (Button) findViewById(R.id.button_registrar_signos);


        signosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = db.rawQuery("SELECT * FROM Registros_RapiCoop", null);;

                String frecuenciaCardiaca = editFrecuencia.getText().toString();
                String temperatura = editTemperatura.getText().toString();
                String sintomas = editSintomas.getText().toString();
                String actividadFisica = editActividad.getText().toString();
                String medicamentos = editMedicamentos.getText().toString();
                ContentValues values = new ContentValues();

                values.put(COL12, frecuenciaCardiaca);
                values.put(COL13, temperatura);
                values.put(COL14, sintomas);
                values.put(COL15, actividadFisica);
                values.put(COL16, medicamentos);
                String f= valorRecibido+"";
                String selection = COL1 + " = ?";
                String[] selectionArgs = { f };

                db.update(
                        TABLE_NAME,
                        values,
                        selection,
                        selectionArgs
                );






            }
        });

    }

    public void buttonVerMapa(View view) {
        Intent intent = new Intent(this, Ubicacion.class);
        startActivity(intent);
    }

    public void button_registrar_signos(View view) {


    }
}
