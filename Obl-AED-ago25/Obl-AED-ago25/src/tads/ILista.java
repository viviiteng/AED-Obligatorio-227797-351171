package tads;
import java.util.Comparator;
public interface ILista<T extends Comparable<T>> {
    
    public boolean esVacia();
    public void agregarInicio(T n);
    public void agregarFinal(T n);
    public void borrarInicio();
    public void borrarFin();
    public void vaciar();
    public void mostrar();
    public void agregarOrd(T n);
    public void borrarElemento(T n);
    public void sustituirElementoPorPosicion(int pos, T n);
    public int cantElementos();
    public Nodo<T> obtenerElemento(T n);
    public void mostrarREC(Nodo l);
    public boolean existeElemento(T n);
    public String listar();    
    public String listarRecursivaDesc(T n);
    public String listarRecursivaAsc(T n);
    public Nodo<T> obtenerElementoPorPosicion(int pos);
    public void agregarOrdenadoConComparador(T dato, Comparator<T> comp);
    public ListaNodos<T> ordenar(Comparator<T> comp);
}