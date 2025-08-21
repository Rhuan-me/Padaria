import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Classe principal que executa o programa
 * Armazena lista de clientes, produtos e funcionarios
 */
public class Main {

    static Scanner sc = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Produto> produtos = new ArrayList<>();
    static ArrayList<Funcionario> funcionarios = new ArrayList<>();
    static ArrayList<Pedido> pedidos = new ArrayList<>();

    //Inicializar o estoque
    static Estoque estoque = new Estoque();


    public static void main(String[] args) {

        inicializarDados();

        while (true) {
            try {
                System.out.println("\n---- MENU PRINCIPAL ----");
                System.out.println("1 - Gerir Clientes");
                System.out.println("2 - Gerir Produtos");
                System.out.println("3 - Gerir Estoque");
                System.out.println("4 - Fazer Pedido");
                System.out.println("5 - Mostrar total de vendas do dia");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1 -> gerirClientes();
                    case 2 -> gerirProdutos();
                    case 3 -> gerirEstoque();
                    case 4 -> fazerPedido();
                    case 5 -> mostrarTotalVendasDoDia();
                    case 0 -> {
                        System.out.println("Saindo do sistema...");
                        return;
                    }
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                sc.nextLine();
            }
        }
    }

    /**
     * Inicializa listas e isntâncias com dados de exemplo para testes.
     */
    public static void inicializarDados() {
        clientes.add(new Cliente("Carlos Alberto", "12345678912", "47997520297"));
        clientes.add(new Cliente("Ana Julia", "98765432198", "47988776655"));

        Produto p1 = new Produto("Bolo de Chocolate", 25.00);
        Produto p2 = new Produto("Bolo de Laranja", 22.50);
        Produto p3 = new Produto("Cuca de Banana", 30.00);
        Produto p4 = new Produto("Suco de Laranja", 10.00);
        Produto p5 = new Produto("Cafezinho", 5.00);

        produtos.add(p1);
        produtos.add(p2);
        produtos.add(p3);
        produtos.add(p4);
        produtos.add(p5);

        estoque.adicionarItem(p1, LocalDate.now(), LocalDate.now().plusDays(5), 8);
        estoque.adicionarItem(p1, LocalDate.now(), LocalDate.now().plusDays(5), 9);
        estoque.adicionarItem(p2, LocalDate.now(), LocalDate.now().plusDays(2), 1); // Próximo ao vencimento
        estoque.adicionarItem(p3, LocalDate.now(), LocalDate.now().plusDays(1), 2); // Próximo ao vencimento
        estoque.adicionarItem(p4, LocalDate.now(), LocalDate.now().plusDays(3), 3);
        estoque.adicionarItem(p5, LocalDate.now(), LocalDate.now().plusDays(1), 6);

        funcionarios.add(new Funcionario("Fernanda Maria", "019875698732", "47988764689", Funcionario.Cargos.GERENTE));
        funcionarios.add(new Funcionario("Brena Raiara", "21398764590", "47856789098", Funcionario.Cargos.ATENDENTE));
        funcionarios.add(new Funcionario("Rhuan Silva", "21209867509", "47988996654", Funcionario.Cargos.PADEIRO));
        funcionarios.add(new Funcionario("Rannyer", "09835689765", "47988987654", Funcionario.Cargos.CAIXA));

    }

