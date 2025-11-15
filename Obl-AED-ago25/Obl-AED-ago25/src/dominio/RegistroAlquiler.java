
package dominio;
import java.time.*;

public class RegistroAlquiler implements Comparable<RegistroAlquiler> {
    private Bicicleta unaBicicleta;
    private Usuario unUsuario;
    private LocalDate fecha;
    private Estacion estacionOrigen;

    public RegistroAlquiler(Bicicleta unaBicicleta, Usuario unUsuario, LocalDate fecha, Estacion estacionOrigen) {
        this.unaBicicleta = unaBicicleta;
        this.unUsuario = unUsuario;
        this.fecha = fecha;
        this.estacionOrigen = estacionOrigen;
    }

    public Bicicleta getUnaBicicleta() {
        return unaBicicleta;
    }

    public void setUnaBicicleta(Bicicleta unaBicicleta) {
        this.unaBicicleta = unaBicicleta;
    }

    public Usuario getUnUsuario() {
        return unUsuario;
    }

    public void setUnUsuario(Usuario unUsuario) {
        this.unUsuario = unUsuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Estacion getEstacionOrigen() {
        return estacionOrigen;
    }

    public void setEstacionOrigen(Estacion estacionOrigen) {
        this.estacionOrigen = estacionOrigen;
    }

    @Override
    public boolean equals(Object obj) {
        RegistroAlquiler otro = (RegistroAlquiler)obj;
        return this.unaBicicleta.equals(otro.getUnaBicicleta());
    }

    @Override
    public int compareTo(RegistroAlquiler o) {
        return this.unaBicicleta.compareTo(o.getUnaBicicleta());    
    }    
    
    @Override
    public String toString() {
        return this.unaBicicleta.getCodigo() + "#" + this.unUsuario.getCedula() + "#" + estacionOrigen;
    }
}
