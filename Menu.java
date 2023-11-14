import java.util.Scanner;

public class Menu { // menu principal
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        System.out.println();
        System.out.println("Menú principal, selecciona que quieres hacer");
        System.out.println();
        System.out.println("1. Ir al menú de Reservas e información de vuelos");
        System.out.println("2. Cambiar mi contraseña");
        System.out.println("3. Salir");
        int opcion = leer.nextInt();
        switch (opcion) {
            case 1:
                System.out.println();
                Reserva.main(args);
                System.out.println();
                break;

            case 2:
                System.out.println();
                CambiarContraseña.main(args);
                System.out.println();
                break;

            case 3:
                System.exit(0);
                break;
        }

        leer.close();

    }
}
