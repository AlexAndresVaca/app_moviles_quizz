package com.utc.apptrivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/*
 * Autor: AVaca
 * Creado: 30-06-2021
 * Modificado: 01-07-2021
 * Descripcion: App para simular una trivia orientada al "Desarrollo de aplicaciones móviles"
 * */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirPregunta1(View vista) {
        Intent ventanaPregunta1 = new Intent(getApplicationContext(), Pregunta1Activity.class);
        startActivity(ventanaPregunta1);
    }

    public void finalizar(View vista) {
        finish();
    }
}