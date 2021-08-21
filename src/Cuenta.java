import java.util.Scanner;

public abstract class Cuenta implements ICuenta {

    private Double saldo;
    private Scanner sc = new Scanner(System.in);

    protected Cuenta(Double montoInicial) {
        if (montoInicial == null || montoInicial < 0) {
            montoInicial = 0.0;
        }

        this.saldo = montoInicial;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }


    public void depositar(Double monto) {
        if (monto != null && monto > 0) {
            this.saldo += monto;
        }
    }
    public abstract String descripcionCuenta();

    public abstract double extraccionYcomision(Double monto);


    public void retirar(Double monto) {
        if (monto != null) {
            double total = extraccionYcomision(monto);
            if (total <= saldo) {
                this.saldo -= total;
                System.out.println("Monto retirado " + total);
            } else {
                if (total > saldo) {
                    System.out.println("Usted no posee dinero suficiente, si desea sacar el total ingrese S");
                    if (sc.next().equals("S")) {
                        setSaldo(0.0);
                    }
                }
            }
        }

    }
}