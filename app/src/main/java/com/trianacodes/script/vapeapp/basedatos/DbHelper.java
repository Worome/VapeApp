package com.trianacodes.script.vapeapp.basedatos;

/* Creo esta clase para añadir el código necesario para crear la base de datos si ésta aún no
   existiese o para actualizarla*/

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
/*Importo las clase Campos_Aromas que está en la clase EstructuraTablas y que lo que hace es
  generar el ID */

public class DbHelper extends SQLiteOpenHelper{

    /* Creo dos constantes. Cuando haya actualziaciones en alguna tabla de la base de datos, tengo
    * que sumar 1 al valore de la constante DATABASE_VERSION.*/

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "Db_Vapeapp.sqlite";
    private final Context contexto;

    public DbHelper(Context contextoActual){

        super(contextoActual,DATABASE_NAME,null,DATABASE_VERSION);
        this.contexto = contextoActual;

    }

    // Aquí doy las instrucciones para crear las tablas
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(EstructuraBd.SQL_CREATE_AROMAS);
        // Aquí debe seguir creando el resto de las tablas

    }

    // Aquí doy las instrucciones para actualizar la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionActual, int versionNueva) {

       /* if (versionActual == 1 && versionNueva == 2){

            db.execSQL(EstructuraBd.ACTUALIZA_BASEDATOS_VERSION_2);

        }*/
       /* Cuando hay una nueva versión de la base de datos, siempre hay que preguntar si la
       * version Actual es menor que la versión nueva; y si es así, hay que ejecutar la sentencia
        * de actualziación. Por cada nueva versión hay que preguntar con nuevos if y ejecutar la
        * actualización dejando los antiguos. No se puede borrar ninguno. */
       if (versionActual < 2){

           db.execSQL(EstructuraBd.ACTUALIZA_BASEDATOS_VERSION_2);

       }

        if (versionActual < 3){

            db.execSQL(EstructuraBd.ACTUALIZA_BASEDATOS_VERSION_3);

        }

        //db.execSQL(EstructuraBd.SQL_DELETE_AROMAS);
        //onCreate(db);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){

                db.setForeignKeyConstraintsEnabled(true);

            } else {

                db.execSQL("PRAGMA foreing_keys=ON");

            }

        }

    }

}
