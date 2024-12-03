package org.example.view;

import org.example.controller.AluguelController;
import org.example.controller.CarroController;
import org.example.controller.UsuarioController;
import org.example.model.Aluguel;
import org.example.model.Carro;
import org.example.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class LocadoraUser extends JDialog {
    private JComboBox<String> comboBoxUsuarios;
    private UsuarioController usuarioController = new UsuarioController();
    private CarroController carroController = new CarroController();
    private Usuario usuarioSelecionado;

    public LocadoraUser(Carro carroSelecionado) {
        super();
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Selecione o Usuário:", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        comboBoxUsuarios = new JComboBox<>();
        loadUsuarios();
        add(comboBoxUsuarios, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnCadastrarUsuario = new JButton("Cadastrar Novo Usuário");
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnProsseguir = new JButton("Prosseguir");
        buttonPanel.add(btnCadastrarUsuario);
        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnProsseguir);
        add(buttonPanel, BorderLayout.SOUTH);

        btnCadastrarUsuario.addActionListener(e -> abrirCadastroUsuario());
        btnCancelar.addActionListener(e -> dispose());
        btnProsseguir.addActionListener(e -> prosseguirComAluguel(carroSelecionado));

        setSize(400, 200);
        setLocationRelativeTo(null);
    }

    private void loadUsuarios() {
        List<Usuario> usuarios = usuarioController.getAllUsuarios();
        comboBoxUsuarios.removeAllItems();
        for (Usuario usuario : usuarios) {
            comboBoxUsuarios.addItem(usuario.getNome());
        }
    }

    private void abrirCadastroUsuario() {
        FormAddUser cadastrarUsuarioPanel = new FormAddUser(this);
        cadastrarUsuarioPanel.setVisible(true);

        loadUsuarios();
    }

    private void prosseguirComAluguel(Carro carroSelecionado) {
        String nomeSelecionado = (String) comboBoxUsuarios.getSelectedItem();
        if (nomeSelecionado != null) {
            usuarioSelecionado = usuarioController.getUsuarioByNome(nomeSelecionado);

            if (usuarioSelecionado == null) {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado! Verifique os dados.");
                return;
            }

            Aluguel novoAluguel = new Aluguel();
            novoAluguel.setUsuario(usuarioSelecionado);
            novoAluguel.setCarro(carroSelecionado);
            novoAluguel.setDt_inicio(new Date());
            novoAluguel.setKm_inicial(carroSelecionado.getKilometragem());
            novoAluguel.setKm_final(0);

            AluguelController aluguelController = new AluguelController();
            aluguelController.inserirAluguel(novoAluguel);

            carroSelecionado.setAlugado(false);
            carroController.updateCarro(carroSelecionado);

            JOptionPane.showMessageDialog(this, "Aluguel registrado com sucesso!");
            dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um usuário ou cadastre um novo.");
        }
    }


    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }


}