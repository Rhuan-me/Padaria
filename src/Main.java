import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.InputMismatchException;

// Classe Principal que executa o programa
public class Main {

    static Scanner sc = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Listas estáticas para armazenar os dados em memória
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Produto> produtos = new ArrayList<>();

    //Inicializar o estoque
    static Estoque estoque = new Estoque();

    public static void main(String[] args) {
        // Inicialização com alguns dados de exemplo
        inicializarDados();

        // Loop principal do menu
        while (true) {
            try {
                System.out.println("\n---- MENU PRINCIPAL ----");
                System.out.println("1 - Gerir Clientes");
                System.out.println("2 - Gerir Estoque");
                System.out.println("3 - Gerir Produtos");
                System.out.println("4 - Fazer Pedido");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = sc.nextInt();
                sc.nextLine(); // Consome a quebra de linha

                switch (opcao) {
                    case 1 -> gerirClientes();
                    case 2 -> gerirEstoque();
                    case 3 -> gerirProdutos();
                    //case 4 -> fazerPedido();
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

    // Metodo para popular o sistema com dados iniciais
    public static void inicializarDados() {
        clientes.add(new Cliente("Carlos Alberto", "12345678912", "47997520297"));
        clientes.add(new Cliente("Ana Julia", "98765432198", "47988776655"));

        Produto p1 = new Produto("Bolo de Chocolate", 25.00);
        Produto p2 = new Produto("Bolo de Laranja", 22.50);
        Produto p3 = new Produto("Cuca de Banana", 30.00);

        produtos.add(p1);
        produtos.add(p2);
        produtos.add(p3);

        estoque.adicionarProduto(p1, LocalDate.now(), LocalDate.now().plusDays(5));
        estoque.adicionarProduto(p2, LocalDate.now(), LocalDate.now().plusDays(2)); // Próximo ao vencimento
    }

    // Gerencia o submenu de Clientes
    public static void gerirClientes() {
        System.out.println("\n---- GERIR CLIENTES ----");
        System.out.println("1 - Adicionar cliente");
        System.out.println("2 - Remover cliente");
        System.out.println("3 - Listar clientes");
        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine(); // Consome a quebra de linha

        switch (opcao) {
            case 1:
                System.out.println("---- ADICIONAR CLIENTE ----");
                System.out.print("Digite o nome do cliente: ");
                String nome = sc.nextLine();
                System.out.print("Digite o CPF do cliente: ");
                String cpf = sc.nextLine();
                System.out.print("Digite o telefone do cliente: ");
                String telefone = sc.nextLine();
                clientes.add(new Cliente(nome, cpf, telefone));
                System.out.println("Cliente adicionado com sucesso!");
                break;
            case 2:
                System.out.println("---- REMOVER CLIENTE ----");
                System.out.print("Digite o CPF do cliente a ser removido: ");
                String cpfParaRemover = sc.nextLine();

                Iterator<Cliente> it = clientes.iterator();
                boolean removido = false;
                while (it.hasNext()) {
                    Cliente cliente = it.next();
                    if (cliente.getCpf().equals(cpfParaRemover)) {
                        it.remove();
                        removido = true;
                        System.out.println("Cliente removido com sucesso!");
                        break;
                    }
                }
                if (!removido) {
                    System.out.println("Cliente não encontrado.");
                }
                break;
            case 3:
                listarClientes();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    // Gerencia o submenu de Estoque
    public static void gerirEstoque() {
        System.out.println("\n---- GERIR ESTOQUE ----");
        System.out.println("1 - Adicionar item ao estoque");
        System.out.println("2 - Remover item do estoque");
        System.out.println("3 - Listar itens do estoque");
        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
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
                    estoque.adicionarProduto(produto, dataProducao, dataValidade);
                    System.out.println("Item adicionado ao estoque com sucesso!");
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de data inválido! Use o formato dd/MM/yyyy.");
                }
                break;
            case 2:
                System.out.println("---- REMOVER ITEM DO ESTOQUE ----");
                if (estoque.itens.isEmpty()) {
                    System.out.println("Estoque vazio, nada para remover.");
                    return;
                }
                estoque.listarProdutosComIndice();
                System.out.print("Digite o número do item a ser removido: ");
                int indice = sc.nextInt();
                sc.nextLine();
                estoque.removerProdutoPeloIndice(indice - 1);
                break;
            case 3:
                System.out.println("---- ITENS NO ESTOQUE ----");
                estoque.listarProdutos();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    // Gerencia o submenu de Produtos
    public static void gerirProdutos() {
        System.out.println("\n---- GERIR PRODUTOS ----");
        System.out.println("1 - Adicionar novo produto");
        System.out.println("2 - Remover produto");
        System.out.println("3 - Listar todos os produtos");
        System.out.println("4 - Buscar produto por código");
        System.out.println("5 - Editar preço do produto");
        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("---- ADICIONAR NOVO PRODUTO ----");
                System.out.print("Nome do novo produto: ");
                String nome = sc.nextLine();
                System.out.print("Preço do novo produto (ex: 25.50): ");
                double preco = sc.nextDouble();
                sc.nextLine();
                produtos.add(new Produto(nome, preco));
                System.out.println("Produto adicionado com sucesso!");
                break;
            case 2:
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
                break;
            case 3:
                listarProdutos();
                break;
            case 4:
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
                break;
            case 5:
                System.out.println("---- EDITAR PREÇO DO PRODUTO ----");
                System.out.println("Digite o código do produto: ");
                int cod = sc.nextInt();
                System.out.println("Digite o novo preço do produto: ");
                double precoNovo = sc.nextDouble();
                sc.nextLine();
                Produto p1 = buscarProduto(cod);
                p1.setPreco(precoNovo);
                System.out.println(p1);
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

//    public static void fazerPedido(){
//        System.out.println("\n---- REALIZANDO PEDIDO ----");
//
//        while (true) {
//            System.out.println("Digite o código do item que deseja comprar: ");
//            estoque.listarProdutos();
//            int cod = sc.nextInt();
//            System.out.println("Deseja finalizar o pedido? (S/N)");
//            if (sc.nextLine().toLowerCase().equals("s")) {}
//        }
//
//
//        System.out.println("É cliente? (s/n)");
//        if (sc.next().equalsIgnoreCase("s")) {
//            System.out.print("Digite o CPF: ");
//            String cpf = sc.nextLine();
//            for (Cliente cliente : clientes){
//                if (cliente.getCpf().equals(cpf)) {Cliente c = cliente;}
//            }
//        }
//
//
//    }


    // Métodos utilitários
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

    public static void listarProdutos() {
        System.out.println("\n---- LISTA DE PRODUTOS DISPONÍVEIS ----");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }
    }

    public static Produto buscarProduto(int codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null;
    }
}