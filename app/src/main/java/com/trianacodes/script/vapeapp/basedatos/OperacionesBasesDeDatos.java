package com.trianacodes.script.vapeapp.basedatos;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;


import com.trianacodes.script.vapeapp.entidades.Aromas;

/* Creo un algoritmo con el patrón Singleton. Esto significa que creo un constructor principal como
*  privado, defino un método estático de la clase y un método estático que permita la obtención del
*  único miembro. */

public final class OperacionesBasesDeDatos {

    private static DbHelper baseDatos;
    private static OperacionesBasesDeDatos instancia = new OperacionesBasesDeDatos();
    // Creo una constante que contiene el nombre de la tabla sobre la que se va a hacer la consulta
    //private static final String CONSULTA_AROMAS = "Aromas";

    /* Esto es un ejemplo más complicado que el de la línea de arriba en la que se indican varias
       tablas unidas por JOINs */
    /*private static final String CABECERA_PEDIDO_JOIN_CLIENTE_Y_FORMA_PAGO = "cabecera_pedido " +
            "INNER JOIN cliente " +
            "ON cabecera_pedido.id_cliente = cliente.id " +
            "INNER JOIN forma_pago " +
            "ON cabecera_pedido.id_forma_pago = forma_pago.id";*/

    /* Este variable de tipo array se usa dentro de la instrucción builder.query, que es el que
       obliga a que dicha variable sea un array. En ella se establece el contenido del SELECT de
       la consulta, es decir los campos que se quieren mostrar en una consulta.*/
    private final String[] resultado = new String[]{
            EstructuraBd.TABLA_AROMA + "." + EstructuraBd.AROMA_ID, EstructuraBd.AROMA_NOMBRE,
            EstructuraBd.AROMA_MARCA, EstructuraBd.AROMA_TIPO, EstructuraBd.AROMA_DESDE_PORCENTAJE,
            EstructuraBd.AROMA_HASTA_PORCENTAJE,EstructuraBd.AROMA_MIN_MACERACION, EstructuraBd.AROMA_MAX_MACERACION,
            EstructuraBd.AROMA_OBSERVACIONES, EstructuraBd.AROMA_VALORACION, EstructuraBd.AROMA_IMAGEN};


    private OperacionesBasesDeDatos(){

    }

    public static OperacionesBasesDeDatos obtenerInstancia(Context contexto){

        if (baseDatos == null){

            baseDatos = new DbHelper(contexto);

        }

        return instancia;

    }

    public SQLiteDatabase getDb(){

        return baseDatos.getWritableDatabase();

    }

    // Obtengo todos los registros de la tabla aromas
    public Cursor obtenerAromas(){

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        /* Se usa la clase SQLiteQueryBuilder para hacer consultas complejas que puedan implicar
           joins. Si la consulta afecta a una sola tabla se puede usar el método rawQuery().*/
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        // El método setTables establece sobre qué tabla se va a realizar la consulta.
        builder.setTables(EstructuraBd.TABLA_AROMA);
        return builder.query(db, resultado,null,null,null, null, null);

    }

    /* Si se quiere obtener sólo un registro dependiendo del valor de alguno de sus campo
       (SELECT... WHERE) se haría de la siguiente forma: */
    public Cursor obtenerAromaPorId(String id){

        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String where = String.format("%s=?", EstructuraBd.AROMA_ID);
        String[] argWhere = {id};
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(EstructuraBd.TABLA_AROMA);
        return builder.query(db,resultado,where,argWhere,null,null,null);

    }

    /* Para insertar registros en una tabla se haría como viene abajo. El parámetro que recibe el
       método insertarAroma es un objeto (aroma) de la clase Aromas*/
    public String insertarAroma(Aromas aroma){

        SQLiteDatabase db = baseDatos.getWritableDatabase();
        // Genero el Id del registro que es a su vez, la clave primaria
        //String idAroma = EstructuraTablas.Campos_Aromas.generarIdAroma();
        /* Creo el contenedor donde se almacenarán los valores de cada campo del regitro*/
        ContentValues valores = new ContentValues();
        /* Almaceno en cada campo del registro (Campos_Aromas.NOMBRE_AROMA, por ejemplo), el
        *  valor establecido por el usuario (aroma.Nombre) */
        //valores.put(EstructuraTablas.Campos_Aromas.ID, idAroma);
        valores.put(EstructuraBd.AROMA_NOMBRE, aroma.getNombre());
        valores.put(EstructuraBd.AROMA_MARCA, aroma.getMarca());
        valores.put(EstructuraBd.AROMA_TIPO, aroma.getTipo());
        valores.put(EstructuraBd.AROMA_DESDE_PORCENTAJE, aroma.getDesdePorcentaje());
        valores.put(EstructuraBd.AROMA_HASTA_PORCENTAJE, aroma.getHastaPorcentaje());
        valores.put(EstructuraBd.AROMA_MIN_MACERACION, aroma.getTiempoMinimoMaceracion());
        valores.put(EstructuraBd.AROMA_MAX_MACERACION, aroma.getTiempoMaximoMaceracion());
        valores.put(EstructuraBd.AROMA_OBSERVACIONES,aroma.getObservaciones());
        valores.put(EstructuraBd.AROMA_VALORACION,aroma.getValoracion());
        valores.put(EstructuraBd.AROMA_IMAGEN,aroma.getImagen());
        //Inserto el registro
        db.insertOrThrow(EstructuraBd.TABLA_AROMA,null,valores);
        // Devuelvo el id generado;
        return EstructuraBd.AROMA_ID;

    }

    /* Para actualizar un registro:*/
    public boolean actualizarAroma(Aromas aroma){

        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(EstructuraBd.AROMA_NOMBRE, aroma.getNombre());
        valores.put(EstructuraBd.AROMA_MARCA, aroma.getMarca());
        valores.put(EstructuraBd.AROMA_TIPO, aroma.getTipo());
        valores.put(EstructuraBd.AROMA_DESDE_PORCENTAJE, aroma.getDesdePorcentaje());
        valores.put(EstructuraBd.AROMA_HASTA_PORCENTAJE, aroma.getHastaPorcentaje());
        valores.put(EstructuraBd.AROMA_MIN_MACERACION, aroma.getTiempoMaximoMaceracion());
        valores.put(EstructuraBd.AROMA_MAX_MACERACION, aroma.getTiempoMaximoMaceracion());
        valores.put(EstructuraBd.AROMA_OBSERVACIONES,aroma.getObservaciones());
        valores.put(EstructuraBd.AROMA_VALORACION,aroma.getValoracion());
        valores.put(EstructuraBd.AROMA_IMAGEN,aroma.getImagen());
        // Defino la cláusula Where necesaria para modificar el registro concreto
        String where = String.format("%s=?", EstructuraBd.AROMA_ID);
        // Establezco el valor por el que se tiene que hacer el WHERE
        String[] whereArgs = {aroma.Identificador};
        int resultado = db.update(EstructuraBd.TABLA_AROMA, valores, where,whereArgs);
        return resultado > 0;

    }

    /*Eliminación de un registro*/
    public boolean eliminarAroma(Integer id){

        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String where = EstructuraBd.AROMA_ID + " =? ";
        String[] whereArgs = {id.toString()};
        int resultado = db.delete(EstructuraBd.TABLA_AROMA,where,whereArgs);
        return resultado > 0;

    }

}
