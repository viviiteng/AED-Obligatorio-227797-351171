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
        s.registrarEstacion("Estacion2", "Centro", 10);
        s.registrarUsuario("50228080", "Agustin");
        s.registrarUsuario("50220080", "Alana");
        s.registrarBicicleta("ABC123", "URBANA");
        s.registrarBicicleta("ABD423", "URBANA");        
        s.asignarBicicletaAEstacion("ABC123", "Estacion2");
        s.asignarBicicletaAEstacion("ABD423", "Estacion2");
        s.alquilarBicicleta("50228080", "Estacion2");
        s.alquilarBicicleta("50220080", "Estacion2");
        retorno = s.deshacerUltimosRetiros(2);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ABD423#50220080#ESTACION2|ABC123#50228080#ESTACION2", retorno.getValorString());
    }

    @Test
    public void DeshacerUltimosRetirosCeroError01() {
        retorno = s.deshacerUltimosRetiros(0);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void DeshacerUltimosRetirosError01() {
        retorno = s.deshacerUltimosRetiros(-2);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }    
}
