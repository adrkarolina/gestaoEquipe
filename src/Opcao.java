public enum Opcao {
    CADASTRAR_FUNCIONARIO(1),
    LISTAR_FUNCIONARIO(2),
    EXIBIR_SALARIOS(3),
    FUNCIONARIO_COM_MAIOR_SALARIO(4),
    QUANTIDADE_DE_FUNCIONARIOS_POR_CARGO(5),
    BUSCAR_FUNCIONARIO_POR_NOME(6),
    SAIR(7);

    private final int codigo;

    Opcao(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static Opcao obterOpcao(int codigo) {
        for (Opcao opcao : Opcao.values()) {
            if (opcao.getCodigo() == codigo) {
                return opcao;
            }
        }
        throw new IllegalArgumentException("Opção inválida: " + codigo);
    }
}
