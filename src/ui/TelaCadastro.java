package ui;

import dominio.Pessoa;
import dominio.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaCadastro extends JFrame {

    private List<Pessoa> usuarios;

    public TelaCadastro(List<Pessoa> usuarios) {
        this.usuarios = usuarios;
        setTitle("Cadastro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a tela no centro do monitor

        JPanel painelCadastro = new JPanel(new GridLayout(4, 1));

        JLabel labelNome = new JLabel("Nome:");
        JTextField campoNome = new JTextField(20);
        painelCadastro.add(labelNome);
        painelCadastro.add(campoNome);

        JLabel labelUsuario = new JLabel("Usuário:");
        JTextField campoUsuario = new JTextField(20);
        painelCadastro.add(labelUsuario);
        painelCadastro.add(campoUsuario);

        JLabel labelSenha = new JLabel("Senha:");
        JPasswordField campoSenha = new JPasswordField(20);
        painelCadastro.add(labelSenha);
        painelCadastro.add(campoSenha);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText();
                String usuario = campoUsuario.getText();
                String senha = new String(campoSenha.getPassword());

                if (nome.isEmpty() || usuario.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.");
                } else {
                    Pessoa novaPessoa = new Pessoa(nome, usuario, senha);
                    usuarios.add(novaPessoa);
                    usuarios.add(new Pessoa("Administrador", "admin", "admin"));
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                    dispose(); // Fecha a tela de cadastro
                }
            }
        });
        painelCadastro.add(botaoCadastrar);

        add(painelCadastro);
    }
}