    /**
     * Submenu para gerenciamento de clientes.
     * Permite adicionar, remover e listar clientes.
     */
    public static void gerirClientes() {

        while (true) {
            System.out.println("\n---- GERIR CLIENTES ----");
            System.out.println("1 - Adicionar cliente");
            System.out.println("2 - Remover cliente");
            System.out.println("3 - Listar clientes");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> adicionarCliente();
                case 2 -> removerCliente();
                case 3 -> listarClientes();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    /**
     * Submenu para gerenciamento do estoque.
     * Permite adicionar, remover e listar itens, incluindo ordenação por validade.
     */
    public static void gerirEstoque() {
        while (true) {
            System.out.println("\n---- GERIR ESTOQUE ----");
            System.out.println("1 - Adicionar item ao estoque");
            System.out.println("2 - Remover item do estoque");
            System.out.println("3 - Listar itens do estoque");
            System.out.println("4 - Listar itens do estoque ordenado pela validade");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> adicionarItemEstoque();
                case 2 -> removerItemEstoque();
                case 3 -> estoque.listarItens();
                case 4 -> estoque.listarItensValidade();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida!");

            }
        }
    }

    /**
     * Submenu para gerenciamento de produtos.
     * Permite adicionar, remover, listar, buscar e editar produtos.
     */
    public static void gerirProdutos() {

        while (true) {
            System.out.println("\n---- GERIR PRODUTOS ----");
            System.out.println("1 - Adicionar novo produto");
            System.out.println("2 - Remover produto");
            System.out.println("3 - Listar todos os produtos");
            System.out.println("4 - Buscar produto por código");
            System.out.println("5 - Editar preço do produto");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> adicionarProduto();
                case 2 -> removerProduto();
                case 3 -> listarProdutos();
                case 4 -> menuBuscar();
                case 5 -> editarPrecoProduto();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    /**
     * Permite ao usuário realizar um pedido selecionando itens do estoque.
     * Itens são removidos do estoque após serem adicionados ao pedido.
     * Caso o cliente tenha fidelidade, associa o pedido ao cliente.
     */
    public static void fazerPedido() {
        if (estoque.estaVazio()) {
            System.out.println("O estoque está vazio");
            return;
        }

        Pedido pedido = new Pedido();

        Estoque estoqueTemporario = new Estoque(estoque);

        System.out.println("\n---- REALIZANDO PEDIDO ----");
        while (true) {
            estoqueTemporario.listarItensComIndice();
            System.out.print("Digite o número do item que deseja comprar (ou 0 para sair): ");
            int itemIndex = sc.nextInt();

            if (itemIndex == 0) break;
            sc.nextLine();

            if (itemIndex < 1 || itemIndex > estoqueTemporario.itens.size()) {
                System.out.println("Item não encontrado.");
                continue;
            }

            ItemEstoque itemEstoque = estoqueTemporario.itens.get(itemIndex - 1);


            System.out.print("Digite a quantidade que deseja comprar: ");
            int quantidadeDesejada = sc.nextInt();
            sc.nextLine();

            System.out.println("--------------------------");

            if (quantidadeDesejada <= 0 || quantidadeDesejada > itemEstoque.getQuantidade()) {
                System.out.println("Quantidade indisponível");
                continue;
            }

            ItemEstoque itemPedido = new ItemEstoque(
                    itemEstoque.getProduto(),
                    itemEstoque.getDataProducao(),
                    itemEstoque.getDataValidade(),
                    quantidadeDesejada
            );
            pedido.adicionarItem(itemPedido);

            itemEstoque.setQuantidade(itemEstoque.getQuantidade() - quantidadeDesejada);
            if (itemEstoque.getQuantidade() == 0) {
                estoqueTemporario.removerProduto(itemEstoque);
            }
            System.out.println(quantidadeDesejada + "x " + itemEstoque.getNome() + " - Adicionado ao pedido");

            System.out.println("--------------------------");
            if (estoqueTemporario.estaVazio()) {
                System.out.println("O estoque está vazio.");
                break;
            }

            System.out.print("Deseja adicionar outro item? (s/n) ");
            String continuar = sc.nextLine();
            if (continuar.equalsIgnoreCase("n")) break;

        }

        if (pedido.isEmpty()) {
            System.out.println("Pedido vazio.");
            return;
        }

        System.out.print("O cliente tem fidelidade? (s/n) ");
        String fidelidade = sc.next();
        if (fidelidade.equalsIgnoreCase("s")) {
            System.out.print("Digite o CPF: ");
            String cpf = sc.next();
            boolean exite = false;

            System.out.println("\n--------------------------");
            for (Cliente cliente : clientes) {
                if (cliente.getCpf().equals(cpf)) {
                    pedido.setCliente(cliente);
                    System.out.println("Bem Vindo! " + cliente.getNome());
                    exite = true;
                }
            }
            if (!exite) System.out.println("CPF não cadastrado");
        }
        sc.nextLine();
        System.out.println("--------------------------");
        System.out.println("Selecione o funcionario que fez o atendimento: ");
        for (int i = 0; i < funcionarios.size(); i++) {
            System.out.printf("%d - %s (%s) \n", i + 1, funcionarios.get(i).getNome(), funcionarios.get(i).getCargo());
        }
        System.out.println("--------------------------");
        System.out.print("Digite o número do funcionário: ");
        int funcIndex = sc.nextInt();
        sc.nextLine();
        if (funcIndex > 0 && funcIndex <= funcionarios.size()) {
            pedido.setFuncionario(funcionarios.get(funcIndex - 1));
        } else {
            System.out.println("Funcionário inválido! Pedido sem funcionário associado.");
        }

        pedido.finalizar();

        pedidos.add(pedido);
        estoque = estoqueTemporario;
    }

    /**
     * Adiciona um novo cliente à lista de clientes.
     */
    public static void adicionarCliente() {
        System.out.println("---- ADICIONAR CLIENTE ----");
        System.out.print("Digite o nome do cliente: ");
        String nome = sc.nextLine();
        System.out.print("Digite o CPF do cliente: ");
        String cpf = sc.nextLine();
        System.out.print("Digite o telefone do cliente: ");
        String telefone = sc.nextLine();
        clientes.add(new Cliente(nome, cpf, telefone));
        System.out.println("Cliente adicionado com sucesso!");
    }

    /**
     * Remove um cliente da lista a partir do CPF.
     */
    public static void removerCliente() {
        System.out.println("---- REMOVER CLIENTE ----");

        System.out.print("Digite o CPF do cliente a ser removido: ");
        String cpfParaRemover = sc.nextLine();

        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            if (c.getCpf().equals(cpfParaRemover)) {
                clientes.remove(i);
                System.out.println("Cliente removido com sucesso!");
                return;
            }
        }
        System.out.println("Cliente não encontrado.");
    }

    /**
     * Adiciona item ao estoque usando o código do produto, data de produção e validade
     */
    public static void adicionarItemEstoque() {
        System.out.println("---- ADICIONAR ITEM AO ESTOQUE ----");
        listarProdutos();
        System.out.print("Digite o código do produto para adicionar ao estoque: ");

        int codigo = sc.nextInt();
        sc.nextLine();

        Produto produto = buscarProduto(codigo);
        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        try {
            System.out.print("Digite a data de validade (dd/MM/yyyy): ");
            LocalDate dataValidade = LocalDate.parse(sc.nextLine(), formatter);
            LocalDate dataProducao = LocalDate.now();
            System.out.print("Digite a quantidade do produto para adicionar ao estoque: ");
            int quantidade = sc.nextInt();
            estoque.adicionarItem(produto, dataProducao, dataValidade, quantidade);
            System.out.println("Item adicionado ao estoque com sucesso!");
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido! Use o formato dd/MM/yyyy.");
        }
    }

    /**
     * Remove um item do estoque pelo índice informado pelo usuário.
     */
    public static void removerItemEstoque() {
        System.out.println("---- REMOVER ITEM DO ESTOQUE ----");

        if (estoque.estaVazio()) {
            System.out.println("Estoque vazio, nada para remover.");
            return;
        }
        estoque.listarItensComIndice();
        System.out.print("Digite o número do item a ser removido: ");
        int indice = sc.nextInt();
        sc.nextLine();
        estoque.removerItemPeloIndice(indice - 1);
    }

    /**
     * Adiciona um novo produto à lista de produtos.
     */
    public static void adicionarProduto() {
        System.out.println("---- ADICIONAR NOVO PRODUTO ----");
        System.out.print("Nome do novo produto: ");

        String nome = sc.nextLine();
        System.out.print("Preço do novo produto (ex: 25.50): ");
        double preco = sc.nextDouble();
        sc.nextLine();
        produtos.add(new Produto(nome, preco));
        System.out.println("Produto adicionado com sucesso!");
    }

    /**
     * Remove um produto da lista a partir do código.
     */
    public static void removerProduto() {

        System.out.println("---- REMOVER PRODUTO ----");
        listarProdutos();
        System.out.print("Digite o código do produto a ser removido: ");

        int codRemover = sc.nextInt();
        sc.nextLine();

        boolean removido = produtos.removeIf(p -> p.getCodigo() == codRemover);
        if (removido) {
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }

    }

    /**
     * Menu para busca de produto por código.
     */
    public static void menuBuscar() {

        System.out.println("---- BUSCAR PRODUTO ----");
        System.out.print("Digite o código do produto: ");

        int codBuscar = sc.nextInt();
        sc.nextLine();
        Produto p = buscarProduto(codBuscar);
        if (p != null) {
            System.out.println("Produto encontrado: " + p);
        } else {
            System.out.println("Produto com o código " + codBuscar + " não foi encontrado.");
        }

    }

    /**
     * Permite editar o preço de um produto existente.
     */
    public static void editarPrecoProduto() {
        System.out.println("---- EDITAR PREÇO DO PRODUTO ----");
        System.out.print("Digite o código do produto: ");
        int cod = sc.nextInt();
        Produto p1 = buscarProduto(cod);
        if (p1 == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Digite o novo preço do produto: ");
        double precoNovo = sc.nextDouble();
        sc.nextLine();

        p1.setPreco(precoNovo);
        System.out.println(p1);
    }

    /**
     * Lista os todos os clientes da lista clientes, se a lista
     * for vazia retorna que não há registro de cadastro
     */
    public static void listarClientes() {
        System.out.println("\n---- LISTA DE CLIENTES ----");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    /**
     * Lista os todos os produtos da lista produtos, se a lista
     * for vazia retorna que não há registro de cadastro
     */
    public static void listarProdutos() {
        System.out.println("\n---- LISTA DE PRODUTOS DISPONÍVEIS ----");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }
        System.out.println("---------------------------------------");
    }

    /**
     * Busca um produto a partir do seu código
     *
     * @param codigo Valor inteiro referente ao código do produto
     * @return produto ou null
     */
    public static Produto buscarProduto(int codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null;
    }

    /**
     * Calcula o total de vendas realizadas no dia
     *
     * @return total
     */
    public static double totalVendasDoDia() {
        LocalDate hoje = LocalDate.now();
        double total = 0;

        for (Pedido pedido : pedidos) {
            if (pedido.getDataPedido().equals(hoje)) {
                total += pedido.getTotal();
            }
        }
        return total;
    }

    /**
     * Imprime total de vendas do dia
     */
    public static void mostrarTotalVendasDoDia() {
        double total = totalVendasDoDia();
        System.out.printf("Total de vendas do dia de hoje: R$ %.2f\n", total);
    }

}
