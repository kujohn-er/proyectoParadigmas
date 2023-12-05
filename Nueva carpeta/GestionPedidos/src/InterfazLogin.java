package proyecto_paradigmas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;

import javax.swing.ImageIcon;

public class InterfazLogin {

    private JFrame frmCreacionDeUsuario;
    private JTextField textField;
    private JTextField textField_1;
    private JLabel lbl_Correo;
    private JLabel lbl_contrasela;
    private JLabel lblNewLabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InterfazLogin window = new InterfazLogin();
                    window.frmCreacionDeUsuario.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public InterfazLogin() {

        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmCreacionDeUsuario = new JFrame();
        frmCreacionDeUsuario.setBackground(new Color(128, 128, 128));
        frmCreacionDeUsuario.setTitle("Creacion de Usuario");
        frmCreacionDeUsuario.getContentPane().setBackground(new Color(0, 0, 0));
        frmCreacionDeUsuario.getContentPane().setLayout(null);

        textField = new JTextField();
        textField.setBounds(77, 143, 193, 20);
        frmCreacionDeUsuario.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(77, 211, 193, 20);
        frmCreacionDeUsuario.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String correo = textField.getText();
                String contrasena = textField_1.getText();

                Usuario usuario = new Usuario(0, "", "", correo, contrasena);
                UsuarioDAOMySQL usuarioDAO = new UsuarioDAOMySQL();
                usuarioDAO.login(usuario); 
                limpiarCajas();
            }
        });
        btnLogin.setForeground(new Color(0, 0, 0));
        btnLogin.setBackground(new Color(128, 128, 255));
        btnLogin.setBounds(130, 255, 89, 23);
        frmCreacionDeUsuario.getContentPane().add(btnLogin);

        JButton btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnRegister.setForeground(new Color(255, 255, 255));
        btnRegister.setBackground(new Color(128, 128, 255));
        btnRegister.setOpaque(false);
        btnRegister.setContentAreaFilled(false);
        btnRegister.setBorderPainted(false);

        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfazUsuario1 interfazUsuario1 = new InterfazUsuario1();
                 interfazUsuario1.mostrarVentanaUsuario();
            }
        });

        btnRegister.setBounds(130, 323, 89, 23);
        frmCreacionDeUsuario.getContentPane().add(btnRegister);

        lbl_Correo = new JLabel("Correo");
        lbl_Correo.setForeground(new Color(255, 255, 255));
        lbl_Correo.setBackground(new Color(255, 255, 255));
        lbl_Correo.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lbl_Correo.setBounds(143, 116, 76, 14);
        frmCreacionDeUsuario.getContentPane().add(lbl_Correo);

        lbl_contrasela = new JLabel("Contraseña:");
        lbl_contrasela.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lbl_contrasela.setForeground(new Color(255, 255, 255));
        lbl_contrasela.setBounds(143, 187, 76, 14);
        frmCreacionDeUsuario.getContentPane().add(lbl_contrasela);

        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\doria\\Downloads\\klipartz.com (1).png"));
        lblNewLabel.setBounds(128, -5, 122, 110);
        frmCreacionDeUsuario.getContentPane().add(lblNewLabel);
        frmCreacionDeUsuario.setBounds(100, 100, 349, 464);
        frmCreacionDeUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void limpiarCajas() {
        textField.setText("");
        textField_1.setText("");
    }
}
