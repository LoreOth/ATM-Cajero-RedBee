import java.util.Scanner;

public abstract class Cuenta {

    private Integer saldo;
    private Scanner sc = new Scanner(System.in);

    public Cuenta(Integer montoInicial) {
        if (montoInicial == null || montoInicial < 0) {
            montoInicial = 0;
        }

        this.saldo = montoInicial;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public abstract void mostrarSaldo();

    public void depositar(Integer monto) {
        System.out.println("Saldo inicial "+ saldo);
        if (monto != null && monto > 0) {
            this.saldo += monto;

        }
    }

    public void retirar(Integer monto) {
        if (monto != null && monto <= saldo) {
            this.saldo -= monto;
            System.out.println("Monto retirado " + monto);
        } else {
            if ( monto>saldo){
                System.out.println("Ustede no posee dinero suficiente, si desea sacar el total ingrese S");
                if(sc.next().equals("S")){
                    setSaldo(0);
                }
            }
        }

        System.out.println("Saldo restante " + this.saldo);
    }
}