package sistemaAutogestion;

//AgustinFeijoo#351171 y Viviana Teng#227797
import java.util.Comparator;
import dominio.Estacion;
import dominio.Usuario;
import dominio.Bicicleta;
import dominio.RankingTipo;
import dominio.RegistroAlquiler;
import tads.ListaNodos;
import tads.Cola;
import tads.PilaNodos;
import tads.ListaDobleNodos;
import java.time.LocalDate;


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
        if (!existe) return Retorno.error2();
            
        Estacion estacion = estaciones.obtenerElemento(nuevo).getDato();
        if(estacion.getBicicletas().cantElementos()>0 || estacion.getColaUsuariosEspera().getCantNodos()>0)
            return Retorno.error3();
        
        estaciones.borrarElemento(estacion);
        return Retorno.ok();
    }

    @Override
    public Retorno asignarBicicletaAEstacion(String codigo, String nombreEstacion) {
        if (codigo == null || codigo.trim().isEmpty() ||
            nombreEstacion == null || nombreEstacion.trim().isEmpty())
        {
            return Retorno.error1();
        }
        Bicicleta b = new Bicicleta(codigo);//,null);
        if(!registroBicicletas.existeElemento(b))
        {
            return Retorno.error2();
        }
        Bicicleta bici = registroBicicletas.obtenerElemento(b).getDato();
        if(bici.isEnAlquiler() || bici.isEnMantenimiento()){
            return Retorno.error2();
        }
        Estacion e = new Estacion(nombreEstacion);//,null,0);
        if(!estaciones.existeElemento(e)){
            return Retorno.error3();
        }
        Estacion est = estaciones.obtenerElemento(e).getDato();
        if(est.getBicicletas().cantElementos() == est.getCapacidad()){
            return Retorno.error4();
        }
        if(deposito.existeElemento(bici)){
            est.getBicicletas().agregarOrd(bici);
            deposito.borrarElemento(bici);
            bici.setUbicacion(est.getNombre());
            return Retorno.ok();
        }else{
            Estacion e1 = new Estacion(bici.getUbicacion());//,null,0);
            est.getBicicletas().agregarFinal(bici);
            estaciones.obtenerElemento(e1).getDato().getBicicletas().borrarElemento(bici);
            return Retorno.ok();
        }
    }

    @Override
    public Retorno alquilarBicicleta(String cedula, String nombreEstacion) {
        if (cedula == null || cedula.trim().isEmpty() ||
            nombreEstacion == null || nombreEstacion.trim().isEmpty())
        {
            return Retorno.error1();
        }
        Usuario u = new Usuario(cedula);
        if(!registroUsuarios.existeElemento(u)){
            return Retorno.error2();
        }
        Usuario uEncontrado = registroUsuarios.obtenerElemento(u).getDato();
        Estacion e = new Estacion(nombreEstacion);
        if(!estaciones.existeElemento(e)){
            return Retorno.error3();
        }
        Estacion est = estaciones.obtenerElemento(e).getDato();
        if(est.getBicicletas().cantElementos()>0){
            Bicicleta biciUno = est.getBicicletas().obtenerElementoPorPosicion(0).getDato();
            biciUno.setEnAlquiler(true);
            est.getBicicletas().borrarElemento(biciUno);
            uEncontrado.setUnaBici(biciUno);
            RegistroAlquiler registro = new RegistroAlquiler(biciUno,uEncontrado,LocalDate.now(),est);
            registroAlquileres.push(registro);
        }else{
            est.getColaUsuariosEspera().encolar(uEncontrado);
        }
        return Retorno.ok();
    }

    @Override
    public Retorno devolverBicicleta(String cedula, String nombreEstacionDestino) {
        if (cedula == null || cedula.trim().isEmpty() ||
            nombreEstacionDestino == null || nombreEstacionDestino.trim().isEmpty())
        {
            return Retorno.error1();
        }          
        
        Estacion nuevo = new Estacion(nombreEstacionDestino);                
        Usuario unUsuario = new Usuario(cedula);
        
        boolean existe = registroUsuarios.existeElemento(unUsuario);
        if(!existe) {
            return Retorno.error2();
        }
        
        Usuario Useteado = registroUsuarios.obtenerElemento(unUsuario).getDato();        
        if (Useteado.getUnaBici().isEnAlquiler()==false) {
            return Retorno.error2();
        }
        
        if(!estaciones.existeElemento(nuevo)){
          return Retorno.error3();
        }
        Estacion unaEstacion = estaciones.obtenerElemento(nuevo).getDato();
        
        if(unaEstacion.getCapacidad() == unaEstacion.getBicicletas().cantElementos()){
            unaEstacion.getColaUsuariosDevolucion().encolar(Useteado);           
        }else{
            Bicicleta biciDevuelta = Useteado.getUnaBici();
            biciDevuelta.setEnAlquiler(false);
            asignarBicicletaAEstacion(biciDevuelta.getCodigo(), nombreEstacionDestino);
            Useteado.setUnaBici(null);
           
            if(unaEstacion.getColaUsuariosEspera().getCantNodos()>0){
                alquilarBicicleta(unaEstacion.getColaUsuariosEspera().getFrente().getDato().getCedula(), nombreEstacionDestino);
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
    
    @Override
    public Retorno listarUsuarios() {
        ListaNodos<Usuario> listaOrdenada = registroUsuarios.ordenar(Comparator.comparing(Usuario::getNombre));
        return Retorno.ok(listaOrdenada.listar());
    }

    @Override
    public Retorno listarBicisEnDeposito() {
        if(deposito.cantElementos()==0){
            return Retorno.ok("");
        }
        return Retorno.ok(deposito.listarRecursivaAsc(deposito.getLista().getDato()));        
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
        String ret="";
        if(unaEstacion.getBicicletas().cantElementos()==0){
            return Retorno.ok(ret); 
        }
        for (int i = 0; i < unaEstacion.getBicicletas().cantElementos(); i++) {
            Bicicleta b = unaEstacion.getBicicletas().obtenerElementoPorPosicion(i).getDato();
            ret += b.getCodigo() + "|";
        }
        ret = ret.substring(0, ret.length() - 1);
        return Retorno.ok(ret);        
    }

    @Override
    public Retorno estacionesConDisponibilidad(int n) {
        if(n<=1)return Retorno.error1();
        int cantEst = 0;
        for (int i = 0; i < estaciones.cantElementos(); i++) {
            Estacion e = estaciones.obtenerElementoPorPosicion(i).getDato();
            int espaciosDisp = e.getCapacidad() - e.getBicicletas().cantElementos();
            if(espaciosDisp > n){
                cantEst++;
            }
        }
        return Retorno.ok(cantEst);
    }

    @Override
    public Retorno ocupacionPromedioXBarrio() {
        
        int largo = estaciones.cantElementos();
        ListaNodos<String> Barrios = new ListaNodos();
        ListaNodos<Integer> CantXBarrio = new ListaNodos();
        ListaNodos<Integer> PorcentajeOcupXBarrio = new ListaNodos();
        String retorno = "";
        
        for (int i = 0; i < largo; i++) {
            Estacion e = estaciones.obtenerElementoPorPosicion(i).getDato();
            boolean barrioYaRegistrado = false;
            int posBarrio = -1;
            for (int j = 0; j < Barrios.cantElementos(); j++) {
                if(e.getBarrio().equals(Barrios.obtenerElementoPorPosicion(j).getDato())){
                    barrioYaRegistrado = true;
                    posBarrio = j;
                    break;
                }
            }
            int porcentajeOcupacion = e.getBicicletas().cantElementos() * 100 / e.getCapacidad();
            if(!barrioYaRegistrado){
                Barrios.agregarFinal(e.getBarrio());
                CantXBarrio.agregarFinal(1);
                PorcentajeOcupXBarrio.agregarFinal(porcentajeOcupacion);
            }else{
                int cantXBarrio = CantXBarrio.obtenerElementoPorPosicion(posBarrio).getDato();
                cantXBarrio++;
                CantXBarrio.sustituirElementoPorPosicion(posBarrio, cantXBarrio);

                int porcentajeAnt = PorcentajeOcupXBarrio.obtenerElementoPorPosicion(posBarrio).getDato();
                int nuevoPromedio = (porcentajeAnt * (cantXBarrio - 1) + porcentajeOcupacion) / cantXBarrio;

                PorcentajeOcupXBarrio.sustituirElementoPorPosicion(posBarrio, nuevoPromedio);
            }
        }
        
        for (int i = 0; i < Barrios.cantElementos(); i++) {
            String barrio = Barrios.obtenerElementoPorPosicion(i).getDato();
            int porcentajeOcupXBarrio = PorcentajeOcupXBarrio.obtenerElementoPorPosicion(i).getDato();
            retorno+= barrio + "#" + porcentajeOcupXBarrio + "|";
        }
        retorno = retorno.substring(0, retorno.length() - 1);
        return Retorno.ok(retorno);
    }

    @Override
    public Retorno rankingTiposPorUso() {
       int cantBicisUrbanas = obtenerCantBicisPorAlquilerSegunTipo("URBANA");
       int cantBicisMountain = obtenerCantBicisPorAlquilerSegunTipo("MOUNTAIN");
       int cantBicisElectrica = obtenerCantBicisPorAlquilerSegunTipo("ELECTRICA");
       RankingTipo urbana = new RankingTipo(cantBicisUrbanas,"URBANA");
       RankingTipo mountain = new RankingTipo(cantBicisMountain,"MOUNTAIN");
       RankingTipo electrica = new RankingTipo(cantBicisElectrica,"ELECTRICA");
       ListaNodos<RankingTipo> rankingTipos = new ListaNodos();
       rankingTipos.agregarOrd(urbana);
       rankingTipos.agregarOrd(mountain);
       rankingTipos.agregarOrd(electrica); 
       return Retorno.ok(rankingTipos.listar());
    }
    

    @Override
    public Retorno usuariosEnEspera(String nombreEstacion) {
        String retorno = "";
        Estacion e = estaciones.obtenerElemento(new Estacion(nombreEstacion)).getDato();
        for (int i = 0; i < e.getColaUsuariosEspera().getCantNodos(); i++) {
            Usuario u = e.getColaUsuariosEspera().obtenerLista().obtenerElementoPorPosicion(i).getDato();
            retorno += u.getNombre() + "|";
        }
        retorno = retorno.substring(0, retorno.length() - 1);
        return Retorno.ok(retorno);
    }

    @Override
    public Retorno usuarioMayor() {
        ListaNodos<RegistroAlquiler> RegistrosdePila = registroAlquileres.ConvertirPilaLista(registroAlquileres);
        
        int cedulaMostrar=0;
        int max = 0;
        for (int i = 0; i < registroUsuarios.cantElementos(); i++) {            
            String cedula = registroUsuarios.obtenerElementoPorPosicion(i).getDato().getCedula();
                int contadorBicis = 0;
                for (int j = 0; j < RegistrosdePila.cantElementos(); j++) {
                    RegistroAlquiler r = RegistrosdePila.obtenerElementoPorPosicion(j).getDato();

                    if (r.getUnUsuario().getCedula().equals(cedula)) {
                        contadorBicis++;
                    }                   
                }
                if(contadorBicis>max){
                    max = contadorBicis;
                    cedulaMostrar=Integer.parseInt(cedula);
                }else if(contadorBicis==max){
                    int newCedula = Integer.parseInt(cedula);
                    if(cedulaMostrar > newCedula){
                        cedulaMostrar = newCedula;
                    }                    
                }
        }
        return Retorno.ok("" + cedulaMostrar);
    }
    
    public Bicicleta obtenerBiciEnDeposito(String codigo) {
        
        Bicicleta b = new Bicicleta (codigo);
        if(!deposito.existeElemento(b)){
            return null;
        }
        return deposito.obtenerElemento(b).getDato();
    }
    
    public Bicicleta obtenerBiciEnEstacion(String codigo, String nombreEstacion) {
        
        Estacion nueva = new Estacion (nombreEstacion);
        if(!estaciones.existeElemento(nueva)){
            return null;
        }
        Estacion unaEstacion = estaciones.obtenerElemento(nueva).getDato();
        
        Bicicleta b = new Bicicleta (codigo);
        if(!unaEstacion.getBicicletas().existeElemento(b)){
            return null;
        }
        
        return unaEstacion.getBicicletas().obtenerElemento(b).getDato();
    }
    
    
    public Usuario obtenerUsuarioTesting(String cedula) {
        
        Usuario b = new Usuario (cedula);
        if(!registroUsuarios.existeElemento(b)){
            return null;
        }
        return registroUsuarios.obtenerElemento(b).getDato();
    }
    public Bicicleta obtenerBiciEnBicicletas(String codigo) {
        
        Bicicleta b = new Bicicleta (codigo);
        if(!registroBicicletas.existeElemento(b)){
            return null;
        }
        return deposito.obtenerElemento(b).getDato();
    }
    
    public ListaNodos<Estacion> obtenerEstacionesTest() {
        return estaciones;
    }
    
    public Estacion obtenerEstacionTest(String nombre) {
        
        Estacion b = new Estacion (nombre);
        if(!estaciones.existeElemento(b)){
            return null;
        }
        return estaciones.obtenerElemento(b).getDato();
    }
    
    public int obtenerCantBicisPorAlquilerSegunTipo(String tipo) {        
        int contador = 0;
        for (int i = 0; i < registroBicicletas.cantElementos(); i++) {
            Bicicleta unaBici = registroBicicletas.obtenerElementoPorPosicion(i).getDato();
            if(unaBici.getTipo() == tipo && unaBici.isEnAlquiler()){
                contador++;
            }           
        }
        return contador;
    }

}
