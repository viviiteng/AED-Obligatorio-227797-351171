package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class Test3_9UsuariosEnEspera {
    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void UsuariosEnEsperaOk() {
        s.registrarEstacion("Estacion1", "Aguada", 5);
        s.registrarUsuario("50228080", "Agustin");
        s.registrarUsuario("24555555", "Viviana");
        s.alquilarBicicleta("50228080", "Estacion1");
        s.alquilarBicicleta("24555555", "Estacion1");
        retorno = s.usuariosEnEspera("Estacion1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("50228080|24555555", retorno.getValorString());
    }
}
