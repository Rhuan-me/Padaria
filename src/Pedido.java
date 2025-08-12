import java.util.ArrayList;

public class Pedido {
    private static int proximoNumero = 1;
    private int numero;
    private Cliente cliente;
    private ArrayList<ItemEstoque> itens;
    private double total;

    public Pedido(Cliente cliente) {
        this.numero = proximoNumero++;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.total = 0;
    }

    public void adicionarItem(ItemEstoque item) {
        this.itens.add(item);
        calcularTotal();
    }

    public void finalizar(){//finalizar somente se tiver itens no pedido
        System.out.println("Pedido finalizado com sucesso!");
        System.out.println(this.itens);
        System.out.println("Valor total: " + this.total);
    }

    public void calcularTotal() {
        double t = 0;

        boolean isCliente = (this.cliente != null);
        for (ItemEstoque item : itens) {
            t += item.getPrecoComDesconto(isCliente);
        }
        this.total = t;
    }
}
