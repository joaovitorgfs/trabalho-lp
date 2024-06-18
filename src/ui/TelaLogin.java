package ui;

import dominio.Pessoa;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaLogin extends JFrame {

    private List<Pessoa> usuarios;

    public TelaLogin(List<Pessoa> usuarios) {
        this.usuarios = usuarios;
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a tela no centro do monitor

        JPanel painelLogin = new JPanel(new GridLayout(3, 1));

        JLabel labelUsuario = new JLabel("Usuário:");
        JTextField campoUsuario = new JTextField(20);
        painelLogin.add(labelUsuario);
        painelLogin.add(campoUsuario);

        JLabel labelSenha = new JLabel("Senha:");
        JPasswordField campoSenha = new JPasswordField(20);
        painelLogin.add(labelSenha);
        painelLogin.add(campoSenha);

        JButton botaoLogin = new JButton("Login");
        botaoLogin.addActionListener(e -> {
            String usuario = campoUsuario.getText();
            String senha = new String(campoSenha.getPassword());

            boolean autenticado = false;
            for (Pessoa pessoa : usuarios) {
                if (pessoa.getUsuario().equals(usuario) && pessoa.getSenha().equals(senha)) {
                    autenticado = true;
                    break;
                }
            }

            if (autenticado) {
                JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
                // Abre a tela principal após o login ser bem sucedido
                new TelaPrincipal().setVisible(true);
                dispose(); // Fecha a tela de login
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
            }
        });
        painelLogin.add(botaoLogin);

        JButton botaoCadastro = new JButton("Cadastrar");
        botaoCadastro.addActionListener(e -> {
            new TelaCadastro(usuarios).setVisible(true);
        });
        painelLogin.add(botaoCadastro);

        add(painelLogin);
    }
}
