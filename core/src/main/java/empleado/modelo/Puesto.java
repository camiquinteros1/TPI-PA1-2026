package empleado.modelo;

public enum Puesto {

    ANALISTA(500000),
    SUPERVISOR(800000),
    GERENTE(1200000);

    private final double salarioMinimo;

    Puesto(double salarioMinimo) {
        this.salarioMinimo = salarioMinimo;
    }

    public double getSalarioMinimo() {
        return salarioMinimo;
    }
}
