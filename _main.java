import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;

import Class.*;

public class _main{

     public static void main(String[] args){

        ArrayList<Employee> lista_empregados = new ArrayList<Employee>();
        Scanner inp = new Scanner(System.in);
        
        while(true){    
            
            System.out.println("\n*******DIGITE O CODIGO DA FUNCAO DESEJADA:\n\nCODIGO |    FUNCAO ");
            System.out.println("   1   |  ADICAO DE EMPREGADO\n   2   |  REMOCAO DE EMPREGADO\n   3   |  LANCAR UM CARTAO DE PONTO\n   4   |  LANCAR UM RESULTADO VENDA\n   5   |  LANCAR UMA TAXA DE SERVICO\n   6   |  ALTERAR DADOS DE UM EMPREGADO\n   7   |  REALIZAR FOLHA DE PAGAMENTO PARA HOJE\n   8   |  UNDO/REDO\n   9   |  AGENDA DE PAGAMENTO\n   10  |  CRIACAO DE NOVA AGENDA DE PAGAMENTO\n");
            System.out.print("DIGITE: ");
            int funcao = inp.nextInt();
            String nome;
            inp.nextLine();
            //processar escolha
            switch (funcao) {
                case 1:
                    adicionar_empregado(lista_empregados, inp);
                    break;
                case 2:
                    Remove_employee(lista_empregados, inp);           
                    break;
                case 3:
                    lancar_cartao_ponto(lista_empregados, inp);
                    break;
                case 4:
                    lancar_venda(lista_empregados, inp);
                    break;
                case 5:
                    lancar_taxa_de_servico(lista_empregados, inp);
                    break;
                case 6:
                    System.out.println("QUAL EMPREGADO DESEJA ALTERAR OS DADOS?");
                    System.out.print("DIGITE NOME DO EMPREGADO: ");
                    nome = inp.nextLine();
                    alterar_dados(nome, lista_empregados, inp);

                    break;
                case 7:
                    run_payment_today(lista_empregados, inp);
                    break;
                case 8:
                   listar_cartao_pointo(lista_empregados, inp); 
                    break;
                case 9:

                    System.out.print("DIGITE EMPREGADO PRA VER VENDAS: ");
                    nome = inp.nextLine();
                    Commissioned emp = find_employee_Commissioned(nome, lista_empregados);
                    
                    if(emp != null){
                        emp.listar_vendas();
                    }else{
                        System.out.println("EMPREGADO NAO ENCONTRADO");
                    }              

                    System.out.print("TECLE ENTER PARA CONTINUAR");
                    inp.nextLine();
                    
                    break;
                case 10:
                    listar_services_usados(lista_empregados, inp);
                    break;
                case 11:
                    Next_Payment_day(lista_empregados, inp);
                    break;
                case 12:
                    show_method_payment(lista_empregados, inp);
                    break;
                case 13:
                    listar(lista_empregados, inp);
                    break;
                case 14:
                    change_day_of_payment(lista_empregados, inp);
                    break;
                default:
                    System.out.println("opcao invalida");
                    break;
             }   
        }
    }

    public static Commissioned find_employee_Commissioned(String nome, ArrayList<Employee> lista_de_empregados){
    
        for(Employee employee : lista_de_empregados) {
            if(employee.getNome().equals(nome)){
                if(employee instanceof Commissioned){
                    return (Commissioned)employee;
                }                 
            }
        }
        return null;
    }

    public static Hourly find_employee_hourly(String nome, ArrayList<Employee> lista_de_empregados){
    
        for(Employee employee : lista_de_empregados) {
            if(employee.getNome().equals(nome)){
                if(employee instanceof Hourly){
                    return (Hourly)employee;
                }                 
            }
        }
        return null;
    }

