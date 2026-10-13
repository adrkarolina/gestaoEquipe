import java.util.*;

public class GerenciadorFuncionarios {
    static final Scanner leitor = new Scanner(System.in);
    private static final List<Funcionario> funcionarios = new ArrayList<>();

    public void iniciar(){
        Opcao opcao = null; //para o enum

        do {
            System.out.println("Escolha uma opção: ");
            GerenciadorFuncionarios.exibirMenu();

            try{
                opcao = Opcao.obterOpcao(GerenciadorFuncionarios.leitor.nextInt());
            } catch (IllegalArgumentException e){
                System.out.println("Opção invalida.");
                continue;
            }


            switch (opcao) {
                case CADASTRAR_FUNCIONARIO -> GerenciadorFuncionarios.cadastrarFuncionario();
                case LISTAR_FUNCIONARIO -> GerenciadorFuncionarios.listarFuncionarios();
                case EXIBIR_SALARIOS -> GerenciadorFuncionarios.exibirSalariosFinais();
                case FUNCIONARIO_COM_MAIOR_SALARIO -> GerenciadorFuncionarios.funcionarioMaiorSalario();
                case QUANTIDADE_DE_FUNCIONARIOS_POR_CARGO -> GerenciadorFuncionarios.funcionariosPorCargo();
                case BUSCAR_FUNCIONARIO_POR_NOME -> GerenciadorFuncionarios.buscarFuncionarioPorNome();
                case SAIR -> GerenciadorFuncionarios.sair();

            }
        } while (opcao != Opcao.SAIR);
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
        System.out.print("Digite o nome: ");
        leitor.nextLine(); //consumir quebra de linha pendente, ajuste para add nome composto
        String nome = leitor.nextLine();

        System.out.print("Informe o salário: ");
        double salario = leitor.nextDouble();
        leitor.nextLine(); //limpar o buffer

        System.out.print("Informe o cargo: ");
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

    private static void funcionariosPorCargo(){
        Map<Cargo, Integer> mapFuncionarioPorCargo = new HashMap<>();

        for (Funcionario funcionario : funcionarios) {
            int quantidadeAtual = mapFuncionarioPorCargo.getOrDefault(funcionario.getCargo(), 0);
            mapFuncionarioPorCargo.put(funcionario.getCargo(), quantidadeAtual +1);
        }

        for (Map.Entry<Cargo, Integer> cargoIntegerEntry : mapFuncionarioPorCargo.entrySet()) {
            System.out.println(cargoIntegerEntry.getKey().getDescricao() + " -> " + cargoIntegerEntry.getValue());
        }
    }
    private static void buscarFuncionarioPorNome(){
        boolean existeFuncionario = false;

        System.out.print("Digite o nome: ");
        String nome = leitor.next();

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getNome().equalsIgnoreCase(nome)) {
                System.out.println(" ------ Funcionário encontrado! ------ ");
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Cargo: " + funcionario.getCargo().getDescricao());
                System.out.println("Salario base: " + funcionario.obterSalarioFinal());
                existeFuncionario = true;
            }
        } if (!existeFuncionario) {
            System.out.println("Funcionário não localizado!");

        }
    }


    private static void sair(){
        System.out.println("\nEncerrando sistema... Até logo!");
    }
}
