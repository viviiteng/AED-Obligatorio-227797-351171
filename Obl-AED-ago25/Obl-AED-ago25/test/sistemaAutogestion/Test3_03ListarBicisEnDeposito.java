package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3_03ListarBicisEnDeposito {
    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void listarBicisEnDepositoVacio() {
        retorno = s.listarBicisEnDeposito();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    public void listarBicisEnDepositoSoloUnUsuario() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        ((Sistema) s).obtenerBiciEnDeposito("ABC123").setEnMantenimiento(true);
        retorno = s.listarBicisEnDeposito();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ABC123#MOUNTAIN#Mantenimiento", retorno.getValorString());
    }

    @Test
    public void listarBicisEnDepositoIngresoPrimero() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        ((Sistema) s).obtenerBiciEnDeposito("ABC123").setEnMantenimiento(true);
        s.registrarBicicleta("ABC124", "MOUNTAIN");
        ((Sistema) s).obtenerBiciEnDeposito("ABC124").setEnMantenimiento(true);        
        s.registrarBicicleta("ABC125", "MOUNTAIN");
        ((Sistema) s).obtenerBiciEnDeposito("ABC125").setEnMantenimiento(false);        
        retorno = s.listarBicisEnDeposito();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ABC123#MOUNTAIN#Mantenimiento|ABC124#MOUNTAIN#Mantenimiento|ABC125#MOUNTAIN#Disponible", retorno.getValorString());
    }

    @Test
    public void listarBicisEnDepositoIngresoDesordenado() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        ((Sistema) s).obtenerBiciEnDeposito("ABC123").setEnMantenimiento(true);
        s.registrarBicicleta("ABC125", "MOUNTAIN");
        ((Sistema) s).obtenerBiciEnDeposito("ABC125").setEnMantenimiento(false);  
        s.registrarBicicleta("ABC124", "MOUNTAIN");
        ((Sistema) s).obtenerBiciEnDeposito("ABC124").setEnMantenimiento(true);        
              
        retorno = s.listarBicisEnDeposito();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ABC123#MOUNTAIN#Mantenimiento|ABC125#MOUNTAIN#Disponible|ABC124#MOUNTAIN#Mantenimiento", retorno.getValorString());
    }
}
