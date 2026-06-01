package projeto_pooii_2bim;

import java.util.Scanner;

public class Hotel {

    private Quarto[] quartos = new Quarto[100];
    private ProdutoFrigobar[] produtos = new ProdutoFrigobar[20];
    private Scanner scanner = new Scanner(System.in);

    public Hotel() {
        // Inicializar quartos
        for (int i = 0; i < 100; i++) {
            quartos[i] = new Quarto(i + 1);
        }

        // Inicializar produtos
        produtos[0] = new ProdutoFrigobar("Água", 3.00, 10);
        produtos[1] = new ProdutoFrigobar("Refrigerante", 8.00, 10);
        produtos[2] = new ProdutoFrigobar("Cerveja", 10.00, 10);
        produtos[3] = new ProdutoFrigobar("Chocolate", 6.00, 5);
        produtos[4] = new ProdutoFrigobar("Salgadinho", 7.00, 5);
    }

    public void reservarQuarto() {
        System.out.print("Número do quarto (1-100): ");
        int num = scanner.nextInt() - 1;
        scanner.nextLine();

        if (num < 0 || num >= 100) {
            System.out.println("Quarto inválido!");
            return;
        }

        if (quartos[num].isReservado()) {
            System.out.println("Quarto já reservado!");
            return;
        }

        System.out.print("Nome do hóspede: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Hospede hospede = new Hospede(nome, email, telefone);
        quartos[num].reservarQuarto(hospede);
        System.out.println("Reserva realizada! Hóspede precisa fazer check-in para consumir.");
    }

    public void fazerCheckin() {
        System.out.print("Número do quarto para check-in: ");
        int num = scanner.nextInt() - 1;

        if (num < 0 || num >= 100) {
            System.out.println("Quarto inválido!");
            return;
        }

        quartos[num].fazerCheckin();
    }

    public void fazerCheckout() {
        System.out.print("Numero do quarto para check-out: ");
        int num = scanner.nextInt() - 1;

        if (num < 0 || num >= 100) {
            System.out.println("Quarto invalido!");
            return;
        }

        if (quartos[num].isCheckinRealizado()) {
            System.out.println("\n--- CONSUMO DO FRIGOBAR ---");
            quartos[num].exibirConsumosDetalhados();  // Mostra detalhado
            quartos[num].fazerCheckout();
        } else {
            System.out.println("Check-in nao foi realizado ainda.");
            System.out.println("Total consumido no frigobar: R$ 0.0");
        }
    }

    public void cancelarReserva() {
        System.out.print("Número do quarto: ");
        int num = scanner.nextInt() - 1;

        if (num < 0 || num >= 100) {
            System.out.println("Quarto inválido!");
            return;
        }

        quartos[num].cancelarReserva();
    }

    public void listarReservas() {
        System.out.println("\n=== RESERVAS ===");
        for (Quarto q : quartos) {
            if (q.isReservado()) {
                q.exibirResumoComDetalhes();  // Mostra detalhado
                System.out.println();
            }
        }
    }

    public void listarOcupados() {
        System.out.println("\n=== QUARTOS OCUPADOS (check-in realizado) ===");
        for (Quarto q : quartos) {
            if (q.isCheckinRealizado()) {
                q.exibirResumo();
            }
        }
    }

    public void consultarHospede() {
        System.out.print("Número do quarto: ");
        int num = scanner.nextInt() - 1;

        if (num < 0 || num >= 100) {
            System.out.println("Quarto inválido!");
            return;
        }

        quartos[num].consultarHospede();
    }

    public void editarHospede() {
        System.out.print("Número do quarto: ");
        int num = scanner.nextInt() - 1;
        scanner.nextLine();

        if (num < 0 || num >= 100) {
            System.out.println("Quarto inválido!");
            return;
        }

        if (!quartos[num].isReservado() || quartos[num].getHospede() == null) {
            System.out.println("Quarto não está reservado!");
            return;
        }

        Hospede h = quartos[num].getHospede();
        System.out.print("Novo nome (" + h.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) {
            h.setNome(nome);
        }

        System.out.print("Novo email (" + h.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            h.setEmail(email);
        }

        System.out.print("Novo telefone (" + h.getTelefone() + "): ");
        String telefone = scanner.nextLine();
        if (!telefone.isEmpty()) {
            h.setTelefone(telefone);
        }

        System.out.println("Dados atualizados!");
    }

    public void listarProdutosFrigobar() {
        System.out.println("\n=== PRODUTOS DO FRIGOBAR ===");
        for (int i = 0; i < produtos.length; i++) {
            if (produtos[i] != null) {
                System.out.print(i + " - ");
                produtos[i].exibirProduto();
            }
        }
    }

    public void registrarConsumoFrigobar() {
        System.out.print("Número do quarto: ");
        int num = scanner.nextInt() - 1;

        if (num < 0 || num >= 100) {
            System.out.println("Quarto inválido!");
            return;
        }

        // Verifica se o hóspede está dentro do quarto (check-in realizado)
        if (!quartos[num].podeConsumir()) {
            System.out.println("❌ Consumo negado! Hóspede não está no quarto. Faça o check-in primeiro.");
            return;
        }

        listarProdutosFrigobar();
        System.out.print("Escolha o produto (0-4): ");
        int prodIndex = scanner.nextInt();
        System.out.print("Quantidade consumida: ");
        int qtd = scanner.nextInt();

        if (prodIndex < 0 || prodIndex >= produtos.length || produtos[prodIndex] == null) {
            System.out.println("Produto inválido!");
            return;
        }

        ProdutoFrigobar produto = produtos[prodIndex];

        if (produto.getQuantidade() < qtd) {
            System.out.println("Estoque insuficiente! Disponível: " + produto.getQuantidade());
            return;
        }

        ConsumoFrigobar consumo = new ConsumoFrigobar(quartos[num], produto, qtd);
        consumo.registrarConsumo();

        // Atualiza estoque
        produto.setQuantidade(produto.getQuantidade() - qtd);

        System.out.println("✅ Consumo registrado! Total: R$ " + consumo.calcularValorConsumo());
    }

    public void calcularTotalQuarto() {
        System.out.print("Número do quarto: ");
        int num = scanner.nextInt() - 1;

        if (num < 0 || num >= 100) {
            System.out.println("Quarto inválido!");
            return;
        }

        System.out.println("Valor consumido no frigobar do quarto " + (num + 1) + ": R$ " + quartos[num].getValorConsumido());
    }

    public void menu() {
        int opcao;
        do {
            System.out.println("\n======================================");
            System.out.println("        SISTEMA DE HOTEL              ");
            System.out.println("======================================");
            System.out.println("1  - Reservar quarto                  ");
            System.out.println("2  - Fazer check-in                   ");
            System.out.println("3  - Fazer check-out                  ");
            System.out.println("4  - Cancelar reserva                 ");
            System.out.println("5  - Listar reservas                  ");
            System.out.println("6  - Listar quartos ocupados          ");
            System.out.println("7  - Consultar hospede                ");
            System.out.println("8  - Editar dados do hospede          ");
            System.out.println("9  - Listar produtos frigobar         ");
            System.out.println("10 - Registrar consumo frigobar       ");
            System.out.println("11 - Calcular total do quarto         ");
            System.out.println("0  - Sair                             ");
            System.out.println("======================================");
            System.out.print("Opcao: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    reservarQuarto();
                    break;
                case 2:
                    fazerCheckin();
                    break;
                case 3:
                    fazerCheckout();
                    break;
                case 4:
                    cancelarReserva();
                    break;
                case 5:
                    listarReservas();
                    break;
                case 6:
                    listarOcupados();
                    break;
                case 7:
                    consultarHospede();
                    break;
                case 8:
                    editarHospede();
                    break;
                case 9:
                    listarProdutosFrigobar();
                    break;
                case 10:
                    registrarConsumoFrigobar();
                    break;
                case 11:
                    calcularTotalQuarto();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);
    }

    // MAIN
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        hotel.menu();
    }
}
