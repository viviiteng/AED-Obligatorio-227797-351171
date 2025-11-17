package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class Test2_8AsignarBicicletaAEstacion {
    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void AsignarBicicletaAEstacionOk() {
        s.registrarBicicleta("BiciN1", "MOUNTAIN");
        s.registrarEstacion("Estacion1", "Centro", 1);
        retorno = s.asignarBicicletaAEstacion("BiciN1", "Estacion1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void AsignarBicicletaAEstacionError01() {
        retorno = s.asignarBicicletaAEstacion(null, null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.asignarBicicletaAEstacion("", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.asignarBicicletaAEstacion("BiciN1", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.asignarBicicletaAEstacion("", "Estacion1");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void AsignarBicicletaAEstacionError02() {
        retorno = s.asignarBicicletaAEstacion("BiciN1", "Estacion1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        s.registrarBicicleta("BiciN1", "MOUNTAIN");
        s.marcarEnMantenimiento("BiciN1", "motivo");
        retorno = s.asignarBicicletaAEstacion("BiciN1", "Estacion1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void AsignarBicicletaAEstacionError03() {
        s.registrarBicicleta("BiciN1", "MOUNTAIN");
        retorno = s.asignarBicicletaAEstacion("BiciN1", "Estacion1");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}
