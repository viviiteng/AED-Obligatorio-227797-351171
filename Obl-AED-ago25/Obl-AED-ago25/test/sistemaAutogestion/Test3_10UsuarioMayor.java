package sistemaAutogestion;
 
import org.junit.Before;

import org.junit.Test;

import static org.junit.Assert.*;

public class Test3_10UsuarioMayor {

    private Retorno retorno;

    private final IObligatorio s = new Sistema();
 
    @Before

    public void setUp() {

        s.crearSistemaDeGestion();

    }
 
    @Test

    public void usuarioMayorUnUsuario() {
        s.registrarEstacion("Estacion2", "Centro", 10);
        s.registrarUsuario("50228080", "Agustin");
        s.registrarUsuario("47387057", "Pepe");
        s.registrarBicicleta("ABC123", "URBANA");
        s.registrarBicicleta("ABD423", "URBANA");
        s.registrarBicicleta("123ABC", "MOUNTAIN"); 
        
        s.asignarBicicletaAEstacion("ABC123", "Estacion2");
        s.asignarBicicletaAEstacion("ABD423", "Estacion2");
        s.asignarBicicletaAEstacion("123ABC", "Estacion2");
        
        s.alquilarBicicleta("50228080", "Estacion2");
        s.alquilarBicicleta("50228080", "Estacion2");
        s.alquilarBicicleta("47387057", "Estacion2");

        retorno = s.usuarioMayor();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("50228080", retorno.getValorString());

    }

    @Test

    public void usuarioMayorIgualCedulas() {
        s.registrarEstacion("Estacion2", "Centro", 10);
        s.registrarUsuario("50228080", "Agustin");
        s.registrarUsuario("47387057", "Pepe");
        
        s.registrarBicicleta("ABC123", "URBANA");
        s.registrarBicicleta("ABD423", "URBANA");
        s.registrarBicicleta("123ABC", "MOUNTAIN"); 
        s.registrarBicicleta("ATE423", "MOUNTAIN");

        s.asignarBicicletaAEstacion("ABC123", "Estacion2");
        s.asignarBicicletaAEstacion("ABD423", "Estacion2");
        s.asignarBicicletaAEstacion("123ABC", "Estacion2");
        s.asignarBicicletaAEstacion("ATE423", "Estacion2");

        s.alquilarBicicleta("50228080", "Estacion2");
        s.alquilarBicicleta("50228080", "Estacion2");
        s.alquilarBicicleta("47387057", "Estacion2");
        s.alquilarBicicleta("47387057", "Estacion2");

        retorno = s.usuarioMayor();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("47387057", retorno.getValorString());
    }

}

 