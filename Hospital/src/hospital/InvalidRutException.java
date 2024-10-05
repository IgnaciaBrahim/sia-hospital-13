package hospital;  // Asegúrate de usar el mismo paquete que el resto de tus clases.

public class InvalidRutException extends Exception {
    public InvalidRutException(String message) {
        super(message);
    }
}
