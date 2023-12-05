package ProyectoParadigmas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazGrafica extends JFrame {
    private Restaurante restaurante;

    public InterfazGrafica() {
        this.restaurante = new Restaurante();

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Acciones");
        menuBar.setBackground(new Color(10, 10, 10));
        menuBar.setOpaque(true);
        menu.setForeground(Color.WHITE);

        menuBar.add(menu);
        setJMenuBar(menuBar);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(10, 10, 10));
        panelPrincipal.setOpaque(true);

        ImageIcon logoIcon = new ImageIcon("C:\\Pruebas\\ProyectoParadigmas\\LogoRappi.png");
        JLabel logoLabel = new JLabel(logoIcon);
        panelPrincipal.add(logoLabel, BorderLayout.SOUTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelBotones.setBackground(new Color(10, 10, 10));
        panelBotones.setOpaque(true);

        BotonRealizandoPedido botonRealizandoPedido = new BotonRealizandoPedido(restaurante);
        BotonPedidoListo botonPedidoListo = new BotonPedidoListo(restaurante);
        BotonPedidoEntregado botonPedidoEntregado = new BotonPedidoEntregado(restaurante);
        botonRealizandoPedido.setFont(new Font("default", Font.BOLD, 14));
        botonPedidoListo.setFont(new Font("default", Font.BOLD, 14));
        botonPedidoEntregado.setFont(new Font("default", Font.BOLD, 18));

        botonPedidoEntregado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panelBotones.add(botonRealizandoPedido);
        panelBotones.add(botonPedidoListo);
        panelBotones.add(botonPedidoEntregado);

        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        add(panelPrincipal);

        setTitle("Rappi: Sistema del Restaurante");
        setSize(375, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new InterfazGrafica();
    }
}
