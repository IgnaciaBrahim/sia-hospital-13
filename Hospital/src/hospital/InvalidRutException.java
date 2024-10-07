package hospital;  

/**
 * Excepción personalizada para manejar errores relacionados con RUT inválidos.
 * 
 * Esta clase extiende `Exception` y es utilizada para lanzar una excepción 
 * cuando se detecta que un RUT ingresado no es válido. Proporciona un mensaje 
 * específico que describe el error.
 * 
 * @see java.lang.Exception
 */
public class InvalidRutException extends Exception {
    /**
     * Constructor que crea una nueva instancia de `InvalidRutException`.
     * 
     * @param message El mensaje de error que describe el problema con el RUT.
     */
    public InvalidRutException(String message) {
        super(message);
    }
}
