import java.util.ArrayList;

public class Usuario {
    private String nombre= "Pepe";
    private String contraseña = "123";

    public void cambiarContrasenia(String nuevaContrasenia) {
        if (nuevaContrasenia != null) {
            setContraseña(nuevaContrasenia);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}