package dominio;

public class Bicicleta implements Comparable<Bicicleta>{
   private String Codigo;
   private String Tipo;
   private boolean enAlquiler;
   private boolean enMantenimiento;   
   private String MotivoMantenimiento;
   private String Ubicacion;

   public Bicicleta(String Codigo, String Tipo) {
        this.Codigo = Codigo;
        this.Tipo = Tipo;
        this.enAlquiler = false;
        this.enMantenimiento = false;
        this.MotivoMantenimiento = null;
    }
   
    public Bicicleta(String Codigo) {
        this.Codigo = Codigo;
    }
   
   

    public boolean isEnAlquiler() {
        return enAlquiler;
    }

    public void setEnAlquiler(boolean enAlquiler) {
        this.enAlquiler = enAlquiler;
    }

    public String getMotivoMantenimiento() {
        return MotivoMantenimiento;
    }

    public void setMotivoMantenimiento(String MotivoMantenimiento) {
        this.MotivoMantenimiento = MotivoMantenimiento;
    }

    public boolean isEnMantenimiento() {
        return enMantenimiento;
    }

    public void setEnMantenimiento(boolean enMantenimiento) {
        this.enMantenimiento = enMantenimiento;
    }
   
    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getTipo() {
        return Tipo;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion.toUpperCase();
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo.toUpperCase();
    }

    
   
    @Override
    public boolean equals(Object obj) {
        Bicicleta b2 = (Bicicleta) obj;
        return this.Codigo.equals(b2.getCodigo());  
    }

    @Override
    public int compareTo(Bicicleta o) {
        Bicicleta b2 = (Bicicleta)o;
        return this.Codigo.compareTo(b2.getCodigo());
    }

    @Override
    public String toString() {
        String estado;
        if(this.enAlquiler){
            estado = "Alquilada";
        }else if(this.enMantenimiento){
            estado = "Mantenimiento";
        }else{
            estado = "Disponible";
        }
        return this.Codigo + "#" + this.Tipo + "#" + estado;
    }
    
}
