package dominio;

import java.util.Objects;

public class Usuario implements Comparable<Usuario>  {
    private String Cedula;
    private String Nombre;
    
    public Usuario(String cedula, String nombre){
        this.Cedula = cedula;
        this.Nombre = nombre;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public boolean equals(Object obj) {
        Usuario u2 = (Usuario) obj;
        return this.Cedula.equals(u2.getCedula());  
    }

    @Override
    public int compareTo(Usuario o) {
        Usuario u2 = (Usuario)o;
        return this.Nombre.compareTo(u2.getNombre());
    }

    @Override
    public String toString() {
        return this.Nombre + "#" + this.Cedula;
    }
    

}
