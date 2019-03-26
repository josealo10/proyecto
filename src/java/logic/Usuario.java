package logic;

/*
 * @author Alessandro Fazio Perez / Jose Alonso Alfaro Perez
 */
public class Usuario {
    private String id, clave, permiso;

    public Usuario(String id, String clave, String puesto) {
        this.id = id;
        this.clave = clave;
        this.permiso = puesto;
    }

    public Usuario() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String puesto) {
        this.permiso = puesto;
    }
}
