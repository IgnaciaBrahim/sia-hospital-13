package hospital;

/**
 * Excepción personalizada que se lanza cuando se introduce un valor inválido
 * para el triage de un paciente en el sistema hospitalario.
 * Los valores válidos de triage suelen estar en un rango específico, dependiendo
 * del sistema de clasificación.
 */
public class TriageInvalidoException extends Exception {
     /**
     * Constructor que recibe un mensaje de error personalizado.
     *
     * @param mensaje El mensaje que detalla la causa de la excepción.
     */
    public TriageInvalidoException(String mensaje) {
        super(mensaje);
    }
}
