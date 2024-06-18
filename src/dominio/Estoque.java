package dominio;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<Produto, Integer> produtos;

    public Estoque() {
        this.produtos = new HashMap<>();
    }

    // Método para adicionar um produto ao estoque.
    public void adicionarProduto(Produto produto, int quantidade) {
        this.produtos.put(produto, quantidade);
    }

    // Método para remover uma quantidade específica de um produto do estoque.
    public void removerProduto(Produto produto, int quantidade) {
        if (this.produtos.containsKey(produto)) {
            int novaQuantidade = this.produtos.get(produto) - quantidade;
            if (novaQuantidade <= 0) {
                this.produtos.remove(produto);
            } else {
                this.produtos.put(produto, novaQuantidade);
            }
        }
    }

    // Método para obter a quantidade disponível de um produto no estoque.
    public int getQuantidade(Produto produto) {
        return this.produtos.getOrDefault(produto, 0);
    }

    // Método getter para acessar o mapa de produtos no estoque.
    public Map<Produto, Integer> getProdutos() {
        return produtos;
    }
}