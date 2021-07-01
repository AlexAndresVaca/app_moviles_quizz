package com.utc.apptrivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Pregunta5Activity extends AppCompatActivity {
    //    Definicion de atributos
    TextView txtConometroP5, txtPregunta5;
    CountDownTimer cronometro;
    BaseDatos bdd;
    int puntuacion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta5);
        // Mapear elementos
        txtConometroP5 = (TextView) findViewById(R.id.txtConometro);
        txtPregunta5 = (TextView) findViewById(R.id.txtPregunta);
        // Instanciacion del cronomentro
        newCronometro();
        cronometro.start();
        // Base de datos
        bdd = new BaseDatos(getApplicationContext());
// Asignar pregunta traida de la base de datos
        asignarPregunta();
        // Traer valores de puntuacion
        Bundle parametrosExtra = getIntent().getExtras(); //Capturando los parametros que se han pasado ha esta actividad
        if (parametrosExtra != null) {
            try {
                //Intente realizar estas lineas de codigo
                String puntuacionStr = parametrosExtra.getString("puntuacion");
                puntuacion = Integer.parseInt(puntuacionStr);
                Toast.makeText(getApplicationContext(), "Puntuación: " + puntuacion, Toast.LENGTH_LONG).show();

            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Puntuación no encontrada, " + ex.toString(), Toast.LENGTH_LONG).show();

            }
        }
    }

    public void asignarPregunta() {
        try {
            Cursor pregunta = bdd.obtenerPregunta(5);
            if (pregunta != null) {
                String preg = pregunta.getString(1).toString();
                txtPregunta5.setText(preg);
            }
        } catch (Exception ex) {
            Toast.makeText(this, "No hemos encontrado la pregunta... " + ex, Toast.LENGTH_SHORT).show();
        }
    }

    private void newCronometro() {
        // asignar valores
        int tiempoRestante = 10 * 1000; // Tiempo en milisegundos
        int intervalo = 1000;
        // crear cronometro
        cronometro = new CountDownTimer(tiempoRestante, intervalo) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Presentar el tiempo restante
                txtConometroP5.setText(String.valueOf(millisUntilFinished / 1000)); // Dividimos para mil ya que el tiempo entregado es en milisegundos
            }

            @Override
            public void onFinish() {
//                Toast.makeText(Pregunta1Activity.this, "Tiempo terminado!", Toast.LENGTH_LONG).show();
                respondeMal(null);
            }
        };
    }

    //Logica cuando el usuario responde bien
    public void respondeBien(View vista) {
        finish(); // Cerrar la actividad de la pregunta 1
        Intent seguirPregunta6 = new Intent(getApplicationContext(), Pregunta6Activity.class);
        puntuacion++;
        seguirPregunta6.putExtra("puntuacion", "" + puntuacion);
        startActivity(seguirPregunta6);
        cronometro.cancel();
    }

    //Logica cuando el usuario responde mal
    public void respondeMal(View vista) {
        finish(); // Cerrar la actividad de la pregunta 1
        Intent seguirPregunta6 = new Intent(getApplicationContext(), Pregunta6Activity.class);
        seguirPregunta6.putExtra("puntuacion", "" + puntuacion);
        startActivity(seguirPregunta6);
        cronometro.cancel();
    }

}