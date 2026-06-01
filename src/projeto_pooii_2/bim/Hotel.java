package projeto_pooii_2.bim;
import java.util.Scanner;
import java.util.Random;

public class Hotel {
            
    public static Scanner entradaDados = new Scanner(System.in);
    public static String hospedes[][]= new String[100][3];
    public static boolean statusQuartos[] = new boolean [100];
    public static String consumoFrigobar[][] = new String[100][4];
    
    public static void reservarQuarto()
        {
            System.out.println("Digite o número do quarto: ");
            int numeroQuarto = entradaDados.nextInt();
            if (!statusQuartos[numeroQuarto-1]){
                System.out.println("Digite o nome do hóspede: ");
                String nome = entradaDados.next();
                //hospedar o hospede
            } else {
                System.out.println("Quarto não está disponível");
            }
        }
    
    public static void main(String[] args) {
        Random random = new Random();
        Boolean rodar = true;
        
        do{
        
            System.out.println
            (
                "[1] Reservar quarto\n" +
                "[2] Cancelar reserva\n" +
                "[3] Listar reserva\n" +
                "[4] Consultar hóspede\n" +
                "[5] Editar hóspede\n" +
                "[6] Registrar consumo do frigobar\n" +
                "[7] Check-out\n" +
                "[8] Finalizar programa" 
            );
            
            String operacao = entradaDados.next();
            
            switch (operacao) {
                case "1":
                    reservarQuarto();
            }
            
        }while (rodar == true);
    }
    
}
