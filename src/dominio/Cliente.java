package dominio;

public class Cliente extends Pessoa {
    private String endereco;

    public Cliente(String nome, String cpf, String telefone, String endereco) {
        super(nome, cpf, telefone);
        this.endereco = endereco;
    }

    // Métodos getters e setters para acessar e modificar o endereço do cliente.
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}