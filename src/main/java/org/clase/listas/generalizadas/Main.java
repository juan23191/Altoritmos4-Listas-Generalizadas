package org.clase.listas.generalizadas;

public class Main {
    public static void main(String[] args) {

        Arbol arbol = new Arbol();

        // Insertar el primer nodo en la raíz
        arbol.insertar(14);

        // Insertar nodos en el árbol recursivamente
        arbol.insertar(arbol.getRaiz(), 14, 9);
        arbol.insertar(arbol.getRaiz(), 9, 4);
        arbol.insertar(arbol.getRaiz(), 9, 5);
        arbol.insertar(arbol.getRaiz(), 14, 6);
        arbol.insertar(arbol.getRaiz(), 14, 11);
        arbol.insertar(arbol.getRaiz(), 11, 7);
        arbol.insertar(arbol.getRaiz(), 7, 1);
        arbol.insertar(arbol.getRaiz(), 7, 2);
        arbol.insertar(arbol.getRaiz(), 11, 8);
        arbol.insertar(arbol.getRaiz(), 8, 12);
        arbol.insertar(arbol.getRaiz(), 11, 10);
        arbol.insertar(arbol.getRaiz(), 11, 3);
        arbol.insertar(arbol.getRaiz(), 14, 13);

        //Eliminar un dato
        arbol.eliminar(arbol.getRaiz(), 11);

        // Buscar un dato
        Nodo resultante = arbol.buscarDato(arbol.getRaiz(), 7);
        if (resultante != null) {
            System.out.println("El dato encontrado es: " + resultante.getInfo());
        } else {
            System.out.println("Dato a buscar no encontrado");
        }

        // Mostrar las raíces
        System.out.print("Raices: ");
        arbol.mostrarRaices(arbol.getRaiz());

        //Contar Hojas
        System.out.println("\nLa cantidad de hojas es:  " + arbol.contarHojas(arbol.getRaiz()));

        //Mostrar hojas
        System.out.print("Hojas: ");
        arbol.mostrarHojas(arbol.getRaiz());

        //Grado del arbol
        arbol.mostrarGradoArbol(arbol.getRaiz());

        //Grado de un arbol segun dato dado
        arbol.mostrarGradoDatoDado(arbol.getRaiz(), 6);

        // Mostrar los hijos de un nodo específico
        arbol.mostrarHijosDatoDado(arbol.getRaiz(), 11);

        //Mostrar hermanos de un dato dado
        arbol.mostrarHermanosDatoDado(arbol.getRaiz(), 9);

        // Mostrar el nivel de un nodo específico
        arbol.mostrarNivelDatoDado(arbol.getRaiz(), 1);

        // Mostrar la altura del árbol
        arbol.mostrarAltura(arbol.getRaiz());

        //Mostrar pade de dato dado
        arbol.mostrarPadreDatoDado(arbol.getRaiz(), 7);

        //Mostrar datos de un nivel dado
        arbol.mostrarDatosNivelDado(arbol.getRaiz(), 1);

        // Eliminar los datos de un nivel específico
        //arbol.eliminarDatosNivelDado(arbol.getRaiz(), 1);

        // Mostrar el contenido del árbol
        System.out.println("\nEstructura del árbol:");
        arbol.mostrar(arbol.getRaiz());
    }
}