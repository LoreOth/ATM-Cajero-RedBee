public class CajaAhorroPesos extends Cuenta {

    public CajaAhorroPesos(Integer montoInicial) {
        super(montoInicial);
    }

    public void mostrarSaldo() {
        System.out.println("Saldo disponible: $" + this.getSaldo());
    }
}