# Sistema de Gerenciamento de Padaria

Este é um projeto de um sistema de gerenciamento para uma padaria, desenvolvido em Java. O sistema permite gerenciar clientes, produtos, estoque e realizar pedidos através de uma interface de linha de comando (CLI).

## Funcionalidades

O sistema oferece um menu interativo com as seguintes funcionalidades:

*   **Gerenciamento de Clientes:**
    *   Adicionar novos clientes.
    *   Remover clientes existentes pelo CPF.
    *   Listar todos os clientes cadastrados.

*   **Gerenciamento de Produtos:**
    *   Adicionar novos tipos de produtos (ex: "Bolo de Chocolate").
    *   Remover produtos pelo código.
    *   Listar todos os produtos com seus preços.
    *   Editar o preço de um produto.

*   **Gerenciamento de Estoque:**
    *   Adicionar itens ao estoque, associando um produto a uma data de validade.
    *   Remover itens específicos do estoque.
    *   Listar todos os itens em estoque, com indicação de produtos próximos ao vencimento.
    *   Listar itens ordenados pela data de validade.

*   **Realização de Pedidos:**
    *   Criar um novo pedido selecionando itens disponíveis no estoque.
    *   Associar um cliente (com ou sem cadastro de fidelidade) ao pedido.
    *   Aplicar descontos automaticamente:
        *   **50%** para clientes cadastrados.
        *   **30%** para produtos próximos ao vencimento (2 dias ou menos).
    *   Associar o funcionário que realizou o atendimento.
    *   Finalizar o pedido e exibir um recibo.

*   **Relatórios:**
    *   Exibir o total de vendas realizadas no dia.

## Tecnologias Utilizadas

*   **Java 11+**

## Como Executar o Projeto

1.  **Pré-requisitos:**
    *   Certifique-se de ter o **Java Development Kit (JDK)** instalado em sua máquina.

2.  **Clone o repositório (se aplicável) ou tenha os arquivos em um diretório.**

3.  **Navegue até o diretório `src` do projeto pelo seu terminal:**
    ```bash
    cd caminho/para/o/projeto/Padaria/src
    ```

4.  **Compile todos os arquivos `.java`:**
    ```bash
    javac *.java
    ```

5.  **Execute a classe principal `Main`:**
    ```bash
    java Main
    ```

Após a execução, o menu principal do sistema será exibido no console, e você poderá interagir com as opções disponíveis.

## 📂 Estrutura do Projeto

O projeto está organizado nas seguintes classes dentro do pacote `src`:

*   `Main.java`: Classe principal que contém o menu interativo (CLI), a lógica de navegação e orquestra as operações do sistema.
*   `Cliente.java`: Modela um cliente da padaria, com informações como nome, CPF e telefone.
*   `Produto.java`: Modela um tipo de produto que a padaria vende (e.g., "Bolo de Chocolate", "Café").
*   `ItemEstoque.java`: Representa uma instância específica de um `Produto` no estoque, com datas de produção e validade.
*   `Estoque.java`: Gerencia a coleção de `ItemEstoque`, controlando a entrada e saída de itens.
*   `Pedido.java`: Modela um pedido feito por um cliente, contendo uma lista de itens, cliente associado e valor total.
*   `Funcionario.java`: Modela um funcionário da padaria, com nome, cargo e outros dados.

---

*Este projeto foi criado durante o curso de lógica de programação em Java. Sendo os integrantes deste projeto:*

*   *   **Rhuan**
*   *   **Fernanda**
*   *   **Brena**
