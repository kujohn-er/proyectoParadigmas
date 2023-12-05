package proyecto_paradigmas;

public interface UsuarioDAO {

    void crearUsuario(Usuario usuario);

    void login(Usuario usuario);

    void hacerPedido(Pedido pedido);

   void seleccionarPedidoPorId(int idPedido); 

    void cancelarPedido(Pedido pedido);

    void modificarPedido(Pedido pedido);
    void seleccionarUsuario(Usuario usuario);

    void actualizarUsuario(Usuario usuario);

    void eliminarUsuario(String correo);
}
