package ProyectoParadigmas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonPedidoListo extends JButton {
    private Restaurante restaurante;

    public BotonPedidoListo(Restaurante restaurante) {
        super("Pedido Listo");
        this.restaurante = restaurante;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurante.notificarPedidoListo();
                JOptionPane.showMessageDialog(null, "Pedido listo para entrega.");
            }
        });
    }
}
