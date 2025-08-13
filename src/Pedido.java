import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe que representa um pedido realizado por um cliente.
 * Armazena a lista de itens, o cliente associado e o valor total.
 */
public class Pedido {
    private Cliente cliente;
    private ArrayList<ItemEstoque> itens;
    private double total;
    private LocalDate dataPedido;

    private Funcionario funcionario;

    /**
     * Construtor do Pedido.
     * <p>
     * Inicializa a lista de itens e o valor total como zero.
     */
    public Pedido() {
        this.itens = new ArrayList<>();
        this.total = 0;
        this.dataPedido = LocalDate.now();
    }

    /**
     * Adiciona um item ao pedido.
     *
     * @param item ItemEstoque que será adicionado
     */
    public void adicionarItem(ItemEstoque item) {
        this.itens.add(item);
    }

    /**
     * Define o cliente associado ao pedido.
     *
     * @param cliente Cliente do pedido
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Finaliza o pedido, imprimindo os itens e o valor total.
     * Aplica desconto se houver cliente de fidelidade.
     */
    public void finalizar() {
        System.out.println("===== Bug's Bakery =====");
        System.out.println("Pedido emitido em: " + dataPedido);
        System.out.println("---------------------------------------------");

        if (itens.isEmpty()) {
            System.out.println("O pedido está vazio!");
            return;
        }
        imprimirItens();
        calcularTotal();
        System.out.printf("Valor total: R$ %.2f\n", this.total);

        if (funcionario != null) {
            System.out.println("Atendimento feito por: " + funcionario.getNome());
        }
        System.out.println("Pedido finalizado com sucesso!");
        System.out.println("Volte sempre " + cliente.getNome() + "!");
        System.out.println("---------------------------------------------");
    }

    /**
     * Calcula o valor total do pedido.
     * Se o cliente estiver associado, aplica o desconto nos itens.
     */
    public void calcularTotal() {
        total = 0;
        boolean isCliente = (this.cliente != null);
        for (ItemEstoque item : itens) {
            total += item.getPrecoComDesconto(isCliente);
        }
    }

    /**
     * Imprime os nomes dos itens do pedido.
     */
    public void imprimirItens() {
        for (ItemEstoque item : itens) {
            System.out.println(item.getNome() + "   |   R$" + item.getPreco());
        }
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public double getTotal() {
        return total;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }


}