    public static Employee find_any_empleyee(ArrayList<Employee> lista_de_empregados, String nome){

        for(Employee employee : lista_de_empregados) {
            if(employee.getNome().equals(nome)){
                    return employee;
            }
        }
        return null;
    }
    
    
    public static void adicionar_empregado(ArrayList<Employee> lista_de_empregados, Scanner input){
    
        System.out.print("Digite o nome do empregado: ");
        String nome = input.nextLine();
        System.out.print("Digite o endereco do empregado: ");
        String endereco = input.nextLine();

        System.out.print("Qual o tipo de empregado \n1 : Salariado\n2 : Hourly\n3 : Commissioned\n");
        int op = input.nextInt();
        input.nextLine();
       
        if(op == 1){
            System.out.println("QUAL O SALARIO DO EMPREGADO?: ");
            double salary = input.nextDouble();
            
            Salaried new_employee = new Salaried(nome,endereco, salary);
            lista_de_empregados.add(new_employee);
            input.nextLine();
            new_employee.addPaymentSchedule("mensal");
        
        }else if(op == 2){
            System.out.println("QUAL O VALOR DA HORA DE TRABALHO DO EMPREGADO?: ");
            double hour_rate = input.nextDouble();

            Hourly new_employee = new Hourly(nome,endereco, hour_rate);
            lista_de_empregados.add(new_employee);
            input.nextLine();
            new_employee.addPaymentSchedule("semanal");

        }else if(op == 3){
            System.out.println("QUAL O SALARIO DO EMPREGADO?: ");
            double salary = input.nextDouble();
            System.out.println("QUAL A COMISSAO DO EMPREGADO?: ");
            double commission = input.nextDouble();
                        
            Commissioned new_employee = new Commissioned(nome,endereco,salary,commission);
            lista_de_empregados.add(new_employee);
            input.nextLine();
            new_employee.addPaymentSchedule("bisemanal");

        }else{
            System.out.print("ENTRADA INVALIDA! EMPREGADO NAO CADASTRADO");
            return;
        }

        System.out.print("O EMPREGADO FAZ PARTE DO SINDICATO?(S/N): ");
        String opcao = input.nextLine();

        if(opcao.equals("S") || opcao.equals("s")){
            System.out.print("DIGITE O VALOR DA TAXA SINDICAL MENSAL: ");    
            lista_de_empregados.get(lista_de_empregados.size()-1).add_syndicate(input.nextDouble());
            input.nextLine();
        }

        //adicao de metodo de pagamento
        System.out.print("QUAL O METODO DE PAGAMENTO (correios, deposito, cheque)?: ");
        String pm = input.nextLine();
        change_payment_method((lista_de_empregados.get(lista_de_empregados.size()-1)), pm);


        System.out.printf("%s ADICIONADO COM SUCESSO\nDESEJA ADICIONAR OUTRO EMPREGADO S/N: ", nome);
        String o = input.nextLine();
        if(o.equals("S")|| o.equals("s")){
            adicionar_empregado(lista_de_empregados, input);
        }
    }

    public static void lancar_venda(ArrayList<Employee> lista_de_empregados, Scanner inp){
    
        System.out.print("DIGITE NOME DO EMPREGADO QUE REALIZOU A VENDA: ");
        String nome = inp.nextLine();
        Commissioned employee = find_employee_Commissioned(nome, lista_de_empregados);
                    
        //VERIFICA SE EMPREGADO ENCONTRADO
        if(employee != null){
            System.out.print("Digite o nome do produto vendido: ");
            String produto = inp.nextLine();
            System.out.print("Digite a quantidade de " + produto + " vendido: ");
            int quantidade = inp.nextInt();
            System.out.print("Digite o valor unitario do produto " + produto + ": ");
            double preco_unitario = inp.nextDouble();
            inp.nextLine();
            System.out.print("Digite a data da venda: ");
            String data = inp.nextLine();
            employee.add_sale(produto, quantidade, preco_unitario, data);
            System.out.println("Produto " + produto + " adicionado com sucesso ao empregado " + employee.getNome());

            }else{
                System.out.println("EMPREGADO NAO ENCONTRADO");
            }

            System.out.print("TECLE ENTER PARA CONTINUAR");
            inp.nextLine();
    }


    
    public static void Remove_employee(ArrayList<Employee> lista_de_empregados, Scanner inp){

        System.out.print("DIGITE NOME DO EMPREGADO: ");
        String nome = inp.nextLine();
        
        for (Employee employee : lista_de_empregados) {
            if(employee.getNome().equals(nome)){
                lista_de_empregados.remove(employee);
                break;
            }
        }
    }

    //metodo de remoção de empregado por id
    public static void Remove_employee(int id, ArrayList<Employee> lista_de_empregados){
    }

