package ProyectoParadigmas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonPedidoEntregado extends JButton {
    private Restaurante restaurante;

    public BotonPedidoEntregado(Restaurante restaurante) {
        super(" Pedido Entregado");
        this.restaurante = restaurante;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurante.notificarPedidoEntregado();
                JOptionPane.showMessageDialog(null, "Pedido entregado al repartidor.");
            }
        });
    }
}
