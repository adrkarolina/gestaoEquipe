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
                case 4 -> funcionarioMaiorSalario();
                case 5 -> funcionariosPorCargo();
                case 6 -> buscarFuncionarioPorNome();
                case 7 -> sair();
                default -> System.out.println("Opção inválida");

            }
        } while (opcao != 7);

    }

    private static void exibirMenu() {
        System.out.println("""
                1 - Cadastrar funcionário
                2 - Listar funcionários
                3 - Exibir salários finais
                4 - Funcionário com maior salário
                5 - Quantidade de funcionários por cargo
                6 - Buscar funcionário por nome
                7 - Sair""");
    }

    private static void cadastrarFuncionario() {
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

    private static void listarFuncionarios() {
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Cargo: " + funcionario.getCargo().getDescricao());
        }
    }

    private static void exibirSalariosFinais() {
        for (Funcionario funcionario : funcionarios) {

            String nome = funcionario.getNome();
            double salarioBase = funcionario.getSalario();
            double salarioFinal = salarioBase + funcionario.getCargo().calcularBonus(salarioBase);

            System.out.println(nome + " ->" + salarioFinal);

        }
    }

    private static void funcionarioMaiorSalario() {
        System.out.println("\n--- Funcionário com Maior Salário Final ---");
        Funcionario funcionarioMaiorSalario = null;
        for (Funcionario funcionario : funcionarios) {
            if (funcionarioMaiorSalario == null) {
                funcionarioMaiorSalario = funcionario;
            } else {
                if (funcionario.obterSalarioFinal() > funcionarioMaiorSalario.obterSalarioFinal()) {
                    funcionarioMaiorSalario = funcionario;
                }
            }
        }
        if (funcionarioMaiorSalario != null) {
            System.out.println("Funcionário com maior salário: " + funcionarioMaiorSalario.getNome());
        } else {
            System.out.println("Sem funcionários cadastrados no sistema.");
        }
    }

    private static void funcionariosPorCargo(){}
    private static void buscarFuncionarioPorNome(){}

    private static void sair(){
        System.out.println("\nEncerrando sistema... Até logo!");
    }
}
