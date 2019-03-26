package logic;

/*
 * @author Alessandro Fazio Perez / Jose Alonso Alfaro Perez
 */
public class Funcionario {
    private String nombre, id;
    private Dependencia dependencia;
    private Usuario usuario;

    public Funcionario(String nombre, String id, Dependencia dependencia, Usuario usuario) {
        this.nombre = nombre;
        this.id = id;
        this.dependencia = dependencia;
        this.usuario = usuario;
    }

    public Funcionario() {
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

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    } 
}
