import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM atm = new ATM();

        System.out.println("--------------------------------------");
        System.out.println("Bienvenido, ingrese su usuario");
        String nombre = sc.next();
        System.out.println("--------------------------------------");
        System.out.println("Ingrese su contraseña  ");
        String pass = sc.next();

        atm.validarUsuario(nombre, pass);
        atm.menuPrincipal();


    }
}
