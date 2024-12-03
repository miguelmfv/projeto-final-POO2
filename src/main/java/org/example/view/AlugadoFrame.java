package org.example.view;

import org.example.controller.CarroController;
import org.example.controller.AluguelController;
import org.example.model.Carro;
import org.example.model.Aluguel;
import org.example.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AlugadoFrame extends JPanel {
    private JTable tabelaCarrosAlugados;
    private DefaultTableModel tableModel;
    private CarroController carroController = new CarroController();
    private AluguelController aluguelController = new AluguelController();

    public AlugadoFrame() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Todos os Aluguéis", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"ID Aluguel", "ID Carro", "Modelo", "Marca", "Ano", "Cor", "Preço", "ID Locatário", "Nome Locatário", "Data Início", "KM Inicial", "KM Final"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaCarrosAlugados = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaCarrosAlugados);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnFinalizarAluguel = new JButton("Finalizar Aluguel");
        JButton btnAtualizar = new JButton("Atualizar");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnFinalizarAluguel);
        buttonPanel.add(btnAtualizar);
        add(buttonPanel, BorderLayout.SOUTH);

        btnFinalizarAluguel.addActionListener(e -> finalizarAluguel());
        btnAtualizar.addActionListener(e -> loadAllAlugueis());
        loadAllAlugueis();
    }

    public void loadAllAlugueis() {
        tableModel.setRowCount(0);
        List<Aluguel> alugueis = aluguelController.getAllAlugueis();

        alugueis.forEach(aluguel -> {
            Carro carro = aluguel.getCarro();
            Usuario usuario = aluguel.getUsuario();

            tableModel.addRow(new Object[]{
                    aluguel.getId(),
                    carro.getId(),
                    carro.getModelo(),
                    carro.getMarca(),
                    carro.getAno(),
                    carro.getCor(),
                    carro.getPreco(),
                    usuario.getId(),
                    usuario.getNome(),
                    aluguel.getDt_inicio(),
                    aluguel.getKm_inicial(),
                    aluguel.getKm_final()
            });
        });
    }

    private void finalizarAluguel() {
        int selectedRow = tabelaCarrosAlugados.getSelectedRow();
        if (selectedRow >= 0) {
            Long aluguelId = (Long) tableModel.getValueAt(selectedRow, 0);
            Aluguel aluguel = aluguelController.getAluguelById(aluguelId);
            Carro carro = aluguel.getCarro();

            if (!carro.getAlugado()) {

                if (aluguel != null) {
                    String skmFinal = JOptionPane.showInputDialog(
                            this,
                            "Informe a quilometragem final do carro:",
                            "Finalizar Aluguel",
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (skmFinal != null && !skmFinal.isEmpty()) {
                        try {
                            int kmFinal = Integer.parseInt(skmFinal);
                            if (kmFinal >= aluguel.getKm_inicial()) {

                                carro.setAlugado(true);
                                aluguel.setKm_final(kmFinal);
                                Long diferenca = aluguel.getKm_final() - aluguel.getKm_inicial();
                                Long kmAtual = carro.getKilometragem();
                                Long novoKmAtual = kmAtual + diferenca;
                                carro.setKilometragem(novoKmAtual);
                                carroController.updateCarro(carro);
                                aluguelController.atualizarAluguel(aluguel);

                                loadAllAlugueis();
                                JOptionPane.showMessageDialog(this, "Aluguel finalizado com sucesso!");
                            } else {
                                JOptionPane.showMessageDialog(this, "O valor informado para o KM Final é menor que o KM Incial.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(this, "Informe um número válido para a quilometragem.");
                        }
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Esse aluguel já está finalizado!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um aluguel para finalizar.");
        }
    }
}
