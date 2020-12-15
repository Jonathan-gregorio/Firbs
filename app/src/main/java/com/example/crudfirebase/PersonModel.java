package com.example.crudfirebase;

public class PersonModel {

    String id, marca, modelo, precio, memoriaRam, procesador, sistemaoperativo, lanzamiento;

    public PersonModel(String id, String marca, String modelo, String precio, String memoriaRam, String procesador, String sistemaoperativo, String lanzamiento) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.memoriaRam = memoriaRam;
        this.procesador = procesador;
        this.sistemaoperativo = sistemaoperativo;
        this.lanzamiento = lanzamiento;
    }

    public PersonModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return marca;
    }

    public void setNombre(String marca) {
        this.marca = marca;
    }

    public String getApaterno() {
        return modelo;
    }

    public void setApaterno(String modelo) {
        this.modelo = modelo;
    }

    public String getAmaterno() {
        return modelo;
    }

    public void setAmaterno(String precio) {
        this.precio = precio;
    }

    public String getSexo() {
        return precio;
    }

    public void setSexo(String memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public String getDireccion() {
        return memoriaRam;
    }

    public void setDireccion(String procesador) {
        this.procesador = procesador;
    }

    public String getFacebook() {
        return procesador;
    }

    public void setFacebook(String sistemaoperativo) {
        this.sistemaoperativo = sistemaoperativo;
    }

    public String getInstagram() {
        return sistemaoperativo;
    }

    public void setInstagram(String lanzamiento) {
        this.lanzamiento = lanzamiento;
    }
}
