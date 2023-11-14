import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CambiarContraseña {
    private String archivo = "usuarios.csv";

    public void cambiarContraseña(String usuario, String nuevaContraseña) {
        try {
            File archivoCSV = new File(archivo);
            Scanner leer = new Scanner(archivoCSV);
            StringBuilder nuevacontraseña = new StringBuilder();

            if (leer.hasNextLine()) {
                nuevacontraseña.append(leer.nextLine()).append("\n");
            }

            while (leer.hasNextLine()) {
                String linea = leer.nextLine();
                String[] rotulos = linea.split(",");
                String usuarioo = rotulos[0].trim();

                if (usuarioo.equals(usuario)) {
                    rotulos[3] = nuevaContraseña; // Reemplazar la contraseña antigua con la nueva
                }

                nuevacontraseña.append(String.join(",", rotulos)).append("\n");
            }
            leer.close();

            // editar en el csv
            FileWriter escribir = new FileWriter(archivo);
            escribir.write(nuevacontraseña.toString());
            escribir.close();

            System.out.println("Contraseña cambiada con éxito para el usuario: " + usuario);
        } catch (IOException e) {
            System.err.println("Error al cambiar la contraseña: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        System.out.print("Usuario: ");
        String usuario = leer.nextLine();
        System.out.print("Contraseña: ");
        String nuevaContraseña = leer.nextLine();
        leer.close();

        CambiarContraseña cambiarContraseña = new CambiarContraseña();
        cambiarContraseña.cambiarContraseña(usuario, nuevaContraseña);
    }
}
