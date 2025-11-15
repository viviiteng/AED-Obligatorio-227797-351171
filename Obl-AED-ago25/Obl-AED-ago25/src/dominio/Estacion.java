package dominio;

import java.util.Objects;
import tads.ListaNodos;
import tads.Cola;


public class Estacion implements Comparable<Estacion>{
    private String Nombre;
    private String Barrio;
    private int Capacidad;
    private ListaNodos<Bicicleta> Bicicletas;
    //hacer dos colas, una de espera para dar y otra para devolver bici
    private Cola<Usuario> colaUsuariosEspera;    
    private Cola<Usuario> colaUsuariosDevolucion;

    public Cola<Usuario> getColaUsuariosEspera() {
        return colaUsuariosEspera;
    }

    public void setColaUsuariosEspera(Cola<Usuario> colaUsuariosEspera) {
        this.colaUsuariosEspera = colaUsuariosEspera;
    }

    public Cola<Usuario> getColaUsuariosDevolucion() {
        return colaUsuariosDevolucion;
    }

    public void setColaUsuariosDevolucion(Cola<Usuario> colaUsuariosDevolucion) {
        this.colaUsuariosDevolucion = colaUsuariosDevolucion;
    }

    
    public Estacion(String nombre, String barrio, int capacidad){
        this.Nombre = nombre;
        this.Barrio = barrio;
        this.Capacidad = capacidad;
    }
    public Estacion(String nombre){
        this.Nombre = nombre;
    }
    
    public String getBarrio() {
        return Barrio;
    }

    public void setBarrio(String Barrio) {
        this.Barrio = Barrio;
    }

    public ListaNodos<Bicicleta> getBicicletas() {
        return Bicicletas;
    }

    public void setBicicletas(ListaNodos<Bicicleta> Bicicletas) {
        this.Bicicletas = Bicicletas;
    }
    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public int getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.Capacidad = capacidad;
    }
    
    @Override
    public boolean equals(Object obj) {
        Estacion e2 = (Estacion) obj;
        return this.Nombre.equals(e2.getNombre());  
    }

    @Override
    public int compareTo(Estacion o) {
        Estacion e2 = (Estacion)o;
        return this.Nombre.compareTo(e2.getNombre());
    }
   
}
