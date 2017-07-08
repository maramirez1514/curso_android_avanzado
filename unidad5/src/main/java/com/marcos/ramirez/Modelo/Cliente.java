package com.marcos.ramirez.Modelo;

/**
 * Created by windows 8.1 on 24/06/2017.
 */

public class Cliente {
    private long idcliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

    public Cliente(String nombre, String apellido, String telefono, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    public Cliente(long idcliente, String nombre, String apellido, String telefono, String email) {
        this.idcliente = idcliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    public long getIdcliente() {
        return idcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
