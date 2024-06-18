package ui;

import dominio.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TelaPrincipal extends JFrame {

    private List<Produto> produtos;
    private Map<Produto, Integer> produtosCarrinho;
    private DefaultListModel<String> modelListaProdutos;
    private Map<Produto, JSpinner> spinners;

    public TelaPrincipal() {
        setTitle("Tela Principal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a tela no centro da tela do monitor

        produtosCarrinho = new HashMap<>();
        spinners = new HashMap<>();

        try {
            inicializarProdutos();
        } catch (Exception e) {
            System.out.println("Erro ao inicializar produtos: " + e.getMessage());
            e.printStackTrace();
        }

        JPanel painelPrincipal = new JPanel(new BorderLayout());

        modelListaProdutos = new DefaultListModel<>();
        atualizarListaProdutos();

        JList<String> listaProdutos = new JList<>(modelListaProdutos);
        listaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listaProdutos);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Painel para spinner e botão de adicionar ao carrinho
        JPanel painelBotoes = new JPanel(new BorderLayout());

        JPanel painelSpinners = new JPanel(new GridLayout(0, 2));
        for (Produto produto : produtos) {
            JPanel painelProduto = new JPanel(new FlowLayout(FlowLayout.LEFT));
            painelProduto.add(new JLabel(produto.getNome()));
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, produto.getQuantidadeEmEstoque(), 1));
            painelProduto.add(spinner);
            spinners.put(produto, spinner);
            painelSpinners.add(painelProduto);
        }

        painelBotoes.add(painelSpinners, BorderLayout.CENTER);

        // Botão para adicionar ao carrinho
        JButton botaoAdicionarCarrinho = new JButton("Adicionar ao Carrinho");
        botaoAdicionarCarrinho.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listaProdutos.getSelectedIndex();
                if (selectedIndex != -1) {
                    Produto produtoSelecionado = produtos.get(selectedIndex);
                    int quantidadeSelecionada = (int) spinners.get(produtoSelecionado).getValue();
                    int quantidadeCarrinho = produtosCarrinho.getOrDefault(produtoSelecionado, 0);
                    if (quantidadeCarrinho + quantidadeSelecionada <= produtoSelecionado.getQuantidadeEmEstoque()) {
                        produtosCarrinho.put(produtoSelecionado, quantidadeCarrinho + quantidadeSelecionada);
                        System.out.println("Produto adicionado ao carrinho: " + produtoSelecionado.getNome() + " - Quantidade: " + quantidadeSelecionada);
                        atualizarProdutos(); // Atualiza a lista de produtos na tela principal
                    } else {
                        JOptionPane.showMessageDialog(null, "Quantidade insuficiente em estoque para adicionar mais deste produto.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um produto para adicionar ao carrinho.");
                }
            }
        });

        painelBotoes.add(botaoAdicionarCarrinho, BorderLayout.SOUTH);

        // Botão para abrir o carrinho
        JButton botaoAbrirCarrinho = new JButton("Abrir Carrinho");
        botaoAbrirCarrinho.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    abrirTelaCarrinho();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao abrir a tela do carrinho: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        JPanel painelBotoesPrincipal = new JPanel(new GridLayout(1, 2));
        painelBotoesPrincipal.add(botaoAdicionarCarrinho);
        painelBotoesPrincipal.add(botaoAbrirCarrinho);

        painelPrincipal.add(painelBotoes, BorderLayout.NORTH);
        painelPrincipal.add(painelBotoesPrincipal, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    private void inicializarProdutos() throws Exception {
        try {
            System.out.println("Inicializando produtos...");
            produtos = new ArrayList<>();
            produtos.add(new Produto("Djavan - 21 de Junho", 250.00, 50));
            produtos.add(new Produto("Alien Trance - 28 de Junho", 250.00, 100));
            produtos.add(new Produto("Alien Trance - 19 de Julho", 150.00, 250));
            produtos.add(new Produto("Diversity Festival - 3 a 14 de Setembro", 180.00, 500));
            // Adicione outros produtos conforme necessário
        } catch (Exception e) {
            System.out.println("Erro ao inicializar produtos: " + e.getMessage());
            e.printStackTrace();
            throw new Exception("Erro ao inicializar produtos.", e);
        }
    }

    private void atualizarListaProdutos() {
        modelListaProdutos.clear();
        for (Produto produto : produtos) {
            modelListaProdutos.addElement(produto.getNome() + " - R$" + produto.getPreco() + " - Quantidade: " + produto.getQuantidadeEmEstoque());
        }
    }

    private void abrirTelaCarrinho() {
        TelaCarrinho telaCarrinho = new TelaCarrinho(produtosCarrinho, this);
        telaCarrinho.setVisible(true); // Exibe a tela de carrinho
    }

    public void atualizarProdutos() {
        atualizarListaProdutos();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaPrincipal().setVisible(true); // Exibe a tela principal
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao iniciar a tela principal: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    public void limparCarrinho() {
        produtosCarrinho.clear();
    }
}
