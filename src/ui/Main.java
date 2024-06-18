package ui;

import dominio.Pessoa;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Pessoa> usuarios = new ArrayList<>(); // Inicializa a lista de usuários
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin(usuarios).setVisible(true); // Passa a lista de usuários para a tela de login
            }
        });
    }
}
