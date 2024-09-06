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

    public void mostrar(Nodo R) {
        Nodo P = R;
        while (P != null) {
            if (P.getSw() == 0) {
                System.out.print(P.getInfo());
            } else {
                mostrar(P.getLigaLista());
            }
            P = P.getLiga();
        }
    }

    public void insertar(int info) {
        Nodo nuevo = new Nodo(info);
        if (esVacio()) {
            this.raiz = nuevo;
        }
    }

    public void insertar(Nodo R, int datoPadre, int dato) {
        Nodo P = R;
        while (P != null) {
            if (P.getSw() == 0) {
                if (P.getInfo() == datoPadre) {
                    Nodo nuevo = new Nodo(dato);
                    if (P != R) {
                        Nodo nuevoPadre = new Nodo(P.getInfo());
                        nuevoPadre.setLiga(nuevo);
                        P.setSw(1);
                        P.setLigaLista(nuevoPadre);
                    } else {
                        Nodo temp = P;
                        while (temp.getLiga() != null) {
                            temp = temp.getLiga();
                        }
                        temp.setLiga(nuevo);
                    }
                    return;
                }
            } else {
                insertar(P.getLigaLista(), datoPadre, dato);
            }
            P = P.getLiga();  // Continuar con los hermanos
        }
    }
    public void eliminar(Nodo R, int dato) {
        if (R == null) {
            return;
        }

        // Caso 1: Si el nodo a eliminar es la raíz del árbol
        if (R == raiz && R.getInfo() == dato) {
            if (R.getSw() == 1) {
                Nodo temp = R.getLigaLista();
                while (temp != null) {
                    Nodo temp2 = temp.getLiga();
                    temp.setLiga(null);
                    temp = temp2;
                }
            }
            raiz = R.getLiga();
            return;
        }

        Nodo P = R;
        Nodo anterior = null;

        while (P != null) {
            // Caso 2: Si el nodo a eliminar es un átomo con sw == 0
            if (P.getSw() == 0 && P.getInfo() == dato) {
                if (anterior == null) {
                    R = P.getLiga();
                } else {
                    anterior.setLiga(P.getLiga());
                }
                return;
            }

            // Caso 3: Si el nodo a eliminar es la cabeza de una sublista
            if (P.getSw() == 1 && P.getInfo() == dato) {
                Nodo nuevoPadre = P.getLigaLista();
                nuevoPadre.setSw(1);
                if (anterior != null) {
                    anterior.setLiga(nuevoPadre);
                } else {
                    R = nuevoPadre;
                }
                Nodo ultimoHijo = nuevoPadre;
                while (ultimoHijo.getLiga() != null) {
                    ultimoHijo = ultimoHijo.getLiga();
                }
                ultimoHijo.setLiga(P.getLiga());
                return;
            }

            // Caso 4: Si el nodo a eliminar es un átomo de una sublista
            if (P.getSw() == 1) {
                eliminar(P.getLigaLista(), dato);
            }

            anterior = P;
            P = P.getLiga();
        }
    }
}