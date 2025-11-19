package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class Test3_8RankingTiposPorUso {
    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void RankingTiposPorUsoOk() {
        s.registrarBicicleta("ABC123", "URBANA");
        s.registrarBicicleta("ABC124", "URBANA");
        s.registrarBicicleta("ABC125", "MOUNTAIN");
        
        s.registrarUsuario("50228080", "Agustin");
        s.registrarUsuario("24555555", "Viviana");
        s.registrarUsuario("33344444", "Pepe");
      
        s.registrarEstacion("Estacion1", "Aguada", 5);
        
        s.asignarBicicletaAEstacion("ABC123", "Estacion1");
        s.asignarBicicletaAEstacion("ABC124", "Estacion1");
        s.asignarBicicletaAEstacion("ABC125", "Estacion1");
        
        s.alquilarBicicleta("50228080", "Estacion1");
        s.alquilarBicicleta("24555555", "Estacion1");
        s.alquilarBicicleta("33344444", "Estacion1");
        
        retorno = s.rankingTiposPorUso();
        
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ELECTRICA#0|MOUNTAIN#1|URBANA#2", retorno.getValorString());
    }
}
