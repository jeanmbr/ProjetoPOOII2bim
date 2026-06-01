package projeto_pooii_2bim;

public class Hospede {
    private String nome;
    private String email;
    private String telefone;
    
    // Construtor vazio
    public Hospede() {}
    
    // Construtor com parâmetros
    public Hospede(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }
    
    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    // Método para exibir dados
    public void exibirDados() {
        System.out.println("Nome: " + nome + " | Email: " + email + " | Telefone: " + telefone);
    }
}