    public static void alterar_dados(String nome, ArrayList<Employee> lista_de_empregados, Scanner inp){

        for (Employee employee : lista_de_empregados) {
            if(employee.getNome().equals(nome)){

                System.out.println("EMPREGADO " + employee.getNome() + " encontrado");
                System.out.print("OQUE DESEJA ALTEAR!\n1 - Nome\n2 - Endereco\n3 - Tipo de Empregado\n4 - Metodo de pagamento\n5 - Pertencimento ao sindicato\n6 - Taxa sindical\nDigite a opção: ");                
                int opcao = inp.nextInt();
                inp.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.print("DIGITE O NOVO NOME: ");
                        String new_name = inp.nextLine();
                        employee.setNome(new_name); 
                        System.out.println("NOME ALTERADO COM SUCESSO PARA " + new_name);

                        break;
                    case 2:
                        System.out.print("DIGITE O NOVO ENDEREÇO: ");
                        String new_adress = inp.nextLine();
                        employee.setEndereco(new_adress);
                        break;
                    case 3:
                        System.out.print("ALTERAR PARA QUAL TIPO: ");
                        String new_type = inp.nextLine();
                        
                        //tratar atlerarcao de tipo class
                        break;
                    case 4:
                        System.out.print("ALTERAR PARA QUAL METODO(correios, deposito, cheque)?: ");
                        String type = inp.nextLine();
                        change_payment_method(employee, type);
                        break;
                    case 5:
                        change_syndicate_on(employee, inp);
                        break;
                    case 6:
                        change_monthly_fee(employee, inp);
                        break;
                    default:
                        System.out.println("OPCAO INVALIDA");
                        break;
                }
            break;
            }
        }
        System.out.println("TECLE ENTER PARA CONTINUAR");
        inp.nextLine();
    }

    public static void listar(ArrayList<Employee> lista_de_empregados, Scanner inp){
        if(lista_de_empregados.size() <= 0){
            System.out.println("NAO HA EMPREGADOS CADASTRADOS!");
        }else{
             for(Employee emp : lista_de_empregados){
                System.out.printf("%s\n", emp.getNome());
            }
        }
        System.out.println("TECLE ENTER PARA CONTINUAR");
        inp.nextLine();
    }

    public static void lancar_cartao_ponto(ArrayList<Employee> lista_de_empregados, Scanner inp){
        
        System.out.print("De quem é o cartao de ponto? ");
        String nome = inp.nextLine();
        Hourly emp = find_employee_hourly(nome, lista_de_empregados);

        if(emp != null){

            System.out.print("DIGITE HORA DE ENTRADA: ");
            int time_in = inp.nextInt();
            System.out.print("DIGITE HORA DE SAIDA: ");
            int time_out = inp.nextInt();
            inp.nextLine();
            if(time_in >= 0 && time_in < 24 && time_out >= 0 && time_out < 24 && time_in < time_out){
                emp.add_time_card(time_in, time_out);
                System.out.println("CARTAO ADICIONADO COM SUCESSO!");
            }else{
                System.out.println("HORARIO INVALIDO!");
            }
        }else{
            System.out.println("EMPREGADO NAO ENCONTRADO!");
        }
        System.out.print("TECLE ENTER PARA CONTINUAR ");
        inp.nextLine();
    }
    public static void listar_cartao_pointo(ArrayList<Employee> lista_de_empregados, Scanner inp){

        System.out.print("DIGITE O NOME DO EMPREGADO HOURISTA: ");
        String nome = inp.nextLine();
        Hourly emp = find_employee_hourly(nome, lista_de_empregados);
        if(emp != null){
            emp.list_time_card();
        }else{
            System.out.println("USUARIO NAO ENCONTRADO");
        }
        
        System.out.print("TECLE ENTER PARA CONTINUAR ");
        inp.nextLine();
    }

    public static void lancar_taxa_de_servico(ArrayList<Employee> lista_de_empregados, Scanner inp){

        System.out.print("DIGITE O NOME DO EMPREGADO PARA LANCAR TAXA: ");
        String nome = inp.nextLine();
        
        for (Employee employee : lista_de_empregados) {
            if(employee.getNome().equals(nome)){
                if(employee.getSyndicate() != null){
                    System.out.print("DIGITE O NOME DO SERVICO USADO: ");
                    String name_service = inp.nextLine();
                    System.out.print("DIGITE O VALOR DA TAXA: ");
                    double value_taxe = inp.nextDouble();
                    inp.nextLine();
                    employee.getSyndicate().add_union_service_taxe(name_service, value_taxe);
                }else{
                    System.out.println("EMPREGADO NAO FAZ PARTE DO SINDICATO!");
                }
                break;
            }           
        }
        System.out.println("TECLE ENTER PARA CONTINUAR");
        inp.nextLine();
    }

    public static void listar_services_usados(ArrayList<Employee> lista_de_empregados, Scanner inp){

        System.out.print("DIGITE O NOME DO EMPREGADO: ");
        String nome = inp.nextLine();

        for (Employee employee : lista_de_empregados){
            if(employee.getNome().equals(nome) && employee.getSyndicate() != null){
                employee.getSyndicate().listar_services();
            }
        }

        System.out.print("TECLE ENTER PARA CONTINUAR");
        inp.nextLine();
    }

    public static void Next_Payment_day(ArrayList<Employee> lista_de_empregados, Scanner inp){

        System.out.print("DIGITE NOME DO EMPREGADO PARA VISULIZAR PROXIMO DIA DE PAGAMENTO: ");
        String nome = inp.nextLine();
        for(Employee employee : lista_de_empregados) {
           if(employee.getNome().equals(nome)){
               System.out.println("Proximo dia de pagamento: " + employee.getPaymentschedule().getNextDatePayment());
           }    
        }
        System.out.println("TECLE ENTER PARA CONTINUAR");
        inp.nextLine();
        
    }

    public static void change_payment_method(Employee emp, String pm){
        emp.setPaymentMethod(pm);
    }

    public static void show_method_payment(ArrayList<Employee> lista_de_empregados, Scanner inp){

        System.out.println("DIGITE O NOME DO EMPREGADO: ");
        String nome = inp.nextLine();
        Employee emp = find_any_empleyee(lista_de_empregados, nome);
        if(emp != null){
            emp.getPaymentMethod().do_payment(10000);
        }

        System.out.println("TECLE ENTER PARA CONTINUAR");
        inp.nextLine();
    }


    public static void run_payment_today(ArrayList<Employee> lista_de_empregados, Scanner inp){

        int number_emp_today = 0;
        for (Employee employee : lista_de_empregados) {
            if(employee.getPaymentschedule().getNextDatePayment().equals(LocalDate.now())){
                number_emp_today++;
                System.out.println("EMPREGAO A SER PAGO HOJE: " + employee.getNome());
                processar_pagamento(employee);
            }
        }
        if(number_emp_today == 0){
            System.out.println("NAO HA EMPREGADO PARA PAGAR HOJE");
        }
        System.out.println("TECLE ENTER PARA CONTINUAR");
        inp.nextLine();
    }

    public static void processar_pagamento(Employee employee){

        double syndicate_taxe = 0;
        double value = 0;
        if(employee.getSyndicate() != null){
            syndicate_taxe += employee.getSyndicate().total_value_to_pay();
        }

        if(employee instanceof Hourly){
            Hourly emp = (Hourly)employee;
            value = emp.calculate_commission();
            emp.clear_time_cards();
            value -= syndicate_taxe;
            System.out.println("Valor das taxas a pagar: " + syndicate_taxe);
            
        }else if(employee instanceof Commissioned){

            Commissioned emp = (Commissioned) employee;
            value = emp.total_sales() * emp.getComission();
            value += emp.getSalary();
            emp.clear_sales();
            value += emp.getSalary();
            value -= syndicate_taxe;
            System.out.println("Valor das taxas a pagar: " + syndicate_taxe);
                    
        }else{
            Salaried emp = (Salaried) employee;
            value = emp.getSalary();
            value -= syndicate_taxe;
            value -= emp.getSyndicate().getMonthly_fee();
            System.out.println("TAXA DE SERVICOS A PAGAR: " + syndicate_taxe + " | TAXA MENSAL: " + emp.getSyndicate().getMonthly_fee());
            
        }
            employee.getPaymentschedule().setLast_payment(employee.getPaymentschedule().getNextDatePayment());
            employee.getPaymentschedule().setNextDatePayment(employee.getPaymentschedule().Processar_new_date_to_pay(employee.getPaymentschedule().getSchedule()));

            employee.getPaymentMethod().do_payment(value);
    }

    public static void change_day_of_payment(ArrayList<Employee> lista_de_empregados, Scanner inp){

        System.out.println("MUDAR PROXIMO DIA DE PAGAMENTO DO: ");
        String nome = inp.nextLine();
        Employee emp = find_any_empleyee(lista_de_empregados, nome);

        emp.getPaymentschedule().setNextDatePayment(LocalDate.now());
        System.out.println("MUDADO COM SUCESSO PARA: " + LocalDate.now() + "\nTECLE ENTER PARA CONTINUAR");
        inp.nextLine();
    }

    public static void change_syndicate_on(Employee employee, Scanner input){
        
        if(employee.getSyndicate() != null){
            employee.setSyndicate(null);
            System.out.println("AGORA O EMPREGADO " + employee.getNome() + " NAO PERTENCE MAIS AO SINDICATO");    
        }else{
            System.out.println("DIGITE A TAXA SINDICAL: ");
            Double taxe = input.nextDouble();
            input.nextLine();
            employee.add_syndicate(taxe);
            System.out.println("AGORA O EMPREGADO " + employee.getNome() + " PERTENCE AO SINDICATO");
        }
        input.nextLine();
    }
    public static void change_monthly_fee(Employee employee, Scanner input){

        if(employee.getSyndicate() != null){
            System.out.print("DIGITE A NOVA TAXA SINDICAL: ");
            employee.getSyndicate().setMonthly_fee(input.nextDouble());
            input.nextLine();
        }else{
            System.out.println("EMPREGADO NAO FAZ PARTE DO SINDICATO");
        }
        System.out.println("TECLE ENTER PARA CONTINUAR");
        input.nextLine();       
    }
}
