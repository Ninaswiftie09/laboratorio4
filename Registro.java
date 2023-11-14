import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Registro extends Usuarios {
    String archivo = "usuarios.csv"; // ponemos como se llama el csv al que queremos ingresar

    public Registro(String usuario, String nombre, String apellido, String contraseña, int tarjeta,
            String tipo_cliente) {
        super(usuario, nombre, apellido, contraseña, tarjeta, tipo_cliente); // llamamos al constructor de Usuario
    }

    public void guardarusuario(Usuarios usuario) { // guarda los datos del usuario en el csv con los gets que estan en
                                                   // la
                                                   // superclase

        try {
            FileWriter insertar = new FileWriter(archivo, true);
            insertar.append(usuario.getUsuario());
            insertar.append(",");
            insertar.append(usuario.getNombre());
            insertar.append(",");
            insertar.append(usuario.getApellido());
            insertar.append(",");
            insertar.append(usuario.getContraseña());
            insertar.append(",");
            insertar.append(String.valueOf(usuario.getTarjeta()));
            insertar.append(",");
            insertar.append(usuario.getTipo_cliente());
            insertar.append("\n");
            insertar.flush();
            insertar.close();
            System.out.println("Su registro ha sido exitoso.");
        } catch (IOException e) {
            System.err.println("Error, no se guardaron los datos: " + e.getMessage());
        }
    }

    public static void main(String[] args) { // metodo para solicitarle al usuario su información
        Scanner leer = new Scanner(System.in);
        System.out.print("Para ingresar un nuevo usuario, proporciona la siguiente información: \n");
        System.out.print("Usuario: ");
        String usuario = leer.nextLine();
        System.out.print("Nombre: ");
        String nombre = leer.nextLine();
        System.out.print("Apellido: ");
        String apellido = leer.nextLine();
        System.out.print("contraseña(sin números): ");
        String contraseña = leer.nextLine();
        System.out.print("Número de tarjeta (ultimos 4 digitos): ");
        int tarjeta = Integer.parseInt(leer.nextLine());
        System.out.print("Plan vip o gratis: ");
        String tipo_cliente = leer.nextLine();
        leer.close();

        Usuarios user = new Usuarios(usuario, nombre, apellido, contraseña, tarjeta, tipo_cliente);
        Registro guardar = new Registro(usuario, nombre, apellido, contraseña, tarjeta, tipo_cliente);
        guardar.guardarusuario(user);
    }
}