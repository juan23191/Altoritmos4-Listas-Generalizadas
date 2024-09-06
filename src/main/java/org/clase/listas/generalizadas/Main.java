package org.clase.listas.generalizadas;

public class Main {
    public static void main(String[] args) {

        Arbol arbol = new Arbol();

        // Insertar el primer nodo en la raíz
        arbol.insertar(14);

        arbol.insertar(arbol.getRaiz(), 14, 9);
        arbol.insertar(arbol.getRaiz(), 9, 4);
        arbol.insertar(arbol.getRaiz(), 9, 5);
        arbol.insertar(arbol.getRaiz(), 14, 6);
        arbol.insertar(arbol.getRaiz(), 14, 11);
        arbol.insertar(arbol.getRaiz(), 11, 7);
        arbol.insertar(arbol.getRaiz(), 7, 1);
        arbol.insertar(arbol.getRaiz(), 7, 2);

        arbol.eliminar(arbol.getRaiz(), 14);
        arbol.eliminar(arbol.getRaiz(), 7);

        // Mostrar el contenido del árbol
        System.out.println("Estructura del árbol:");
        arbol.mostrar(arbol.getRaiz());
    }
}