package logic;

/*
 * @author Alessandro Fazio Perez / Jose Alonso Alfaro Perez
 */
public class Activo {

    private String marca, modelo;
    private int codigo, precio;
    private Categoria categoria;

    public Activo(String marca, String modelo, int codigo, int precio, Categoria categoria) {
        this.marca = marca;
        this.modelo = modelo;
        this.codigo = codigo;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Activo() {
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
