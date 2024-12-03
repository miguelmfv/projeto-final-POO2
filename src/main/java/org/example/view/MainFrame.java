package org.example.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JLabel lblTitle = new JLabel("Bem-vindo(a) ao ALUCar");
    private JPanel mainPanel = new JPanel();
    private JPanel mainContentPanel = new JPanel(new CardLayout());

    public MainFrame() {
        setTitle("ALUCar - Locadora de Veículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setMinimumSize(new Dimension(720, 480));
        setLocationRelativeTo(null);

        mainPanel.setLayout(new BorderLayout());

        JPanel painelTopo = new JPanel();
        painelTopo.setLayout(new BorderLayout());
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 28));
        painelTopo.add(lblTitle, BorderLayout.CENTER);
        mainPanel.add(painelTopo, BorderLayout.NORTH);

        JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnLocadora = new JButton("Locadora");
        JButton btnAlugados = new JButton("Carros Alugados");
        JButton btnAdmin = new JButton("Admin");
        navbar.add(btnLocadora);
        navbar.add(btnAlugados);
        navbar.add(btnAdmin);
        mainPanel.add(navbar, BorderLayout.WEST);

        mainContentPanel.add(createLocadoraPanel(), "Locadora");
        mainContentPanel.add(createAlugadosPanel(), "Alugados");
        mainContentPanel.add(createAdminPanel(), "Admin");
        mainPanel.add(mainContentPanel, BorderLayout.CENTER);

        CardLayout cardLayout = (CardLayout) mainContentPanel.getLayout();
        btnLocadora.addActionListener(e -> cardLayout.show(mainContentPanel, "Locadora"));
        btnAlugados.addActionListener(e -> cardLayout.show(mainContentPanel, "Alugados"));
        btnAdmin.addActionListener(e -> cardLayout.show(mainContentPanel, "Admin"));

        add(mainPanel);
    }

    private JPanel createLocadoraPanel() {
        LocadoraFrame locadoraPanel = new LocadoraFrame();
        locadoraPanel.loadCarros();
        return locadoraPanel;
    }

    private JPanel createAlugadosPanel() {
        AlugadoFrame alugadosPanel = new AlugadoFrame();
        alugadosPanel.loadAllAlugueis();
        return alugadosPanel;
    }

    private JPanel createAdminPanel() {
        AdminFrame adminFrame = new AdminFrame();
        adminFrame.loadCarros();
        return adminFrame;
    }
}