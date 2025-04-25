package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {

    private JPanel cardPanel;
    private CardLayout cardLayout;

    public MenuPrincipal() {
        setTitle("Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Configurar CardLayout como administrador principal
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        add(cardPanel); // Añadir el panel de "tarjetas" al JFrame

        // 1. Crear el panel del menú principal (con los 3 botones)
        JPanel menuPanel = crearMenuPanel();
        cardPanel.add(menuPanel, "MENU"); // "MENU" es el nombre de esta "tarjeta"

        JPanel opcion2Panel = crearPanelOpcion("Pantalla de Opción 2", Color.PINK);
        JPanel opcion3Panel = crearPanelOpcion("Pantalla de Opción 3", Color.CYAN);

        cardPanel.add(opcion2Panel, "OPCION2");
        cardPanel.add(opcion3Panel, "OPCION3");

        // Mostrar el panel del menú al inicio
        cardLayout.show(cardPanel, "MENU");
        setVisible(true);
    }

    // Método para crear el panel del menú principal
    private JPanel crearMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("SELECCIONE UNA OPCIÓN", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton boton1 = new JButton("PLATAFORMA");
        JButton boton2 = new JButton("Opción 2");
        JButton boton3 = new JButton("Opción 3");

        // Configurar acciones para cambiar de panel y pasar la opción seleccionada
        boton1.addActionListener(e -> {
            int opcionSeleccionada = 1;  // Opción 1
            Interfaz plataforma = new Interfaz(opcionSeleccionada);
            plataforma.setVisible(true);
            this.dispose();
        });
        boton2.addActionListener(e -> {
            int opcionSeleccionada = 2;  // Opción 2
            Interfaz plataforma = new Interfaz(opcionSeleccionada);
            plataforma.setVisible(true);
            this.dispose();
        });
        boton3.addActionListener(e -> {
            int opcionSeleccionada = 3;  // Opción 3
            Interfaz plataforma = new Interfaz(opcionSeleccionada);
            plataforma.setVisible(true);
            this.dispose();
        });

        panelBotones.add(boton1);
        panelBotones.add(boton2);
        panelBotones.add(boton3);

        panel.add(panelBotones, BorderLayout.CENTER);
        return panel;
    }

    // Método para crear los paneles de cada opción (simplificado)
    private JPanel crearPanelOpcion(String mensaje, Color colorFondo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(colorFondo);

        JLabel label = new JLabel(mensaje, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label, BorderLayout.CENTER);

        // Botón para volver al menú principal
        JButton volverButton = new JButton("Volver al Menú");
        volverButton.addActionListener(e -> cardLayout.show(cardPanel, "MENU"));
        panel.add(volverButton, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipal());
    }
}
