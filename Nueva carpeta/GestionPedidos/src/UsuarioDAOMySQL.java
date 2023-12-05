package proyecto_paradigmas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UsuarioDAOMySQL  {

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/rappi";
    private static final String usr = "root";
    private static final String pass = "";

    static {
        try {
            // Load the MySQL JDBC driver
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading the driver");
            e.printStackTrace();
        }
    }

    // Establish a database connection
    public Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, usr, pass);
            System.out.println("Connection successful");
        } catch (SQLException e) {
            System.out.println("Error in connection");
            e.printStackTrace();
        }
        return con;
    }

    // Insert a new user record into the database
    public void crearUsuario(Usuario usuario) {
        try {
            Connection cn = conectar();
            String insertar = "insert into usuarios value (?,?,?,?,?)";
            PreparedStatement pstm = cn.prepareStatement(insertar);
            pstm.setInt(1, usuario.getId());
            pstm.setString(2, usuario.getNombre());
            pstm.setString(3, usuario.getApellido());
            pstm.setString(4, usuario.getCorreo());
            pstm.setString(5, usuario.getContrasena());

            pstm.executeUpdate();

            System.out.println("Usuario creado");
            JOptionPane.showMessageDialog(null, "Usuario Creado");

            pstm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error al crear usuario");
            e.printStackTrace();
        }
    }

    public int obtenerIdUsuario(String correo) {
        int idUsuario = -1; // Valor predeterminado si no se puede obtener el ID

        try {
            Connection cn = conectar();
            String seleccionar = "SELECT id FROM usuarios WHERE correo=?";
            PreparedStatement pstm = cn.prepareStatement(seleccionar);
            pstm.setString(1, correo);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                idUsuario = rs.getInt("id");
                System.out.println("ID del usuario con correo " + correo + " es: " + idUsuario);
                JOptionPane.showMessageDialog(null, "ID del usuario con correo " + correo + " es: " + idUsuario);
            } else {
                System.out.println("No se encontró el usuario con correo " + correo);
                JOptionPane.showMessageDialog(null, "No se encontró el usuario con correo " + correo);
            }

            rs.close();
            pstm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error al obtener la ID del usuario");
            e.printStackTrace();
        }

        return idUsuario;
    }

    public void login(Usuario usuario) {
        try {
            Connection cn = conectar();
            String seleccionar = "SELECT correo, contrasena FROM usuarios WHERE correo=?";
            PreparedStatement pstm = cn.prepareStatement(seleccionar);
            pstm.setString(1, usuario.getCorreo());

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String correo = rs.getString("correo");
                String contrasena = rs.getString("contrasena");

                if (correo.equals(usuario.getCorreo()) && contrasena.equals(usuario.getContrasena())) {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso para el usuario con correo: " + usuario.getCorreo());
                    InterfazPedido interfazPedido = new InterfazPedido();
                    interfazPedido.mostrarVentanaPedido();

                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
            }

            rs.close();
            pstm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error en el proceso de inicio de sesión");
            e.printStackTrace();
        }
    }

    public void hacerPedido(Pedido pedido) {
        try {
            Connection cn = conectar();

            String insertarPedido = "INSERT INTO pedidos (id_pedido, id_usuario, producto, cantidad, direccion_entrega) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstm = cn.prepareStatement(insertarPedido);
            pstm.setInt(1, pedido.getIdPedido());
            pstm.setInt(2, pedido.getIdUsuario());
            pstm.setString(3, pedido.getProducto());
            pstm.setInt(4, pedido.getCantidad());
            pstm.setString(5, pedido.getDireccion());

            pstm.executeUpdate();

            System.out.println("Pedido realizado");
            JOptionPane.showMessageDialog(null, "Pedido Realizado");

            pstm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error al hacer el pedido");
            e.printStackTrace();
        }
    }

   
