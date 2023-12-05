package proyecto_paradigmas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazPedido {

    private JFrame frame;
    private JTextField text_IdPedi;
    private JTextField text_Cliente;
    private JTextField text_Prodcuto;
    private JTextField text_Cantidad;
    private JTextField text_Direccion;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InterfazPedido window = new InterfazPedido();
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
    public InterfazPedido() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(0, 0, 0));
        frame.setBounds(100, 100, 484, 416);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lbl_Pedido = new JLabel("Pedido");
        lbl_Pedido.setForeground(new Color(255, 255, 255));
        lbl_Pedido.setBackground(new Color(255, 255, 255));
        lbl_Pedido.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbl_Pedido.setBounds(169, 22, 55, 14);
        frame.getContentPane().add(lbl_Pedido);

        JLabel lbl_idPedido = new JLabel("Id Pedido:");
        lbl_idPedido.setForeground(new Color(255, 255, 255));
        lbl_idPedido.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbl_idPedido.setBounds(35, 76, 69, 14);
        frame.getContentPane().add(lbl_idPedido);

        JLabel lbll_Cliente = new JLabel("Id Cliente:");
        lbll_Cliente.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbll_Cliente.setForeground(new Color(255, 255, 255));
        lbll_Cliente.setBounds(35, 119, 69, 14);
        frame.getContentPane().add(lbll_Cliente);

        JLabel lbl_producto = new JLabel("Producto:");
        lbl_producto.setForeground(new Color(255, 255, 255));
        lbl_producto.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbl_producto.setBounds(35, 165, 69, 14);
        frame.getContentPane().add(lbl_producto);

        JLabel lbl_Cantidad = new JLabel("Cantidad:");
        lbl_Cantidad.setForeground(new Color(255, 255, 255));
        lbl_Cantidad.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbl_Cantidad.setBounds(35, 213, 69, 14);
        frame.getContentPane().add(lbl_Cantidad);

        JLabel lbl_Direccion = new JLabel("Direccion:");
        lbl_Direccion.setForeground(new Color(255, 255, 255));
        lbl_Direccion.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbl_Direccion.setBounds(35, 260, 69, 14);
        frame.getContentPane().add(lbl_Direccion);

        text_IdPedi = new JTextField();
        text_IdPedi.setBounds(138, 74, 106, 20);
        frame.getContentPane().add(text_IdPedi);
        text_IdPedi.setColumns(10);

        text_Cliente = new JTextField();
        text_Cliente.setBounds(138, 117, 106, 20);
        frame.getContentPane().add(text_Cliente);
        text_Cliente.setColumns(10);

        text_Prodcuto = new JTextField();
        text_Prodcuto.setBounds(138, 163, 106, 20);
        frame.getContentPane().add(text_Prodcuto);
        text_Prodcuto.setColumns(10);

        text_Cantidad = new JTextField();
        text_Cantidad.setBounds(138, 211, 106, 20);
        frame.getContentPane().add(text_Cantidad);
        text_Cantidad.setColumns(10);

        text_Direccion = new JTextField();
        text_Direccion.setBounds(138, 258, 106, 20);
        frame.getContentPane().add(text_Direccion);
        text_Direccion.setColumns(10);

        JButton btn_Pedido = new JButton("Agregar Pedido");
        btn_Pedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idPedido = Integer.parseInt(text_IdPedi.getText());
                int idCliente = Integer.parseInt(text_Cliente.getText());
                String producto = text_Prodcuto.getText();
                int cantidad = Integer.parseInt(text_Cantidad.getText());
                String direccion = text_Direccion.getText();

                // Crear un objeto de la clase Pedido con los datos obtenidos
                Pedido nuevoPedido = new Pedido(idPedido, idCliente, producto, cantidad, direccion);

                UsuarioDAOMySQL usuarioDAO = new UsuarioDAOMySQL();
                usuarioDAO.hacerPedido(nuevoPedido);
                limpiarCajas();
            }
        });
        btn_Pedido.setBounds(304, 162, 133, 23);
        frame.getContentPane().add(btn_Pedido);

        JButton btn_Informacion = new JButton("Informacion Pedido.");
        btn_Informacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 int idPedido = Integer.parseInt(text_IdPedi.getText()); 
        UsuarioDAOMySQL pedidoDAO = new UsuarioDAOMySQL(); 

        Pedido pedidoEncontrado = pedidoDAO.seleccionarPedidoPorId(idPedido); 

        if (pedidoEncontrado != null) {
            text_IdPedi.setText(String.valueOf(pedidoEncontrado.getIdPedido()));
            text_Cliente.setText(String.valueOf(pedidoEncontrado.getIdUsuario()));
            text_Prodcuto.setText(pedidoEncontrado.getProducto());
            text_Cantidad.setText(String.valueOf(pedidoEncontrado.getCantidad()));
            text_Direccion.setText(pedidoEncontrado.getDireccion());
        } else {
            // Limpiar las cajas de texto si no se encuentra el pedido
            text_IdPedi.setText("");
            text_Prodcuto.setText("");
            text_Prodcuto.setText("");
            text_Cantidad.setText("");
            text_Direccion.setText("");
            // Mostrar mensaje de pedido no encontrado
        }
            }
        });
        btn_Informacion.setBounds(304, 210, 133, 23);
        frame.getContentPane().add(btn_Informacion);

        JButton btn_Actualizar = new JButton("Actualizar pedido");
        btn_Actualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idPedido = Integer.parseInt(text_IdPedi.getText());
                int idCliente = Integer.parseInt(text_Cliente.getText());
                String producto = text_Prodcuto.getText();
                int cantidad = Integer.parseInt(text_Cantidad.getText());
                String direccion = text_Direccion.getText();

                // Crear un objeto de la clase Pedido con los datos obtenidos
                Pedido nuevoPedido = new Pedido(idPedido, idCliente, producto, cantidad, direccion);

                UsuarioDAOMySQL usuarioDAO = new UsuarioDAOMySQL();
                usuarioDAO.modificarPedidoPorId(nuevoPedido);
                limpiarCajas();
            }
        });
        btn_Actualizar.setBounds(304, 257, 133, 23);
        frame.getContentPane().add(btn_Actualizar);

        JButton btn_Salir = new JButton("Regresar.");
        btn_Salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        btn_Salir.setBounds(35, 323, 89, 23);
        frame.getContentPane().add(btn_Salir);

        JButton btn_Eliminar = new JButton("Cancelar Pedido");
        btn_Eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idPedido = Integer.parseInt(text_IdPedi.getText());
                UsuarioDAOMySQL usuarioDAO = new UsuarioDAOMySQL();
                usuarioDAO.cancelarPedido(idPedido);
                limpiarCajas();
            }
        });
        btn_Eliminar.setBounds(304, 304, 133, 23);
        frame.getContentPane().add(btn_Eliminar);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\doria\\Downloads\\1669069 (1).png"));
        lblNewLabel.setBounds(304, 28, 115, 112);
        frame.getContentPane().add(lblNewLabel);
    }
    
    private void limpiarCajas() {
    text_IdPedi.setText("");
    text_Cliente.setText("");
    text_Prodcuto.setText("");
    text_Cantidad.setText("");
    text_Direccion.setText("");
    System.out.println("Pedido no encontrado");
    // Mostrar mensaje de pedido no encontrado si lo deseas
}


    public void mostrarVentanaPedido() {
        frame.setVisible(true);
    }

}
