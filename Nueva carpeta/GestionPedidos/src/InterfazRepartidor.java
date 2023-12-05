import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazRepartidor extends JFrame {
    private JTextField textFieldCorreo;
    private JPasswordField passwordField;
    private JButton btnIniciarSesion;
    private final UsuarioDAOMySQL usuarioDAO;

    public InterfazRepartidor() {
        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        usuarioDAO = new UsuarioDAOMySQL(); // Instancia de UsuarioDAOMySQL
        initComponents();
        setVisible(true);
    }
    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30, 30, 80, 25);
        panel.add(lblCorreo);
        textFieldCorreo = new JTextField();
        textFieldCorreo.setBounds(110, 30, 150, 25);
        panel.add(textFieldCorreo);
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(30, 70, 80, 25);
        panel.add(lblContrasena);
        passwordField = new JPasswordField();
        passwordField.setBounds(110, 70, 150, 25);
        panel.add(passwordField);
        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setBounds(90, 120, 120, 30);
        panel.add(btnIniciarSesion);
        btnIniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener datos ingresados por el usuario
                String correo = textFieldCorreo.getText();
                String contrasena = new String(passwordField.getPassword());
                // Crear un objeto Usuario con los datos ingresados
                Usuario usuario = new Usuario(WIDTH, contrasena, contrasena, correo, contrasena, null);
                // Llamar al método de inicio de sesión de UsuarioDAOMySQL
                loginUsuario(usuario);
            }
        });
        add(panel);
    }
    private void loginUsuario(Usuario usuario) {
        // Llamada al método login de UsuarioDAOMySQL
        usuarioDAO.login(usuario);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InterfazRepartidor();
            }
        });
    }
}