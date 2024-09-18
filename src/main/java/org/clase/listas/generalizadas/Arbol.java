package org.clase.listas.generalizadas;

import java.util.ArrayList;
import java.util.List;
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

    public boolean esVacio() {
        return this.raiz == null;
    }

    public void mostrar(Nodo R) {
        if (esVacio()) {
            System.out.println("Arbol Sin nada para mostrar");
            return;
        }

        Nodo P = R;
        boolean esPrimero = true; // Control para comas

        // Mientras haya nodos en la lista
        while (P != null) {
            if (!esPrimero) {
                System.out.print(", ");
            }

            // Si es un nodo con información (sw == 0), imprimirlo directamente
            System.out.print(P.getInfo());

            // Si el nodo apunta a una sublista (sw == 1), abrir paréntesis y mostrar la sublista
            if (P.getSw() == 1) {
                System.out.print(" (");
                mostrar(P.getLigaLista());
                System.out.print(")");
            }

            // Avanzar al siguiente nodo
            esPrimero = false;
            P = P.getLiga();
        }
    }

    public void insertar(int info) {
        Nodo nuevo = new Nodo(info);
        if (esVacio()) {
            this.raiz = nuevo;
        }
    }

    public void insertar(Nodo R, Object datoPadre, Object dato) {
        Nodo P = R;
        Nodo nuevo = new Nodo(dato);

        while (P != null) {
            if (P.getInfo().equals(datoPadre)) {
                // Si el nodo padre tiene una sublista
                if (P.getSw() == 1) {
                    Nodo temp = P.getLigaLista();
                    // Añadir el nuevo nodo al final de la sublista
                    while (temp.getLiga() != null) {
                        temp = temp.getLiga();
                    }
                    temp.setLiga(nuevo);
                } else {
                    // Si el nodo padre no tiene una sublista
                    P.setSw(1); // Indica que tiene una sublista
                    P.setLigaLista(nuevo); // Inicializa la sublista con el nuevo nodo
                }
                return;
            } else if (P.getSw() == 1) {
                // Si el nodo tiene sublista, buscar en la sublista
                insertar(P.getLigaLista(), datoPadre, dato);
            }
            P = P.getLiga(); // Avanzar al siguiente hermano
        }
    }

    public void eliminar(Nodo R, Object dato) {
        if (esVacio()) {
            System.out.println("Arbol Vacío");
            return;
        }

        // Caso: El dato a eliminar está en la raíz
        if (raiz.getInfo().equals(dato)) {
            if (raiz.getLigaLista() == null && raiz.getLiga() == null) {
                // El dato a eliminar es el único nodo en el árbol
                raiz = null;
                System.out.println("Se ha eliminado el único dato en el árbol");
            } else {
                // El dato a eliminar está en la raíz pero tiene hijos
                raiz = raiz.getLigaLista() != null ? raiz.getLigaLista() : raiz.getLiga();
                System.out.println("Se ha eliminado la raíz del árbol,Con dato: " + dato);
            }
            return;
        }

        eliminarRecursivo(R, dato);
    }

    private void eliminarRecursivo(Nodo R, Object dato) {
        Nodo P = R;
        Nodo anterior = P; // Para guardar el nodo previo en la lista de hermanos
        while (P != null) {
            if (P.getSw() == 1) {
                // Si el nodo tiene sublista, buscar en la sublista
                if (P.getLigaLista() != null && P.getLigaLista().getInfo().equals(dato)) {
                    Nodo nodoEliminar = P.getLigaLista();
                    if (nodoEliminar.getLigaLista() != null) {
                        // El primer hijo pasa a ser el nuevo padre
                        P.setLigaLista(nodoEliminar.getLigaLista());
                        Nodo temp = nodoEliminar.getLigaLista();
                        while (temp.getLiga() != null) {
                            temp = temp.getLiga();
                        }
                        temp.setLiga(nodoEliminar.getLiga());
                    } else {
                        P.setLigaLista(nodoEliminar.getLiga());
                    }
                    System.out.println("Se ha eliminado el nodo con dato: " + dato);
                    return;
                } else {
                    eliminarRecursivo(P.getLigaLista(), dato);
                    anterior = P; // Guardar el nodo previo

                }
            } else {
                if (P.getLiga() != null && anterior.getLiga().getInfo().equals(dato)) {
                    anterior.setLiga(P.getLiga());
                    System.out.println("Valor Eliminado: " + dato);
                    return;
                }
            }

            P = P.getLiga(); // Avanzar al siguiente hermano
        }
    }

    public Nodo buscarDato(Nodo R, Object dato) {
        Nodo P = R;
        while (P != null) {
            if (P.getInfo().equals(dato)) {
                return P; // Dato encontrado
            } else if (P.getSw() == 1) {
                Nodo resultado = buscarDato(P.getLigaLista(), dato);
                if (resultado != null) {
                    return resultado; // Dato encontrado en la sublista
                }
            }
            P = P.getLiga(); // Avanzar al siguiente hermano
        }
        return null; // Dato no encontrado
    }

    public void mostrarRaices(Nodo R) {
        if (R == null) {
            return;
        }

        Nodo P = R;
        while (P != null) {
            if (P.getSw() == 1) {
                System.out.print(P.getInfo() + " ");
                mostrarRaices(P.getLigaLista()); // Recorrer sublista
            }
            P = P.getLiga(); // Avanzar al siguiente hermano
        }
    }

    public int contarHojas(Nodo R) {
        Nodo P = R;
        int contador = 0;
        while (P != null) {
            if (P.getSw() == 0) {
                contador++;
            } else {
                contador += contarHojas(P.getLigaLista());
            }
            P = P.getLiga();
        }
        return contador;
    }

    public void mostrarHojas(Nodo R) {
        Nodo P = R;
        while (P != null) {
            if (P.getSw() == 0) {
                System.out.print(P.getInfo() + " ");
            } else {
                mostrarHojas(P.getLigaLista());
            }
            P = P.getLiga();
        }
    }

    public void mostrarGradoArbol(Nodo R) {
        int grado = calcularGrado(R);
        System.out.println("\nEl grado del árbol es: " + grado);
    }

    private int calcularGrado(Nodo R) {
        if (R == null) {
            return 0;
        }

        Nodo P = R;
        int maxGrado = 0;

        while (P != null) {
            int gradoActual = 0;
            if (P.getSw() == 1) {
                Nodo hijo = P.getLigaLista();
                while (hijo != null) {
                    gradoActual++;
                    hijo = hijo.getLiga();
                }
            }
            maxGrado = Math.max(maxGrado, gradoActual);
            maxGrado = Math.max(maxGrado, calcularGrado(P.getLigaLista()));
            P = P.getLiga();
        }

        return maxGrado;
    }

    public void mostrarGradoDatoDado(Nodo R, Object dato) {
        int grado = calcularGradoNodo(R, dato);
        System.out.println("El grado del árbol (" + dato + ") es: " + grado);
    }

    public int calcularGradoNodo(Nodo R, Object dato) {
        Nodo nodo = buscarDato(R, dato);
        if (nodo == null) {
            return -1; // Nodo no encontrado
        }

        int grado = 0;
        if (nodo.getSw() == 1) {
            Nodo hijo = nodo.getLigaLista();
            while (hijo != null) {
                grado++;
                hijo = hijo.getLiga();
            }
        }
        return grado;
    }

    public void mostrarHijosDatoDado(Nodo R, Object dato) {
        Nodo nodo = buscarDato(R, dato);
        if (nodo == null) {
            System.out.println("Nodo no encontrado");
            return;
        }

        if (nodo.getSw() == 1) {
            Nodo hijo = nodo.getLigaLista();
            System.out.print("Hijos de " + dato + ": ");
            while (hijo != null) {
                System.out.print(hijo.getInfo() + " ");
                hijo = hijo.getLiga();
            }
            System.out.println();
        } else {
            System.out.println("El nodo no tiene hijos");
        }
    }

    public void mostrarHermanosDatoDado(Nodo R, Object dato) {
        Nodo nodo = buscarDato(R, dato);
        if (nodo == null) {
            System.out.println("Nodo no encontrado");
            return;
        }

        Nodo padre = buscarPadre(R, dato);
        if (padre == null) {
            System.out.println("El nodo no tiene hermanos");
            return;
        }

        Nodo hermano = padre.getLigaLista();
        System.out.print("Hermanos de " + dato + ": ");
        while (hermano != null) {
            if (!hermano.getInfo().equals(dato)) {
                System.out.print(hermano.getInfo() + " ");
            }
            hermano = hermano.getLiga();
        }
        System.out.println();
    }

    public void mostrarNivelDatoDado(Nodo R, Object dato) {
        int nivel = calcularNivel(R, dato, 0);
        if (nivel == -1) {
            System.out.println("Nodo no encontrado");
        } else {
            System.out.println("El nivel del nodo " + dato + " es: " + nivel);
        }
    }

    private int calcularNivel(Nodo R, Object dato, int nivelActual) {
        if (R == null) {
            return -1;
        }

        if (R.getInfo().equals(dato)) {
            return nivelActual;
        }

        if (R.getSw() == 1) {
            Nodo hijo = R.getLigaLista();
            while (hijo != null) {
                int nivel = calcularNivel(hijo, dato, nivelActual + 1);
                if (nivel != -1) {
                    return nivel;
                }
                hijo = hijo.getLiga();
            }
        }

        return calcularNivel(R.getLiga(), dato, nivelActual);
    }

    public void mostrarAltura(Nodo R) {
        int altura = calcularAltura(R);
        System.out.println("La altura del árbol es: " + altura);
    }

    private int calcularAltura(Nodo R) {
        if (R == null) {
            return -1; // Altura de un árbol vacío es -1
        }

        if (R.getSw() == 0) {
            return 0; // Altura de una hoja es 0
        }

        int maxAltura = 0;
        Nodo hijo = R.getLigaLista();
        while (hijo != null) {
            int alturaHijo = calcularAltura(hijo);
            if (alturaHijo > maxAltura) {
                maxAltura = alturaHijo;
            }
            hijo = hijo.getLiga();
        }

        return maxAltura + 1; // Altura del nodo actual es 1 más la altura máxima de sus hijos
    }

    public void mostrarPadreDatoDado(Nodo R, Object dato) {
        Nodo padre = buscarPadre(R, dato);
        if (padre == null) {
            System.out.println("El nodo no tiene padre");
        } else {
            System.out.println("El padre de " + dato + " es: " + padre.getInfo());
        }
    }

    private Nodo buscarPadre(Nodo R, Object dato) {
        if (R == null) {
            return null;
        }

        if (R.getSw() == 1) {
            Nodo hijo = R.getLigaLista();
            while (hijo != null) {
                if (hijo.getInfo().equals(dato)) {
                    return R;
                }
                Nodo padre = buscarPadre(hijo, dato);
                if (padre != null) {
                    return padre;
                }
                hijo = hijo.getLiga();
            }
        }
        return null;
    }

    public void mostrarDatosNivelDado(Nodo R, int nivel) {
        List<Object> datos = new ArrayList<>();
        recorrerNivel(R, nivel, 0, datos);
        if (datos.isEmpty()) {
            System.out.println("Nivel no encontrado o vacío");
        } else {
            System.out.print("Datos en el nivel " + nivel + ": ");
            for (Object dato : datos) {
                System.out.print(dato + " ");
            }
            System.out.println();
        }
    }

    private void recorrerNivel(Nodo R, int nivelObjetivo, int nivelActual, List<Object> datos) {
        if (R == null) {
            return;
        }

        if (nivelActual == nivelObjetivo) {
            if (!datos.contains(R.getInfo())) {
                datos.add(R.getInfo());
            }
        }

        if (R.getSw() == 1) {
            Nodo hijo = R.getLigaLista();
            while (hijo != null) {
                recorrerNivel(hijo, nivelObjetivo, nivelActual + 1, datos);
                hijo = hijo.getLiga();
            }
        }

        recorrerNivel(R.getLiga(), nivelObjetivo, nivelActual, datos);
    }

}
