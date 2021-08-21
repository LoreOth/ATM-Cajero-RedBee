public class CuentaCorriente extends Cuenta {

    public CuentaCorriente(Integer montoInicial) {
        super(montoInicial);
    }

    public void mostrarSaldo() {
        System.out.println("Saldo disponible: $" + this.getSaldo());
    }

}