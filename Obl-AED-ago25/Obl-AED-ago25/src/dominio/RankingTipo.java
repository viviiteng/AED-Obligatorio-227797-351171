package dominio;
import java.util.Comparator;
 
public class RankingTipo implements Comparable<RankingTipo>{
    private int cantidadBici;
    private String NombreTipo;
 
    public int getCantidadBici() {
        return cantidadBici;
    }
 
    public void setCantidadBici(int cantidadBici) {
        this.cantidadBici = cantidadBici;
    }
 
    public String getNombreTipo() {
        return NombreTipo;
    }
 
    public void setNombreTipo(String NombreTipo) {
        this.NombreTipo = NombreTipo;
    }
 
    public RankingTipo(int cantidadBici, String NombreTipo) {
        this.cantidadBici = cantidadBici;
        this.NombreTipo = NombreTipo;
    }   
 
    @Override
    public String toString() {
        return cantidadBici + NombreTipo;
    }
    @Override
    public int compareTo(RankingTipo otro){        
        int cmp = Integer.compare(this.getCantidadBici(), otro.getCantidadBici());
        if (cmp != 0) return cmp;        
        return otro.getNombreTipo().compareTo(this.getNombreTipo());
    }
}