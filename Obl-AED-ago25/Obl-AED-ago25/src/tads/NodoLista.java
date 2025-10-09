package tads;

public class NodoLista<T extends Comparable> {
    
    private T dato;
    private NodoLista<T> siguiente;
    
    public NodoLista(T elDato){
        this.dato = elDato;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoLista<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista<T> siguiente) {
        this.siguiente = siguiente;
    }
        
}