public Pedido seleccionarPedidoPorId(int idPedido) {
    Pedido pedido = null;
    try {
        Connection cn = conectar();
        String seleccionar = "SELECT * FROM pedidos WHERE id_pedido = ?";
        PreparedStatement pstm = cn.prepareStatement(seleccionar);
        pstm.setInt(1, idPedido);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int pedidoId = rs.getInt("id_pedido");
            int usuarioId = rs.getInt("id_usuario");
            String producto = rs.getString("producto");
            int cantidad = rs.getInt("cantidad");
            String direccion = rs.getString("direccion_entrega");

            pedido = new Pedido(pedidoId, usuarioId, producto, cantidad, direccion);
        }

        rs.close();
        pstm.close();
        cn.close();
    } catch (Exception e) {
        System.out.println("Error al seleccionar el pedido por ID");
        e.printStackTrace();
    }
    return pedido;
}
    
    public void cancelarPedido(int id_pedido) {
        try {
            Connection cn = conectar();

            String eliminarPedido = "DELETE FROM pedidos WHERE id_pedido = ?";
            PreparedStatement pstm = cn.prepareStatement(eliminarPedido);
            pstm.setInt(1, id_pedido); // Usar el parámetro id_pedido

            int filasAfectadas = pstm.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Pedido cancelado");
                JOptionPane.showMessageDialog(null, "Pedido Cancelado");
            } else {
                System.out.println("No se encontró el pedido");
                JOptionPane.showMessageDialog(null, "No se encontró el pedido");
            }

            pstm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error al cancelar el pedido");
            e.printStackTrace();
        }
    }

   public void modificarPedidoPorId(Pedido pedido) {
    try {
        Connection cn = conectar();

        String actualizarPedido = "UPDATE pedidos SET id_usuario = ?, producto = ?, cantidad = ?, direccion_entrega = ? WHERE id_pedido = ?";
        PreparedStatement pstm = cn.prepareStatement(actualizarPedido);

        pstm.setInt(1, pedido.getIdUsuario());
        pstm.setString(2, pedido.getProducto());
        pstm.setInt(3, pedido.getCantidad());
        pstm.setString(4, pedido.getDireccion());
        pstm.setInt(5, pedido.getIdPedido());

        int filasActualizadas = pstm.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Pedido actualizado correctamente.");
            JOptionPane.showMessageDialog(null, "Pedido Actualizado");
        } else {
            System.out.println("No se pudo actualizar el pedido.");
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el pedido");
        }

        pstm.close();
        cn.close();
    } catch (Exception e) {
        System.out.println("Error al modificar el pedido");
        e.printStackTrace();
    }
}

    public Usuario seleccionarUsuarioPorCorreo(String correo) {
        Usuario usuario = null;
        try {
            Connection cn = conectar();
            String seleccionar = "SELECT * FROM usuarios WHERE correo = ?";
            PreparedStatement pstm = cn.prepareStatement(seleccionar);
            pstm.setString(1, correo);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                int usuarioId = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("correo");
                String contrasena = rs.getString("contrasena");

                usuario = new Usuario(usuarioId, nombre, apellido, email, contrasena);
            }

            rs.close();
            pstm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error al seleccionar el usuario por correo");
            e.printStackTrace();
        }
        return usuario;
    }

   public void actualizarUsuario(String correo, Usuario nuevoUsuario) {
    try {
        Connection cn = conectar();

        // Actualizar los datos del usuario basado en el correo electrónico
        String actualizarUsuario = "UPDATE usuarios SET nombre = ?, apellido = ?, correo = ?, contrasena = ? WHERE correo = ?";
        PreparedStatement pstm = cn.prepareStatement(actualizarUsuario);

        pstm.setString(1, nuevoUsuario.getNombre());
        pstm.setString(2, nuevoUsuario.getApellido());
        pstm.setString(3, nuevoUsuario.getCorreo());
        pstm.setString(4, nuevoUsuario.getContrasena());
        pstm.setString(5, correo); // Buscar por el correo electrónico

        int filasAfectadas = pstm.executeUpdate();

        if (filasAfectadas > 0) {
            System.out.println("Usuario actualizado");
            JOptionPane.showMessageDialog(null, "Usuario Actualizado");
        } else {
            System.out.println("No se encontró el usuario");
            JOptionPane.showMessageDialog(null, "No se encontró el usuario");
        }

        pstm.close();
        cn.close();
    } catch (Exception e) {
        System.out.println("Error al actualizar el usuario");
        e.printStackTrace();
    }
}

    public void eliminarUsuarioPorCorreo(String correo) {
        try {
            Connection cn = conectar();

            String eliminar = "DELETE FROM usuarios WHERE correo = ?";
            PreparedStatement pstm = cn.prepareStatement(eliminar);
            pstm.setString(1, correo);

            int filasAfectadas = pstm.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Usuario eliminado");
                JOptionPane.showMessageDialog(null, "Usuario Eliminado");
            } else {
                System.out.println("No se encontró el usuario");
                JOptionPane.showMessageDialog(null, "No se encontró el usuario");
            }

            pstm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error al eliminar el usuario");
            e.printStackTrace();
        }
    }

}
