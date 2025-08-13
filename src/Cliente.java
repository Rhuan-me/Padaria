import java.util.ArrayList;

/**
 * Classe que representa um cliente
 */
public class Cliente {
    private static int proximoCodigo = 1;
    private int codigo;
    private String nome;
    private String cpf;
    private String telefone;
    private ArrayList<Pedido> pedidos = new ArrayList<>();


    /**
     * Construtor do Cliente
     *
     * @param nome     Nome completo do cliente
     * @param cpf      CPF do cliente
     * @param telefone Telefone de contado do cliente
     */
    public Cliente(String nome, String cpf, String telefone) {
        this.codigo = proximoCodigo++;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    /**
     * Retorna CPF do cliente
     *
     * @return cpf String
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Retorna nome do cliente
     *
     * @return nome String
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna telefone do cliente
     *
     * @return telefone String
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do cliente
     *
     * @param telefone String
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Adiciona o pedido a lista de pedidos do cliente
     *
     * @param p Pedido a ser adicionado
     */
    public void adicionarPedido(Pedido p) {
        pedidos.add(p);
    }

    @Override
    public String toString() {
        return "Cód: " + codigo + " | Nome: " + nome + " | CPF: " + cpf + " | Telefone: " + telefone;
    }
}