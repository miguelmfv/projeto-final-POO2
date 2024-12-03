package org.example.view;

import org.example.controller.UsuarioController;
import org.example.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class FormAddUser extends JDialog {
    private JTextField txtNome, txtEmail, txtTelefone;
    private UsuarioController usuarioController = new UsuarioController();

    public FormAddUser(LocadoraUser parentFrame) {
        super(parentFrame, "Cadastrar Novo Usuário", true);
        setLayout(new GridLayout(4, 2, 10, 10));

        // Campos de entrada
        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Email:"));
        txtEmail = new JTextField();
        add(txtEmail);

        add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        add(txtTelefone);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(e -> cadastrarUsuario());
        add(btnCadastrar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);

        setSize(300, 200);
        setLocationRelativeTo(parentFrame);
    }

    private void cadastrarUsuario() {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String telefone = txtTelefone.getText();

        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.");
            return;
        }

        Usuario usuario = new Usuario(nome, email, telefone);
        usuarioController.addUsuario(usuario);
        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso.");
        dispose();
    }
}