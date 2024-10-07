package hospital;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * La clase GestionTerminal proporciona utilidades para interactuar con la terminal o consola
 * del sistema, como detener la ejecución del programa hasta que el usuario presione una tecla
 * y limpiar la pantalla de la consola. Estas funciones son útiles para mejorar la experiencia 
 * de usuario en aplicaciones de consola.
 */
public class GestionTerminal {

    /**
     * Detiene la ejecución del programa hasta que el usuario presione una tecla.
     * Utiliza un lector de entrada estándar para esperar la entrada del usuario.
     * 
     * @throws IOException Si ocurre un error durante la lectura de la entrada.
     */
    public void presioneTecla() throws IOException {
        System.out.print("Presione tecla para continuar...");
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        lector.readLine(); 
    }

    /**
     * Limpia la pantalla de la consola. Este método intenta ejecutar comandos
     * específicos dependiendo del sistema operativo (Windows o UNIX/Linux/MacOS).
     * En Windows, usa el comando "cls" y en sistemas tipo UNIX, envía secuencias
     * ANSI para borrar la pantalla.
     */
    public void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Para sistemas UNIX/Linux/MacOS
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("No se pudo limpiar la consola.");
        }
    }
}
