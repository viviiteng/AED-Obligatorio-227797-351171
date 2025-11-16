package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class Test3_5ListarBicicletasDeEstacion {
    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void Ok() {
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void Error01() {
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void Error02() {
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void Error03() {
        
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}
