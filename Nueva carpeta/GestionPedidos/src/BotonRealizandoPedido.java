package ProyectoParadigmas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonRealizandoPedido extends JButton {
    private Restaurante restaurante;

    public BotonRealizandoPedido(Restaurante restaurante) {
        super("Preparando Pedido");
        this.restaurante = restaurante;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurante.notificarPedidoEnProceso();
                JOptionPane.showMessageDialog(null, "Pedido en proceso.");
            }
        });
    }
}
