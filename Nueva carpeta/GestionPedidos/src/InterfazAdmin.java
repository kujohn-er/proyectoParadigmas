import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazAdmin extends JFrame {
    private ConexionAdministrador conexionAdministrador;
    private JTextField textFieldIdPedido;
    private JTextField textFieldIdUsuario;
    private JTextField textFieldProducto;
    private JTextField textFieldCantidad;
    private JTextField textFieldDireccion;

    public InterfazAdmin() {
        conexionAdministrador = new ConexionAdministrador();

        setTitle("Interfaz de Administrador");
        setSize(360, 365);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        textFieldIdPedido = new JTextField();
        textFieldIdUsuario = new JTextField();
        textFieldProducto = new JTextField();
        textFieldCantidad = new JTextField();
        textFieldDireccion = new JTextField();

        JButton botonObtenerInformacion = new JButton("Obtener Información");
        botonObtenerInformacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obtenerInformacionPedido(textFieldIdPedido.getText());
            }
        });

        JButton botonVerificarPedido = new JButton("Verificar Pedido");
        botonVerificarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarPedido();
            }
        });

        JButton botonVerTodos = new JButton("Ver todos los pedidos");
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

        textFieldIdPedido.setBackground(new Color(220, 220, 220));
        textFieldIdUsuario.setBackground(new Color(220, 220, 220));
        textFieldProducto.setBackground(new Color(220, 220, 220));
        textFieldCantidad.setBackground(new Color(220, 220, 220));
        textFieldDireccion.setBackground(new Color(220, 220, 220));

        panel.add(new JLabel("ID Pedido:")).setBounds(10, 10, 150, 20);
        panel.add(textFieldIdPedido).setBounds(190, 10, 150, 25);

        panel.add(new JLabel("ID Usuario:")).setBounds(10, 40, 150, 20);
        panel.add(textFieldIdUsuario).setBounds(190, 40, 150, 25);

        panel.add(new JLabel("Producto:")).setBounds(10, 70, 150, 20);
        panel.add(textFieldProducto).setBounds(190, 70, 150, 25);

        panel.add(new JLabel("Cantidad:")).setBounds(10, 100, 150, 20);
        panel.add(textFieldCantidad).setBounds(190, 100, 150, 25);

        panel.add(new JLabel("Dirección:")).setBounds(10, 130, 150, 20);
        panel.add(textFieldDireccion).setBounds(190, 130, 150, 25);

        panel.add(botonObtenerInformacion).setBounds(70, 170, 200, 30);
        panel.add(botonVerificarPedido).setBounds(70, 210, 200, 30);
        panel.add(botonVerTodos).setBounds(70, 250, 200, 30);
        panel.add(botonRegresarPrincipal).setBounds(240, 300, 100, 20);

        this.getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void obtenerInformacionPedido(String idPedido) {
        try {
            conexionAdministrador.setIdPedido(idPedido);
            conexionAdministrador.obtenerInformacionPedido();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    textFieldIdUsuario.setText(String.valueOf(conexionAdministrador.getIdUsuario()));
                    textFieldProducto.setText(conexionAdministrador.getProducto());
                    textFieldCantidad.setText(conexionAdministrador.getCantidad());
                    textFieldDireccion.setText(conexionAdministrador.getDireccion());
                }
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al obtener información del pedido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verificarPedido() {
        try {
            conexionAdministrador.setIdPedido(textFieldIdPedido.getText());
            conexionAdministrador.setIdUsuario(Integer.parseInt(textFieldIdUsuario.getText()));
            conexionAdministrador.setProducto(textFieldProducto.getText());
            conexionAdministrador.setCantidad(textFieldCantidad.getText());
            conexionAdministrador.setDireccion(textFieldDireccion.getText());

            conexionAdministrador.verificarPedido();
            JOptionPane.showMessageDialog(this, "Pedido verificado", "Verificación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al verificar el pedido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void verTodosLosRegistros() {
        try {
            String[] columnas = {"ID Pedido", "ID Usuario", "Producto", "Cantidad", "Dirección"};
            String[][] datos = conexionAdministrador.obtenerTodosLosRegistrosEnMatriz();

            JTable tabla = new JTable(datos, columnas);
            tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JScrollPane scrollPane = new JScrollPane(tabla);
            JOptionPane.showMessageDialog(this, scrollPane, "Todos los Registros", JOptionPane.PLAIN_MESSAGE);

            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada != -1) {
                String idPedidoSeleccionado = (String) tabla.getValueAt(filaSeleccionada, 0);
                obtenerInformacionPedido(idPedidoSeleccionado);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al obtener todos los registros: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void regresarAPrincipal() {
        // Cerrar la interfaz actual
        dispose();
    }
}