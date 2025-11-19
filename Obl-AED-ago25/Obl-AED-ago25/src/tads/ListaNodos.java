package tads;
import java.util.Comparator;
public class ListaNodos<T extends Comparable<T>> implements ILista<T> {
    
    private Nodo<T> lista;
    private int cantidad;
    
    public ListaNodos(){
        lista = null;
        cantidad = 0;
    }

    public Nodo<T> getLista() {
        return lista;
    }
    
    @Override
    public boolean esVacia() {
        return lista == null;
    }

    @Override
    public void agregarInicio(T n) {
        Nodo nuevo = new Nodo(n);
        nuevo.setSiguiente(lista);
        lista = nuevo;
        this.cantidad++;
    }

    @Override
    public void agregarFinal(T n) {
        if(esVacia()){
            agregarInicio(n);
        }else{
            Nodo aux = lista;
        
            while(aux.getSiguiente() != null){
                aux = aux.getSiguiente();
            }

            Nodo nuevo = new Nodo(n);
            aux.setSiguiente(nuevo);
            
            this.cantidad++;
        }
    }

    @Override
    public void borrarInicio() {
        if(!esVacia()){
            Nodo aBorrar = this.lista;
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
                Nodo aux = this.lista;
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
        Nodo aux = this.lista;
        
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
            Nodo aux = this.lista;
            while(aux.getSiguiente() != null && aux.getSiguiente().getDato().compareTo(n) < 0){
                aux = aux.getSiguiente();
            }
            
            Nodo nuevo = new Nodo(n);
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
                Nodo aux = this.lista;
                while(aux.getSiguiente() != null && !aux.getSiguiente().getDato().equals(n)){
                    aux = aux.getSiguiente();
                }
                
                if(aux.getSiguiente() != null){
                    Nodo aBorrar = aux.getSiguiente();
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
    public Nodo<T> obtenerElemento(T n) {
        Nodo<T> ret = this.lista;
        
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
    public void mostrarREC(Nodo l) {
        if(l != null){
            mostrarREC(l.getSiguiente());
            System.out.println(l.getDato());
        }
    }
    
    @Override
    public String listar(){
        
       Nodo aux = this.lista;
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
    
    @Override
    public ListaNodos<T> ordenar(Comparator<T> comp){
        ListaNodos<T> nueva = new ListaNodos<>();
        Nodo<T> actual = this.lista;
        while (actual != null) {
            nueva.agregarOrdenadoConComparador(actual.getDato(), comp);
            actual = actual.getSiguiente();
        }
        return nueva;        
    }
    
    @Override
    public void agregarOrdenadoConComparador(T dato, Comparator<T> comp){
        
        if(this.lista == null || comp.compare(dato,this.lista.getDato()) < 0 ){
            agregarInicio(dato);
        }else{
            Nodo<T> aux = this.lista;
            while(aux.getSiguiente() != null && comp.compare(dato, aux.getSiguiente().getDato()) > 0){
                aux = aux.getSiguiente();
            }
            
            Nodo nuevo = new Nodo(dato);
            nuevo.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(nuevo);
            
            this.cantidad++;
        }
    }
    
    @Override
    public String listarRecursivaDesc(Nodo n){
        if (n == null) {
            return "";
        }
        String mensaje = listarRecursivaDesc(n.getSiguiente());
        if(!mensaje.isEmpty()){            
            mensaje += "|";            
        }
        mensaje += n.getDato().toString();
        return mensaje;
    }
    @Override
    public String listarRecursivaAsc(Nodo n){
        if (n == null) {
            return "";
        }
        String mensaje = n.getDato().toString(); 
        if(n.getDato()!=null){       
            mensaje += "|" + listarRecursivaDesc(n.getSiguiente());            
        }
        return mensaje;
    }
    @Override
    public Nodo<T> obtenerElementoPorPosicion(int pos) {
        Nodo<T> ret = this.lista;
        int contador = 0;

        while(ret != null){
            if(contador == pos) return ret;
            ret = ret.getSiguiente();
            contador++;
        }

        return ret;
    }
    
    @Override
    public void sustituirElementoPorPosicion(int pos, T n){
        Nodo<T> aBorrar = obtenerElementoPorPosicion(pos);
        if (aBorrar != null) {
        aBorrar.setDato(n);
        }
    }

}
