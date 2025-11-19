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
        s.registrarEstacion("Estacion1", "Aguada", 10);        
        s.registrarEstacion("Estacion2", "Centro", 10);
        s.registrarUsuario("50228080", "Agustin");
        s.registrarBicicleta("ABC123", "URBANA");        
        s.asignarBicicletaAEstacion("ABC123", "Estacion1");
        //((Sistema) s).obtenerUsuarioTesting("50228080").setUnaBici(((Sistema) s).obtenerBiciEnBicicletas("ABC123"));
        //((Sistema) s).obtenerBiciEnBicicletas("ABC123").setEnAlquiler(true);
        s.alquilarBicicleta("50228080", "Estacion1");
        retorno = s.devolverBicicleta("50228080", "Estacion1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void DevolverBicicletaError01() {
        s.registrarEstacion("Estacion1", "Aguada", 10);        
        s.registrarEstacion("Estacion2", "Centro", 10);
        s.registrarUsuario("50228080", "Agustin");
        s.registrarBicicleta("ABC123", "URBANA"); 
        
        retorno = s.devolverBicicleta("", "Estacion1");
        retorno = s.devolverBicicleta(null, "Estacion1");
        retorno = s.devolverBicicleta("50228080", "");
        retorno = s.devolverBicicleta("50228080", null);
        
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void DevolverBicicletaUsuarioInexistenteError02() {
        retorno = s.devolverBicicleta("50228080", "Estacion1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
    
    @Test
    public void DevolverBicicletaBiciAlquiladaError02() {
        s.registrarEstacion("Estacion1", "Aguada", 10);        
        s.registrarEstacion("Estacion2", "Centro", 10);
        s.registrarUsuario("50228080", "Agustin");
        s.registrarBicicleta("ABC123", "URBANA");        
        
        ((Sistema) s).obtenerUsuarioTesting("50228080").setUnaBici(((Sistema) s).obtenerBiciEnBicicletas("ABC123"));
        ((Sistema) s).obtenerBiciEnBicicletas("ABC123").setEnAlquiler(false);

        retorno = s.devolverBicicleta("50228080", "Estacion1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void DevolverBicicletaError03() {
        s.registrarEstacion("Estacion1", "Aguada", 10);           
        s.registrarUsuario("50228080", "Agustin");
        s.registrarBicicleta("ABC123", "URBANA");        
        
        ((Sistema) s).obtenerUsuarioTesting("50228080").setUnaBici(((Sistema) s).obtenerBiciEnBicicletas("ABC123"));
        ((Sistema) s).obtenerBiciEnBicicletas("ABC123").setEnAlquiler(true);
        
        retorno = s.devolverBicicleta("50228080", "Estacion2");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}
