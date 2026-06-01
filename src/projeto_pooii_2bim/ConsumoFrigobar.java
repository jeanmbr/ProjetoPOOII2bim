package projeto_pooii_2bim;

public class ConsumoFrigobar {
    private Quarto quarto;
    private ProdutoFrigobar produto;
    private int quantidadeConsumida;
    
    public ConsumoFrigobar() {}
    
    public ConsumoFrigobar(Quarto quarto, ProdutoFrigobar produto, int quantidadeConsumida) {
        this.quarto = quarto;
        this.produto = produto;
        this.quantidadeConsumida = quantidadeConsumida;
    }
    
    public Quarto getQuarto() { return quarto; }
    public void setQuarto(Quarto quarto) { this.quarto = quarto; }
    
    public ProdutoFrigobar getProduto() { return produto; }
    public void setProduto(ProdutoFrigobar produto) { this.produto = produto; }
    
    public int getQuantidadeConsumida() { return quantidadeConsumida; }
    public void setQuantidadeConsumida(int quantidadeConsumida) { this.quantidadeConsumida = quantidadeConsumida; }
    
    public void registrarConsumo() {
        double total = produto.getPreco() * quantidadeConsumida;
        quarto.adicionarConsumo(total);
        quarto.adicionarItemConsumido(this);  // Adiciona ao historico do quarto
    }
    
    public double calcularValorConsumo() {
        return produto.getPreco() * quantidadeConsumida;
    }
    
    public void exibirConsumo() {
        System.out.println("  Produto: " + produto.getNomeProduto() + 
                           " | Qtd: " + quantidadeConsumida +
                           " | Preco unit: R$ " + produto.getPreco() +
                           " | Subtotal: R$ " + calcularValorConsumo());
    }
}