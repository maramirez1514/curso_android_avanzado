package com.marcos.examen_integrador.Model;

/**
 * Created by windows 8.1 on 04/08/2017.
 */

public class ListaTODO {

    private String url_imagen;
    private String tarea;
    private int hecho;

    public ListaTODO(String url_imagen, String tarea, int hecho) {
        this.url_imagen = url_imagen;
        this.tarea = tarea;
        this.hecho = hecho;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public String getTarea() {
        return tarea;
    }

    public int getHecho() {
        return hecho;
    }

    @Override
    public String toString() {
        return "ListaTODO{" +
                "url_imagen='" + url_imagen + '\'' +
                ", tarea='" + tarea + '\'' +
                ", hecho=" + hecho +
                '}';
    }
}
