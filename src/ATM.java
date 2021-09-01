import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import javax.swing.Timer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.io.File;
import java.io.FileWriter;
import java.io.*;

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
    private Scanner sc = new Scanner(System.in);


    public void validarUsuario(String nombre, String pass) {
        usuario = new Usuario(nombre, pass);
        Movimientos.getInstance(usuario);

    }

    public Integer manejoExcepcion() {
        try {
            return sc.nextInt();
        } catch (InputMismatchException ex) {
            Movimientos.getInstance(usuario).agregarError(" Se debe ingresa un número");
        }
        return null;
    }

    public void menuPrincipal() { // switch o mapa ver clase menu/ estrategy, factory ver interaz
        boolean ok = true;
        while (ok) {
            Logger logger = Logger.getLogger("Logger.txt");     // ver como resolver esto
            System.out.println("--------------------------------------");
            System.out.println("1. Elegir tipo de cuenta\n2. Cambiar contraseña\n3. SALIR");
            System.out.println("--------------------------------------");
            try {
                int opcion = Integer.parseInt(sc.nextLine());
                if (opcion == 1) {
                    Cuenta cta = menuEleccionCuenta();

                    Movimientos.getInstance(usuario).agregarMovimiento("Acceso a cuenta : " + cta.descripcionCuenta());
                    menuCuenta(cta);
                } else {
                    if (opcion == 2) {

                        Movimientos.getInstance(usuario).agregarMovimiento("Actualización de contraseña");
                    } else {
                        if (opcion == 3) {
                            System.out.println("--------------------------------------");
                            System.out.println("-------- Registro de eventos ---------");
                            System.out.println("--------------------------------------");
                            System.out.println();
                            Movimientos.getInstance(usuario).devolverInforme();
                            Movimientos.getInstance(usuario).mostrarErrores();
                            ok = false;
                        } else {
                            System.out.println("Opción inválida");
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                Movimientos.getInstance(usuario).agregarError(" Se debe ingresa un número");

            }
        }
    }

    public Cuenta menuEleccionCuenta() {
        System.out.println("--------------------------------------");
        System.out.println("1. Cuenta corriente\n2. Caja de ahorro en pesos\n3. Caja de ahorro en dólares ");
        System.out.println("--------------------------------------");
        int opcion = Integer.parseInt(sc.nextLine());
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
        Movimientos.getInstance(usuario).agregarMovimiento("Operaciones de cuenta");
        while (ok) {
            System.out.println("--------------------------------------");
            System.out.println("1. Mostrar saldo\n2. Depositar\n3. Retirar\n4. Volver atrás");
            System.out.println("--------------------------------------");
            try {
                int opcion = Integer.parseInt(sc.nextLine());
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
                            if (opcion == 4) {
                                ok = false;
                            }
                        }
                    }
                }
            } catch (NullPointerException ex) {
                Movimientos.getInstance(usuario).agregarError(" Se ingresó un número que no está en el menú");
            } catch (NumberFormatException ex) {
                Movimientos.getInstance(usuario).agregarError(" No se ingresó un número");

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
        Logger logger = Logger.getLogger("Logger.txt");
        System.out.println("--------------------------------------");
        System.out.println("1. $5000\n2. $10.000\n3. $20.000\n4. $50.000\n5. Retirar otro monto.");
        System.out.println("--------------------------------------");

        try {
            int opcion = Integer.parseInt(sc.nextLine());
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
            if (opcion >= 1 && opcion <= 5) {
                System.out.println("Saldo restante " + cta.getSaldo());
            }
            Movimientos.getInstance(usuario).agregarMovimiento("Extracción, saldo actual " + "$" + cta.getSaldo());
        } catch (InputMismatchException ex) {
            Movimientos.getInstance(usuario).agregarError(" No se ingresó un número");
        }
    }

    private void retirarOtroImporte(Cuenta cta) {
        System.out.println("Monto a retirar: ");
        cta.retirar(sc.nextDouble());
    }

    private void depositar(Cuenta cta) {
        System.out.println("Monto a depositar: ");
        Double dinero = sc.nextDouble();
        Movimientos.getInstance(usuario).agregarMovimiento("Depósito de $ " + dinero);
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
