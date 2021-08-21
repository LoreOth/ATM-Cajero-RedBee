public class CuentaCorriente extends Cuenta {
    private Double tasa = 0.003;

    public CuentaCorriente(Double montoInicial) {
        super(montoInicial);
    }


    public double extraccionYcomision(Double monto) {
        return (monto + (monto * tasa));

    }
    public String descripcionCuenta(){
        return "Cuenta corriente ";
    }
}