package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3_2ListarUsuarios {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void listarUsuariosVacio() {
        retorno = s.listarUsuarios();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    public void listarUsuariosSoloUnUsuario() {
        s.registrarUsuario("12345678", "Usuario01");
        retorno = s.listarUsuarios();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("Usuario01#12345678", retorno.getValorString());
    }

    @Test
    public void listarUsuariosIngresoOrdenado() {
        s.registrarUsuario("11111111", "Agustin");
        s.registrarUsuario("31221111", "Mario");
        s.registrarUsuario("11331111", "Viviana");
        retorno = s.listarUsuarios();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("Agustin#11111111|Mario#31221111|Viviana#11331111", retorno.getValorString());
    }

    @Test
    public void listarUsuariosIngresoDesordenado() {
        s.registrarUsuario("11331111", "Viviana");
        s.registrarUsuario("11111111", "Agustin");
        s.registrarUsuario("31221111", "Mario");
        retorno = s.listarUsuarios();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("Agustin#11111111|Mario#31221111|Viviana#11331111", retorno.getValorString());
    }

}
