import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimientos {

    private ArrayList<String> movimientos ;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


    public Movimientos( Usuario user) {
        this.movimientos  = new ArrayList<>();
        movimientos.add("Bienvenido " + user.getNombre() );
        movimientos.add(dtf.format(LocalDateTime.now()));
    }

    public void agregarMovimiento (String mov){
        movimientos.add(mov);
        movimientos.add(dtf.format(LocalDateTime.now()));
    }

    public void devolverInforme(){
        for (String movimiento : movimientos
        ) { System.out.println(movimiento);

        }
        System.out.println("--------------------------------------");
        System.out.println("¡ Gracias por confiar en nosotros !");

    }
}
