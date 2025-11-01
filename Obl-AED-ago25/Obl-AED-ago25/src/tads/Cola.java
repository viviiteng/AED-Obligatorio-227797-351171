
package tads;

public class Cola<T extends Comparable<T>> implements ICola<T> {
    Nodo frente;
    Nodo fondo;
    int cantnodospermitidos;
    int cantnodos;

    public Cola(int cantnodospermitidos) {
        this.frente = null;
        this.fondo = null;
        this.cantnodospermitidos = cantnodospermitidos;
        this.cantnodos = 0;
    }

    public Nodo getFrente() {
        return frente;
    }

    public void setFrente(Nodo frente) {
        this.frente = frente;
    }

    public Nodo getFondo() {
        return fondo;
    }

    public void setFondo(Nodo fondo) {
        this.fondo = fondo;
    }

    public int getCantnodospermitidos() {
        return cantnodospermitidos;
    }

    public void setCantnodospermitidos(int cantnodospermitidos) {
        this.cantnodospermitidos = cantnodospermitidos;
    }

    public int getCantnodos() {
        return cantnodos;
    }

    public void setCantnodos(int cantnodos) {
        this.cantnodos = cantnodos;
    }

    @Override
    public boolean esVacia() {
        return this.getCantnodos()==0;
    }
    @Override
    public boolean esLLena() {
        return this.getCantnodos()==this.getCantnodospermitidos();
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
                this.cantnodos++;
            }else{
                System.out.println("La cola esta llena, no puede agregar elemento");
            
            }
    }

    @Override
    public void desencolar() {
        if (!this.esVacia()){
            if (this.cantnodos==1){
                this.frente=null;
                this.fondo=null;

            }else{
                frente=frente.getSiguiente();
                
            }
            this.cantnodos--;           
            
        }else{
         System.out.println("La cola esta vacia, no hay elementos para borrar");
        
        }

    }

    @Override
    public Nodo frente() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Nodo fondo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int cantidadnodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
