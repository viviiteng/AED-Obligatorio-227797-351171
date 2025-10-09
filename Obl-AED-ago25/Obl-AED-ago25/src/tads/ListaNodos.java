package tads;

public class ListaNodos<T extends Comparable<T>> implements ILista<T> {
    
    private NodoLista<T> lista;
    private int cantidad;
    
    public ListaNodos(){
        lista = null;
        cantidad = 0;
    }

    public NodoLista<T> getLista() {
        return lista;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    @Override
    public boolean esVacia() {
        return lista == null;
    }

    @Override
    public void agregarInicio(T n) {
        NodoLista nuevo = new NodoLista(n);
        nuevo.setSiguiente(lista);
        lista = nuevo;
        this.cantidad++;
    }

    @Override
    public void agregarFinal(T n) {
        if(esVacia()){
            agregarInicio(n);
        }else{
            NodoLista aux = lista;
        
            while(aux.getSiguiente() != null){
                aux = aux.getSiguiente();
            }

            NodoLista nuevo = new NodoLista(n);
            aux.setSiguiente(nuevo);
            
            this.cantidad++;
        }
    }

    @Override
    public void borrarInicio() {
        if(!esVacia()){
            NodoLista aBorrar = this.lista;
            this.lista = this.lista.getSiguiente();
            aBorrar.setSiguiente(null);
            this.cantidad--;
        }
    }

    @Override
    public void borrarFin() {
        if(!esVacia()){
            if(this.lista.getSiguiente() == null) {
                vaciar();
            }else{
                NodoLista aux = this.lista;
                while(aux.getSiguiente() != null && aux.getSiguiente().getSiguiente() != null){
                    aux = aux.getSiguiente();
                }
                aux.setSiguiente(null);
                this.cantidad--;
            }
        }
    }

    @Override
    public void vaciar() {
        this.lista = null;
        this.cantidad = 0;
    }

    @Override
    public void mostrar() {
        NodoLista aux = this.lista;
        
        while(aux != null){
            System.out.println(aux.getDato());
            aux = aux.getSiguiente();
        }
    }

    @Override
    public void agregarOrd(T n) {
        if(this.lista == null || n.compareTo(this.lista.getDato()) < 0 ){
            agregarInicio(n);
        }else{
            NodoLista aux = this.lista;
            while(aux.getSiguiente() != null && aux.getSiguiente().getDato().compareTo(n) < 0){
                aux = aux.getSiguiente();
            }
            
            NodoLista nuevo = new NodoLista(n);
            nuevo.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(nuevo);
            
            this.cantidad++;
        }
    }

    @Override
    public void borrarElemento(T n) {
        if(this.lista != null){
            if(this.lista.getDato().equals(n)){
                borrarInicio();
            }else{
                NodoLista aux = this.lista;
                while(aux.getSiguiente() != null && !aux.getSiguiente().getDato().equals(n)){
                    aux = aux.getSiguiente();
                }
                
                if(aux.getSiguiente() != null){
                    NodoLista aBorrar = aux.getSiguiente();
                    aux.setSiguiente(aux.getSiguiente().getSiguiente());
                    aBorrar.setSiguiente(null);
                    
                    this.cantidad--;
                }
            }
        }
    }

    @Override
    public int cantElementos() {
        return cantidad;
    }

    @Override
    public NodoLista<T> obtenerElemento(T n) {
        NodoLista<T> ret = this.lista;
        
        while(ret != null){
            if(ret.getDato().equals(n)) return ret;
            ret = ret.getSiguiente();
        }
        
        return ret;
    }
    
    @Override
    public boolean existeElemento(T n){
        boolean ret = false;
        if(this.obtenerElemento(n) != null){
            ret = true;
        }
        return ret;
    }

    @Override
    public void mostrarREC(NodoLista l) {
        if(l != null){
            mostrarREC(l.getSiguiente());
            System.out.println(l.getDato());
        }
    }
    
    @Override
    public String listar(){
        
       NodoLista aux = this.lista;
       String list = ""; 
        while(aux != null){
            list += (aux.getDato().toString()+"|");
            aux = aux.getSiguiente();
        }
       if (!list.isEmpty()) {
        list = list.substring(0, list.length() - 1);
       }
       return list;
    }
       
}
