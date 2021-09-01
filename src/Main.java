import java.util.Scanner;
//Preguntar como hacer un logger. Es un archivo donde se guardan todos los movimientos? o solo las excepciones manejadas?
//Como creo el archivo.txt?
// ver si está bien este manejo de excepciones
// como usaria optionals aca




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
