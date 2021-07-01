package com.utc.apptrivia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {
    //definiendo el nombre de la bdd
    private static final String nombreBdd = "bdd_quizz";
    // definiendo la version de la bdd
    private static final int versionBdd = 1;
    // TABLAS
    private static final String tablaPreguntas = "CREATE TABLE pregunta(" +
            "id_pre integer PRIMARY KEY AUTOINCREMENT ," +
            "texto_pre text)";
    private static final String pregunta1 = "INSERT INTO pregunta (texto_pre) " +
            "VALUES ('El código espagueti es una mala practica que se debe evitar?');";
    private static final String pregunta2 = "INSERT INTO pregunta (texto_pre) " +
            "VALUES ('Una variable puede ser utilizada para almacenar un comentario del programador?');";
    private static final String pregunta3 = "INSERT INTO pregunta (texto_pre) " +
            "VALUES ('Una variable tipo String es un contenedor que puede almacenar un valor entero?');";
    private static final String pregunta4 = "INSERT INTO pregunta (texto_pre) " +
            "VALUES ('Las variables de tipo vector se utiliza para almacenar un arreglo o conjutno de datdos?');";
    private static final String pregunta5 = "INSERT INTO pregunta (texto_pre) " +
            "VALUES ('Se puede realizar una suma si uno de sus elementos es de tipo string y el otro de tipo integer? ');";
    private static final String pregunta6 = "INSERT INTO pregunta (texto_pre) " +
            "VALUES ('La condicional IF funciona si deseamos repetir una instrucción o fracción de código por n numero de veces?');";
    private static final String pregunta7 = "INSERT INTO pregunta (texto_pre) " +
            "VALUES ('El ciclo FOR recorre su índice desde el número 0?');";
    private static final String pregunta8 = "INSERT INTO pregunta (texto_pre) " +
            "VALUES ('Una función de tipo void regresa un valor de tipo String?');";
    private static final String pregunta9 = "INSERT INTO pregunta (texto_pre) " +
            "VALUES ('Objeto es a clase como, Quito es a Capital?');";
    private static final String pregunta10 = "INSERT INTO pregunta (texto_pre) " +
            "VALUES ('Se puede acceder a los métodos de un objeto sin haber instanciado de una clase previamente?');";

    //CONSTRUCTOR
    public BaseDatos(Context contexto) {
        super(contexto, nombreBdd, null, versionBdd);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tablaPreguntas);
        db.execSQL(pregunta1);
        db.execSQL(pregunta2);
        db.execSQL(pregunta3);
        db.execSQL(pregunta4);
        db.execSQL(pregunta5);
        db.execSQL(pregunta6);
        db.execSQL(pregunta7);
        db.execSQL(pregunta8);
        db.execSQL(pregunta9);
        db.execSQL(pregunta10);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS pregunta");
        db.execSQL(tablaPreguntas);
    }

    // Metodos
    public Cursor obtenerPregunta(int numPregunta) {
        SQLiteDatabase miBDD = getReadableDatabase();
        String sql = "SELECT * FROM pregunta " +
                "WHERE id_pre = " + numPregunta + "; ";
        Cursor pregunta = miBDD.rawQuery(sql, null);
        if (pregunta.moveToFirst()) {
            return pregunta;
        } else {
            return null;
        }
    }
}
