package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormAddCarro extends JDialog {
    private JTextField txtModelo;
    private JTextField txtMarca;
    private JTextField txtAno;
    private JTextField txtCor;
    private JTextField txtPlaca;
    private JTextField txtPreco;
    private JTextField txtKilometragem;
    private JComboBox<String> comboStatus;

    public FormAddCarro(Frame owner) {
        super(owner, "Adicionar Novo Carro", true);
        setSize(400, 300);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        formPanel.add(txtModelo);

        formPanel.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        formPanel.add(txtMarca);

        formPanel.add(new JLabel("Ano:"));
        txtAno = new JTextField();
        formPanel.add(txtAno);

        formPanel.add(new JLabel("Cor:"));
        txtCor = new JTextField();
        formPanel.add(txtCor);

        formPanel.add(new JLabel("Placa:"));
        txtPlaca = new JTextField();
        formPanel.add(txtPlaca);

        formPanel.add(new JLabel("Preço:"));
        txtPreco = new JTextField();
        formPanel.add(txtPreco);

        formPanel.add(new JLabel("Kilometragem:"));
        txtKilometragem = new JTextField();
        formPanel.add(txtKilometragem);

        formPanel.add(new JLabel("Status:"));
        comboStatus = new JComboBox<>(new String[]{"Disponível", "Alugado"});
        formPanel.add(comboStatus);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSave = new JButton("Salvar");
        JButton btnCancel = new JButton("Cancelar");
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        add(buttonPanel, BorderLayout.SOUTH);

        btnSave.addActionListener(e -> {
            if (validateInput()) {
                dispose();
            }
        });
        btnCancel.addActionListener(e -> dispose());
    }

    private boolean validateInput() {
        if (txtModelo.getText().isEmpty() || txtMarca.getText().isEmpty() ||
                txtAno.getText().isEmpty() || txtCor.getText().isEmpty() ||
                txtPreco.getText().isEmpty() || txtPlaca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Pattern pattern = Pattern.compile("([A-Za-z]{3})-?\\s?(\\d[0-9A-Za-z]\\d{2})");
        Matcher matcher = pattern.matcher(txtPlaca.getText());
        boolean match = matcher.find();
        if (!match) {
            JOptionPane.showMessageDialog(this, "Placa Invalida.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Double.parseDouble(txtPreco.getText());
            Integer.parseInt(txtAno.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ano e preço devem ser valores numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public String getModelo() {
        return txtModelo.getText();
    }
    public String getMarca() {
        return txtMarca.getText();
    }
    public int getAno() {
        return Integer.parseInt(txtAno.getText());
    }
    public String getPlaca() {
        return txtPlaca.getText();
    }
    public String getCor() {
        return txtCor.getText();
    }
    public Float getPreco() {
        return Float.parseFloat(txtPreco.getText());
    }
    public Long getKilometragem() {
        return Long.parseLong(txtKilometragem.getText());
    }
    public boolean getStatus() {
        String status = (String) comboStatus.getSelectedItem();
        return status.equalsIgnoreCase("Disponível");
    }
}
