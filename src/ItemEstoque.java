import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

class ItemEstoque {
    private Produto produto;
    private LocalDate dataProducao;
    private LocalDate dataValidade;

    ItemEstoque(Produto produto, LocalDate dataProducao, LocalDate dataValidade) {
        this.produto = produto;
        this.dataProducao = dataProducao;
        this.dataValidade = dataValidade;
    }

    public boolean estaProximoVencimento() {
        return java.time.Period.between(LocalDate.now(), dataValidade).getDays() <= 2;
    }

    public Produto getProduto() {
        return produto;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public double getPrecoComDesconto(boolean isCliente) {
        double precoBase = produto.getPreco();
        double desconto = 0.0;

        if (estaProximoVencimento()) {
            desconto += 0.3; // 30% de desconto
        }
        if (isCliente) {
            desconto += 0.2; // 20% extra para cliente
        }

        desconto = Math.min(desconto, 1.0);

        return precoBase * (1 - desconto);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String statusVencimento = estaProximoVencimento() ? " [PRÓXIMO AO VENCIMENTO]" : "";
        return produto.toString() + " | Produção: " + dataProducao.format(dtf) + " | Validade: " + dataValidade.format(dtf) + statusVencimento;
    }
}
