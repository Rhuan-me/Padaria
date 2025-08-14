import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Classe que representa o estoque.
 * Armazena e gerencia uma lista de itens
 */
public class Estoque {

    public ArrayList<ItemEstoque> itens;

    /**
     * Construtor do Estoque
     * Inicializa a lista de itens
     */
    public Estoque() {
        this.itens = new ArrayList<>();
    }

    public Estoque(Estoque estoque) {
        this.itens = new ArrayList<>();
        for (ItemEstoque item : estoque.itens) {
            this.itens.add(new ItemEstoque(
                    item.getProduto(),
                    item.getDataProducao(),
                    item.getDataValidade(),
                    item.getQuantidade()
            ));
        }
    }

    /**
     * Adiciona item a lista do estoque
     *
     * @param dataProducao LocalDate - Data da produção do item
     * @param dataValidade LocalDate - Data da validade do item
     */
    public void adicionarItem(Produto produto, LocalDate dataProducao, LocalDate dataValidade, int quantidade) {

        for (ItemEstoque item : itens) {
            if (produto == item.getProduto() && dataProducao.equals(item.getDataProducao()) && dataValidade.equals(item.getDataValidade())) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                return;
            }
        }
        ItemEstoque item = new ItemEstoque(produto, dataProducao, dataValidade, quantidade);
        itens.add(item);
    }

    /**
     * Remove item da lista do estoque
     *
     * @param item ItemEstoque - item que será removido da lista
     */
    public void removerProduto(ItemEstoque item) {
        itens.remove(item);
    }

    /**
     * Remove item da lista do estoque a partir da busca pelo código
     *
     * @param index int - código do item
     */
    public void removerItemPeloIndice(int index) {
        if (index >= 0 && index < itens.size()) {
            itens.remove(index);
            System.out.println("Item removido com sucesso.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    /**
     * Lista todos os itens do estoque, se estiver vazia retorna
     */
    public void listarItens() {
        System.out.println("\n---- ITENS NO ESTOQUE ----");
        if (itens.isEmpty()) {
            System.out.println("O estoque está vazio.");
            return;
        }
        for (ItemEstoque item : itens) {
            System.out.println(item.resumo());
        }
        System.out.println("--------------------------");
    }

    /**
     * Lista todos os itens do estoque com index,
     * se estiver vazia retorna
     */
    public void listarItensComIndice() {
        if (itens.isEmpty()) {
            System.out.println("O estoque está vazio.");
            return;
        }
        System.out.println("\n---- ITENS NO ESTOQUE ----");
        for (int i = 0; i < itens.size(); i++) {
            System.out.println((i + 1) + ": " + itens.get(i).resumo());
        }
        System.out.println("--------------------------");
    }

    public void listarItensValidade() {
        System.out.println("\n---- ITENS NO ESTOQUE ORDENADO POR VALIDADE ----");
        ArrayList<ItemEstoque> ordenados = new ArrayList<>(itens);
        ordenados.sort(Comparator.comparing(ItemEstoque::getDataValidade));
        for (ItemEstoque i : ordenados) {
            System.out.println(i);
        }
        System.out.println("--------------------------");
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }
}
