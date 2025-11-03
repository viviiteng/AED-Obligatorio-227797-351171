package dominio;

import java.util.Objects;
import tads.ListaNodos;
import tads.Cola;


public class Estacion implements Comparable<Estacion>{
    private String Nombre;
    private String Barrio;
    private int Capacidad;
    private ListaNodos<Bicicleta> Bicicletas;
    private Cola<Usuario> Usuarios;
    
    public Estacion(String nombre, String barrio, int capacidad){
        this.Nombre = nombre;
        this.Barrio = barrio;
        this.Capacidad = capacidad;
    }
    public Estacion(String nombre){
        this.Nombre = nombre;
    }
    public Cola<Usuario> getUsuarios() {
        return Usuarios;
    }

    public void setUsuarios(Cola<Usuario> Usuarios) {
        this.Usuarios = Usuarios;
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
