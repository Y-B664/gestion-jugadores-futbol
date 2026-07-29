import java.util.*;

public class App {

    static final int MAX_JUGADORES = 2;
    static final int NUMERO_POSICIONES = 11;
    static Map<Integer,List<String[]>> posiciones;
    //ansi codes
    static final String AMARILLO = "\u001B[33m";
    static final String RESET = "\u001B[0m";
    

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int opcion;
        crear_posiciones();

        do{
            System.out.println();
            mostrar_menu();
            System.out.print(RESET);
            opcion = validar_entrada_numerica(sc, "Por favor ingrese la opcion: ");
            switch (opcion) {
                case 0 -> System.out.println("Gracias por haber utilizado el programa.!!");
                case 1 -> registrar_usuario(sc);
                case 2 -> listar_jugadores();
                default -> System.out.println("Opcion invalida !!");
            }

        }while(opcion != 0);
        //System.out.println(posiciones);
        sc.close();
            }

    public static void mostrar_menu(){
        System.out.println(AMARILLO + "|" + "-".repeat(40) + "|" + AMARILLO);
        System.out.println("\u001B[0m" + "| APP PARA GESTIONAR JUGADORES DE FUTBOL |" + "\u001B[0m");
        System.out.println(AMARILLO + "|" + "-".repeat(40) + "|" + AMARILLO);
        System.out.print(RESET);
        System.out.printf("|%-40s|\n"," 1. Registrar jugador.");
        System.out.printf("|%-40s|\n"," 2. Listar jugadores registrados.");
        System.out.printf("|%-40s|\n"," 0. Salir del programa.");
        System.out.println(AMARILLO + "|" + "-".repeat(40) + "|" + AMARILLO);
    }
    public static void crear_posiciones(){
        posiciones = new TreeMap<>(Comparator.reverseOrder());

        for(int i = 1;i<12;i++){
            List<String[]> jugadores = new ArrayList<>();
            posiciones.put(i, jugadores);
        }
        System.out.println("Done");
    } 

    public static void registrar_usuario(Scanner sc){
        if(posiciones_disponibles()==null){
            System.out.println("No se pueden registrar mas posiciones.");
            return;
        }
        System.out.println(posiciones_disponibles().toString());
        int posicion = validar_posicion(sc);
        String[] jugador = obtener_datos_jugador(sc);
        System.out.println("jugador nombre: " + jugador[1]);
        guardar_jugador(jugador, posicion);
        
    }

    public static void listar_jugadores(){
        System.out.printf("|%s|\n","-".repeat(52));
        System.out.printf("|  %-13s|  %-15s |  %-15s|\n","Posicion","Nombre","Apellido");
        System.out.printf("|%s|\n","-".repeat(52));

        for(Map.Entry<Integer,List<String[]>> dato : posiciones.entrySet()){
            if(dato.getValue().size() != 0){
                for(String[] jugador : dato.getValue()){
                    System.out.printf("%-3s%-13d%-3s%-16s%-3s%-15s|\n","|",dato.getKey(),"|",jugador[0],"|",jugador[1]);
                    System.out.printf("|%s|\n","-".repeat(52));
                }
                
            }
        }

    }

    public static void guardar_jugador(String[] jugador, int posicion){
        List<String[]> jugadores = posiciones.get(posicion);
        jugadores.add(jugador);
        posiciones.put(posicion, jugadores);
        System.out.printf("El jugador %s, ha sido registrado correctamente en la posicion %d.\n",jugador[0],posicion);
        System.out.println();
    }
    
    public static int validar_entrada_numerica(Scanner sc, String mensaje){
        int entrada;
        while(true){
            System.out.print(mensaje);
            if(sc.hasNextInt() ){
                entrada = sc.nextInt();
                sc.nextLine();
                return entrada;
            }
            sc.nextLine();
            System.out.println("El valor ingresado no es un numero;");
        }
    }

    public static int validar_posicion(Scanner sc){
        int posicion;
        while(true){
            posicion = validar_entrada_numerica(sc, "Por favor ingrese la posicion: ");
            if( posicion>=1 && posicion <= NUMERO_POSICIONES){
                if(posicion_disponible(posicion)){
                 return posicion;
                }else{
                    System.out.println("Esta posicion ya tiene 2 jugadores asignados.");
                }
            }
            System.out.println("Posicion invalida.");

        }
    }
    public static List<String> posiciones_disponibles(){
        List<String> posicionesDisponibles = new ArrayList<>();
        for(Map.Entry<Integer, List<String[]>> dato : posiciones.entrySet()){
            if(dato.getValue()==null  || dato.getValue().size() != MAX_JUGADORES  ){
                posicionesDisponibles.add(dato.getKey().toString());

            }
        }
        if(posicionesDisponibles.size() == 0){
            return null;
        }
        System.out.println("Posiciones disponibles " + posicionesDisponibles.size());

        return posicionesDisponibles;
    }

    public static boolean posicion_disponible(int posicion){
        for(Map.Entry<Integer, List<String[]>> dato : posiciones.entrySet()){
            if(dato.getValue().size() != MAX_JUGADORES){
                if(dato.getKey() == posicion){
                    return true;
                }
            }
        }
        return false;
    }

    public static String[] obtener_datos_jugador(Scanner sc){

        String nombre;
        String apellido;
        String origen;

        System.out.println("Por favor Ingresar:");
        System.out.print("Nombre: ");
        nombre = sc.nextLine().strip();
        System.out.print("Apellido: ");
        apellido = sc.nextLine().strip();
        System.out.print("Ciudad de origen: ");
        origen = sc.nextLine().strip();
        String[] jugador = {nombre, apellido, origen};
        return jugador;
    }
    }



