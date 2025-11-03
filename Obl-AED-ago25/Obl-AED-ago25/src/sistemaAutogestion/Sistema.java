package sistemaAutogestion;

//AgustinFeijoo#351171 y Viviana Teng#227797

import dominio.Estacion;
import dominio.Usuario;
import dominio.Bicicleta;
import dominio.RegistroAlquiler;
import tads.ListaNodos;
import tads.Cola;
import tads.PilaNodos;
import tads.ListaDobleNodos;

public class Sistema implements IObligatorio {
    
    private ListaNodos<Estacion> estaciones;
    private ListaNodos<Usuario> registroUsuarios;
    private ListaNodos<Bicicleta> deposito;
    private ListaNodos<Bicicleta> registroBicicletas;
    private PilaNodos<RegistroAlquiler> registroAlquileres;

    @Override
    public Retorno crearSistemaDeGestion() {
        estaciones = new ListaNodos();
        registroUsuarios = new ListaNodos();
        deposito = new ListaNodos();
        registroBicicletas = new ListaNodos(); 
        registroAlquileres = new PilaNodos();
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
            deposito.agregarFinal(bici);
            //cambie agregar ordenado a agregar final para el Req 3.3
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
        
        if (nombre == null || nombre.trim().isEmpty()){
            return Retorno.error1();
        }
        
        Estacion nuevo = new Estacion(nombre);
        boolean existe = estaciones.existeElemento(nuevo);
        if (!existe) {
            return Retorno.error2();
        }
        
        Estacion estacion = estaciones.obtenerElemento(nuevo).getDato();
        if(estacion.getBicicletas().cantElementos()>0 && estacion.getUsuarios().cantidadnodos()>0)
            return Retorno.error3();
        
        estaciones.borrarElemento(estacion);
        return Retorno.ok();
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
        if (cedula == null || cedula.trim().isEmpty()){
            return Retorno.error1();
        }
        if (nombreEstacionDestino == null || nombreEstacionDestino.trim().isEmpty()){
            return Retorno.error1();
        }
        Usuario unUsuario = new Usuario(cedula);
        boolean existe = registroUsuarios.existeElemento(unUsuario);
        Estacion nuevo = new Estacion(nombreEstacionDestino);
        Estacion unaEstacion = estaciones.obtenerElemento(nuevo).getDato();
        
        if(!existe && unUsuario.getUnaBici()==null){
            return Retorno.error2();
        }
        
        if(unaEstacion==null){
//        destion inexistente
          return Retorno.error3();
        }
        
        if(unaEstacion.getCapacidad()>5){
            //anclje? eran 5?
            unaEstacion.getUsuarios().encolar(unUsuario);
            //hay limite de encolacion?
        }else{
            asignarBicicletaAEstacion(unUsuario.getUnaBici().getCodigo(), nombreEstacionDestino);
            //se asume que en esa funcion ya se debe marcar bici en disponible
            if(unaEstacion.getUsuarios().cantidadnodos()>0){
                alquilarBicicleta(unUsuario.getUnaBici().getCodigo(), nombreEstacionDestino);
            }
        }
        return Retorno.ok();
    }

    @Override
    public Retorno deshacerUltimosRetiros(int n) {
        String mensaje = new String();
        if(n<=0){
            return Retorno.error1();
        }
        for (int i = 0; i < n; i++) {
            RegistroAlquiler unRegistro = registroAlquileres.poptop();
            String cedula = unRegistro.getUnUsuario().getCedula();
            String nombreEstacionDestino = unRegistro.getEstacionOrigen().getNombre();
            devolverBicicleta(cedula, nombreEstacionDestino);
            mensaje += (unRegistro.toString() + '|');
        }
        mensaje = mensaje.substring(0, mensaje.length() - 1);
        return Retorno.ok(mensaje);
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
        return Retorno.ok(deposito.listarRecursivaDesc(deposito.getLista()));
        //esta mal usar getLista()?
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
        Estacion nueva = new Estacion (nombreEstacion);
        Estacion unaEstacion = estaciones.obtenerElemento(nueva).getDato();
        if(unaEstacion.getBicicletas()!=null){
            unaEstacion.getBicicletas().listarRecursivaAsc(unaEstacion.getBicicletas().getLista());
            return Retorno.ok();
        }
        return Retorno.error1();
        //podemos hacer esto por mas que la letra no lo plantee?
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
