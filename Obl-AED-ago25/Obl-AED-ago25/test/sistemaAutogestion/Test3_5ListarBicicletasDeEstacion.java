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
    public void listarBicicletasDeEstacionVacia() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        s.registrarEstacion("Estacion2", "Centro", 10);
        retorno = s.listarBicicletasDeEstacion("Estacion2");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    public void listarBicicletasDeEstacionSoloUnaBici() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        s.registrarEstacion("Estacion2", "Centro", 10);
        s.asignarBicicletaAEstacion("ABC123", "Estacion2");
        
        retorno = s.listarBicicletasDeEstacion("Estacion2");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ABC123", retorno.getValorString());
    }

    @Test
    public void listarBicicletasDeEstacionOrdenado() {
        
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        s.registrarBicicleta("ABC124", "MOUNTAIN");
        s.registrarBicicleta("ABC125", "MOUNTAIN");
        
        s.registrarEstacion("Estacion2", "Centro", 10);
        
        s.asignarBicicletaAEstacion("ABC123", "Estacion2");
        s.asignarBicicletaAEstacion("ABC124", "Estacion2");
        s.asignarBicicletaAEstacion("ABC125", "Estacion2");
                
        retorno = s.listarBicicletasDeEstacion("Estacion2");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ABC123|ABC124|ABC125", retorno.getValorString());
    }

    @Test
    public void listarBicisEnDepositoIngresoDesordenado() {
        s.registrarBicicleta("ABC123", "MOUNTAIN");
        s.registrarBicicleta("ABC124", "MOUNTAIN");
        s.registrarBicicleta("ABC125", "MOUNTAIN");
        
        s.registrarEstacion("Estacion2", "Centro", 10);
        
        s.asignarBicicletaAEstacion("ABC125", "Estacion2");
        s.asignarBicicletaAEstacion("ABC123", "Estacion2");
        s.asignarBicicletaAEstacion("ABC124", "Estacion2");
        
                
        retorno = s.listarBicicletasDeEstacion("Estacion2");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ABC123|ABC124|ABC125", retorno.getValorString());
    }
}
