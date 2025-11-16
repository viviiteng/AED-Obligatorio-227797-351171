package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3_4MapaDeEstaciones {
    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }
    
    @Test
    public void matrizNull(){
        String [][] mat = null;
        retorno = s.informaciónMapa(mat);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("0#ambas|no existe", retorno.getValorString());
    }
    
    @Test
    public void matrizVacia(){
        String [][] mat = {};
        retorno = s.informaciónMapa(mat);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("0#ambas|no existe", retorno.getValorString());
        
    }
    @Test
    public void ejemplo1(){
       String [][] mat = {  {"","","","","",""},
                            {"","","","E3","",""},
                            {"","","","","",""},
                            {"E1","","","","E5",""},
                            {"","","","","",""},
                            {"","","E2","","E6",""},
                            {"","","","","E7",""},
                            {"","","","E4","",""}
                         };
        retorno = s.informaciónMapa(mat);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("3#columna|existe", retorno.getValorString());
        
    }
    @Test
    public void ejemplo2(){
        String [][] mat = {  {"","","","","",""},
                            {"","","","E3","",""},
                            {"","","","","",""},
                            {"E1","","","","E5",""},
                            {"","","","","",""},
                            {"","","E2","","E6",""},
                            {"","","","","",""},
                            {"","","","E4","",""}
                         };
        retorno = s.informaciónMapa(mat);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("2#ambas|existe", retorno.getValorString());
       
    }
    @Test
    public void ejemplo3(){
        String [][] mat = {  {"","","","","",""},
                            {"","","","E3","",""},
                            {"","","","","",""},
                            {"E1","","","","E5",""},
                            {"","","","","",""},
                            {"","","E2","","E6",""},
                            {"","E7","","","",""},
                            {"","","","E4","",""}
                         };
        retorno = s.informaciónMapa(mat);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("2#ambas|no existe", retorno.getValorString());
       
    }
    
    @Test
    public void ejemplo4(){
        String [][] mat = {  {"","","","","",""},
                            {"","","","E3","",""},
                            {"","","","","",""},
                            {"E1","","","","E5",""},
                            {"","","","","",""},
                            {"","","E2","","",""},
                            {"","","","","",""},
                            {"","","","","",""}
                         };
        retorno = s.informaciónMapa(mat);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("2#fila|no existe", retorno.getValorString());
       
    }
}
