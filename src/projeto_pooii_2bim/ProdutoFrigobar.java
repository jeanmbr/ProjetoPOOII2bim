package projeto_pooii_2bim;

public class ProdutoFrigobar {
    private String nomeProduto;
    private double preco;
    private int quantidade;
    
    // Construtores
    public ProdutoFrigobar() {}
    
    public ProdutoFrigobar(String nomeProduto, double preco, int quantidade) {
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    
    // Sobrecarga
    public ProdutoFrigobar(String nomeProduto, double preco) {
        this(nomeProduto, preco, 0);
    }
    
    // Getters e Setters
    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    
    public double calcularTotal() {
        return preco * quantidade;
    }
    
    public void exibirProduto() {
        System.out.println(nomeProduto + " - R$ " + preco + " - Estoque: " + quantidade);
    }
}