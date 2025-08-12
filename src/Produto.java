import java.util.Random;

public class Produto {
    private static int proximoCodigo = 1;
    private int codigo;
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.codigo = proximoCodigo++;
        this.nome = nome;
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return String.format("Cód: %d | %s | R$%.2f", codigo, nome, preco);
    }
}
