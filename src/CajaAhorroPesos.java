public class CajaAhorroPesos extends Cuenta {

    public CajaAhorroPesos(Double montoInicial) {
        super(montoInicial);
    }


    public double extraccionYcomision(Double monto) {
        return (double) monto;

    }
    public  String descripcionCuenta(){
        return "Caja de ahorro en pesos";
    }
}