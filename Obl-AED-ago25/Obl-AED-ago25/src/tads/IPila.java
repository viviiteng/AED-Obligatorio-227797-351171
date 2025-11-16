package tads;

public interface IPila<T extends Comparable<T>> {
    
    public boolean esVacia();
    public void push(T n);
    public void pop();
    public T top();
    public T poptop();
    public void vaciar();
    public int cantElementos();
    public ListaNodos<T> ConvertirPilaLista(PilaNodos<T> p);
    public void mostrar();
    
}