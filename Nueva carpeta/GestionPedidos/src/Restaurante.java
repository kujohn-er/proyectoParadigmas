package ProyectoParadigmas;

import java.util.ArrayList;

public class Restaurante {
    private ArrayList<String> inventario;
    private boolean pedidoEnProceso;
    private boolean pedidoListo;
    private boolean pedidoEntregado;

    public Restaurante() {
        this.inventario = new ArrayList<>();
        this.pedidoEnProceso = false;
        this.pedidoListo = false;
        this.pedidoEntregado = false;
    }

    public void notificarPedidoEnProceso() {
        this.pedidoEnProceso = true;
        System.out.println("El pedido se está preparando.");
    }

    public void notificarPedidoListo() {
        this.pedidoEnProceso = false;
        this.pedidoListo = true;
        System.out.println("El pedido está listo para ser entregado.");
    }

    public void notificarPedidoEntregado() {
        this.pedidoListo = false;
        this.pedidoEntregado = true;
        System.out.println("El pedido fue entregado correctamente.");
    }

    public void reiniciarEstadoPedidos() {
        this.pedidoEnProceso = false;
        this.pedidoListo = false;
        this.pedidoEntregado = false;
    }

    public ArrayList<String> obtenerInventario() {
        return this.inventario;
    }

    public boolean isPedidoEnProceso() {
        return this.pedidoEnProceso;
    }

    public boolean isPedidoListo() {
        return this.pedidoListo;
    }

    public boolean isPedidoEntregado() {
        return this.pedidoEntregado;
    }
}
