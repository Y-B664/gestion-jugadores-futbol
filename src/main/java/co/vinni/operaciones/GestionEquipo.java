package co.vinni.operaciones;

import co.vinni.datos.Equipo;
import co.vinni.datos.Jugador;
import co.vinni.datos.Pais;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author  : Vinni 2026
 */

public class GestionEquipo {

    public Jugador crearJugador(String nombres, String apellidos, LocalDate fechaNacimiento){
        if(nombres == null || apellidos == null || nombres.isBlank() || apellidos.isBlank() || fechaNacimiento == null){
            return null;
        }
        return new Jugador(nombres,apellidos,fechaNacimiento);
    }
    public Equipo crearEquipo(String nombre, Pais pais){
        if(nombre == null || nombre.isBlank() || pais == null){
            return null;
        }
        Equipo equipo = new Equipo();
        equipo.modificarNombre(nombre);
        equipo.modificarPais(pais);
        return equipo;
    }

    public boolean adicionarJugadorEquipo(Equipo elEquipo, Jugador jugadorSel) {
        if(elEquipo == null || jugadorSel == null){
            return false;
        }
        ArrayList<Jugador> jugadores = elEquipo.obtenerJugadores();
        if(jugadores.size()==11){
            return false;
        }
        jugadores.add(jugadorSel);
        elEquipo.modificarJugadores(jugadores);
        return true;
    }

    public int obtenerEdad(LocalDate fechaNacimiento) {
        if(fechaNacimiento == null){
            return -1;
        }
        int fechaActual = LocalDate.now().getYear();
        int edad = fechaActual - fechaNacimiento.getYear();
        return edad;
    }

    public int getCantidadJugadores(Equipo elEquipo) {
        if(elEquipo == null){
            return -1;
        }
        return elEquipo.obtenerJugadores().size();
    }
}
