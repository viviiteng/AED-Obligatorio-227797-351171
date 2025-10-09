package sistemaAutogestion;

//AgustinFeijoo#35 y números de estudiante de los integrantes del equipo

import dominio.Estacion;
import dominio.Usuario;
import dominio.Bicicleta;
import tads.ListaNodos;
public class Sistema implements IObligatorio {
    
    private ListaNodos<Estacion> estaciones;
    private ListaNodos<Usuario> registroUsuarios;
    private ListaNodos<Bicicleta> deposito;
    private ListaNodos<Bicicleta> registroBicicletas;



    @Override
    public Retorno crearSistemaDeGestion() {
        estaciones = new ListaNodos();
        registroUsuarios = new ListaNodos();
        deposito = new ListaNodos();
        registroBicicletas = new ListaNodos(); 
        return Retorno.ok();
    }

    @Override
    public Retorno registrarEstacion(String nombre, String barrio, int capacidad) {
        if (nombre == null || nombre.trim().isEmpty() || 
            barrio == null || barrio.trim().isEmpty()) 
        {
            return Retorno.error1();
        }
        if (capacidad <= 0) {
            return Retorno.error2();
        }
        if (estaciones.existeElemento(new Estacion(nombre, barrio, capacidad))) {
            return Retorno.error3();
        }
        estaciones.agregarOrd(new Estacion(nombre, barrio, capacidad));
        return Retorno.ok();

    }

    @Override
    public Retorno registrarUsuario(String cedula, String nombre) {
        if(cedula == null || cedula.trim().isEmpty() ||
           nombre == null || nombre.trim().isEmpty())
        {
            return Retorno.error1();
        }
        if(cedula.length() != 8 || !validarNumeros(cedula)){
            return Retorno.error2();
        }
        if(registroUsuarios.existeElemento(new Usuario(cedula,nombre)) ){
            return Retorno.error3();
        }
        registroUsuarios.agregarOrd(new Usuario(cedula,nombre));
        return Retorno.ok();
    }
    
    private boolean validarNumeros(String codigo) {
    for (int i = 0; i < codigo.length(); i++) {
        char c = codigo.charAt(i);
        if (!Character.isDigit(c)) {
            return false; 
        }
    }
    return true; 
}

    
    @Override
    public Retorno registrarBicicleta(String codigo, String tipo) {
        if(codigo == null || codigo.trim().isEmpty() ||
           tipo == null || tipo.trim().isEmpty())
        {
            return Retorno.error1();
        }else{
            Bicicleta bici = new Bicicleta(codigo,tipo);
       
            if(codigo.length() != 6){
                return Retorno.error2();
            }
           
            if(!(bici.getTipo().equals("URBANA") || bici.getTipo().equals("MOUNTAIN") || bici.getTipo().equals("ELECTRICA"))){
                return Retorno.error3();
            }
            if(registroBicicletas.existeElemento(bici)){
                return Retorno.error4();
            }
            bici.setUbicacion("Deposito");
            registroBicicletas.agregarOrd(bici);
            deposito.agregarFinal(bici);
            return Retorno.ok();
            
        }
    }

    @Override
    public Retorno marcarEnMantenimiento(String codigo, String motivo) {
       
        if (codigo == null || codigo.trim().isEmpty()
                || motivo == null || motivo.trim().isEmpty()) {
            return Retorno.error1();
        }
        Bicicleta bici;
        Bicicleta b = new Bicicleta(codigo);
        boolean existe = registroBicicletas.existeElemento(b);

        if (!existe) {
            return Retorno.error2();
        }
        
        bici = registroBicicletas.obtenerElemento(b).getDato();
        if (bici.isEnAlquiler()) {
            return Retorno.error3();
        }
        
        if (bici.isEnMantenimiento()) {
            return Retorno.error4();
        }

        if (!bici.getUbicacion().equals("DEPOSITO") && bici.getUbicacion() != null) {
            Estacion est = estaciones.obtenerElemento(new Estacion(bici.getUbicacion(), null, 0)).getDato();
            deposito.agregarOrd(bici);
            est.getBicicletas().borrarElemento(bici);
        }
        bici.setEnMantenimiento(true);
        bici.setMotivoMantenimiento(motivo);
        return Retorno.ok();
    }

