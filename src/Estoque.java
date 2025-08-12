import java.time.LocalDate;
import java.util.ArrayList;

public class Estoque {
    ArrayList<ItemEstoque> itens;

    public Estoque() {
        this.itens = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto, LocalDate dataProducao, LocalDate dataValidade) {
        ItemEstoque item = new ItemEstoque(produto, dataProducao, dataValidade);
        itens.add(item);
    }

    public void removerProduto(ItemEstoque item) {
        itens.remove(item);
    }

    public void removerProdutoPeloIndice(int index) {
        if (index >= 0 && index < itens.size()) {
            itens.remove(index);
            System.out.println("Item removido com sucesso.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public void listarProdutos() {
        if (itens.isEmpty()) {
            System.out.println("O estoque está vazio.");
            return;
        }
        for (ItemEstoque item : itens) {
            System.out.println(item);
        }
    }

    public void listarProdutosComIndice() {
        if (itens.isEmpty()) {
            System.out.println("O estoque está vazio.");
            return;
        }
        System.out.println("\n---- ITENS NO ESTOQUE ----");
        for (int i = 0; i < itens.size(); i++) {
            System.out.println((i + 1) + ": " + itens.get(i));
        }
    }
}
