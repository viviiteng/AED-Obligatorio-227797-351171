package tads;

public class Nodo<T extends Comparable> {
    
    private T dato;
    private Nodo<T> siguiente;
    
    public Nodo(T elDato){
        this.dato = elDato;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
        
}
