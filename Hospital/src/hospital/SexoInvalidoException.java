package hospital;

/**
 * Excepción personalizada que se lanza cuando se introduce un valor inválido
 * para el sexo de una persona en el sistema hospitalario.
 * Los valores válidos típicamente son:
 * - 0: Masculino
 * - 1: Femenino
 */
public class SexoInvalidoException extends Exception {
    /**
     * Constructor que recibe un mensaje de error personalizado.
     *
     * @param mensaje El mensaje que detalla la causa de la excepción.
     */
    public SexoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
