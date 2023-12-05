package proyecto_paradigmas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class InterfazUsuario1 {

    UsuarioDAOMySQL usuarioDAO = new UsuarioDAOMySQL();

    private JFrame frame;
    private JTextField text_Nombre;
    private JTextField text_Apelli;
    private JTextField text_Corre;
    private JTextField text_Contra;
    private JButton btn_Actualizar;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InterfazUsuario1 window = new InterfazUsuario1();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public InterfazUsuario1() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBackground(new Color(255, 255, 255));
        frame.getContentPane().setBackground(new Color(0, 0, 0));
        frame.setBounds(100, 100, 487, 422);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Crear Usuario");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBackground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(74, 45, 131, 14);
        frame.getContentPane().add(lblNewLabel);

        JLabel lbl_Nombre = new JLabel("Nombre:");
        lbl_Nombre.setForeground(new Color(255, 255, 255));
        lbl_Nombre.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbl_Nombre.setBounds(25, 120, 71, 14);
        frame.getContentPane().add(lbl_Nombre);

        JLabel lbl_Apellido = new JLabel("Apellido:");
        lbl_Apellido.setForeground(new Color(255, 255, 255));
        lbl_Apellido.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbl_Apellido.setBounds(25, 167, 71, 14);
        frame.getContentPane().add(lbl_Apellido);

        JLabel lbl_Correo = new JLabel("Correo:");
        lbl_Correo.setForeground(new Color(255, 255, 255));
        lbl_Correo.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbl_Correo.setBounds(25, 214, 71, 14);
        frame.getContentPane().add(lbl_Correo);

        JLabel lbl_Contrasena = new JLabel("Contraseña:");
        lbl_Contrasena.setForeground(new Color(255, 255, 255));
        lbl_Contrasena.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbl_Contrasena.setBounds(25, 258, 87, 14);
        frame.getContentPane().add(lbl_Contrasena);

        text_Nombre = new JTextField();
        text_Nombre.setBounds(129, 118, 124, 20);
        frame.getContentPane().add(text_Nombre);
        text_Nombre.setColumns(10);

        text_Apelli = new JTextField();
        text_Apelli.setBounds(129, 165, 124, 20);
        frame.getContentPane().add(text_Apelli);
        text_Apelli.setColumns(10);

        text_Corre = new JTextField();
        text_Corre.setBackground(new Color(255, 255, 255));
        text_Corre.setBounds(129, 212, 124, 20);
        frame.getContentPane().add(text_Corre);
        text_Corre.setColumns(10);

        text_Contra = new JTextField();
        text_Contra.setBounds(129, 256, 124, 20);
        frame.getContentPane().add(text_Contra);
        text_Contra.setColumns(10);

        JButton btn_Crear = new JButton("Crear Usuario");
        btn_Crear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener datos del nuevo usuario desde los campos de texto
                String nombre = text_Nombre.getText();
                String apellido = text_Apelli.getText();
                String correo = text_Corre.getText();
                String contrasena = text_Contra.getText();

                // Crear un nuevo objeto Usuario con los datos obtenidos
                Usuario nuevoUsuario = new Usuario(0, nombre, apellido, correo, contrasena);

                // Llamar al método para crear un nuevo usuario en la base de datos
                UsuarioDAOMySQL usuarioDAO = new UsuarioDAOMySQL();
                usuarioDAO.crearUsuario(nuevoUsuario);

                int idUsuarioCreado = usuarioDAO.obtenerIdUsuario(correo);
                if (idUsuarioCreado != -1) {
                    JOptionPane.showMessageDialog(null, "ID del usuario creado con correo " + correo + " es: " + idUsuarioCreado);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo obtener el ID del usuario creado");
                }
                limpiarCajas();
            }
        });
        btn_Crear.setBackground(new Color(255, 255, 255));
        btn_Crear.setBounds(324, 164, 124, 23);
        frame.getContentPane().add(btn_Crear);

        JButton btn_Eliminar = new JButton("Eliminar Usuario");
        btn_Eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String correoUsuarioAEliminar = text_Corre.getText(); // Obtener el correo del usuario a eliminar
                UsuarioDAOMySQL usuarioDAO = new UsuarioDAOMySQL();
                usuarioDAO.eliminarUsuarioPorCorreo(correoUsuarioAEliminar);
                limpiarCajas();
            }
        });
        btn_Eliminar.setBackground(new Color(255, 255, 255));
        btn_Eliminar.setBounds(324, 332, 124, 23);
        frame.getContentPane().add(btn_Eliminar);

        btn_Actualizar = new JButton("Actualizar Usuario");
        btn_Actualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener datos del nuevo usuario desde los campos de texto
                String nombre = text_Nombre.getText();
                String apellido = text_Apelli.getText();
                String correo = text_Corre.getText();
                String contrasena = text_Contra.getText();

                // Crear un nuevo objeto Usuario con los datos obtenidos
                Usuario nuevoUsuario = new Usuario(0, nombre, apellido, correo, contrasena);

                // Llamar al método para crear un nuevo usuario en la base de datos
                UsuarioDAOMySQL usuarioDAO = new UsuarioDAOMySQL();
                usuarioDAO.actualizarUsuario(correo, nuevoUsuario);
                limpiarCajas();
            }
        });
        btn_Actualizar.setBackground(new Color(255, 255, 255));
        btn_Actualizar.setBounds(324, 255, 124, 23);
        frame.getContentPane().add(btn_Actualizar);

        JButton btn_Regresar = new JButton("Regresar");
        btn_Regresar.setBackground(new Color(255, 255, 255));
        btn_Regresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        btn_Regresar.setBounds(25, 332, 89, 23);
        frame.getContentPane().add(btn_Regresar);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\doria\\Downloads\\profile (2) (1).png"));
        lblNewLabel_1.setBounds(296, 0, 175, 159);
        frame.getContentPane().add(lblNewLabel_1);

        JButton btn_Selec = new JButton("Selecionar Datos");
        btn_Selec.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String correoABuscar = text_Corre.getText();

                UsuarioDAOMySQL usuarioDAO = new UsuarioDAOMySQL();
                Usuario usuarioEncontrado = usuarioDAO.seleccionarUsuarioPorCorreo(correoABuscar);

                // Mostrar los datos del usuario encontrado en las cajas de texto
                if (usuarioEncontrado != null) {
                    text_Nombre.setText(usuarioEncontrado.getNombre());
                    text_Apelli.setText(usuarioEncontrado.getApellido());
                    text_Corre.setText(usuarioEncontrado.getCorreo());
                    text_Contra.setText(usuarioEncontrado.getContrasena());
                } else {
                    // Limpiar las cajas de texto si no se encuentra el usuario
                    text_Nombre.setText("");
                    text_Apelli.setText("");
                    text_Corre.setText("");
                    text_Contra.setText("");
                    System.out.println("Usuario no encontrado");
                    // Mostrar mensaje de usuario no encontrados
                }
            }
        });
        btn_Selec.setBackground(new Color(255, 255, 255));
        btn_Selec.setBounds(324, 211, 124, 23);
        frame.getContentPane().add(btn_Selec);
    }

    private void limpiarCajas() {
        text_Nombre.setText("");
        text_Apelli.setText("");
        text_Corre.setText("");
        text_Contra.setText("");
    }

    public void mostrarVentanaUsuario() {
        frame.setVisible(true);
    }
}
