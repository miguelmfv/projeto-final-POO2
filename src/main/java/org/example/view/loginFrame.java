package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginFrame extends JFrame {
    JButton btnLogin = new JButton("Login");
    JTextField fieldEmail = new JTextField();
    JPasswordField fieldPassword = new JPasswordField();
    JPanel mainPanel = new JPanel();
    JPanel emailPanel = new JPanel(new BorderLayout(10, 0));
    JPanel passwordPanel = new JPanel(new BorderLayout(10, 0));
    JLabel lblTitle = new JLabel("ALUCar - Login");
    JLabel lblPassword = new JLabel("Senha: ");
    JLabel lblEmail = new JLabel("E-mail: ");

    public loginFrame() {
        setTitle("Login ALUCar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setMinimumSize(new Dimension(720, 480));
        setLocationRelativeTo(null);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 28));
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(lblTitle);

        fieldEmail.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        fieldEmail.setMaximumSize(new Dimension(300, 50));
        fieldEmail.setBorder(BorderFactory.createCompoundBorder(fieldEmail.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        emailPanel.add(lblEmail, BorderLayout.WEST);
        emailPanel.add(fieldEmail, BorderLayout.CENTER);
        emailPanel.setMaximumSize(new Dimension(400, 50));
        fieldEmail.add(Box.createVerticalStrut(10));
        mainPanel.add(emailPanel);

        fieldPassword.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        fieldPassword.setMaximumSize(new Dimension(300, 50));
        fieldPassword.setBorder(BorderFactory.createCompoundBorder(fieldPassword.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        passwordPanel.add(lblPassword, BorderLayout.WEST);
        passwordPanel.add(fieldPassword, BorderLayout.CENTER);
        passwordPanel.setMaximumSize(new Dimension(400, 50));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(passwordPanel);

        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(btnLogin);

        add(mainPanel);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email;
                String password;
                email = fieldEmail.getText();
                password = fieldPassword.getPassword().toString();
            }
        });
    }
}
