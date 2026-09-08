package co.vinni.gui;

import co.vinni.datos.Equipo;
import co.vinni.datos.Jugador;
import co.vinni.operaciones.GestionEquipo;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.CaretListener;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  : Vinni 2026
 */
public class VentanaGral extends JFrame {

    private final GestionEquipo servicio = new GestionEquipo();
    private Equipo elEquipo;

    private final List<Jugador> listaJugadores = new ArrayList<>();

    private JTextField txtNombreEquipo, txtNombreJugador, txtApellidoJugador ;
    private JDateChooser txtFechaNacimiento;
    private JComboBox<String> cbJugadores;
    private JTextArea txtAreaConsola;
    private JButton btnCrearJugador, btnAsignar;
    private final DateTimeFormatter formateadorFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");



    public VentanaGral() {
        setTitle("Gestión de Equipo Único y Arreglo de 20 Jugadores");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.decode("#010318"));
        setLayout(new BorderLayout(10, 10));

        initComponentes();
        deshabilitarControlesJugador(); // Bloqueados hasta que exista el equipo
    }

    private void initComponentes() {
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Color colorAzulOscuro = Color.decode("#010318");
        Color colorAmarillo = Color.decode("#ffe600");
        Color colorGris = Color.decode("#323232");
        Color colorMorado = Color.decode("#6e79dd");
        Color colorMoradoOscuro = Color.decode("#4754a5");
        panelIzquierdo.setBackground(colorAzulOscuro);
        // 1. Crear Único Equipo
        JPanel pnlEquipo = new JPanel(new GridLayout(2, 2, 6, 6));
        Font fuenteTitulo = new Font("Tahoma",Font.BOLD,14);
        Font fuenteEtiqueta = new Font("Tahoma",Font.BOLD,13);
        Font fuenteTexto = new Font("Tahoma",0,12);
        Color colorAzulClaro = Color.decode("#31ccee");
        Border bordeVacio = BorderFactory.createEmptyBorder(5,5,5,5);
        Border bordeLineaAzulClaro = BorderFactory.createLineBorder(colorAzulClaro,2,true);
        Border bordeLineaAzulOscuro = BorderFactory.createLineBorder(colorAzulOscuro,1,true);
        Border bordeCompuestoInputs = BorderFactory.createCompoundBorder(bordeLineaAzulClaro,bordeVacio);
        Border bordeCompuestoBotones = BorderFactory.createCompoundBorder(bordeVacio,bordeLineaAzulOscuro);
        pnlEquipo.setBorder(BorderFactory.createTitledBorder(bordeCompuestoInputs,"1. Inicializar Equipo",0,0,fuenteTitulo,colorAzulClaro));
        pnlEquipo.setBackground(Color.decode("#010318"));
        JLabel etiquetaE = new JLabel("Nombre del Equipo:");
        etiquetaE.setFont(fuenteEtiqueta);
        etiquetaE.setForeground(Color.white);
        etiquetaE.setHorizontalAlignment(0);
        etiquetaE.setVerticalAlignment(0);
        pnlEquipo.add(etiquetaE);
        txtNombreEquipo = new JTextField();
        txtNombreEquipo.setFont(fuenteTexto);
        pnlEquipo.add(txtNombreEquipo);
        JButton btnCrearEquipo = new JButton("Guardar Equipo");
        //btnCrearEquipo.setBackground(new Color(51, 111, 158)); // Azul
        btnCrearEquipo.setBackground(colorAmarillo);
        btnCrearEquipo.setFont(fuenteTitulo);
        btnCrearEquipo.setForeground(colorAzulOscuro);
        btnCrearEquipo.setBorder(bordeCompuestoBotones);
        btnCrearEquipo.setContentAreaFilled(false);
        btnCrearEquipo.setOpaque(true);
        btnCrearEquipo.setFocusPainted(false);

        pnlEquipo.add(new JLabel());
        pnlEquipo.add(btnCrearEquipo);

        // 2. Crear Jugador
        JPanel pnlJugador = new JPanel(new GridLayout(4, 2, 6, 6));
        pnlJugador.setBackground(colorAzulOscuro);
        pnlJugador.setBorder(BorderFactory.createTitledBorder(bordeCompuestoInputs,"2. Crear Jugador",0,0,fuenteTitulo,colorAzulClaro));
        JLabel etiquetaNombres = new JLabel("Nombres:");
        JLabel etiquetaApellidos = new JLabel("Apellidos:");
        JLabel etiquetaFecha = new JLabel("F. Nacimiento (dd/mm/yyyy):");
        //configuracion etiqueta nombres
        etiquetaNombres.setForeground(Color.white);
        etiquetaNombres.setFont(fuenteEtiqueta);
        etiquetaNombres.setHorizontalAlignment(0);
        //configuracion etiqeuta apellidos
        etiquetaApellidos.setForeground(Color.white);
        etiquetaApellidos.setFont(fuenteEtiqueta);
        etiquetaApellidos.setHorizontalAlignment(0);
        //configuracion etiqueta fecha
        etiquetaFecha.setForeground(Color.white);
        etiquetaFecha.setHorizontalAlignment(0);
        etiquetaFecha.setFont(fuenteEtiqueta);

        pnlJugador.add(etiquetaNombres);
        txtNombreJugador = new JTextField();
        txtNombreJugador.setFont(fuenteTexto);
        pnlJugador.add(txtNombreJugador);
        pnlJugador.add(etiquetaApellidos);
        txtApellidoJugador = new JTextField();
        txtApellidoJugador.setFont(fuenteTexto);
        pnlJugador.add(txtApellidoJugador);
        pnlJugador.add(etiquetaFecha);
         txtFechaNacimiento = new JDateChooser();
       // txtFechaNacimiento = new JTextField();
        txtFechaNacimiento.setFont(fuenteTexto);
        pnlJugador.add(txtFechaNacimiento);
        btnCrearJugador = new JButton("Crear Jugador");
        //btnCrearJugador.setBackground(new Color(51, 111, 158)); // Azul
        btnCrearJugador.setBackground(colorAmarillo);
        btnCrearJugador.setForeground(colorAzulOscuro);
        btnCrearJugador.setFont(fuenteTitulo);
        btnCrearJugador.setBorder(bordeCompuestoBotones);
        btnCrearJugador.setContentAreaFilled(false);
        btnCrearJugador.setOpaque(true);
        btnCrearJugador.setFocusPainted(false);
        pnlJugador.add(new JLabel());
        pnlJugador.add(btnCrearJugador);

        // 3. Adicionar al único equipo existente
        JPanel pnlAsignacion = new JPanel(new GridLayout(2, 2, 6, 6));
        pnlAsignacion.setBackground(colorAzulOscuro);
        String titulo3 = "3. Adicionar a " + (elEquipo != null ? elEquipo.obtenerNombre() : "Equipo");
        pnlAsignacion.setBorder(BorderFactory.createTitledBorder(bordeCompuestoInputs,titulo3,0,0,fuenteTitulo,colorAzulClaro));
        //configurar etiqueta seleccionar jugador
        JLabel etiquetaSelect = new JLabel("Seleccionar Jugador:");
        etiquetaSelect.setFont(fuenteEtiqueta);
        etiquetaSelect.setHorizontalAlignment(0);
        etiquetaSelect.setForeground(Color.white);

        pnlAsignacion.add(etiquetaSelect);
        cbJugadores = new JComboBox<>();
        cbJugadores.setFont(fuenteTexto);
        pnlAsignacion.add(cbJugadores);
        btnAsignar = new JButton("Adicionar al Equipo");
       // btnAsignar.setBackground(new Color(51, 111, 158)); // Azul
        btnAsignar.setBackground(colorAmarillo);
        btnAsignar.setBorder(bordeCompuestoBotones);
        btnAsignar.setFont(fuenteTitulo);
        btnAsignar.setForeground(colorAzulOscuro);
        btnAsignar.setContentAreaFilled(false);
        btnAsignar.setOpaque(true);
        btnAsignar.setFocusPainted(false);
        pnlAsignacion.add(new JLabel());
        pnlAsignacion.add(btnAsignar);

        panelIzquierdo.add(pnlEquipo);
        panelIzquierdo.add(Box.createVerticalStrut(10));
        panelIzquierdo.add(pnlJugador);
        panelIzquierdo.add(Box.createVerticalStrut(10));
        panelIzquierdo.add(pnlAsignacion);

        // Panel de Visualización (Consola de texto)
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(colorAzulOscuro);
        Border bordeCompuestoPD = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20,10,10,10),bordeLineaAzulClaro);
        panelDerecho.setBorder(BorderFactory.createTitledBorder(bordeCompuestoPD,"Estructura del Equipo (Arreglo de 20)",0,0,fuenteTitulo,colorAzulClaro));
        txtAreaConsola = new JTextArea();
        txtAreaConsola.setFont(fuenteTexto);
        txtAreaConsola.setEditable(false);
        txtAreaConsola.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        txtAreaConsola.setFocusable(false);
        //txtAreaConsola.setFont(fuenteTexto);
        //txtAreaConsola.setBackground(colorGris);
        txtAreaConsola.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(txtAreaConsola);
        panelDerecho.add(scroll, BorderLayout.CENTER);

        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);

        // --- MANEJO DE EVENTOS ---

        btnCrearEquipo.addActionListener(e -> {
            String nombre = txtNombreEquipo.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre del equipo no puede estar vacío.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            elEquipo = new Equipo();
            elEquipo.modificarNombre(nombre);

            btnCrearEquipo.setEnabled(false);
            txtNombreEquipo.setEditable(false);
            txtNombreEquipo.setFocusable(false);
            habilitarControlesJugador();
            pnlAsignacion.setBorder(BorderFactory.createTitledBorder(bordeCompuestoInputs,"3. Adicionar a: " + elEquipo.obtenerNombre(),0,0,fuenteTitulo,colorAzulClaro));

            actualizarAreaTexto();
        });

        btnCrearJugador.addActionListener(e -> {
            try {
                String nombres = txtNombreJugador.getText().trim();
                String apellidos = txtApellidoJugador.getText().trim();
                if(nombres.isBlank() || apellidos.isBlank()){
                     JOptionPane.showMessageDialog(this, "Los nombres y apellidos no pueden ser vacios.", "Validación nombres", JOptionPane.WARNING_MESSAGE);
                     return;
                }
                // Cambia la línea antigua por esta:
                LocalDate fecha = txtFechaNacimiento.getDate().toInstant()
                          .atZone(ZoneId.systemDefault())
                          .toLocalDate();
                

                Jugador nuevoJugador = servicio.crearJugador(nombres, apellidos, fecha);
                listaJugadores.add(nuevoJugador);

                actualizarComboJugadores();
                txtNombreJugador.setText("");
                txtApellidoJugador.setText("");
             //   txtFechaNacimiento.setText("");
                JOptionPane.showMessageDialog(this, "Jugador creado. Listo para asignarse.");
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use dd-mm-aaaa", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Validación de Edad", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnAsignar.addActionListener(e -> {
            int idxJugador = cbJugadores.getSelectedIndex();
            if (idxJugador == -1) {
                JOptionPane.showMessageDialog(this, "No hay ningún jugador seleccionado.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Jugador jugadorSel = listaJugadores.get(idxJugador);
                if(servicio.adicionarJugadorEquipo(elEquipo, jugadorSel)){
                    listaJugadores.remove(idxJugador);
                    actualizarComboJugadores();
                    actualizarAreaTexto();
                }else{
                    JOptionPane.showMessageDialog(this,"No se pueden asignar mas jugadores.",null,JOptionPane.ERROR_MESSAGE);
                }
            } catch (IllegalStateException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Regla de Negocio", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void deshabilitarControlesJugador() {
        txtNombreJugador.setEnabled(false);
        txtApellidoJugador.setEnabled(false);
        txtFechaNacimiento.setEnabled(false);
        btnCrearJugador.setEnabled(false);
        cbJugadores.setEnabled(false);
        btnAsignar.setEnabled(false);
    }

    private void habilitarControlesJugador() {
        txtNombreJugador.setEnabled(true);
        txtApellidoJugador.setEnabled(true);
        txtFechaNacimiento.setEnabled(true);
        btnCrearJugador.setEnabled(true);
        cbJugadores.setEnabled(true);
        btnAsignar.setEnabled(true);
    }

    private void actualizarComboJugadores() {
        cbJugadores.removeAllItems();
        for (Jugador j : listaJugadores) {
            cbJugadores.addItem(j.obtenerNombres() + " " + j.obtenerApellidos());
        }
    }

    private void actualizarAreaTexto() {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════════════\n");
        sb.append("             ESTADO DEL EQUIPO         \n");
        sb.append("═══════════════════════════════════════════════════\n");

        if (elEquipo == null) {
            sb.append("Ningún equipo inicializado aún.");
        } else {
            sb.append("  ⚽ EQUIPO: ").append(elEquipo.obtenerNombre().toUpperCase()).append("\n");
            sb.append("    Inscritos actuales para jugar: ").append(servicio.getCantidadJugadores(elEquipo)).append(" / 11\n");
         //   sb.append("   Capacidad total física de la plantilla: 20 posiciones.\n");
            sb.append("─".repeat(51)+"\n");

            // Recorremos las 20 casillas físicas fijas del arreglo
            for (int i = 0; i < elEquipo.obtenerJugadores().size(); i++) {
                Jugador j = elEquipo.obtenerJugadores().get(i);
                if (j != null) {
                    int edad = servicio.obtenerEdad(j.obtenerFechaNacimiento());
                    sb.append(String.format("   📌 Posición [%02d]: %s %s (%d años)\n", i, j.obtenerNombres(), j.obtenerApellidos(), edad));
                } else {
                    sb.append(String.format("   🔲 Posición [%02d]: [Vacío]\n", i));
                }
            }
        }
        txtAreaConsola.setText(sb.toString());
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            Font fuenteGeneral = new Font("Arial", Font.PLAIN, 14);
            java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
            while (keys.hasMoreElements()) {
                Object key = keys.nextElement();
                Object value = UIManager.get(key);
                if (value instanceof javax.swing.plaf.FontUIResource) {
                    UIManager.put(key, fuenteGeneral);
                }
            }
        } catch (Exception e) {
            System.err.println("No se pudo establecer el Look and Feel del sistema, se usará el por defecto.");
        }

        SwingUtilities.invokeLater(() -> {
            new VentanaGral().setVisible(true);
        });
    }
}