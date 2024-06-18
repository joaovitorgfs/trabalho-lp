package dominio;

import java.util.ArrayList;
import java.util.List;

public class Venda {
    private Cliente cliente;
    private Vendedor vendedor;
    private List<ItemVenda> itens;
    private FormaPagamento formaPagamento;

    public Venda(Cliente cliente, Vendedor vendedor, FormaPagamento formaPagamento) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.formaPagamento = formaPagamento;
        this.itens = new ArrayList<>();
    }

    // Métodos getters e setters para acessar e modificar os atributos da classe.
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    // Método para adicionar um item à lista de itens da venda.
    public void adicionarItem(ItemVenda item) {
        this.itens.add(item);
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    // Método para calcular o total da venda, somando o preço de todos os itens.
    public double calcularTotal() {
        double total = 0;
        for (ItemVenda item : itens) {
            total += item.getQuantidade() * item.getProduto().getPreco();
        }
        return total;
    }
}