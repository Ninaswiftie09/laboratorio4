
//Nombre: Ingrid Nina Alessandra Nájera Marakovits
//Laboratorio 4 POO
import java.io.File; //correr el programa desde acá
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login {
    private static final String archivo = "usuarios.csv";

    public boolean login(String usuario, String contraseña) {
        try {
            File archivoCSV = new File(archivo);
            Scanner leer = new Scanner(archivoCSV);

            if (leer.hasNextLine()) {
                leer.nextLine();
            }

            while (leer.hasNextLine()) {
                String linea = leer.nextLine();
                String[] rotulos = linea.split(",");

                if (rotulos.length == 6) {
                    String Usuario = rotulos[0].trim();
                    String Contraseña = rotulos[3].trim();

                    if (Usuario.equals(usuario) && Contraseña.equals(contraseña)) { // validar usuario y contraseña
                        leer.close();
                        return true;
                    }
                }
            }
            leer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) { // correr el pograma
        Scanner leer = new Scanner(System.in);
        System.out.println("Hola, selecciona una opción: ");
        System.out.println("1. Iniciar Sesión ");
        System.out.println("2. Registrarse ");
        System.out.println("3. Salir ");

        int opcion = -1;

        try {
            opcion = leer.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Opción no válida. Saliendo del programa.");
            System.exit(0);
        }

        leer.nextLine();

        switch (opcion) {
            case 1:
                System.out.println("Por favor brinda los datos que se te solicitan:");
                System.out.print("Usuario: ");
                String usuario = leer.nextLine();
                System.out.print("Contraseña: ");
                String contraseña = leer.nextLine();

                Login login = new Login();
                boolean ingreso = login.login(usuario, contraseña);

                if (ingreso) {
                    System.out.println("Bienvenido " + usuario);
                    System.out.println();
                    Menu.main(args);
                } else {
                    System.out.println("Error, usuario o contraseña incorrectos.");
                }
                break;

            case 2:
                Registro.main(args);
                break;

            case 3:
                System.out.println("Saliendo del programa.");
                System.exit(0);
                break;

            default:
                System.out.println("Opción no válida. Saliendo del programa.");
                System.exit(0);
        }

        leer.close();
    }
}
