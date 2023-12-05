import java.sql.*;
import java.util.ArrayList;

public class ConexionRepartidor {

    private static final String controlador = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/rappi";
    private static final String usr = "root";
    private static final String pass = "";
    
    public String id_pedido;
    public String id_usuario;
    public String producto;
    public String cantidad;
    public String direccion_entrega;
    public String estado;

    static {
        try {
            Class.forName(controlador);
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador");
            e.printStackTrace();
        }
    }

    public Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, usr, pass);
            System.out.println("Conexion correcta");
        } catch (SQLException e) {
            System.out.println("Error de conexion");
            e.printStackTrace();
        }
        return con;
    }

    // Obtener pedidos disponibles
    public ArrayList<String> obtenerPedidosDisponibles() {
        ArrayList<String> pedidos = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            cn = conectar();
            String seleccionar = "SELECT id_pedido, producto, cantidad, direccion_entrega FROM pedidos WHERE estado = 'Pendiente'";
            pstm = cn.prepareStatement(seleccionar);
            rs = pstm.executeQuery();

            while (rs.next()) {
                int id_pedido = rs.getInt("id_pedido");
                String producto = rs.getString("producto");
                int cantidad = rs.getInt("cantidad");
                String direccionEntrega = rs.getString("direccion_entrega");
                pedidos.add("ID: "+id_pedido+": Producto: " + producto + " Cantidad: " + cantidad + " Dirección de entrega: " + direccionEntrega);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pedidos");
            e.printStackTrace();
        } finally {
            cerrarConexion(cn, pstm, rs);
        }

        return pedidos;
    }

    // Marcar pedido como "en reparto"
    public void marcarEnReparto(String id_pedido) {
    	
        Connection cn = null;
        PreparedStatement pstm = null;

        try {
            cn = conectar();
            String actualizar = "UPDATE pedidos SET estado = 'EnReparto' WHERE id_pedido = ?";
            pstm = cn.prepareStatement(actualizar);
            
            pstm.setString(1, id_pedido);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al marcar como en reparto");
            e.printStackTrace();
        } finally {
            cerrarConexion(cn, pstm, null);
        }
    }


    private void cerrarConexion(Connection con, PreparedStatement pstm, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión");
            e.printStackTrace();
        }
    }
}