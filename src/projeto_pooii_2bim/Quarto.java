package projeto_pooii_2bim;

public class Quarto {
    private int numero;
    private boolean reservado;
    private boolean checkinRealizado;
    private Hospede hospede;
    private double valorConsumido;
    private ConsumoFrigobar[] consumos;  // Historico de consumos
    private int totalConsumos;            // Quantos consumos registrados
    
    public Quarto() {}
    
    public Quarto(int numero) {
        this.numero = numero;
        this.reservado = false;
        this.checkinRealizado = false;
        this.hospede = null;
        this.valorConsumido = 0.0;
        this.consumos = new ConsumoFrigobar[50];  // Maximo 50 consumos
        this.totalConsumos = 0;
    }
    
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    
    public boolean isReservado() { return reservado; }
    public void setReservado(boolean reservado) { this.reservado = reservado; }
    
    public boolean isCheckinRealizado() { return checkinRealizado; }
    public void setCheckinRealizado(boolean checkinRealizado) { this.checkinRealizado = checkinRealizado; }
    
    public Hospede getHospede() { return hospede; }
    public void setHospede(Hospede hospede) { this.hospede = hospede; }
    
    public double getValorConsumido() { return valorConsumido; }
    public void setValorConsumido(double valorConsumido) { this.valorConsumido = valorConsumido; }
    
    public void reservarQuarto(Hospede h) {
        this.hospede = h;
        this.reservado = true;
        this.checkinRealizado = false;
    }
    
    public void fazerCheckin() {
        if (reservado && !checkinRealizado) {
            this.checkinRealizado = true;
            System.out.println("Check-in realizado! Hospede " + hospede.getNome() + " esta no quarto " + numero);
        } else if (!reservado) {
            System.out.println("Quarto nao esta reservado.");
        } else if (checkinRealizado) {
            System.out.println("Check-in ja foi realizado.");
        }
    }
    
    public void fazerCheckout() {
        if (checkinRealizado) {
            this.checkinRealizado = false;
            this.reservado = false;
            System.out.println("Check-out realizado. Quarto " + numero + " liberado.");
        } else {
            System.out.println("Check-in nao foi realizado ainda.");
        }
    }
    
    public void cancelarReserva() {
        if (!checkinRealizado) {
            this.hospede = null;
            this.reservado = false;
            System.out.println("Reserva do quarto " + numero + " cancelada.");
        } else {
            System.out.println("Nao e possivel cancelar. Hospede ja fez check-in.");
        }
    }
    
    public boolean podeConsumir() {
        return checkinRealizado;
    }
    
    public void adicionarConsumo(double valor) {
        if (podeConsumir()) {
            this.valorConsumido += valor;
        } else {
            System.out.println("Consumo negado! Hospede nao esta no quarto.");
        }
    }
    
    // Adiciona item consumido ao historico
    public void adicionarItemConsumido(ConsumoFrigobar consumo) {
        if (totalConsumos < consumos.length) {
            consumos[totalConsumos] = consumo;
            totalConsumos++;
        }
    }
    
    // Exibe todos os consumos detalhados
    public void exibirConsumosDetalhados() {
        if (totalConsumos == 0) {
            System.out.println("  Nenhum consumo registrado.");
        } else {
            System.out.println("  --- ITENS CONSUMIDOS ---");
            for (int i = 0; i < totalConsumos; i++) {
                consumos[i].exibirConsumo();
            }
            System.out.println("  ------------------------");
            System.out.println("  TOTAL CONSUMIDO: R$ " + valorConsumido);
        }
    }
    
    public void consultarHospede() {
        if (reservado && hospede != null) {
            System.out.println("Quarto " + numero);
            System.out.println("Reservado: " + (reservado ? "Sim" : "Nao"));
            System.out.println("Check-in: " + (checkinRealizado ? "Sim" : "Nao"));
            hospede.exibirDados();
        } else {
            System.out.println("Quarto " + numero + " esta vazio.");
        }
    }
    
    public void exibirResumo() {
        System.out.println("Quarto " + numero + 
                           " | Reservado: " + (reservado ? "Sim" : "Nao") +
                           " | Check-in: " + (checkinRealizado ? "Sim" : "Nao") +
                           " | Consumo: R$ " + valorConsumido);
        if (reservado && hospede != null) {
            System.out.println("   Hospede: " + hospede.getNome());
        }
    }
    
    public void exibirResumoComDetalhes() {
        exibirResumo();
        exibirConsumosDetalhados();
    }
}
