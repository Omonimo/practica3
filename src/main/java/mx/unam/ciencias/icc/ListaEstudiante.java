package mx.unam.ciencias.icc;

/**
 * <p>Clase para listas de estudiantes doblemente ligadas.</p>
 *
 * <p>Las listas de estudiantes nos permiten agregar elementos al inicio o final
 * de la lista, eliminar elementos de la lista, comprobar si un elemento está o
 * no en la lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas de estudiantes son iterables utilizando sus nodos. Las listas
 * no aceptan a <code>null</code> como elemento.</p>
 *
 * <p>Los elementos en una lista de estudiantes siempre son instancias de la
 * clase {@link Estudiante}.</p>
 */
public class ListaEstudiante {

    /**
     * Clase interna para nodos.
     */
    public class Nodo {

        /* El elemento del nodo. */
        private Estudiante elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(Estudiante elemento) {
            this.elemento = elemento;
        }

        /**
         * Regresa el nodo anterior del nodo.
         * @return el nodo anterior del nodo.
         */
        public Nodo getAnterior() {
            // Aquí va su código.
            return anterior;
        }

        /**
         * Regresa el nodo siguiente del nodo.
         * @return el nodo siguiente del nodo.
         */
        public Nodo getSiguiente() {
            // Aquí va su código.
            return siguiente;
        }

