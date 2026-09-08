package co.vinni.datos;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author  : Vinni 2026
 */
public class Jugador {
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;

    public Jugador(String nombres, String apellidos, LocalDate fechaNacimiento) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
    }
    public void modificarNombres(String nombres){
        this.nombres = nombres;
    }
    public String obtenerNombres(){
        return this.nombres;
    }
    public void modificarApellidos(String apellidos){
        this.apellidos = apellidos;
    }
    public String obtenerApellidos(){
        return this.apellidos;
    }
    public void modificarFechaNacimiento(LocalDate fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }
    public LocalDate obtenerFechaNacimiento(){
        return this.fechaNacimiento;
    }

}
