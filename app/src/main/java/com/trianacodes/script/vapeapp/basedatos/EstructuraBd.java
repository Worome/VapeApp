package com.trianacodes.script.vapeapp.basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class EstructuraBd {

    /* Creo las constantes para definir el nombre de las tablas, de sus campos */
    public static final String TABLA_AROMA = "Aromas";
    public static final String AROMA_ID = "Id";
    public static final String AROMA_NOMBRE = "Nombre";
    public static final String AROMA_MARCA = "Marca";
    public static final String AROMA_TIPO = "Tipo";
    public static final String AROMA_DESDE_PORCENTAJE = "Porcentaje";
    public static final String AROMA_HASTA_PORCENTAJE = "hPorcentaje";
    public static final String AROMA_MIN_MACERACION = "MinMaceracion";
    public static final String AROMA_MAX_MACERACION = "MaxMaceracion";
    public static final String AROMA_OBSERVACIONES = "Observaciones";
    public static final String AROMA_VALORACION = "Valoracion";
    public static final String AROMA_IMAGEN = "Imagen";

    // Constantes que contienen textos generales
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    /* Constantes que contienen las sentencias de creación de las tablas (Cada vez que añada, borre
     o modificar un campo de una tabla debo añadir aquí el cambio como he hecho añadiendo el campo
    AROMA_OBSERVACIONES. Esto es para que si la base de datos se crea nueva, vaya con todas las
    modificaciones hechas)*/
    public static final String SQL_CREATE_AROMAS = "CREATE TABLE " + TABLA_AROMA +
            " (" + AROMA_ID + " INTEGER PRIMARY KEY autoincrement, " + AROMA_NOMBRE + TEXT_TYPE +
            COMMA_SEP + AROMA_MARCA + TEXT_TYPE + COMMA_SEP + AROMA_TIPO + TEXT_TYPE +
            COMMA_SEP + AROMA_DESDE_PORCENTAJE + INTEGER_TYPE + COMMA_SEP + AROMA_HASTA_PORCENTAJE +
            INTEGER_TYPE  + COMMA_SEP + AROMA_MIN_MACERACION + INTEGER_TYPE +
            COMMA_SEP + AROMA_MAX_MACERACION + INTEGER_TYPE + COMMA_SEP + AROMA_OBSERVACIONES + TEXT_TYPE +
            COMMA_SEP + AROMA_VALORACION + INTEGER_TYPE + COMMA_SEP + AROMA_IMAGEN + INTEGER_TYPE + " )";

    // Constantes que contienen las sentencias de eliminación de tablas
    public static final String SQL_DELETE_AROMAS = "DROP TABLE IF EXISTS " + TABLA_AROMA;

    /* Las actualizaciones de bases de datos siempre deben ir como esta instrucción de abajo */
    public static final String ACTUALIZA_BASEDATOS_VERSION_2 = "ALTER TABLE " + TABLA_AROMA + " ADD COLUMN " +
            AROMA_OBSERVACIONES + " TEXT";

    public static final String ACTUALIZA_BASEDATOS_VERSION_3 = "ALTER TABLE " + TABLA_AROMA + " ADD COLUMN " +
            AROMA_HASTA_PORCENTAJE + INTEGER_TYPE + COMMA_SEP +
            AROMA_VALORACION + INTEGER_TYPE + COMMA_SEP + AROMA_IMAGEN + INTEGER_TYPE;


    // Creo el constructor
    private EstructuraBd(){

    }

}
