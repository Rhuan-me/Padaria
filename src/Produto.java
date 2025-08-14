import java.util.Random;

/**
 * Classe que representa um produto no sistema.
 * Possui código gerado automaticamente, nome e preço.
 */
public class Produto {
    private static int proximoCodigo = 1;
    private int codigo;
    private String nome;
    private double preco;

    /**
     * Construtor da classe Produto.
     * O código é gerado automaticamente.
     *
     * @param nome  String
     * @param preco double
     */
    public Produto(String nome, double preco) {
        this.codigo = proximoCodigo++;
        this.nome = nome;
        this.preco = preco;
    }

    /**
     * Retorna o código do produto.
     *
     * @return codigo int
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return nome String
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o preço do produto.
     *
     * @return preco double
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço do produto.
     *
     * @param preco double
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return String.format("Cód: %d | %s | R$%.2f", codigo, nome, preco);
    }

    public String resumo() {
        return String.format("%s | R$%.2f", nome, preco);
    }
}
