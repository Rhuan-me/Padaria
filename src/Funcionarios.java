public class Funcionarios {
    private String nome;
    private String cpf;
    private String telefone;
    private String cargo;

    public Funcionarios(String nome, String cpf, String telefone, String cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cargo = cargo;
    }

    // Getters
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public String getCargo() { return cargo; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    @Override
    public String toString() {
        return "Funcionário: " + nome + "\nCPF: " + cpf + "\nTelefone: " + telefone + "\nCargo: " + cargo;
    }
}







