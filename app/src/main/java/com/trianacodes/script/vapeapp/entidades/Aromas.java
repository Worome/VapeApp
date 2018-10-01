package com.trianacodes.script.vapeapp.entidades;

public class Aromas {


    /*Creo los argumentos con private para que no se pueda acceder a ellos nada más que desde
    esta clase.*/
    private int tiempoMinimoMaceracion, tiempoMaximoMaceracion, desdePorcentaje, hastaPorcentaje;
    private int valoracion, imagen;
    private String Tipo, Nombre, Marca, Observaciones;
    public String Identificador;

    /*Creo el constructor que tiene que ser public y en el que se tiene que dar los valores
      iniciales a cada una de sus propiedades.*/

    public Aromas(int tiempoMinimoMaceracion, int tiempoMaximoMaceracion, int desdePorcentaje,
                  int hastaPorcentaje, int valoracion, int imagen, String tipo, String nombre,
                  String marca, String observaciones, String identificador) {
        this.tiempoMinimoMaceracion = tiempoMinimoMaceracion;
        this.tiempoMaximoMaceracion = tiempoMaximoMaceracion;
        this.desdePorcentaje = desdePorcentaje;
        this.hastaPorcentaje = hastaPorcentaje;
        this.valoracion = valoracion;
        this.imagen = imagen;
        this.Tipo = tipo;
        this.Nombre = nombre;
        this.Marca = marca;
        this.Observaciones = observaciones;
        this.Identificador = identificador;

    }

    public Aromas() {

        this.tiempoMinimoMaceracion = 0;
        this.tiempoMaximoMaceracion = 0;
        this.desdePorcentaje = 0;
        this.hastaPorcentaje = 0;
        this.valoracion = 0;
        this.imagen = 0;
        this.Tipo = "";
        this.Nombre = "";
        this.Marca = "";
        this.Observaciones = "";
        this.Identificador = "";

    }

    /* Este es un ejemplo de constructor en el que al crearse un nuevo objeto habría que pasarle
     todos estos argumentos*/
    /*public Aromas(String identificador, String Nombre, String Marca, String Tipo,
                  int porcentajeRecomendado,int tiempoMinimoMaceracion, int tiempoMaximoMaceracion){


        // Inicializo las propiedades
        this.Identificador = identificador;
        this.Nombre = Nombre;
        this.Marca = Marca;
        this.Tipo = Tipo;
        this.porcentajeRecomendado = porcentajeRecomendado;
        this.tiempoMinimoMaceracion = tiempoMinimoMaceracion;
        this.tiempoMaximoMaceracion = tiempoMaximoMaceracion;

    }*/

    public int getTiempoMinimoMaceracion() {
        return tiempoMinimoMaceracion;
    }

    public void setTiempoMinimoMaceracion(int tiempoMinimoMaceracion) {
        this.tiempoMinimoMaceracion = tiempoMinimoMaceracion;
    }

    public int getTiempoMaximoMaceracion() {
        return tiempoMaximoMaceracion;
    }

    public void setTiempoMaximoMaceracion(int tiempoMaximoMaceracion) {
        this.tiempoMaximoMaceracion = tiempoMaximoMaceracion;
    }

    public int getDesdePorcentaje() {
        return desdePorcentaje;
    }

    public void setDesdePorcentaje(int desdePorcentaje) {
        this.desdePorcentaje = desdePorcentaje;
    }

    public int getHastaPorcentaje() {
        return hastaPorcentaje;
    }

    public void setHastaPorcentaje(int hastaPorcentaje) {
        this.hastaPorcentaje = hastaPorcentaje;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String observaciones) {
        Observaciones = observaciones;
    }
}
