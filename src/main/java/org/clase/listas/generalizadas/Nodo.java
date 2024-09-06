package org.clase.listas.generalizadas;

public class Nodo {
    private int sw; // Si está en 0 es átomo, si está en 1 es lista
    private Nodo ligaLista;
    private int info;
    private Nodo liga;

    public Nodo(int info) {
        this.sw = 0;
        this.ligaLista = null;
        this.info = info;
        this.liga = null;
    }


    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    public Nodo getLigaLista() {
        return ligaLista;
    }

    public void setLigaLista(Nodo ligaLista) {
        this.ligaLista = ligaLista;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public Nodo getLiga() {
        return liga;
    }

    public void setLiga(Nodo liga) {
        this.liga = liga;
    }
}