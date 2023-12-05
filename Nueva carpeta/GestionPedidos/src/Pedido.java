package proyecto_paradigmas;
/**
 *
 * @author doria
 */
public class Pedido {
    private int idPedido;
    private int idUsuario;
    private String producto;
    private int cantidad;
    private String direccion;

    public Pedido(int idPedido, int idUsuario, String producto, int cantidad, String direccion) {
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
        this.producto = producto;
        this.cantidad = cantidad;
        this.direccion = direccion;
    }
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
}
