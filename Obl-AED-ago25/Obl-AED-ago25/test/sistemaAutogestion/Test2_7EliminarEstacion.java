package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class Test2_7EliminarEstacion {
    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void EliminarEstacionOk() {
        s.registrarEstacion("Estacion1", "Centro", 15);
        retorno = s.eliminarEstacion("Estacion1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void EliminarEstacionError01() {
        retorno = s.eliminarEstacion("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.eliminarEstacion(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void EliminarEstacionError02() {
        retorno = s.eliminarEstacion("Estacion");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void EliminarEstacionError03() {
        s.registrarBicicleta("BiciN1","MOUNTAIN");
        s.registrarUsuario("50228080", "Agustin");
        
        s.registrarEstacion("Estacion1", "Centro", 15);
        s.registrarEstacion("Estacion2", "Aguada", 10);
        
        s.asignarBicicletaAEstacion("BiciN1", "Estacion1");
        s.alquilarBicicleta("50228080", "Estacion2");
        
        retorno = s.eliminarEstacion("Estacion1");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
        retorno = s.eliminarEstacion("Estacion2");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}
