package org.example.view;

import org.example.model.Carro;
import org.example.controller.CarroController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LocadoraFrame extends JPanel {
    private CarroController carroController = new CarroController();
    private JTable table;
    private DefaultTableModel tableModel;


    public LocadoraFrame() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Carros Disponíveis para Aluguel", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Modelo", "Marca", "Placa", "Ano", "Cor", "Preço", "Status", "Kilometragem"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        JButton btnAlugar = new JButton("Iniciar Aluguel");
        JButton btnAtualizar = new JButton("Atualizar");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnAlugar);
        buttonPanel.add(btnAtualizar);
        add(buttonPanel, BorderLayout.SOUTH);

        btnAlugar.addActionListener(e -> iniciarAluguel());
        btnAtualizar.addActionListener(e -> loadCarros());
    }

    private void iniciarAluguel() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Long carroId = (Long) tableModel.getValueAt(selectedRow, 0);
            Carro carro = carroController.getCarroById(carroId);

            LocadoraUser locUser = new LocadoraUser(carro);
            locUser.setVisible(true);
            locUser.setLocationRelativeTo(null);
            locUser.pack();
            loadCarros();
        }
        else
        {
        JOptionPane.showMessageDialog(this, "Selecione um carro para alugar.");
        }
    }

    public void loadCarros() {
        tableModel.setRowCount(0);
        carroController.getAllCarros().stream()
                .filter(carro -> carro.getAlugado())
                .forEach(carro -> {
                    tableModel.addRow(new Object[]{
                            carro.getId(),
                            carro.getModelo(),
                            carro.getMarca(),
                            carro.getPlaca(),
                            carro.getAno(),
                            carro.getCor(),
                            carro.getPreco(),
                            carro.getAlugado() ? "Disponível" : "Indisponível",
                            carro.getKilometragem()
                    });
                });
    }
}


