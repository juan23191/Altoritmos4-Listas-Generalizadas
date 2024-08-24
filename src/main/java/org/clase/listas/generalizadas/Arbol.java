package org.clase.listas.generalizadas;

import java.util.logging.Logger;

public class Arbol {

    Logger logger = Logger.getLogger(getClass().getName());
    private Nodo raiz;

    public Arbol() {
        this.raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public void mostrar(Nodo R){
        Nodo P = R;
        while (P != null){
            if (P.getSw() == 0){
                System.out.print(P.getInfo());
            }else{
                System.out.print("(");
                mostrar(P.getLigaLista());
                System.out.print(")");
            }
            P = P.getLiga();
        }
    }

    public void insertar(int info){
        Nodo nuevo = new Nodo(info);
        if (esVacio()){
            this.raiz = nuevo;
        }
    }

    public void insertar(Nodo R, int datoPadre, int dato){
        Nodo P = R;
        Nodo nuevo = new Nodo(dato);
        while (P != null) {
            if (P.getSw() == 0) {
                if (P.getInfo() == datoPadre) {
                    Nodo temp = P;
                    while (temp.getLiga() != null) {
                        temp = temp.getLiga();
                    }
                    temp.setLiga(nuevo);
                    return;
                }
            } else {
                insertar(P.getLigaLista(), datoPadre, dato);
            }
            P = P.getLiga();
        }
    }
}