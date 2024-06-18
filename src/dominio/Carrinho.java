package dominio;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<ItemVenda> itens;

    public Carrinho() {
        this.itens = new ArrayList<>();
    }

    // Método para adicionar um item ao carrinho.
    public void adicionarItem(ItemVenda item) {
        this.itens.add(item);
    }

    // Método para remover um item do carrinho.
    public void removerItem(ItemVenda item) {
        this.itens.remove(item);
    }

    // Método para calcular o total do carrinho, somando o preço de todos os itens.
    public double calcularTotal() {
        double total = 0;
        for (ItemVenda item : itens) {
            total += item.getQuantidade() * item.getProduto().getPreco();
        }
        return total;
    }

    // Método getter para acessar a lista de itens no carrinho.
    public List<ItemVenda> getItens() {
        return itens;
    }
}