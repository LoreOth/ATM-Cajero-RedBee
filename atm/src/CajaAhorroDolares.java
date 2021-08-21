public class CajaAhorroDolares extends Cuenta {

    public CajaAhorroDolares(Integer montoInicial) {
        super(montoInicial);
    }

    public void mostrarSaldo() {
        System.out.println("Saldo disponible: $" + this.getSaldo());
    }
}