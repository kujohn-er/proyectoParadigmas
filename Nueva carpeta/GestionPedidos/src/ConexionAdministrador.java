import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ConexionAdministrador {

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/rappi";
    private static final String usuario = "root";
    private static final String password = "";

    public String idPedido;
    public int idUsuario;
    public String producto;
    public String cantidad;
    public String direccion;

    public String id;
    public String nombre;
    public String apellido;
    public String correo;
    public String contrasena;

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar el controlador", e);
        }
    }

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, usuario, password);
    }
    public void obtenerInformacionPedido() {
        try (Connection cn = conectar();
                PreparedStatement pstm = cn.prepareStatement("SELECT * FROM pedidos WHERE id_pedido=?")) {

            pstm.setString(1, idPedido);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    idUsuario = rs.getInt("id_usuario");
                    producto = rs.getString("producto");
                    cantidad = rs.getString("cantidad");
                    direccion = rs.getString("direccion_entrega");

                    System.out.println("Pedido: " + idPedido);
                    System.out.println("Usuario: " + idUsuario);
                    System.out.println("Producto ordenado: " + producto);
                    System.out.println("Cantidad: " + cantidad);
                    System.out.println("Ubicación: " + direccion);
                } else {
                    System.out.println("No se encontró el pedido con ID: " + idPedido);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener información del pedido");
            e.printStackTrace();
        }
    }

    public void datosUsarios() {
        try (Connection cn = conectar();
                PreparedStatement pstm = cn.prepareStatement("SELECT * FROM usuarios WHERE id=?")) {
            pstm.setString(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    nombre = rs.getString("nombre");
                    apellido = rs.getString("apellido");
                    correo = rs.getString("correo");
                    contrasena = rs.getString("contrasena");

                    System.out.println("nombre: " + nombre);
                    System.out.println("Apellido: " + apellido);
                    System.out.println("Correo: " + correo);
                    System.out.println("Contraseña: " + contrasena);
                } else {
                    System.out.println("No se encontro el usuario");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener informacion del usuario");
            e.printStackTrace();
        }
    }

    public void verificarPedido() {
        try (Connection cn = conectar();
                PreparedStatement pstm = cn.prepareStatement(
                        "UPDATE pedidos SET id_usuario=?, producto=?, cantidad=?, direccion_entrega=? WHERE id_pedido=?")) {

            pstm.setInt(1, idUsuario);
            pstm.setString(2, producto);
            pstm.setString(3, cantidad);
            pstm.setString(4, direccion);
            pstm.setString(5, idPedido);

            if (!producto.isEmpty() && !cantidad.isEmpty() && !direccion.isEmpty()) {
                int modificado = pstm.executeUpdate();
                if (modificado > 0) {
                    System.out.println("Pedido verificado");
                } else {
                    System.out.println("No se encontró el pedido");
                }
            } else {
                System.out.println("Alguno de los valores requeridos está vacío");
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar el pedido");
            e.printStackTrace();
        }
    }

    public void verificarUsuario() {
        try (Connection cn = conectar();
                PreparedStatement pstm = cn.prepareStatement(
                        "UPDATE usuarios SET nombre=?, apellido=?, correo=?, contrasena=? WHERE id=?")) {
            pstm.setString(1, nombre);
            pstm.setString(2, apellido);
            pstm.setString(3, correo);
            pstm.setString(4, contrasena);
            pstm.setString(5, id);

            if (!nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty()) {
                int modificado = pstm.executeUpdate();
                if (modificado > 0) {
                    System.out.println("Usuario verificado");
                } else {
                    System.out.println("No se encontro el usuario");
                }
            } else {
                System.out.println("Alguno de los valores requeridos está vacío");
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el usuario");
            e.printStackTrace();
        }
    }

    public String obtenerTodosLosRegistros() {
        StringBuilder result = new StringBuilder();

        try (Connection cn = conectar();
                Statement stmt = cn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM pedidos")) {

            while (rs.next()) {
                StringJoiner joiner = new StringJoiner(", ");
                joiner.add("ID Pedido: " + rs.getString("id_pedido"));
                joiner.add("ID Usuario: " + rs.getString("id_usuario"));
                joiner.add("Producto: " + rs.getString("producto"));
                joiner.add("Cantidad: " + rs.getString("cantidad"));
                joiner.add("Dirección: " + rs.getString("direccion_entrega"));

                result.append(joiner.toString()).append("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public String obtenerTodosLosUsuarios() {
        StringBuilder result = new StringBuilder();

        try (Connection cn = conectar();
                Statement stmt = cn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios")) {

            while (rs.next()) {
                StringJoiner joiner = new StringJoiner(", ");
                joiner.add("Id Usuario: " + rs.getString("id"));
                joiner.add("Nombre: " + rs.getString("nombre"));
                joiner.add("Apellido: " + rs.getString("apellido"));
                joiner.add("Correo: " + rs.getString("correo"));
                joiner.add("Contraseña: " + rs.getString("contrasena"));

                result.append(joiner.toString()).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public String[][] obtenerTodosLosUsuariosM() {
        List<String[]> registrosUsuarios = new ArrayList<>();
        try (Connection cn = conectar();
                Statement stmt = cn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios")) {

            while (rs.next()) {
                String[] registro2 = {
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getString("contrasena")
                };
                registrosUsuarios.add(registro2);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosUsuarios.toArray(new String[0][0]);
    }

    public String[][] obtenerTodosLosRegistrosEnMatriz() {
        List<String[]> registros = new ArrayList<>();

        try (Connection cn = conectar();
                Statement stmt = cn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM pedidos")) {

            while (rs.next()) {
                String[] registro = {
                        rs.getString("id_pedido"),
                        rs.getString("id_usuario"),
                        rs.getString("producto"),
                        rs.getString("cantidad"),
                        rs.getString("direccion_entrega")
                };
                registros.add(registro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registros.toArray(new String[0][0]);
    }

    public void setIdPedido(String text) {
        idPedido = text;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setProducto(String text) {
        producto = text;
    }

    public void setCantidad(String text) {
        cantidad = text;
    }

    public void setDireccion(String text) {
        direccion = text;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getProducto() {
        return producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setId(String text) {
        id =text;
    }

    public void setNombre(String text) {
        nombre = text;
    }

    public void setApellido(String text) {
        apellido = text;
    }

    public void setCorreo(String text) {
        correo = text;
    }

    public void setContrasena(String text) {
        contrasena = text;
    }
    
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }
}