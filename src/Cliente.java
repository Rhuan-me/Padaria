import java.util.ArrayList;

public class Cliente {
    private static int proximoCodigo = 1;
    private int codigo;
    private String nome;
    private String cpf;
    private String telefone;
    ArrayList<Pedido> pedidos;

    public Cliente(String nome, String cpf, String telefone) {
        this.codigo = proximoCodigo++;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void adicionarPedido(Pedido p) {
        pedidos.add(p);
    }

    @Override
    public String toString() {
        return "Cód: " + codigo + " | Nome: " + nome + " | CPF: " + cpf + " | Telefone: " + telefone;
    }
}