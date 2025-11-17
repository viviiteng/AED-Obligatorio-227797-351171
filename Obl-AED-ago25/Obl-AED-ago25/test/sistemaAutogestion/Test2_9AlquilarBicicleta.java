package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class Test2_9AlquilarBicicleta {
    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void AlquilarBicicletaOk() {
        s.registrarEstacion("Estacion1", "Aguada", 10);
        s.registrarUsuario("50228080", "Agustin");
        retorno = s.alquilarBicicleta("50228080", "Estacion1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void AlquilarBicicletaError01() {
        retorno = s.alquilarBicicleta(null, null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.alquilarBicicleta("", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.alquilarBicicleta("50228080", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.alquilarBicicleta("", "Estacion1");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void AlquilarBicicletaError02() {
        s.registrarEstacion("Estacion1", "Aguada", 10);
        retorno = s.alquilarBicicleta("50228080", "Estacion1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void AlquilarBicicletaError03() {
        s.registrarUsuario("50228080", "Agustin");
        retorno = s.alquilarBicicleta("50228080", "Estacion1");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}
