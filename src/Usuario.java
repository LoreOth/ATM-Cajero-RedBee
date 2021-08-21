import java.util.Map;

public class Usuario {
    private String nombre;
    private String password;
    private Map<String, Cuenta> cuentas;


    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
        this.cuentas = Map.of(
                "CC" , new CuentaCorriente(0.0),
                "CP", new CajaAhorroPesos(0.0),
                "CD", new CajaAhorroDolares(0.0)
        );
    }
    public Cuenta ctaCorriente(){
        return cuentas.get("CC");
    }

    public Cuenta ctaPesos(){
        return cuentas.get("CP");
    }

    public Cuenta ctaDolar(){
        return cuentas.get("CD");
    }


    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<String, Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(Map<String, Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}