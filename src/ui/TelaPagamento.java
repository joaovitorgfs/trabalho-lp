package ui;

import dominio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class TelaPagamento extends JFrame {

    private Map<Produto, Integer> produtosCarrinho;
    private TelaPrincipal telaPrincipal;

    public TelaPagamento(Map<Produto, Integer> produtosCarrinho, TelaPrincipal telaPrincipal) {
        this.produtosCarrinho = produtosCarrinho;
        this.telaPrincipal = telaPrincipal;

        setTitle("Pagamento");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a tela no centro da tela do monitor

        JPanel painelPagamento = new JPanel(new GridLayout(3, 1));

        JLabel labelTitulo = new JLabel("Selecione a Forma de Pagamento:");
        painelPagamento.add(labelTitulo);

        JComboBox<String> comboBoxFormaPagamento = new JComboBox<>();
        comboBoxFormaPagamento.addItem("Cartão de Crédito");
        comboBoxFormaPagamento.addItem("Boleto Bancário");
        comboBoxFormaPagamento.addItem("Transferência Bancária");
        painelPagamento.add(comboBoxFormaPagamento);

        JButton botaoPagar = new JButton("Pagar");
        botaoPagar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String formaPagamento = (String) comboBoxFormaPagamento.getSelectedItem();
                    realizarPagamento(formaPagamento);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao processar pagamento: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        painelPagamento.add(botaoPagar);

        add(painelPagamento);
    }

    private void realizarPagamento(String formaPagamento) {
        try {
            // Lógica de simulação de pagamento (pode ser integrada com sistemas de pagamento reais)
            boolean pagamentoEfetuado = simularPagamento(formaPagamento);

            if (pagamentoEfetuado) {
                JOptionPane.showMessageDialog(null, "Pagamento efetuado com sucesso! Compra concluída.");
                // Atualizar a quantidade em estoque dos produtos
                for (Map.Entry<Produto, Integer> entry : produtosCarrinho.entrySet()) {
                    Produto produto = entry.getKey();
                    int quantidade = entry.getValue();
                    produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - quantidade);
                }
                telaPrincipal.atualizarProdutos();
                // Limpar carrinho após o pagamento
                telaPrincipal.limparCarrinho();
                // Fechar a tela de pagamento
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Falha no pagamento. Por favor, tente novamente.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar pagamento: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private boolean simularPagamento(String formaPagamento) {
        return true;
    }
}
