package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class Test2_10DevolverBicicleta {
    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void DevolverBicicletaOk() {
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void DevolverBicicletaError01() {
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void DevolverBicicletaError02() {
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void DevolverBicicletaError03() {
        
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}
