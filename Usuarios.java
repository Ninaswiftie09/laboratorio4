public class Usuarios {
    String usuario;
    String nombre;
    String apellido;
    String contraseña;
    int tarjeta;
    String tipo_cliente;

    public Usuarios(String usuario, String nombre, String apellido, String contraseña, int tarjeta,
            String tipo_cliente) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
        this.tarjeta = tarjeta;
        this.tipo_cliente = tipo_cliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getContraseña() {
        return contraseña;
    }

    public int getTarjeta() {
        return tarjeta;
    }

    public String getTipo_cliente() {
        return tipo_cliente;
    }
}
