package com.utc.apptrivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Autor: AVaca
 * Creado: 30-06-2021
 * Modificado: 30-06-2021
 * Descripcion: Pregunta 1 de la trivia
 * */
public class Pregunta1Activity extends AppCompatActivity {
    //    Definicion de atributos
    TextView txtConometroP1, txtPregunta1;
    CountDownTimer cronometro;
    BaseDatos bdd;
    int puntuacion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta1);
        // Mapear elementos
        txtConometroP1 = (TextView) findViewById(R.id.txtConometro);
        txtPregunta1 = (TextView) findViewById(R.id.txtPregunta);
        // Base de datos
        bdd = new BaseDatos(getApplicationContext());
        // Instanciacion del cronomentro
        newCronometro();
        cronometro.start();
        // Asignar pregunta traida de la base de datos
        asignarPregunta();
    }

    public void asignarPregunta() {
        try {
            Cursor pregunta = bdd.obtenerPregunta(1);
            if (pregunta != null) {
                String preg = pregunta.getString(1).toString();
                txtPregunta1.setText(preg);
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
                txtConometroP1.setText(String.valueOf(millisUntilFinished / 1000)); // Dividimos para mil ya que el tiempo entregado es en milisegundos
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
        puntuacion++;
        Intent seguirPregunta2 = new Intent(getApplicationContext(), Pregunta2Activity.class);
        seguirPregunta2.putExtra("puntuacion", "" + puntuacion);
        startActivity(seguirPregunta2);
        cronometro.cancel();
    }

    //Logica cuando el usuario responde mal
    public void respondeMal(View vista) {
        finish(); // Cerrar la actividad de la pregunta 1
        Intent seguirPregunta2 = new Intent(getApplicationContext(), Pregunta2Activity.class);
        seguirPregunta2.putExtra("puntuacion", "" + puntuacion);
        startActivity(seguirPregunta2);
        cronometro.cancel();
    }


}