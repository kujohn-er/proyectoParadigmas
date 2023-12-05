import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuariosAdmin extends JFrame {
    private ConexionAdministrador conexionAdministrador;
    private JTextField textFieldIdUsuario;
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldCorreo;
    private JTextField textFieldContrasena;

    public UsuariosAdmin() {
        conexionAdministrador = new ConexionAdministrador();

        setTitle("Interfaz de Usuario");
        setSize(360, 365);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        textFieldIdUsuario = new JTextField();
        textFieldNombre = new JTextField();
        textFieldApellido = new JTextField();
        textFieldCorreo = new JTextField();
        textFieldContrasena = new JTextField();

        JButton botonObtenerInformacion = new JButton("Obtener Información");
        botonObtenerInformacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obtenerInformacionUsuario(textFieldIdUsuario.getText());
            }
        });

        JButton botonVerificarUsuario = new JButton("Verificar Usuario");
        botonVerificarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarUsuario();
            }
        });

        JButton botonVerTodos = new JButton("Ver todos los usuarios");
        botonVerTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verTodosLosRegistros();
            }
        });

        JButton botonRegresarPrincipal = new JButton("Regresar a Principal");
        botonRegresarPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAPrincipal();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240));

        textFieldIdUsuario.setBackground(new Color(220, 220, 220));
        textFieldNombre.setBackground(new Color(220, 220, 220));
        textFieldApellido.setBackground(new Color(220, 220, 220));
        textFieldCorreo.setBackground(new Color(220, 220, 220));
        textFieldContrasena.setBackground(new Color(220, 220, 220));

        panel.add(new JLabel("ID Usuario:")).setBounds(10, 10, 150, 20);
        panel.add(textFieldIdUsuario).setBounds(190, 10, 150, 25);

        panel.add(new JLabel("Nombre:")).setBounds(10, 40, 150, 20);
        panel.add(textFieldNombre).setBounds(190, 40, 150, 25);

        panel.add(new JLabel("Apellido:")).setBounds(10, 70, 150, 20);
        panel.add(textFieldApellido).setBounds(190, 70, 150, 25);

        panel.add(new JLabel("Correo:")).setBounds(10, 100, 150, 20);
        panel.add(textFieldCorreo).setBounds(190, 100, 150, 25);

        panel.add(new JLabel("Contraseña:")).setBounds(10, 130, 150, 20);
        panel.add(textFieldContrasena).setBounds(190, 130, 150, 25);

        panel.add(botonObtenerInformacion).setBounds(70, 170, 200, 30);
        panel.add(botonVerificarUsuario).setBounds(70, 210, 200, 30);
        panel.add(botonVerTodos).setBounds(70, 250, 200, 30);
        panel.add(botonRegresarPrincipal).setBounds(240, 300, 100, 20);

        this.getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void obtenerInformacionUsuario(String idUsuario) {
        try {
            conexionAdministrador.setId(idUsuario);
            conexionAdministrador.datosUsarios();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    textFieldNombre.setText(conexionAdministrador.nombre);
                    textFieldApellido.setText(conexionAdministrador.apellido);
                    textFieldCorreo.setText(conexionAdministrador.correo);
                    textFieldContrasena.setText(conexionAdministrador.contrasena);
                }
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al obtener información del usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verificarUsuario() {
        try {
            conexionAdministrador.setId(textFieldIdUsuario.getText());
            conexionAdministrador.setNombre(textFieldNombre.getText());
            conexionAdministrador.setApellido(textFieldApellido.getText());
            conexionAdministrador.setCorreo(textFieldCorreo.getText());
            conexionAdministrador.setContrasena(textFieldContrasena.getText());

            conexionAdministrador.verificarUsuario();
            JOptionPane.showMessageDialog(this, "Usuario verificado", "Verificación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al verificar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void verTodosLosRegistros() {
        try {
            String[] columnas = {"Id Usuario", "Nombre", "Apellido", "Correo", "Contraseña"};
            String[][] datos = conexionAdministrador.obtenerTodosLosUsuariosM();

            JTable tabla = new JTable(datos, columnas);
            tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JScrollPane scrollPane = new JScrollPane(tabla);
            JOptionPane.showMessageDialog(this, scrollPane, "Todos los Registros", JOptionPane.PLAIN_MESSAGE);

            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada != -1) {
                String idUsuarioSeleccionado = (String) tabla.getValueAt(filaSeleccionada, 0);
                obtenerInformacionUsuario(idUsuarioSeleccionado);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al obtener todos los registros: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void regresarAPrincipal() {
        // Cerrar la interfaz actual
        dispose();
    }}
