public enum Cargo {
    DEV("Desenvolvedor") {
        @Override
        double calcularBonus(double salario) {
            return salario * 0.10;
        }
    },
    PM("Product Manager") {
        @Override
        double calcularBonus(double salario) {
            return salario * 0.2;
        }
    },
    DEVOPS("DevOps_Engineer") {
        @Override
        double calcularBonus(double salario) {
            return salario * 0.15;
        }
    };

    private final String descricao;

    abstract double calcularBonus(double salario);

    Cargo(String descricao) {

        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
