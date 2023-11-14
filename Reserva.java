import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Reserva {

    public String archivo = "vuelos.csv";
    public String archivobd = "bd.csv";

    public void mostrarinfo() {
        try {
            File archivoCSV = new File(archivo);
            Scanner leer = new Scanner(archivoCSV);

            if (leer.hasNextLine()) {
                System.out.println("Información de Todos los Vuelos:");
            }

            while (leer.hasNextLine()) {
                String linea = leer.nextLine();
                String[] datosVuelo = linea.split(",");

                // Mostrar la información de cada vuelo
                for (String dato : datosVuelo) {
                    System.out.print(dato + ", ");
                }
                System.out.println(); // espacios por estética
            }
            leer.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado - " + e.getMessage());
        }
    }

    public void reservar() {
        try {
            Scanner leer = new Scanner(System.in);

            // Pedir información al usuario
            System.out.print("Para reservar un vuelo ingresa la siguiente información: ");
            System.out.println();
            System.out.print("Usuario: ");
            String usuario = leer.nextLine();
            System.out.print("Asiento: ");
            String asiento = leer.nextLine();
            System.out.print("Cuántas maletas llevas: ");
            int maletas = leer.nextInt();
            leer.nextLine();
            System.out.print("País de destino: ");
            String paisDestino = leer.nextLine().trim().toLowerCase(); // dejar mayusculas o minusculas

            File archivoCSV = new File(archivo);
            Scanner leervuelo = new Scanner(archivoCSV);

            if (leervuelo.hasNextLine()) {
                leervuelo.nextLine();
            }

            while (leervuelo.hasNextLine()) {
                String linea = leervuelo.nextLine();
                String[] datitos = linea.split(",");

                String pais = datitos[2].trim().toLowerCase();

                if (pais.equals(paisDestino)) {

                    try {
                        FileWriter archivoBD = new FileWriter(archivobd, true);

                        // Escribir la información en el csv
                        archivoBD.append(usuario);
                        archivoBD.append(",");
                        archivoBD.append(asiento);
                        archivoBD.append(",");
                        archivoBD.append(Integer.toString(maletas));
                        archivoBD.append(",");
                        for (String dato : datitos) {
                            archivoBD.append(dato);
                            archivoBD.append(",");
                        }
                        archivoBD.append("\n");

                        archivoBD.flush();
                        archivoBD.close();

                        System.out.println("Reserva exitosa. Información guardada en la base de datos");
                    } catch (IOException e) {
                        System.err.println("Error, intenta nuevamente más tarde " + e.getMessage());
                    }

                    leervuelo.close();
                    leer.close();
                    return;
                }
            }

            // mensaje de error
            System.out.println("No hay vuelos disponibles para el país de destino ingresado.");

            leervuelo.close();
            leer.close();
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    public void imprimir() {
        try {
            Scanner leer = new Scanner(System.in);

            // Pedir al usuario el nombre de usuario para buscar la reserva
            System.out.print("Para imprimir tu reserva ingresa tu usuario: ");
            String usuario = leer.nextLine();

            File archivoBD = new File(archivobd);
            Scanner leerr = new Scanner(archivoBD);

            if (leerr.hasNextLine()) {
                leerr.nextLine();
            }

            // Buscar info
            boolean buscar = false;
            while (leerr.hasNextLine()) {
                String linea = leerr.nextLine();
                String[] datitos = linea.split(",");

                String usuarioo = datitos[0].trim();

                if (usuarioo.equals(usuario)) {
                    // Mostrar la info
                    System.out.println("Información de la Reserva:");
                    System.out.print(
                            "Usuario, Asiento, Maletas, Fecha, Hora, Destino, Escalas, Tipo, Precio, Aereolinea");
                    System.out.println();
                    for (String dato : datitos) {
                        System.out.print(dato + ", ");
                    }
                    System.out.println(); // espacios por estética

                    buscar = true;
                    break;
                }
            }

            // mensaje de error
            if (!buscar) {
                System.out.println("No se encontró ninguna reserva para tu usuario.");
            }

            leerr.close();
            leer.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error, intenta nuevamente más tarde " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        Reserva reserva = new Reserva();
        System.out.println("Selecciona una opción");
        System.out.println("1. Mostrar vuelos disponibles");
        System.out.println("2. Reservar un vuelo");
        System.out.println("3. Ver reserva de mi vuelo");
        System.out.println("4. Salir");
        int opcion = leer.nextInt();
        switch (opcion) {
            case 1:
                System.out.println();
                reserva.mostrarinfo();
                System.out.println();
                break;

            case 2:
                System.out.println();
                reserva.reservar();
                System.out.println();
                break;
            case 3:
                System.out.println();
                reserva.imprimir();
                System.out.println();
                break;
            case 4:
                System.exit(0);
                break;
        }
        leer.close();
    }
}
