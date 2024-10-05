package hospital; // Asegúrate de que esté en el mismo paquete que las demás clases

public class EdadInvalidaException extends Exception {
    public EdadInvalidaException(String mensaje) {
        super(mensaje);
    }
}
