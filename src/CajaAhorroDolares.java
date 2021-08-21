public class CajaAhorroDolares extends Cuenta {
    private Double tasa = 0.3;

    public CajaAhorroDolares(Double montoInicial) {
        super(montoInicial);
    }


    public double extraccionYcomision(Double monto) {
        return (monto + (monto * tasa));

    }
    public  String descripcionCuenta(){
        return "Caja de ahorro en dólares ";
    }


}