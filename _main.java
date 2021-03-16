import java.util.Scanner;
import java.util.ArrayList;

public class _main{

    public static void main(String[] args){

        ArrayList<empregado> lista_empregados = new ArrayList<empregado>();
        Scanner inp = new Scanner(System.in);
        
        while(true){    
            
            System.out.println("\n*******DIGITE O CODIGO DA FUNCAO DESEJADA:\n\nCODIGO |    FUNCAO ");
            System.out.println("   1   |  ADICAO DE EMPREGADO\n   2   |  REMOCAO DE EMPREGADO\n   3   |  LANCAR UM CARTAO DE PONTO\n   4   |  LANCAR UM RESULTADO VENDA\n   5   |  LANCAR UMA TAXA DE SERVICO\n   6   |  ALTERAR DETALHES DE UM EMPREGADO\n   7   |  REALIZAR FOLHA DE PAGAMENTO PARA HOJE\n   8   |  UNDO/REDO\n   9   |  AGENDA DE PAGAMENTO\n   10  |  CRIACAO DE NOVA AGENDA DE PAGAMENTO\n");
            System.out.print("DIGITE: ");
            int funcao = inp.nextInt();

            //processar escolha
            switch (funcao) {
                case 1:
                    adicionar_empregado(lista_empregados);
                    break;
                case 2:
                    listar(lista_empregados, inp);           
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    
                    break;
                case 6:
                    
                    break;
                case 7:
                    
                    break;
                case 8:
                    
                    break;
                case 9:
                    
                    break;
                case 10:
                    
                    break;
                default:
                    System.out.println("opcao invalida");
                    break;
             }   
        }
    }

    public static void adicionar_empregado(ArrayList<empregado> lista_de_empregados){

        Scanner input = new Scanner(System.in);
        System.out.print("Digite o nome do empregado: ");
        String nome = input.nextLine();
        System.out.print("Digite o endereco do empregado: ");
        String endereco = input.nextLine();
        System.out.print("Digite o tipo de empregado: ");
        String tipo_empregado = input.nextLine();
        System.out.print("Digite o salario horario: ");
        double s_h = input.nextDouble();
        System.out.print("Digite o salario mensal: ");
        double s_m = input.nextDouble();
        System.out.print("Digite a comissao do empregado: ");
        double comissao = input.nextDouble();            
        input.nextLine();
        empregado new_employee = new empregado(nome,endereco, tipo_empregado, s_h, s_m, comissao);
        lista_de_empregados.add(new_employee);

        System.out.printf("%s ADICIONADO COM SUCESSO\nDESEJA ADICIONAR OUTRO EMPREGADO S/N: ", nome);
        String op = input.nextLine();
        if(op.equals("S")|| op.equals("s")){
            adicionar_empregado(lista_de_empregados);
        }
    }
    public static void listar(ArrayList<empregado> lista_de_empregados, Scanner inp){
        if(lista_de_empregados.size() <= 0){
            System.out.println("NAO HA EMPREGADOS CADASTRADOS!");
        }else{
             for(empregado emp : lista_de_empregados){
                System.out.printf("%s\n", emp.getNome());
            }
        }
        System.out.println("TECLE ENTER PARA CONTINUAR");
        inp.nextLine();
    }
        
}