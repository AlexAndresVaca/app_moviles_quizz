package com.utc.apptrivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultadosActivity extends AppCompatActivity {
    TextView txtTipoResultado, txtResultadoTotal;
    BaseDatos bd;
    int puntuacion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        // Mapeo
        txtTipoResultado = (TextView) findViewById(R.id.txtTipoResultado);
        txtResultadoTotal = (TextView) findViewById(R.id.txtPuntuacionFinal);
        // Traer valores de puntuacion
        Bundle parametrosExtra = getIntent().getExtras(); //Capturando los parametros que se han pasado ha esta actividad
        if (parametrosExtra != null) {
            try {
                //Intente realizar estas lineas de codigo
                String puntuacionStr = parametrosExtra.getString("puntuacion");
                puntuacion = Integer.parseInt(puntuacionStr);
                Toast.makeText(getApplicationContext(), "Puntuación: " + puntuacion, Toast.LENGTH_LONG).show();
                String semaforo = "";
                if (puntuacion == 0) {
                    semaforo = "Pesimo, te urge estudiar más!";
                } else if (puntuacion > 0 && puntuacion <= 5) {
                    semaforo = "Regular, dedica más tiempo a estudiar !";
                } else if (puntuacion > 5 && puntuacion <= 7) {
                    semaforo = "Normal, aún puedes mejorar.";
                } else {
                    semaforo = "Excelente!";

                }
                txtTipoResultado.setText(semaforo);
                txtResultadoTotal.setText("" + puntuacion + "pts.");

            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Puntuación no encontrada, " + ex.toString(), Toast.LENGTH_LONG).show();

            }
        }
    }

    public void volverMenuPrincipal(View vista) {
        finish();
        Intent volverMenu = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(volverMenu);
    }

    public void reintentar(View vista) {
        finish();
    }
}