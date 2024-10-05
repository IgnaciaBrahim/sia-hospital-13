package hospital;
public abstract class Persona 
{
    private String rut;
    private String nombre;
    private String apellido;

    //default 
    public Persona() 
    {
        this.rut = null;
        this.nombre = null;
        this.apellido = null;
    }

    public Persona(String rut, String nombre, String apellido){
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    //Abstracción
    public String obtenerDatos()
    {
        return getNombre() + " " + getApellido();
    }
    public void actualizarDatos(String rut, String nombre, String apellido)
    {
        setNombre(nombre);
        setRut(rut);
        setApellido(apellido);
    }
    
    public abstract Object verAsignacion();
    public abstract void registrarse();
    public abstract void salirHospital();
    public abstract void alta();
    
}