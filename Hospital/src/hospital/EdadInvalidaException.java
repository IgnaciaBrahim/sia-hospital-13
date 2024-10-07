package hospital;

/**
 * La clase EdadInvalidaException es una excepción personalizada que se lanza cuando se ingresa
 * una edad inválida en el sistema hospitalario. Esta clase extiende de Exception y permite 
 * proporcionar un mensaje detallado sobre el error.
 */
public class EdadInvalidaException extends Exception {

    /**
     * Constructor que inicializa la excepción con un mensaje personalizado.
     * @param mensaje El mensaje de error que describe la razón por la cual la excepción fue lanzada.
     */
    public EdadInvalidaException(String mensaje) {
        super(mensaje);
    }
}

