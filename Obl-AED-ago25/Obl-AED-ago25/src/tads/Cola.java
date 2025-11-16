
package tads;

public class Cola<T extends Comparable<T>> implements ICola<T> {
    private Nodo<T> frente;
    private Nodo<T> fondo;
    private int cantNodosPermitidos;
    private int cantNodos;

    public Cola(int cantnodospermitidos) {
        this.frente = null;
        this.fondo = null;
        this.cantNodosPermitidos = cantnodospermitidos;
        this.cantNodos = 0;
    }
    
    @Override
    public Nodo<T> getFrente() {
        return frente;
    }

    public void setFrente(Nodo frente) {
        this.frente = frente;
    }
    
    @Override
    public Nodo<T> getFondo() {
        return fondo;
    }

    public void setFondo(Nodo<T> fondo) {
        this.fondo = fondo;
    }

    public int getCantNodosPermitidos() {
        return cantNodosPermitidos;
    }

    public void setCantNodosPermitidos(int cantNodosPermitidos) {
        this.cantNodosPermitidos = cantNodosPermitidos;
    }
    
    @Override
    public int getCantNodos() {
        return cantNodos;
    }

    public void setCantNodos(int cantNodos) {
        this.cantNodos = cantNodos;
    }

    @Override
    public boolean esVacia() {
        return this.getCantNodos()==0;
    }
    @Override
    public boolean esLLena() {
        return this.getCantNodos()==this.getCantNodosPermitidos();
    }
    
    
    @Override
    public void encolar(T dato) {
            Nodo nuevo=new Nodo(dato);
            if (!this.esLLena()){
                if (this.esVacia()){
                    this.setFondo(nuevo);
                    this.setFrente(nuevo);
                }else{
                    this.fondo.setSiguiente(nuevo);
                    this.setFondo(nuevo);                
                }
                this.cantNodos++;
            }else{
                System.out.println("La cola esta llena, no puede agregar elemento");
            
            }
    }

    @Override
    public void desencolar() {
        if (!this.esVacia()){
            if (this.cantNodos==1){
                this.frente=null;
                this.fondo=null;

            }else{
                frente=frente.getSiguiente();
                
            }
            this.cantNodos--;           
        }

    }
    
    @Override
    public ListaNodos<T> obtenerLista(){
        ListaNodos<T> ret = new ListaNodos();
        while(this.getCantNodos()>0){
            ret.agregarFinal(this.getFrente().getDato());
            this.desencolar();
        }
        for (int i = 0; i < ret.cantElementos(); i++) {
            T dato = ret.obtenerElementoPorPosicion(i).getDato();
            this.encolar(dato);
        }
        return ret;
    }


 
    
}
