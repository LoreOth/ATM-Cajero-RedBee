import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimientos {

    private ArrayList<String> movimientos ;
    private ArrayList<String> errores;

    private static Movimientos instance;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


    public Movimientos( Usuario user) {
        this.movimientos  = new ArrayList<>();
        this.errores=new ArrayList<>();
        movimientos.add("-- Bienvenido, " + user.getNombre()+" --" );
        movimientos.add(dtf.format(LocalDateTime.now()));
    }

    public static  Movimientos getInstance(Usuario user){
        if(instance==null){
            instance = new Movimientos(user);
        }
        return instance;

    }

    public void agregarMovimiento (String mov){
        movimientos.add(mov);
        movimientos.add(dtf.format(LocalDateTime.now()));
    }
    public void agregarError(String err){
        errores.add(err);
        errores.add(dtf.format(LocalDateTime.now()));

    }

    public void mostrarErrores(){
        System.out.println("--------------------------------------");
        System.out.println(" Manejo de excepciones que ocurrieron durante la ejecución.-");
        System.out.println("--------------------------------------");
        System.out.println();
        for (String err : errores
        ) { System.out.println(err);

        }

    }
    public void devolverInforme(){

        for (String movimiento : movimientos
        ) { System.out.println(movimiento);

        }
        System.out.println("--------------------------------------");
        System.out.println("¡ Gracias por confiar en nosotros !");
        System.out.println("--------------------------------------");
        System.out.println();
    }
}
