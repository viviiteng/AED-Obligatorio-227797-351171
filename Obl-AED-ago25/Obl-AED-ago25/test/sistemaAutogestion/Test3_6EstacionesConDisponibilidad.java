package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class Test3_6EstacionesConDisponibilidad {
    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void EstacionesConDisponibilidadOk() {
        s.registrarBicicleta("BiciN1","MOUNTAIN");
        s.registrarBicicleta("BiciN2","ELECTRICA");
        s.registrarBicicleta("BiciN3","URBANA");
        s.registrarBicicleta("BiciN4","MOUNTAIN");
        s.registrarBicicleta("BiciN5","ELECTRICA");
        s.registrarBicicleta("BiciN6","URBANA");
        s.registrarBicicleta("BiciN7","MOUNTAIN");
        s.registrarBicicleta("BiciN8","ELECTRICA");
        s.registrarBicicleta("BiciN9","URBANA");
        s.registrarBicicleta("Bici10","MOUNTAIN");
        s.registrarBicicleta("Bici11","ELECTRICA");
        s.registrarBicicleta("Bici12","URBANA");
        
        s.registrarEstacion("Estacion1", "Centro", 15);
        s.registrarEstacion("Estacion2", "Aguada", 20);
        s.registrarEstacion("Estacion3", "Malvin", 25);
        s.registrarEstacion("Estacion4", "Centro", 15);
        s.registrarEstacion("Estacion5", "Palermo", 10);
        s.registrarEstacion("Estacion6", "Cordon", 5);
        s.registrarEstacion("Estacion7", "Palermo", 20);
        
        s.asignarBicicletaAEstacion("BiciN1", "Estacion1");
        s.asignarBicicletaAEstacion("BiciN2", "Estacion1");
        s.asignarBicicletaAEstacion("BiciN3", "Estacion2");
        s.asignarBicicletaAEstacion("BiciN4", "Estacion3");
        s.asignarBicicletaAEstacion("BiciN5", "Estacion4");
        s.asignarBicicletaAEstacion("BiciN6", "Estacion4");
        s.asignarBicicletaAEstacion("BiciN7", "Estacion4");
        s.asignarBicicletaAEstacion("BiciN8", "Estacion5");
        s.asignarBicicletaAEstacion("BiciN9", "Estacion6");
        s.asignarBicicletaAEstacion("Bici10", "Estacion6");
        s.asignarBicicletaAEstacion("Bici11", "Estacion6");
        s.asignarBicicletaAEstacion("Bici12", "Estacion7");
        
        retorno = s.estacionesConDisponibilidad(15);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(3, retorno.getValorEntero());
        retorno = s.estacionesConDisponibilidad(5);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(6, retorno.getValorEntero());
    }

    @Test
    public void EstacionesConDisponibilidadError01() {
        retorno = s.estacionesConDisponibilidad(1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.estacionesConDisponibilidad(0);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.estacionesConDisponibilidad(-1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }
}
