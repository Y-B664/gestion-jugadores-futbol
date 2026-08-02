import java.util.*;

public class App {

    static final int MAX_JUGADORES = 2;
    static final int NUMERO_POSICIONES = 11;
    static Map<Integer,List<String[]>> posiciones;
    //ansi codes
    //SGR
    //\033[PARAMETROSm
    //\033[efecto;efectomtexto
    //\033[0m -- cierre
    static final String AMARILLO_FONDO = "\033[5;48;5;222;194;150m";
    static final String AGUAMARINA_FONDO = "\033[48;2;3;166;150m";
    static final String AGUAMARINA_CLARO_FONDO = "\033[48;2;122;191;184m";
    static final String AZUL_FONDO = "\033[1;48;2;45;97;166m";
    static final String AGUAMARINA_FUENTE = "\033[1;5;5;38;2;3;166;150m";
    static final String ROJO_FUENTE = "\033[1;38;2;242;75;106m";
    static final String BLANCO_FUENTE = "\033[1;38;5;222;194;150m";
    static final String VERDE_FUENTE = "\033[1;38;2;157;213;80m";
    static final String AZUL_FUENTE = "\033[1;38;2;45;97;166m";
    static final String LINEA_ARRIBA = "\033[53m";
    static final String LINEA ="\u2500";
    static final String LINEA_VERTICAL ="\u2502";
    static final String NEGRILLA = "\033[1m";
    static final String CERRAR = "\033[0m";
    static final String RESET = "\u001B[0m";
    

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in); 
        int opcion;
        crear_posiciones();
        System.out.println(AMARILLO_FONDO + AGUAMARINA_FUENTE + "\u250c" + LINEA.repeat(40) + "\u2510" +CERRAR);
        System.out.println(AMARILLO_FONDO + AGUAMARINA_FUENTE + LINEA_VERTICAL +" APP PARA GESTIONAR JUGADORES DE FÚTBOL " + LINEA_VERTICAL+CERRAR);
        System.out.println(AMARILLO_FONDO + AGUAMARINA_FUENTE + "\u2514" + LINEA.repeat(40) + "\u2518" +CERRAR);
        do{
            System.out.println();
            mostrar_menu();
            System.out.print(RESET);
            opcion = validar_entrada_numerica(sc, NEGRILLA + "Por favor ingrese la opcion: " +CERRAR);
            switch (opcion) {
                case 0 -> System.out.println(VERDE_FUENTE+"¡¡Gracias por haber utilizado el programa.!!"+CERRAR);
                case 1 -> registrar_usuario(sc);
                case 2 -> listar_jugadores();
                default -> System.out.println(ROJO_FUENTE+"Opcion invalida !!"+CERRAR);
            }

        }while(opcion != 0);
        
        sc.close();
            }

    public static void mostrar_menu(){
        
        System.out.println(AMARILLO_FONDO + AGUAMARINA_FUENTE + "\u250c" + LINEA.repeat(40) + "\u2510"+ CERRAR);
        System.out.printf(AMARILLO_FONDO + AGUAMARINA_FUENTE +LINEA_VERTICAL+"%s%s%s"+LINEA_VERTICAL+ CERRAR+"\n"," ".repeat(18),"MENÚ"," ".repeat(18));
        //System.out.print(RESET);
        System.out.printf(AMARILLO_FONDO + AGUAMARINA_FUENTE+"\u251c%S\u2524" + CERRAR +"\n",LINEA.repeat(40));
        System.out.printf(NEGRILLA+LINEA_VERTICAL+"%-40s"+LINEA_VERTICAL+"\n"," 1. Registrar jugador.");
        System.out.printf(LINEA_VERTICAL+"%-40s"+LINEA_VERTICAL+"\n"," 2. Listar jugadores registrados.");
        System.out.printf(LINEA_VERTICAL+"%-40s"+LINEA_VERTICAL+"\n"," 0. Salir del programa.");
        System.out.println( "\u2514" + LINEA.repeat(40) + "\u2518");
    }
    public static void crear_posiciones(){
        posiciones = new TreeMap<>(Comparator.reverseOrder());

        for(int i = 1;i<NUMERO_POSICIONES+1;i++){
            List<String[]> jugadores = new ArrayList<>();
            posiciones.put(i, jugadores);
        }
        System.out.println("Done");
    } 

    public static void registrar_usuario(Scanner sc){
        
        List<String> posicionesDisponibles = posiciones_disponibles();
        if(posicionesDisponibles==null){
            System.out.println(ROJO_FUENTE+"No se pueden registrar más posiciones."+ CERRAR);
            System.out.println();
            return;
        }
        int numeroPosicionesDisponibles = posicionesDisponibles.size();
        String StringPosicionesDisponibles = posicionesDisponibles.toString();
        int espacioTitulo = 5+StringPosicionesDisponibles.length()/2;

        System.out.println();
        System.out.printf(AMARILLO_FONDO + AGUAMARINA_FUENTE +LINEA_VERTICAL+"%s%s%s"+LINEA_VERTICAL+ CERRAR+"\n"," ".repeat(espacioTitulo),"REGISTRAR AL JUGADOR"," ".repeat(espacioTitulo));
        System.out.printf(AZUL_FUENTE+"Hay %d posiciones disponibles: " + CERRAR , numeroPosicionesDisponibles);
        System.out.println(NEGRILLA+ StringPosicionesDisponibles + CERRAR);
        System.out.println();
        int posicion = validar_posicion(sc);
        String[] jugador = obtener_datos_jugador(sc);
        System.out.println();
        guardar_jugador(jugador, posicion);
    }

    public static void listar_jugadores(){


        System.out.println();
        System.out.printf(AMARILLO_FONDO+ AGUAMARINA_FUENTE + LINEA_VERTICAL+"%s%s%s"+LINEA_VERTICAL+CERRAR+"\n"," ".repeat(16),"LISTADO DE JUGADORES"," ".repeat(16));
        System.out.printf( "\u251c%s\u252c%s\u252c%s\u2524"+ CERRAR +"\n" ,LINEA.repeat(15),LINEA.repeat(18),LINEA.repeat(17));
        System.out.printf(  NEGRILLA + "\u2502  %-13s\u2502  %-15s \u2502  %-15s\u2502"+ CERRAR +"\n","POSICIÓN","NOMBRE","APELLIDO");
        System.out.printf( "\u251c%s\u253c%s\u253c%s\u2524"+ CERRAR +"\n" ,LINEA.repeat(15),LINEA.repeat(18),LINEA.repeat(17));


        for(Map.Entry<Integer,List<String[]>> dato : posiciones.entrySet()){
            if(dato.getValue().size() != 0){
                for(String[] jugador : dato.getValue()){
                    System.out.printf( "%-3s%-13d%-3s%-16s%-3s%-15s"+LINEA_VERTICAL+ CERRAR +"\n",LINEA_VERTICAL,dato.getKey(),LINEA_VERTICAL,jugador[0],LINEA_VERTICAL,jugador[1]);
                    System.out.printf( NEGRILLA+"\u251c%s\u253c%s\u253c%s\u2524"+ CERRAR +"\n" ,LINEA.repeat(15),LINEA.repeat(18),LINEA.repeat(17));

                }
                
            }
        }

    }

    public static void guardar_jugador(String[] jugador, int posicion){
        List<String[]> jugadores = posiciones.get(posicion);
        jugadores.add(jugador);
        posiciones.put(posicion, jugadores);
        System.out.printf(VERDE_FUENTE +"Los datos ingresados son: %s %s de %s." + CERRAR+"\n",jugador[0], jugador[1],jugador[2]);
        System.out.printf(VERDE_FUENTE+"El jugador %s ha sido registrado correctamente en la posición %d.\n",jugador[0],posicion);
        System.out.print(CERRAR);
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
            System.out.println(ROJO_FUENTE+"El valor ingresado no es un número;"+CERRAR);
            System.out.println();

        }
    }

    public static int validar_posicion(Scanner sc){
        int posicion;
        while(true){
            posicion = validar_entrada_numerica(sc, NEGRILLA+"Ingresar la posicion: ");
            if( posicion>=1 && posicion <= NUMERO_POSICIONES){
                if(posicion_disponible(posicion)){
                 return posicion;
                }else{
                    System.out.println(ROJO_FUENTE+"Esta posición ya tiene 2 jugadores asignados."+CERRAR);
                    System.out.println();
                }
            }
            System.out.println(ROJO_FUENTE+"Posición invalida."+CERRAR);
            System.out.println();


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
        System.out.println(NEGRILLA+"Por favor, ingresar los siguientes datos");
        System.out.print(AGUAMARINA_FUENTE+ "Nombre: "+CERRAR);
        nombre = sc.nextLine().strip();
        System.out.print(AGUAMARINA_FUENTE+ "Apellido: "+CERRAR);
        apellido = sc.nextLine().strip();
        System.out.print(AGUAMARINA_FUENTE+ "Ciudad de origen: "+CERRAR);
        origen = sc.nextLine().strip();
        String[] jugador = {nombre, apellido, origen};
        return jugador;
    }
    }



