package ui;

import dominio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class TelaCarrinho extends JFrame {

    private Map<Produto, Integer> produtosCarrinho;
    private TelaPrincipal telaPrincipal;
    private Map<Produto, JSpinner> spinners;

    public TelaCarrinho(Map<Produto, Integer> produtosCarrinho, TelaPrincipal telaPrincipal) {
        this.produtosCarrinho = produtosCarrinho;
        this.telaPrincipal = telaPrincipal;
        this.spinners = new HashMap<>();

        setTitle("Carrinho de Compras");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a tela no centro da tela do monitor

        JPanel painelCarrinho = new JPanel(new GridLayout(produtosCarrinho.size() + 1, 1));

        try {
            for (Map.Entry<Produto, Integer> entry : produtosCarrinho.entrySet()) {
                Produto produto = entry.getKey();
                int quantidade = entry.getValue();
                JPanel painelProduto = new JPanel(new BorderLayout());
                JLabel labelProduto = new JLabel(produto.getNome() + " - R$" + produto.getPreco() + " - Quantidade no Carrinho: " + quantidade);
                painelProduto.add(labelProduto, BorderLayout.CENTER);

                JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, quantidade, 1));
                spinners.put(produto, spinner);
                painelProduto.add(spinner, BorderLayout.EAST);

                JButton botaoRemover = new JButton("Remover");
                botaoRemover.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int quantidadeRemover = (int) spinners.get(produto).getValue();
                        if (quantidadeRemover == quantidade) {
                            produtosCarrinho.remove(produto);
                        } else {
                            produtosCarrinho.put(produto, quantidade - quantidadeRemover);
                        }
                        JOptionPane.showMessageDialog(null, "Produto removido com sucesso.");
                        dispose(); // Fecha a tela de carrinho
                        new TelaCarrinho(produtosCarrinho, telaPrincipal).setVisible(true); // Reabre a tela de carrinho atualizada
                    }
                });
                painelProduto.add(botaoRemover, BorderLayout.SOUTH);

                JButton botaoRemoverTodos = new JButton("Remover Todos");
                botaoRemoverTodos.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        produtosCarrinho.remove(produto);
                        JOptionPane.showMessageDialog(null, "Todos os itens do produto foram removidos com sucesso.");
                        dispose(); // Fecha a tela de carrinho
                        new TelaCarrinho(produtosCarrinho, telaPrincipal).setVisible(true); // Reabre a tela de carrinho atualizada
                    }
                });
                painelProduto.add(botaoRemoverTodos, BorderLayout.WEST);

                painelCarrinho.add(painelProduto);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir produtos no carrinho: " + ex.getMessage());
            ex.printStackTrace();
        }

        JButton botaoVoltar = new JButton("Voltar para a Tela Principal");
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dispose(); // Fecha a tela de carrinho
                    telaPrincipal.setVisible(true); // Mostra a tela principal novamente
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao voltar para a tela principal: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        painelCarrinho.add(botaoVoltar);

        JButton botaoEsvaziar = new JButton("Esvaziar Carrinho");
        botaoEsvaziar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    produtosCarrinho.clear();
                    JOptionPane.showMessageDialog(null, "Carrinho esvaziado com sucesso.");
                    dispose(); // Fecha a tela de carrinho
                    telaPrincipal.setVisible(true); // Mostra a tela principal novamente
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao esvaziar o carrinho: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        painelCarrinho.add(botaoEsvaziar);

        JButton botaoFinalizarCompra = new JButton("Finalizar Compra");
        botaoFinalizarCompra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    abrirTelaPagamento();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao finalizar a compra: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        painelCarrinho.add(botaoFinalizarCompra);

        add(painelCarrinho);
    }

    private void abrirTelaPagamento() {
        TelaPagamento telaPagamento = new TelaPagamento(produtosCarrinho, telaPrincipal);
        telaPagamento.setVisible(true); // Exibe a tela de pagamento
        dispose(); // Fecha a tela do carrinho
    }
}
