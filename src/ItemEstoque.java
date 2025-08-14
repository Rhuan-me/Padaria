import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Classe que representa um item produzido pela padaria
 */
class ItemEstoque {
    private Produto produto;
    private LocalDate dataProducao;
    private LocalDate dataValidade;
    private int quantidade;

    /**
     * Construtor do ItemEstoque
     *
     * @param produto      Produto
     * @param dataProducao Data de produção do item
     * @param dataValidade Data de validade do item
     */
    ItemEstoque(Produto produto, LocalDate dataProducao, LocalDate dataValidade, int quantidade) {
        this.produto = produto;
        this.dataProducao = dataProducao;
        this.dataValidade = dataValidade;
        this.quantidade = quantidade;
    }

    /**
     * Verifica se o item está próximo de vencer
     *
     * @return boolean
     */
    public boolean estaProximoVencimento() {
        return java.time.Period.between(LocalDate.now(), dataValidade).getDays() <= 2;
    }

    /**
     * Chama a função para retornar o nome do produto
     *
     * @return produto.getNome()    Função que retorna o nome do produto
     */
    public String getNome() {
        return produto.getNome();
    }

    /**
     * Chama a função para retornar o preço do produto
     *
     * @return produto.getPreco()    Função que retorna o preco do produto
     */
    public double getPreco() {
        return produto.getPreco();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Retorna o produto
     *
     * @return produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * Retorna a data de validade
     *
     * @return dataValidade
     */
    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public LocalDate getDataProducao() {
        return dataProducao;
    }

    /**
     * Retorna a data de validade
     *
     * @return dataValidade
     */
    public double getPrecoComDesconto(boolean isCliente) {
        double precoBase = produto.getPreco();
        double descontoVencimento = estaProximoVencimento() ? 0.3 : 0.0; //30% de desconto, valido para todos
        double descontoCliente = isCliente ? 0.2 : 0.0; // 20% de desconto se o cliente for fidelizado

        double descontoTotal = descontoVencimento + descontoCliente;
        descontoTotal = Math.min(descontoTotal, 1.0);

        return precoBase * (1 - descontoTotal);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String statusVencimento = estaProximoVencimento() ? " [PRÓXIMO AO VENCIMENTO]" : "";
        return produto.toString() + " | Produção: " + dataProducao.format(dtf) + " | Validade: " + dataValidade.format(dtf) + statusVencimento;
    }

    public String resumo() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String statusVencimento = estaProximoVencimento() ? " [PRÓXIMO AO VENCIMENTO]" : "";
        return produto.resumo() + " | Quantidade: " + quantidade + " | Produção: " + dataProducao.format(dtf) + " | Validade: " + dataValidade.format(dtf) + statusVencimento;
    }
}
