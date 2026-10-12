import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner leitor = new Scanner(System.in);
    private static final List<Funcionario> funcionarios = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            System.out.println("Escolha uma opção: ");
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1 -> cadastrarFuncionario();
                case 2 -> listarFuncionarios();
                case 3 -> exibirSalariosFinais();
                case 4 -> funcionariosMaiorSalario();
                case 5 -> funcionariosPorCargo();
                case 6 -> buscarFuncionarioPorNome();
                case 7 -> sair();
                default -> System.out.println("Opção inválida");

            }
        } while (opcao !=7);

    }

    private static void exibirMenu(){
        System.out.println("""
                1 - Cadastrar funcionário
                2 - Listar funcionários
                3 - Exibir salários finais
                4 - Funcionário com maior salário
                5 - Quantidade de funcionários por cargo
                6 - Buscar funcionário por nome
                7 - Sair""");
    }

    private static void cadastrarFuncionario(){
        System.out.println("\n--- Cadastro de Funcionário ---");
        System.out.println("Digite o nome: ");
        String nome = leitor.nextLine();

        System.out.println("Informe o salário: ");
        double salario = leitor.nextDouble();
        leitor.nextLine(); //limpar o buffer

        System.out.println("Informe o cargo: ");
        Cargo cargo = Cargo.valueOf(leitor.next().toUpperCase());

        Funcionario funcionario = new Funcionario(nome, salario, cargo);
        funcionarios.add(funcionario);

        /*ou podemos simplificar usando
        funcionarios.add(new Funcionario(nome, salario, cargo));
         */
    }

    private static void listarFuncionarios(){
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Cargo: " + funcionario.getCargo().getDescricao());
        }
    }

    private static void exibirSalariosFinais(){
        for (Funcionario funcionario : funcionarios) {

            String nome = funcionario.getNome();
            double salarioBase = funcionario.getSalario();
            double salarioFinal = salarioBase + funcionario.getCargo().calcularBonus(salarioBase);

            System.out.println(nome +"->" +  salarioFinal);

        }
    }
    private static void funcionariosMaiorSalario(){
        System.out.println("\n--- Funcionário com Maior Salário Final ---");

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado no sistema.");
            return;
        }
        Funcionario maiorSalario = funcionarios.get(0);
        double maiorSalarioFinal = maiorSalario.getSalario() + maiorSalario.getCargo().calcularBonus(maiorSalario.getSalario());

        for (int i=1; i<funcionarios.size(); i++) {
            Funcionario funcionario = funcionarios.get(i);
            double salarioBase = funcionario.getSalario();
            double salarioFinal = salarioBase + funcionario.getCargo().calcularBonus(salarioBase);

            if (salarioFinal > maiorSalarioFinal) {
                maiorSalarioFinal = salarioFinal;
                maiorSalario = funcionario;
            }
        }

        System.out.println("\n--- Funcionário com Maior Salário ---");
        System.out.println("Nome: " + maiorSalario.getNome());
        System.out.println("Cargo: " + maiorSalario.getCargo().getDescricao());
        System.out.println("Salário final: R$ " + maiorSalarioFinal);


    }
    private static void funcionariosPorCargo(){}
    private static void buscarFuncionarioPorNome(){}

    private static void sair(){
        System.out.println("\nEncerrando sistema... Até logo!");
    }
}
