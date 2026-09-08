package co.vinni.datos;

import java.util.ArrayList;

/**
 * @author  : Vinni 2026
 */
public class Equipo {
    private String nombre;
    private Pais pais;
    private ArrayList<Jugador> jugadores;

    public Equipo() {
        this.pais = Pais.COLOMBIA;
        this.jugadores = new ArrayList<>();
    }
    public void modificarNombre(String nombre){
        this.nombre = nombre;
    }
    public  String obtenerNombre(){
        return this.nombre;
    }
    public void modificarPais(Pais pais){
        this.pais = pais;
    }
    public Pais obtenerPais(){
        return this.pais;
    }
    public void modificarJugadores(ArrayList<Jugador> jugadores){
        this.jugadores = jugadores;
    }
    public ArrayList<Jugador> obtenerJugadores(){
        return this.jugadores;
    }


}
