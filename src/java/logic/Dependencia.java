package logic;

/*
 * @author Alessandro Fazio Perez / Jose Alonso Alfaro Perez
 */
public class Dependencia {

    private String id, nombre;
    
    public Dependencia(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Dependencia() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
