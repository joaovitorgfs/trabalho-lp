package dominio;

public class Vendedor extends Pessoa {
    private double salario;

    public Vendedor(String nome, String cpf, String telefone, double salario) {
        super(nome, cpf, telefone);
        this.salario = salario;
    }

    // Métodos getters e setters para acessar e modificar o salário do vendedor.
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}