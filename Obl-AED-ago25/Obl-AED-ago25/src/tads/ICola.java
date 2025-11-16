
package tads;

public interface ICola <T extends Comparable<T>>{
   boolean esVacia(); 
   boolean esLLena();
   void encolar(T dato);
   void desencolar();
   Nodo<T> getFrente();
   Nodo<T> getFondo();
   int getCantNodos();
   ListaNodos<T> obtenerLista();
}
