package org.clase.listas.generalizadas;


public class Nodo {
    private int sw; //Si esta false es atomo -- /Si es true es lista
    private Nodo ligaLista;
    private int Info;
    private Nodo liga;

    public Nodo(int info) {
        this.sw = 0;
        this.ligaLista = null;
        this.Info = info;
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
        return Info;
    }

    public void setInfo(int info) {
        Info = info;
    }

    public Nodo getLiga() {
        return liga;
    }

    public void setLiga(Nodo liga) {
        this.liga = liga;
    }


}
