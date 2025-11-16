package dominio;

public class Alquiler implements Comparable<Alquiler>{
   private Integer Id;
   private static Integer UltimoId = 1;
   private Bicicleta Bicicleta;
   private Usuario Usuario;
   private Estacion Estacion;

    public Alquiler(Bicicleta Bicicleta, Usuario Usuario, Estacion Estacion) {
        this.Id = UltimoId++;
        this.Bicicleta = Bicicleta;
        this.Usuario = Usuario;
        this.Estacion = Estacion;
    }

    public Bicicleta getBicicleta() {
        return Bicicleta;
    }

    public void setBicicleta(Bicicleta Bicicleta) {
        this.Bicicleta = Bicicleta;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    public Estacion getEstacion() {
        return Estacion;
    }

    public int getId() {
        return Id;
    }

    public void setEstacion(Estacion Estacion) {
        this.Estacion = Estacion;
    }

    @Override
    public int compareTo(Alquiler a) {
        return this.Id.compareTo(a.getId());
    }
   
}
