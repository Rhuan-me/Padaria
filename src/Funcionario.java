public class Funcionario {

    public enum Cargos {
        PADEIRO, ATENDENTE, CAIXA, GERENTE
    }

    private String nome;
    private String cpf;
    private String telefone;
    private Cargos cargo;

    public Funcionario(String nome, String cpf, String telefone, Cargos cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cargo = cargo;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public Cargos getCargo() {
        return cargo;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCargo(Cargos cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return String.format(
                "Funcionário: %s\nCPF: %s\nTelefone: %\nCargo: %s"
                , nome, cpf, telefone, cargo
        );
    }
}







