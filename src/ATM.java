import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.Timer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
Ver si está bien la estructura.
Hacer un log  que imprima todo los pasos y hora

 ver en qué lugar seria util hacer una programacion funcional

 Optionals, seria recomdable usar? Como?

 Deberia hacer una clase para test unitarios?

 ver como sincronizar este trabajo con git porque nunca puedo lpm
 */


public class ATM {
    private Usuario usuario;
    private Movimientos movimientos;
    private Scanner sc = new Scanner(System.in);


    public void validarUsuario(String nombre, String pass) {
        usuario = new Usuario(nombre, pass);
        movimientos = new Movimientos(usuario);
    }

    public void menuPrincipal() {
        int i = 3;
        while (true) {
            System.out.println("--------------------------------------");
            System.out.println("1. Elegir tipo de cuenta\n2. Cambiar contraseña\n3. SALIR");
            System.out.println("--------------------------------------");
            int opcion = sc.nextInt();
            if (opcion == 1) {
                Cuenta cta = menuEleccionCuenta();
                if (cta != null) {
                    movimientos.agregarMovimiento("Acceso a cuenta : " + cta.descripcionCuenta());
                    menuCuenta(cta);

                }
            } else {
                if (opcion == 2) {
                    // this.cambiarContrasenia();
                    movimientos.agregarMovimiento("Actualziación de contraseña" );
                } else {
                    if (opcion == 3) {
                        System.out.println("--------------------------------------");
                        System.out.println("-------- Registro de eventos ---------");
                        System.out.println("--------------------------------------");
                        movimientos.devolverInforme();
                        break;
                    }
                    else {
                        System.out.println("Opción inválida");
                    }
                }


            }
        }
    }

    public Cuenta menuEleccionCuenta() {
        System.out.println("--------------------------------------");
        System.out.println("1. Cuenta corriente\n2. Caja de ahorro en pesos\n3. Caja de ahorro en dólares ");
        System.out.println("--------------------------------------");
        int opcion = sc.nextInt();

        if (opcion == 1) {
            return this.usuario.ctaCorriente();
        } else {
            if (opcion == 2) {
                return this.usuario.ctaPesos();
            } else {
                if (opcion == 3) {
                    return this.usuario.ctaDolar();
                } else {
                    System.out.println("Tipo de cuenta inexistente");
                    return null;
                }
            }
        }

    }


    public void menuCuenta(Cuenta cta) {
        boolean ok = true;
        movimientos.agregarMovimiento("Operaciones de cuenta");
        while (ok) {
            System.out.println("--------------------------------------");
            System.out.println("1. Mostrar saldo\n2. Depositar\n3. Retirar\n4. Volver atrás");
            System.out.println("--------------------------------------");
            int opcion = sc.nextInt();
            if (opcion == 1) {
                System.out.println("El saldo es , " + cta.getSaldo());
                menuCuenta(cta);
            } else {
                if (opcion == 2) {
                    this.depositar(cta);
                } else {
                    if (opcion == 3) {
                        menuRetiro(cta);
                    } else {
                        ok = false;
                    }
                }
            }
        }
    }

    private void informarComisiones(Cuenta cta) {

        if (this.usuario.ctaCorriente().equals(cta)) {
            System.out.println(" Se adicionará un 0,3 % en concepto de comisión en operaciones de cuenta corriente-");
        } else {
            if (this.usuario.ctaDolar().equals(cta)) {
                System.out.println(" Se adicionará un 30 % correspondiente al impuesto solidario de caja de ahorro en dólares.-");

            }
        }
    }

    public void menuRetiro(Cuenta cta) {
        System.out.println("--------------------------------------");
        System.out.println("1. $5000\n2. $10.000\n3. $20.000\n4. $50.000\n5. Retirar otro monto.");
        System.out.println("--------------------------------------");
        int opcion = sc.nextInt();
        if (!cta.equals(this.usuario.ctaPesos())) {
            System.out.println("******************************************************");
            informarComisiones(cta);
            System.out.println("******************************************************");
        }
        switch (opcion) {
            case 1:
                cta.retirar(5000.0);
                break;
            case 2:
                cta.retirar(10000.0);
                break;
            case 3:
                cta.retirar(20000.0);
                break;
            case 4:
                cta.retirar(50000.0);
                break;
            case 5:
                this.retirarOtroImporte(cta);
                break;
            default:
                System.out.println("Opción inválida");
        }
        if (opcion>=1 && opcion <=5) {
            System.out.println("Saldo restante " + cta.getSaldo());
        }
        movimientos.agregarMovimiento("Extracción, saldo actual " + "$" +cta.getSaldo());
    }

    private void retirarOtroImporte(Cuenta cta) {
        System.out.println("Monto a retirar: ");
        cta.retirar(sc.nextDouble());
    }

    private void depositar(Cuenta cta) {
        System.out.println("Monto a depositar: ");
        Double dinero = sc.nextDouble();
        movimientos.agregarMovimiento("Depósito de $ "+ dinero);
        cta.depositar(dinero);
    }
/*
    public void cambiarContrasenia() {
        System.out.println("Ingrese su contraseña actual");
        if (this.usuario.getPassword().equals(sc.next())) {
            System.out.println("Ingrese la nueva contraseña: ");
            this.usuario.cambiarContrasenia(sc.next());
        } else {
            System.out.println("No puede realizar esta operación, comuníquese con el banco");
        }
    }

 */
}
