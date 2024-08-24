package org.clase.listas.generalizadas;

public class Main {
    public static void main(String[] args) {

        Arbol arbol = new Arbol();
        arbol.insertar(1);
        arbol.insertar(arbol.getRaiz(), 1, 2);
        arbol.insertar(arbol.getRaiz(), 1, 3);
        arbol.insertar(arbol.getRaiz(), 3, 6);
        arbol.insertar(arbol.getRaiz(), 6, 7);
        arbol.insertar(arbol.getRaiz(), 1, 4);
        arbol.insertar(arbol.getRaiz(), 1, 5);

        arbol.mostrar(arbol.getRaiz());
    }
}