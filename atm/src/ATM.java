import java.util.Scanner;
import java.util.Map;

/*

la extraccion de Cuenta corriente tiene una comicion de 0.3%
16:46
la extraccion de Dolares tiene el impuesto solidario (no se cuanto es)
16:46
la estraccion en pesos no tienen costo


 */


public class ATM {
    private Cuenta cuentaActual;
    private Usuario usuario = new Usuario();
    private Scanner sc = new Scanner(System.in);


    private Map<String, Cuenta> cuentas = Map.of(
            "CUENTA CORRIENTE", new CuentaCorriente(50000),
            "CAJA EN PESOS", new CajaAhorroPesos(10000),
            "CAJA EN DOLARES", new CajaAhorroDolares(35000)
    );

    public boolean validarUsuario() {
        int i = 2;
        System.out.println("--------------------------------------");
        System.out.println("Bienvenido, " + usuario.getNombre());
        System.out.println("--------------------------------------");
        System.out.println("Ingrese su contraseña  ");
        String pass = sc.next();
        while ((i <= 3) && (!pass.equals(usuario.getContraseña()))) {
            System.out.println("Le quedan  " + i + " intentos ");
            pass = sc.next();
            i--;
        }
        if (pass.equals(usuario.getContraseña())) {
            return true;
        } else {
            System.out.println("Debe comunicarse con el banco y gestionar una nueva clave :( ");
        }
        return false;
    }

    public void menuPrincipal() {
        int i = 3;
        boolean ok = validarUsuario();
        while ((true) && (ok)) {
            System.out.println("--------------------------------------");
            System.out.println("1. Elegir tipo de cuenta\n2. Cambiar contraseña\n3. SALIR");
            System.out.println("--------------------------------------");
            int opcion = sc.nextInt();
            if (opcion == 1) {
                if (menuEleccionCuenta()) {
                    menuCuenta();
                }
            } else {
                if (opcion == 2) {
                    this.cambiarContrasenia();
                } else{
                    if (opcion==3) break;
                    else {
                        System.out.println("Opción inválida");
                    }
                }


            }
        }
    }

    public boolean menuEleccionCuenta() {
        System.out.println("--------------------------------------");
        System.out.println("1. Cuenta corriente\n2. Caja de ahorro en pesos\n3. Caja de ahorro en dólares ");
        System.out.println("--------------------------------------");
        int opcion = sc.nextInt();
        boolean ctaValida = true;
        if (opcion == 1) {
            this.cuentaActual = cuentas.get("CUENTA CORRIENTE");
        } else {
            if (opcion == 2) {
                this.cuentaActual = cuentas.get("CAJA EN PESOS");
            } else {
                if (opcion == 3) {
                    this.cuentaActual = cuentas.get("CAJA EN DOLARES");
                } else {
                    System.out.println("Tipo de cuenta inexistente");
                    ctaValida = false;
                }
            }
        }
        return ctaValida;
    }

    public void menuCuenta() {
        while (true) {
            System.out.println("--------------------------------------");
            System.out.println("1. Mostrar saldo\n2. Depositar\n3. Retirar\n4. Volver atrás");
            System.out.println("--------------------------------------");
            int opcion = sc.nextInt();
            if (opcion == 1) {
                this.cuentaActual.mostrarSaldo();

            } else {
                if (opcion == 2) {
                    this.depositar();
                } else {
                    if (opcion == 3) {
                        this.menuRetiro();
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public void menuRetiro() {
        System.out.println("--------------------------------------");
        System.out.println("1. $5000\n2. $10.000\n3. $20.000\n4. $50.000\n4. $50.000\n6. Retirar otro monto.");
        System.out.println("--------------------------------------");
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                this.cuentaActual.retirar(5000);
                break;
            case 2:
                this.cuentaActual.retirar(10000);
                break;
            case 3:
                this.cuentaActual.retirar(20000);
                break;
            case 4:
                this.cuentaActual.retirar(50000);
                break;
            case 6:
                this.retirarOtroImporte();
                break;
            default:
                System.out.println("Opción inválida");
        }
    }

    public void retirarOtroImporte() {
        System.out.println("Monto a retirar: ");
        this.cuentaActual.retirar(sc.nextInt());
    }


    public void depositar() {
        System.out.println("Monto a depositar: ");
        Integer dinero= sc.nextInt();
        this.cuentaActual.depositar(dinero);
    }

    public void cambiarContrasenia() {
        System.out.println("Ingrese su contraseña actual");
        if (this.usuario.getContraseña().equals(sc.next())) {
            System.out.println("Ingrese la nueva contraseña: ");
            this.usuario.cambiarContrasenia(sc.next());
        } else {
            System.out.println("No puede realizar esta operación, comuniquese con el banco");
        }
    }
}