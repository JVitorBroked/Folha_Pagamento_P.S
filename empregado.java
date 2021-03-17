public class empregado {

    private String nome;
    private String endereco;
    private String tipo_de_empregado;
    private double salario_horario;
    private double salario_mensal;
    private double comissao;


    public empregado(String nome, String endereco, String tipo_de_empregado, double salario_horario, double salario_mensal, double comissao){
        this.nome = nome;
        this.endereco = endereco;
        this.tipo_de_empregado = tipo_de_empregado;
        this.salario_horario = salario_horario;
        this.salario_mensal = salario_mensal;
        this.comissao = comissao;
    }
    public String getNome(){
        return this.nome;
    }
    public String print_employee_info(){
        return "Name: " + this.nome + 
                "\nAddress: " + this.endereco;
 
    }
}
