package co.vinni.operaciones;

import co.vinni.datos.Equipo;
import co.vinni.datos.Jugador;
import co.vinni.datos.Pais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author  : Vinni 2026
 */
public class GestionEquipoTest {
    private GestionEquipo servicio;
    Equipo equipoPrueba;
    Jugador jugadorPrueba;

    @BeforeEach
    public void setUp() {
        servicio = new GestionEquipo();
        equipoPrueba = servicio.crearEquipo("FC Java", Pais.ARGENTINA); // Pais nulo para el ejemplo
        LocalDate fechaNacimiento = LocalDate.of(2000,5,5);
        jugadorPrueba = servicio.crearJugador("Mario","Gomez",fechaNacimiento);
    }
    @Test
    public void crearEquipoOk(){
        assertNotNull(equipoPrueba);
    }
    @Test
    public void crearEquipoSinNombre(){
        Equipo equipo = servicio.crearEquipo("", Pais.COLOMBIA);
        assertNull(equipo);
    }
    @Test
    public void crearEquipoNombreNUlo(){
        Equipo equipo = servicio.crearEquipo(null,Pais.COLOMBIA);
        assertNull(equipo);
    }
    @Test
    public void crearEquipoPaisNulo(){
        Equipo equipo = servicio.crearEquipo("Mario",null);
        assertNull(equipo);
    }
    @Test
    public void crearJugadorOk(){
        LocalDate fechaNacimiento = LocalDate.of(2000,5,12);
        Jugador jugador = servicio.crearJugador("Mario","Rossi",fechaNacimiento);
        assertNotNull(jugador);
    }
    @Test
    public void crearJugadorNombresNulo(){
        LocalDate fechaNacimiento = LocalDate.of(2000,05,05);
        Jugador jugador = servicio.crearJugador(null,"Gomez g",fechaNacimiento);
        assertNull(jugador);
    }
    @Test
    public void crearJugadorSinNombres(){
        LocalDate fechaNacimiento = LocalDate.of(2000,05,05);
        Jugador jugador = servicio.crearJugador("","Gomez g",fechaNacimiento);
        assertNull(jugador);
    }
    @Test
    public void crearJugadorApellidosNulo(){
        LocalDate fechaNacimiento = LocalDate.of(2000,05,05);
        Jugador jugador = servicio.crearJugador("Mario",null,fechaNacimiento);
        assertNull(jugador);
    }
    @Test
    public void crearJugadorSinApellidos(){
        LocalDate fechaNacimiento = LocalDate.of(2000,05,05);
        Jugador jugador = servicio.crearJugador("Mario","",fechaNacimiento);
        assertNull(jugador);
    }
    @Test
    public void crearJugadorFechaNacimientoNulo(){
        Jugador jugador = servicio.crearJugador("Mario","Gomez g",null);
        assertNull(jugador);
    }
    @Test
    public void asignarJugadorEquipoOk(){

        boolean respuesta = servicio.adicionarJugadorEquipo(equipoPrueba,jugadorPrueba);
        assertTrue(respuesta);
    }
    @Test
    public void asignarJugadorAEquipoNulo(){
        boolean respuesta = servicio.adicionarJugadorEquipo(null,jugadorPrueba);
        assertFalse(respuesta);
    }
    @Test
    public void asignarJugadorNuloAEquipo(){
        boolean respuesta = servicio.adicionarJugadorEquipo(equipoPrueba,null);
        assertFalse(respuesta);
    }
    @Test
    public void aisgnarJugadorEquipoLleno(){
        Equipo equipo = new Equipo();
        equipo.modificarNombre("FC");
        boolean respuesta = true;
        //23? 0 12?
        for(int i = 1;i<=12;i++){
            LocalDate fechaNacimiento = LocalDate.of(2005,2,i);
            Jugador jugador = new Jugador("Mario "+i,"Lopez",fechaNacimiento);
             respuesta = servicio.adicionarJugadorEquipo(equipo,jugador);
        }
        assertFalse(respuesta);
    }
    @Test
    public void obtenerEdadOK(){
        int edad = servicio.obtenerEdad(jugadorPrueba.obtenerFechaNacimiento());
        assertEquals(26,edad);
    }
    @Test
    public void obtenerEdadParametroNulo(){
        int edad = servicio.obtenerEdad(null);
        assertEquals(-1,edad);
    }
    @Test
    public void obtenerCantidadJugadoresOk(){
        Equipo equipo = new Equipo();
        equipo.modificarNombre("FC");
        for(int i = 1;i<=5;i++){
            LocalDate fechaNacimiento = LocalDate.of(2005,i,i);
            Jugador jugador = new Jugador("Mario "+i,"Lopez",fechaNacimiento);
            servicio.adicionarJugadorEquipo(equipo,jugador);
        }
        int cantidadJugadores=servicio.getCantidadJugadores(equipo);
        assertEquals(5,cantidadJugadores );
    }
    @Test
    public void obtenerCantidadJugadoresEquipoNulo(){
        int cantidadJugadores = servicio.getCantidadJugadores(null);
        assertEquals(-1,cantidadJugadores);
    }


}
