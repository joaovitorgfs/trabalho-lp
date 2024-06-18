package ui;

import dominio.Produto;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaEstoque extends JFrame {

    private List<Produto> produtos;

    public TelaEstoque() {
        try {
            inicializarProdutos(); // Inicializa os produtos ao criar a tela

            setTitle("Tela de Estoque");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null); // Centraliza a tela no centro da tela do monitor

            JPanel painelEstoque = new JPanel(new GridLayout(produtos.size(), 1));

            for (Produto produto : produtos) {
                JLabel labelProduto = new JLabel(produto.getNome() + " - R$" + produto.getPreco() +
                        " | Estoque: " + produto.getQuantidadeEmEstoque());
                painelEstoque.add(labelProduto);
            }

            JButton botaoAdicionarProduto = new JButton("Adicionar Produto");
            botaoAdicionarProduto.addActionListener(e -> {
                JOptionPane.showMessageDialog(null, "Implementar lógica para adicionar produto ao estoque."); // Ação de adicionar produto ao estoque (futuro)
            });
            painelEstoque.add(botaoAdicionarProduto);

            add(painelEstoque);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao inicializar tela de estoque: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void inicializarProdutos() throws Exception {
        try {
            produtos.clear();
            System.out.println("Inicializando produtos...");
            produtos = new ArrayList<>();
            produtos.add(new Produto("Djavan - 21 de Junho", 250.00, 50));
            produtos.add(new Produto("Alien Trance - 28 de Junho", 250.00, 100));
            produtos.add(new Produto("Alien Trance - 19 de Julho", 150.00, 250));
            produtos.add(new Produto("Diversity Festival - 3 a 14 de Setembro", 180.00, 500));
        } catch (Exception e) {
            System.out.println("Erro ao inicializar produtos: " + e.getMessage());
            throw new Exception("Erro ao inicializar produtos.", e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaEstoque().setVisible(true); // Inicia a tela de estoque
            }
        });
    }
}