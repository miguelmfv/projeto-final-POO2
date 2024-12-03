package org.example.view;

import org.example.model.Carro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FormEditCarro extends JDialog {
    private JTextField txtModelo;
    private JTextField txtMarca;
    private JTextField txtAno;
    private JTextField txtPlaca;
    private JTextField txtKilometragem;
    private JTextField txtCor;
    private JTextField txtPreco;
    private JComboBox<String> comboStatus;
    private JButton btnAlterar;
    private JButton btnCancelar;

    public FormEditCarro(Carro carro) {
        super();
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtModelo = new JTextField(carro.getModelo());
        txtMarca = new JTextField(carro.getMarca());
        txtPlaca = new JTextField(carro.getPlaca());
        txtAno = new JTextField(String.valueOf(carro.getAno()));
        txtCor = new JTextField(carro.getCor());
        txtKilometragem = new JTextField(String.valueOf(carro.getKilometragem()));
        txtPreco = new JTextField(String.valueOf(carro.getPreco()));
        comboStatus = new JComboBox<>(new String[]{"Disponível", "Indisponível"});
        comboStatus.setSelectedItem(carro.getAlugado() ? "Disponível" : "Indisponível");

        btnAlterar = new JButton("Alterar");
        btnCancelar = new JButton("Cancelar");

        formPanel.add(new JLabel("Modelo:"));
        formPanel.add(txtModelo);

        formPanel.add(new JLabel("Marca:"));
        formPanel.add(txtMarca);

        formPanel.add(new JLabel("Placa:"));
        formPanel.add(txtPlaca);

        formPanel.add(new JLabel("Ano:"));
        formPanel.add(txtAno);

        formPanel.add(new JLabel("Cor:"));
        formPanel.add(txtCor);

        formPanel.add(new JLabel("Preço:"));
        formPanel.add(txtPreco);

        formPanel.add(new JLabel("Kilometragem:"));
        formPanel.add(txtKilometragem);


        formPanel.add(new JLabel("Status:"));
        formPanel.add(comboStatus);

        formPanel.add(btnAlterar);
        formPanel.add(btnCancelar);

        add(formPanel, BorderLayout.CENTER);

        btnCancelar.addActionListener(e -> dispose());
    }

    public String getModelo() {
        return txtModelo.getText();
    }

    public String getMarca() {
        return txtMarca.getText();
    }

    public String getPlaca() {
        return txtPlaca.getText();
    }

    public int getAno() {
        return Integer.parseInt(txtAno.getText());
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

    public boolean getAlugado() {
        return comboStatus.getSelectedItem().equals("Disponível");
    }

    public void setAlterarAction(ActionListener action) {
        btnAlterar.addActionListener(action);
    }
}
