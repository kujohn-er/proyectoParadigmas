import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazPrincipal extends JFrame {
    private InterfazAdmin interfazAdmin;
    private UsuariosAdmin interfazUsuarios;

    public InterfazPrincipal() {
        setTitle("Administrador");
        setSize(270, 270);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear una instancia de InterfazAdmin (sin inicializarla aquí)
        interfazAdmin = null;
        interfazUsuarios = null;

        // Botón para buscar pedido
        JButton botonBuscar = new JButton("Pedidos");
        botonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirInterfazAdmin();
            }
        });

        // Botón adicional
        JButton botonOtro = new JButton("Usuarios");
        botonOtro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirInterfazUsuarios();
            }
        });

        // Panel con null layout para organizar manualmente los botones
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);

        // Establecer manualmente la posición y el tamaño de los botones
        botonBuscar.setBounds(30, 30, 100, 30);
        botonOtro.setBounds(140, 30, 100, 30);

        // Agregar los botones al panel
        panelPrincipal.add(botonBuscar);
        panelPrincipal.add(botonOtro);

        //IMAGENES
        ImageIcon icono = new ImageIcon("C:/Users/Luis Enrique/Downloads/Documents/Sistema de gestion/proyectoParadigmas/Nueva carpeta/GestionPedidos/image/Admin.png");

        JLabel imagen = new JLabel(icono);

        imagen.setBounds(100, 50, 160, 200);
        

        panelPrincipal.add(imagen);

        // Agregar el panel al JFrame
        add(panelPrincipal);
    }

    private void abrirInterfazAdmin() {
        // Crear la instancia de InterfazAdmin solo si es nula
        if (interfazAdmin == null) {
            interfazAdmin = new InterfazAdmin();
        }
        // Hacer visible la instancia existente de InterfazAdmin
        interfazAdmin.setVisible(true);
    }

    private void abrirInterfazUsuarios() {
        // Crear la instancia de UsuariosAdmin solo si es nula
        if (interfazUsuarios == null) {
            interfazUsuarios = new UsuariosAdmin();
        }
        // Hacer visible la instancia existente de UsuariosAdmin
        interfazUsuarios.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                InterfazPrincipal interfaz1 = new InterfazPrincipal();
                interfaz1.setVisible(true);
            }
        });
    }
}