        /**
         * Regresa el elemento del nodo.
         * @return el elemento del nodo.
         */
        public Estudiante get() {
            // Aquí va su código.
            return elemento;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
    // Aquí va su código.
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
    // Aquí va su código.
        return cabeza == null && rabo == null;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void agregaFinal(Estudiante elemento) {
    // Aquí va su código.
        if (elemento == null)
            return;
        
        Nodo n = new Nodo(elemento);
        if (esVacia()) {
            cabeza = rabo = n;
        } else {
            rabo.siguiente = n;
            n.anterior = rabo;
            rabo = n;
        }
        longitud++;
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void agregaInicio(Estudiante elemento) {
    // Aquí va su código.
        if (elemento == null)
            return;
        
        Nodo n = new Nodo(elemento);
        if (esVacia()) {
            cabeza = rabo = n;
        } else {
            cabeza.anterior = n;
            n.siguiente = cabeza;
            cabeza = n;
        }
        longitud++;
    }

    /**
     * Método auxiliar recursivo para insertar en una posición específica
     */
    private Nodo insertaRecursivo(Nodo actual, int indice, int objetivo, Estudiante elemento) {
        if (indice == objetivo) {
            Nodo n = new Nodo(elemento);
            n.siguiente = actual;
            if (actual != null) {
                n.anterior = actual.anterior;
                if (actual.anterior != null)
                    actual.anterior.siguiente = n;
                actual.anterior = n;
            }
            return n;
        }
        
        if (actual == null || actual.siguiente == null)
            return null;
            
        insertaRecursivo(actual.siguiente, indice + 1, objetivo, elemento);
        return actual;
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar. El elemento se inserta únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void inserta(int i, Estudiante elemento) {
    // Aquí va su código.
        if (elemento == null)
            return;
        
        if (i <= 0) {
            agregaInicio(elemento);
            return;
        }
        
        if (i >= longitud) {
            agregaFinal(elemento);
            return;
        }
        
        Nodo resultado = insertaRecursivo(cabeza, 0, i, elemento);
        if (resultado != null && resultado.anterior == null)
            cabeza = resultado;
        longitud++;
    }

    /**
     * Método auxiliar recursivo para buscar un nodo
     */
    private Nodo buscaRecursivo(Nodo actual, Estudiante elemento) {
        if (actual == null)
            return null;
        if (actual.elemento.equals(elemento))
            return actual;
        return buscaRecursivo(actual.siguiente, elemento);
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(Estudiante elemento) {
    // Aquí va su código.
        if (elemento == null)
            return;
        
        Nodo nodo = buscaRecursivo(cabeza, elemento);
        if (nodo == null)
            return;
        
        if (nodo.anterior != null)
            nodo.anterior.siguiente = nodo.siguiente;
        else
            cabeza = nodo.siguiente;
        
        if (nodo.siguiente != null)
            nodo.siguiente.anterior = nodo.anterior;
        else
            rabo = nodo.anterior;
        
        longitud--;
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Estudiante eliminaPrimero() {
    // Aquí va su código.
        if (esVacia())
            return null;
        
        Estudiante elemento = cabeza.elemento;
        if (longitud == 1) {
            cabeza = rabo = null;
        } else {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
        }
        longitud--;
        return elemento;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Estudiante eliminaUltimo() {
    // Aquí va su código.
        if (esVacia())
            return null;
        
        Estudiante elemento = rabo.elemento;
        if (longitud == 1) {
            cabeza = rabo = null;
        } else {
            rabo = rabo.anterior;
            rabo.siguiente = null;
        }
        longitud--;
        return elemento;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contiene(Estudiante elemento) {
    // Aquí va su código.
        return buscaRecursivo(cabeza, elemento) != null;
    }

    /**
     * Método auxiliar recursivo para reversar la lista
     */
    private void reversaRecursiva(Nodo actual, ListaEstudiante nuevaLista) {
        if (actual == null)
            return;
        reversaRecursiva(actual.siguiente, nuevaLista);
        nuevaLista.agregaFinal(actual.elemento);
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public ListaEstudiante reversa() {
    // Aquí va su código.
        ListaEstudiante nuevaLista = new ListaEstudiante();
        reversaRecursiva(cabeza, nuevaLista);
        return nuevaLista;
    }

    /**
     * Método auxiliar recursivo para copiar la lista
     */
    private void copiaRecursiva(Nodo actual, ListaEstudiante nuevaLista) {
        if (actual == null)
            return;
        nuevaLista.agregaFinal(actual.elemento);
        copiaRecursiva(actual.siguiente, nuevaLista);
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public ListaEstudiante copia() {
    // Aquí va su código.
        ListaEstudiante nuevaLista = new ListaEstudiante();
        copiaRecursiva(cabeza, nuevaLista);
        return nuevaLista;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
    // Aquí va su código.
        cabeza = rabo = null;
        longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Estudiante getPrimero() {
    // Aquí va su código.
        return esVacia() ? null : cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el último elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Estudiante getUltimo() {
    // Aquí va su código.
        return esVacia() ? null : rabo.elemento;
    }

    /**
     * Método auxiliar recursivo para obtener elemento por índice
     */
    private Estudiante getRecursivo(Nodo actual, int indiceActual, int indiceBuscado) {
        if (actual == null || indiceActual > indiceBuscado)
            return null;
        if (indiceActual == indiceBuscado)
            return actual.elemento;
        return getRecursivo(actual.siguiente, indiceActual + 1, indiceBuscado);
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, o <code>null</code> si
     *         <em>i</em> es menor que cero o mayor o igual que el número de
     *         elementos en la lista.
     */
    public Estudiante get(int i) {
    // Aquí va su código.
        if (i < 0 || i >= longitud)
            return null;
        return getRecursivo(cabeza, 0, i);
    }

    /**
     * Método auxiliar recursivo para obtener índice de elemento
     */
    private int indiceRecursivo(Nodo actual, Estudiante elemento, int indice) {
        if (actual == null)
            return -1;
        if (actual.elemento.equals(elemento))
            return indice;
        return indiceRecursivo(actual.siguiente, elemento, indice + 1);
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(Estudiante elemento) {
    // Aquí va su código.
        if (elemento == null)
            return -1;
        return indiceRecursivo(cabeza, elemento, 0);
    }

    /**
     * Método auxiliar recursivo para toString
     */
    private String toStringRecursivo(Nodo actual) {
        if (actual == null)
            return "]";
        if (actual == cabeza && actual.siguiente == null)
            return actual.elemento.toString() + "]";
        if (actual == cabeza)
            return actual.elemento.toString() + ", " + toStringRecursivo(actual.siguiente);
        if (actual.siguiente == null)
            return actual.elemento.toString() + "]";
        return actual.elemento.toString() + ", " + toStringRecursivo(actual.siguiente);
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    public String toString() {
    // Aquí va su código.
        if (esVacia())
            return "[]";
        return "[" + toStringRecursivo(cabeza);
    }

    /**
     * Método auxiliar recursivo para equals
     */
    private boolean equalsRecursivo(Nodo nodo1, Nodo nodo2) {
        if (nodo1 == null && nodo2 == null)
            return true;
        if (nodo1 == null || nodo2 == null)
            return false;
        if (!nodo1.elemento.equals(nodo2.elemento))
            return false;
        return equalsRecursivo(nodo1.siguiente, nodo2.siguiente);
    }

    /**
     * Nos dice si la lista es igual a la lista recibida.
     * @param lista la lista con la que hay que comparar.
     * @return <code>true</code> si la lista es igual a la recibida;
     *         <code>false</code> en otro caso.
     */
    public boolean equals(ListaEstudiante lista) {
    // Aquí va su código.
        if (lista == null || longitud != lista.longitud)
            return false;
        return equalsRecursivo(cabeza, lista.cabeza);
    }

    /**
     * Regresa el nodo cabeza de la lista.
     * @return el nodo cabeza de la lista.
     */
    public Nodo getCabeza() {
        return cabeza;
    }

    /**
     * Regresa el nodo rabo de la lista.
     * @return el nodo rabo de la lista.
     */
    public Nodo getRabo() {
        return rabo;
    }
}