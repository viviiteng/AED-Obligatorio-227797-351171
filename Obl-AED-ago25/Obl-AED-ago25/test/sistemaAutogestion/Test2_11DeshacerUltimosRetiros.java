package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class Test2_11DeshacerUltimosRetiros {
    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void DeshacerUltimosRetirosOk() {
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void DeshacerUltimosRetirosError01() {
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void DeshacerUltimosRetirosError02() {
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void DeshacerUltimosRetirosError03() {
        
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}
