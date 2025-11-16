package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import dominio.Bicicleta;
import static org.junit.Assert.*;
public class Test2_6RepararBicicleta {
    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void RepararBicicletaOk() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        s.marcarEnMantenimiento("ABC123", "M");
        retorno = s.repararBicicleta("ABC123");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void RepararBicicletaError01() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        s.marcarEnMantenimiento("ABC123", "M");
        retorno = s.repararBicicleta("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.repararBicicleta("   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.repararBicicleta(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void RepararBicicletaError02() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        s.marcarEnMantenimiento("ABC123", "M");
        retorno = s.repararBicicleta("ZZZ123");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        
        retorno = s.repararBicicleta("abc123");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void RepararBicicletaError03() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        ((Sistema) s).obtenerBiciEnBicicletas("ABC123").setEnMantenimiento(false);
        retorno = s.repararBicicleta("ABC123");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}
