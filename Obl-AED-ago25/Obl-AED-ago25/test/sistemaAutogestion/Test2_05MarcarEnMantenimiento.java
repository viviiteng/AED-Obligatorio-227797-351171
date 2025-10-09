package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import dominio.Bicicleta;
import static org.junit.Assert.*;
public class Test2_05MarcarEnMantenimiento {
    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void marcarEnMantenimientoOk() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        retorno = s.marcarEnMantenimiento("ABC123", "motivo");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void marcarEnMantenimientoError01() {
        Bicicleta bici = new Bicicleta("ABC123", "MOUNTAIN");
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        
        retorno = s.marcarEnMantenimiento("", "motivo");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.marcarEnMantenimiento("ABC123", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.marcarEnMantenimiento("   ", "motivo");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.marcarEnMantenimiento("ABC123", "   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.marcarEnMantenimiento(null, "motivo");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.marcarEnMantenimiento("ABC123", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void marcarEnMantenimientoError02() {        
        retorno = s.marcarEnMantenimiento("ZZZ123", "motivo");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        
        retorno = s.marcarEnMantenimiento("ABC123", "motivo");
        retorno = s.marcarEnMantenimiento("abc123", "motivo");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
    
    @Test
    public void marcarEnMantenimientoError03() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        ((Sistema) s).obtenerBiciEnBicicletas("ABC123").setEnAlquiler(true);      
        
        retorno = s.marcarEnMantenimiento("ABC123", "motivo");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
    
    @Test
    public void marcarEnMantenimientoError04() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        ((Sistema) s).obtenerBiciEnBicicletas("ABC123").setEnMantenimiento(true);
        retorno = s.marcarEnMantenimiento("ABC123", "motivo");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

    }
}
