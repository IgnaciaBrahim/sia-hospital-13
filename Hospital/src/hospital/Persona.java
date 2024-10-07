package hospital;

/**
 * Clase abstracta que representa a una persona genérica en el sistema hospitalario.
 * Esta clase contiene los atributos comunes que comparten las personas, como el RUT, 
 * nombre y apellido, y métodos para obtener y modificar estos atributos. 
 * Además, define dos métodos abstractos que deben ser implementados por las subclases.
 */
public abstract class Persona 
{
    // Atributos privados
    private String rut;
    private String nombre;
    private String apellido;

    /**
     * Constructor por defecto que inicializa los atributos a valores `null`.
     */
    public Persona() 
    {
        this.rut = null;
        this.nombre = null;
        this.apellido = null;
    }

    /**
     * Constructor que inicializa una persona con los valores proporcionados.
     * 
     * @param rut El RUT de la persona.
     * @param nombre El nombre de la persona.
     * @param apellido El apellido de la persona.
     */
    public Persona(String rut, String nombre, String apellido){
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // Métodos getter y setter para los atributos

    /**
     * Obtiene el RUT de la persona.
     * 
     * @return El RUT de la persona.
     */
    public String getRut() {
        return rut;
    }

    /**
     * Modifica el RUT de la persona.
     * 
     * @param rut El nuevo RUT de la persona.
     */
    public void setRut(String rut) {
        this.rut = rut;
    }

    /**
     * Obtiene el nombre de la persona.
     * 
     * @return El nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

     /**
     * Modifica el nombre de la persona.
     * 
     * @param nombre El nuevo nombre de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

     /**
     * Obtiene el apellido de la persona.
     * 
     * @return El apellido de la persona.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Modifica el apellido de la persona.
     * 
     * @param apellido El nuevo apellido de la persona.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    // Métodos no abstractos

    /**
     * Obtiene el nombre completo de la persona en formato "Nombre Apellido".
     * 
     * @return El nombre completo de la persona.
     */
    public String obtenerDatos()
    {
        return getNombre() + " " + getApellido();
    }

    /**
     * Actualiza los datos de la persona con nuevos valores de RUT, nombre y apellido.
     * 
     * @param rut El nuevo RUT de la persona.
     * @param nombre El nuevo nombre de la persona.
     * @param apellido El nuevo apellido de la persona.
     */
    public void actualizarDatos(String rut, String nombre, String apellido)
    {
        setNombre(nombre);
        setRut(rut);
        setApellido(apellido);
    }
    
    // Métodos abstractos

    /**
     * Método abstracto que debe ser implementado por las subclases. 
     * Representa una asignación relacionada con la persona, como un médico asignado o una habitación.
     * 
     * @return Un objeto relacionado con la asignación de la persona.
     */
    public abstract Object verAsignacion();

    /**
     * Método abstracto que debe ser implementado por las subclases.
     * Representa el registro de la persona en el sistema.
     * 
     * @return Un mensaje indicando que la persona ha sido registrada.
     */
    public abstract String registrarse();
}