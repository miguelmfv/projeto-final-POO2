package org.example.view;
import org.example.controller.CarroController;
import org.example.model.Carro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminFrame extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private CarroController carroController = new CarroController();

    public AdminFrame() {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Todos os carros!", SwingConstants.CENTER);
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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnAddCar = new JButton("Adicionar Novo Carro");
        JButton btnEditCar = new JButton("Editar Carro Selecionado");
        JButton btnRemoveCar = new JButton("Remover Carro Selecionado");
        JButton btnAtualizar = new JButton("Atualizar");
        buttonPanel.add(btnAddCar);
        buttonPanel.add(btnEditCar);
        buttonPanel.add(btnRemoveCar);
        buttonPanel.add(btnAtualizar);
        add(buttonPanel, BorderLayout.SOUTH);

        btnAddCar.addActionListener(e -> adicionarCarro());
        btnEditCar.addActionListener(e -> actEditarCarro());
        btnRemoveCar.addActionListener(e -> actRemoverCarro());
        btnAtualizar.addActionListener(e -> loadCarros());
    }

    private void adicionarCarro() {
        FormAddCarro form = new FormAddCarro((Frame) SwingUtilities.getWindowAncestor(this));
        form.setVisible(true);

        if (form.getModelo() != null && !form.getModelo().isEmpty()) {
            carroController.addCarro(
                    new Carro(form.getMarca(), form.getModelo(), form.getCor(), form.getPlaca(),
                            form.getAno(), form.getStatus(), form.getPreco(), form.getKilometragem())
            );
            loadCarros();
        }
    }

    void loadCarros() {
        tableModel.setRowCount(0);
        carroController.getAllCarros().forEach(carro -> {
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

    private void actEditarCarro() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Long carroId = (Long) tableModel.getValueAt(selectedRow, 0);
            Carro carro = carroController.getCarroById(carroId);

            Frame parent = (Frame) SwingUtilities.getWindowAncestor(this);
            if (parent == null) {
                JOptionPane.showMessageDialog(this, "Erro: Janela principal não encontrada!");
                return;
            }

            FormEditCarro dialog = new FormEditCarro(carro);
            dialog.setAlterarAction(e -> {
                carro.setModelo(dialog.getModelo());
                carro.setMarca(dialog.getMarca());
                carro.setPlaca(dialog.getPlaca());
                carro.setAno(dialog.getAno());
                carro.setCor(dialog.getCor());
                carro.setPreco(dialog.getPreco());
                carro.setKilometragem(dialog.getKilometragem());
                carro.setAlugado(dialog.getAlugado());

                carroController.updateCarro(carro);
                loadCarros();
                dialog.dispose();
            });

            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um carro para editar.");
        }
    }

    private void actRemoverCarro() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Long carroId = (Long) tableModel.getValueAt(selectedRow, 0);
            carroController.deleteCarro(carroId);
            loadCarros();
            JOptionPane.showMessageDialog(this, "Carro removido!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um carro para remover.");
        }
    }
}