    @Override
    public Retorno repararBicicleta(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()){
            return Retorno.error1();
        }
        Bicicleta b = new Bicicleta(codigo);
        boolean existe = registroBicicletas.existeElemento(b);

        if (!existe) {
            return Retorno.error2();
        }
        
        Bicicleta bici = registroBicicletas.obtenerElemento(b).getDato();
        if (!bici.isEnMantenimiento()) {
            return Retorno.error3();
        }
        
        bici.setEnMantenimiento(false);
        bici.setMotivoMantenimiento(null);
        return Retorno.ok();
    }

    @Override
    public Retorno eliminarEstacion(String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno asignarBicicletaAEstacion(String codigo, String nombreEstacion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno alquilarBicicleta(String cedula, String nombreEstacion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno devolverBicicleta(String cedula, String nombreEstacionDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno deshacerUltimosRetiros(int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno obtenerUsuario(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()){
            return Retorno.error1();
        }
        
        if(cedula.length() != 8 || !validarNumeros(cedula)){
            return Retorno.error2();
        }
        Usuario u = new Usuario (cedula,null);
        if(!registroUsuarios.existeElemento(u)){
            return Retorno.error3();
        }
        return Retorno.ok(registroUsuarios.obtenerElemento(u).getDato().toString());
    }
    
  
    public Bicicleta obtenerBiciEnDeposito(String codigo) {
        
        Bicicleta b = new Bicicleta (codigo);
        if(!deposito.existeElemento(b)){
            return null;
        }
        return deposito.obtenerElemento(b).getDato();
    }
    
    public Bicicleta obtenerBiciEnBicicletas(String codigo) {
        
        Bicicleta b = new Bicicleta (codigo);
        if(!registroBicicletas.existeElemento(b)){
            return null;
        }
        return deposito.obtenerElemento(b).getDato();
    }

    @Override
    public Retorno listarUsuarios() {
        return Retorno.ok(registroUsuarios.listar());
    }

    @Override
    public Retorno listarBicisEnDeposito() {
        return Retorno.ok(deposito.listar());
    }

    @Override
    public Retorno informaciónMapa(String[][] mapa) {
        
        if (mapa == null || mapa.length == 0) {
        return Retorno.ok("0#ambas|no existe");
        }
        
        int valorMaximo = 0;
        String tipoRetorno = "ambas";
        String resultadoExistencia = "";
        boolean existeAscendente = false;
        int [] estacionesPorColumna = new int[mapa[0].length];
      
        for (int i = 0; i < mapa.length; i++) {
            int contadorFila=0;
            for (int j = 0; j < mapa[i].length; j++) {
                if(!mapa[i][j].isEmpty()){
                    contadorFila++;
                    estacionesPorColumna[j]++;
                }
            }             
            if(contadorFila>valorMaximo){
                valorMaximo = contadorFila;
                tipoRetorno = "fila";
            }
        }
        
        for (int j = 0; j < estacionesPorColumna.length; j++) {
            if(estacionesPorColumna[j] > valorMaximo){
                valorMaximo = estacionesPorColumna[j];
                tipoRetorno = "columna";
            }else if (estacionesPorColumna[j] == valorMaximo && !tipoRetorno.equals("columna")){
                tipoRetorno = "ambas";
            }
        }
        
        for (int i = 0; i < estacionesPorColumna.length - 2; i++) {
                if (estacionesPorColumna[i] < estacionesPorColumna[i + 1] &&
                    estacionesPorColumna[i + 1] < estacionesPorColumna[i + 2]) {
                    existeAscendente = true;
                    break;
                }
        }  
        if(existeAscendente){
            resultadoExistencia = "existe";
        }else{
            resultadoExistencia = "no existe";
        }
        String retorno = valorMaximo +"#"+ tipoRetorno +"|"+ resultadoExistencia;
        return Retorno.ok(retorno);
    }
    
    @Override
    public Retorno listarBicicletasDeEstacion(String nombreEstacion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno estacionesConDisponibilidad(int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno ocupacionPromedioXBarrio() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno rankingTiposPorUso() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno usuariosEnEspera(String nombreEstacion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno usuarioMayor() {
        return Retorno.noImplementada();
    }

}
