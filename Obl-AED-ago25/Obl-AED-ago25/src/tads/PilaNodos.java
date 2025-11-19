package tads;

public class PilaNodos<T extends Comparable<T>> implements IPila<T> {
    
    private Nodo<T> pila;
    private int cantidad;
    
    public PilaNodos(){
        pila = null;
        cantidad = 0;
    }

    @Override
    public boolean esVacia() {
        return pila == null;
    }

    @Override
    public void push(T n) {
        Nodo nuevo = new Nodo(n);
        nuevo.setSiguiente(pila);
        pila = nuevo;
        this.cantidad++;
    }

    @Override
    public void pop() {
        if(!esVacia()){
            Nodo aBorrar = this.pila;
            this.pila = this.pila.getSiguiente();
            aBorrar.setSiguiente(null);
            this.cantidad--;
        }
    }

    @Override
    public T top() {
        if(!esVacia()){
            return this.pila.getDato();
        }
        return null;
    }

    @Override
    public T poptop() {
        T dato = top();
        pop();
        return dato;
    }

    @Override
    public void vaciar() {
        this.pila = null;
        this.cantidad = 0;
    }

    @Override
    public int cantElementos() {
        return cantidad;
    }
    
    // Con fines didácticos
    @Override
    public void mostrar() {
        Nodo aux = this.pila;
        
        while(aux != null){
            System.out.println(aux.getDato());
            aux = aux.getSiguiente();
        }
    }
    
    @Override
    public ListaNodos<T> ConvertirPilaLista(PilaNodos<T> p) {
        ListaNodos<T> lista = new ListaNodos();
        if(!p.esVacia()){
            while(this.cantElementos()>0){
                lista.agregarInicio(top());
                this.pop();
            }
            for (int i = 0; i < lista.cantElementos(); i++) {
                T n = lista.obtenerElementoPorPosicion(i).getDato();
                this.push(n);
            }
        }
        return lista;   
    }
}
