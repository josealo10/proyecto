package data;

import java.sql.ResultSet;
import java.util.ArrayList;
import logic.Activo;
import logic.Bien;
import logic.Categoria;
import logic.Dependencia;
import logic.Funcionario;
import logic.Solicitud;
import logic.Usuario;

/*
 * @author Alessandro Fazio Pérez / Jose Alonso Alfaro Perez
 */
public class Dao {

    private RelDataBase db;

    public Dao() {
        db = new RelDataBase();
    }

    public void addUsuario(Usuario u) throws Exception {
        String sql = "insert into Usuario (id, clave, permiso) "
                + "values('%s', '%s', '%s')";
        sql = String.format(sql, u.getId(), u.getClave(), u.getPermiso());

        if (db.executeUpdate(sql) == 0) {
            throw new Exception("Usuario ya existe");
        }
    }

    public void addBien(Bien b) throws Exception {
        String sql = "insert into Bien (marca, modelo, precio, tipo, descripcion, solicitud, cantidad) "
                + "values('%s', '%s', %d, '%s', '%s', %d, %d)";
        sql = String.format(sql, b.getMarca(), b.getModelo(), b.getPrecio(), b.getTipo(), b.getDescripcion(), b.getSolicitud().getCodigo(), b.getCantidad());

        if (db.executeUpdate(sql) == 0) {
            throw new Exception("Bien ya existe");
        }
    }

    public void addDependencia(Dependencia d) throws Exception {
        String sql = "insert into Dependencia (id, nombre) "
                + "values('%s', '%s')";
        sql = String.format(sql, d.getId(), d.getNombre());

        if (db.executeUpdate(sql) == 0) {
            throw new Exception("Dependencia ya existe");
        }
    }

    public void addSolicitud(Solicitud s) throws Exception {
        String sql = "insert into Solicitud (funcionario, dependencia, estado) values('%s', '%s', '%s')";
        sql = String.format(sql, s.getFuncionario().getId(), s.getDependencia().getId(), s.getEstado());

        if (db.executeUpdate(sql) == 0) {
            throw new Exception("Solicitud ya existe");
        }
    }

    public void addActivo(Activo a) throws Exception {
        String sql = "insert into Activo (marca, modelo, precio, categoria) "
                + "values('%s', '%s', %d, '%s')";
        sql = String.format(sql, a.getMarca(), a.getModelo(), a.getPrecio(), a.getCategoria().getNombre());

        if (db.executeUpdate(sql) == 0) {
            throw new Exception("Activo ya existe");
        }
    }

    public void addFuncionario(Funcionario f) throws Exception {
        String sql = "insert into Funcionario (id, nombre, puesto, dependencia) "
                + "values('%s', '%s', '%s', '%s')";
        sql = String.format(sql, f.getId(), f.getNombre(), f.getUsuario().getId(), f.getDependencia().getId());

        if (db.executeUpdate(sql) == 0) {
            throw new Exception("Funcionario ya existe");
        }
    }

    public void addCategoria(Categoria c) throws Exception {
        String sql = "insert into Categoria (nombre) values('%s')";
        sql = String.format(sql, c.getNombre());

        if (db.executeUpdate(sql) == 0) {
            throw new Exception("Categoria ya existe");
        }
    }

    public void deleteSolicitud(int codigo) throws Exception {
        String sql = "delete from Solicitud where numero = %d";
        sql = String.format(sql, codigo);

        if (db.executeUpdate(sql) == 0) {
            throw new Exception("No se pudo borrar");
        }
    }

    public void deleteBien(int codigo) throws Exception {
        String sql = "delete from Bien where solicitud = %d";
        sql = String.format(sql, codigo);

        if (db.executeUpdate(sql) == 0) {
            throw new Exception("No se pudo borrar");
        }
    }

    public Usuario searchUsuario(String id) throws Exception {
        String sql = "select * from Usuario u where u.id = '%s'";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return new Usuario(rs.getString("id"), rs.getString("clave"), rs.getString("permiso"));
        } else {
            throw new Exception("Usuario no existe");
        }
    }

    public boolean isSearchUsuario(String id,String clave) throws Exception {
        String sql = "select * from Usuario u where u.id = '%s' and u.clave='%s'";
        sql = String.format(sql, id,clave);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public Dependencia searchDependencia(String id) throws Exception {
        String sql = "select * from Dependencia where id = '%s'";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return new Dependencia(rs.getString("id"), rs.getString("nombre"));
        } else {
            throw new Exception("Dependencia no existe");
        }
    }

    public Categoria searchCategoria(String nombre) throws Exception {
        String sql = "select * from Categoria where nombre = '%s'";
        sql = String.format(sql, nombre);
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return new Categoria(rs.getString("nombre"));
        } else {
            throw new Exception("Categoria no existe");
        }
    }

    public Bien searchBien(int codigo) throws Exception {
        String sql = "select * from Bien where codigo = %d";
        sql = String.format(sql, codigo);
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return new Bien(rs.getString("marca"), rs.getString("modelo"), rs.getString("tipo"), rs.getString("descripcion"),
                    rs.getInt("codigo"), rs.getInt("cantidad"), rs.getInt("precio"), this.searchSolicitud(rs.getInt("solicitud")));
        } else {
            throw new Exception("Categoria no existe");
        }
    }

    public Funcionario searchFuncionario(String id) throws Exception {
        String sql = "select * from Funcionario where id = '%s'";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return new Funcionario(rs.getString("nombre"), rs.getString("id"),
                    this.searchDependencia(rs.getString("dependencia")), this.searchUsuario(rs.getString("puesto")));
        } else {
            return null;
        }
    }

    public Solicitud searchSolicitud(int codigo) throws Exception {
        String sql = "select * from Solicitud where numero = %d";
        sql = String.format(sql, codigo);
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return new Solicitud(rs.getInt("numero"), rs.getDate("fecha"), this.searchFuncionario(rs.getString("funcionario")),
                    this.searchFuncionario(rs.getString("registrador")), this.searchDependencia(rs.getString("dependencia")), rs.getString("estado"));

        } else {
            throw new Exception("Solicitud no existe");
        }
    }

    public ArrayList<Funcionario> searchAllRegistradores() throws Exception {
        String sql = "select * from Funcionario";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Funcionario> registradores = new ArrayList<>();

        while (rs.next()) {
            if (this.searchUsuario(rs.getString("id")).getPermiso().equals("Registrador")) {
                registradores.add(new Funcionario(rs.getString("nombre"), rs.getString("id"),
                        this.searchDependencia(rs.getString("dependencia")), this.searchUsuario(rs.getString("puesto"))));
            }
        }

        if (registradores.isEmpty()) {
            throw new Exception("No existen registradores");
        }

        return registradores;
    }

    public ArrayList<Funcionario> searchAllFuncionarios() throws Exception {
        String sql = "select * from Funcionario";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        while (rs.next()) {
            funcionarios.add(new Funcionario(rs.getString("nombre"), rs.getString("id"), this.searchDependencia(rs.getString("dependencia")), this.searchUsuario(rs.getString("puesto"))));
        }

        if (funcionarios.isEmpty()) {
            throw new Exception("No existen funcionarios");
        }

        return funcionarios;
    }

    public ArrayList<Activo> searchAllActivo() throws Exception {
        String sql = "select * from Activo";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Activo> activos = new ArrayList<>();

        while (rs.next()) {
            activos.add(new Activo(rs.getString("marca"), rs.getString("modelo"), rs.getInt("codigo"), rs.getInt("precio"), this.searchCategoria(rs.getString("categoria"))));
        }

        if (activos.isEmpty()) {
            throw new Exception("No existen activos");
        }

        return activos;
    }

    public ArrayList<Funcionario> searchAllFuncionariosDependencia(String id) throws Exception {
        String sql = "select * from Funcionario where dependencia = '%s'";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        while (rs.next()) {
            funcionarios.add(new Funcionario(rs.getString("nombre"), rs.getString("id"), this.searchDependencia(rs.getString("dependencia")), this.searchUsuario(rs.getString("puesto"))));
        }

        if (funcionarios.isEmpty()) {
            throw new Exception("No existen funcionarios");
        }

        return funcionarios;
    }

    public ArrayList<Dependencia> searchAllDependencias() throws Exception {
        String sql = "select * from Dependencia";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Dependencia> dependencias = new ArrayList<>();

        while (rs.next()) {
            dependencias.add(new Dependencia(rs.getString("id"), rs.getString("nombre")));
        }

        return dependencias;
    }

    public ArrayList<Categoria> searchAllCategorias() throws Exception {
        String sql = "select * from Categoria";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Categoria> categorias = new ArrayList<>();

        while (rs.next()) {
            categorias.add(new Categoria(rs.getString("nombre")));
        }
        
        if (categorias.isEmpty()){
            throw new Exception("No existen categorias");
        }

        return categorias;
    }

    public ArrayList<Solicitud> searchSolicitudes(String objeto, String condicion) throws Exception {
        String sql = "select * from Solicitud where %s = '%s'";
        sql = String.format(sql, objeto, condicion);
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Solicitud> solicitudes = new ArrayList<>();

        while (rs.next()) {
            Solicitud s = new Solicitud(rs.getInt("numero"), rs.getDate("fecha"), this.searchFuncionario(rs.getString("funcionario")),
                    this.searchFuncionario(rs.getString("registrador")), this.searchDependencia(rs.getString("dependencia")), rs.getString("estado"));
            solicitudes.add(s);
        }

        if (solicitudes.isEmpty()) {
            return null;
        }

        return solicitudes;
    }

    public ArrayList<Solicitud> searchSolicitudesAprobadas() throws Exception {
        String sql = "select * from Solicitud where estado = 'Aprobada'";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Solicitud> solicitudes = new ArrayList<>();

        while (rs.next()) {
            Solicitud s = new Solicitud(rs.getInt("numero"), rs.getDate("fecha"), this.searchFuncionario(rs.getString("funcionario")),
                    this.searchFuncionario(rs.getString("registrador")), this.searchDependencia(rs.getString("dependencia")), rs.getString("estado"));
            solicitudes.add(s);
        }

        if (solicitudes.isEmpty()) {
            throw new Exception("No existen solicitudes aprobadas");
        }

        return solicitudes;
    }

    public ArrayList<Bien> searchBienes(int codigo) throws Exception {
        String sql = "select * from Bien where solicitud = %d";
        sql = String.format(sql, codigo);
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Bien> bienes = new ArrayList<>();

        while (rs.next()) {
            Bien b = new Bien(rs.getString("marca"), rs.getString("modelo"), rs.getString("tipo"), rs.getString("descripcion"),
                    rs.getInt("codigo"), rs.getInt("cantidad"), rs.getInt("precio"), this.searchSolicitud(rs.getInt("solicitud")));
            bienes.add(b);
        }

        if (bienes.isEmpty()) {
            throw new Exception("No existen bienes");
        }

        return bienes;
    }

    public ArrayList<Solicitud> searchAllSolicitudes() throws Exception {
        String sql = "select * from Solicitud";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Solicitud> solicitudes = new ArrayList<>();

        while (rs.next()) {
            Solicitud s = new Solicitud(rs.getInt("numero"), rs.getDate("fecha"), this.searchFuncionario(rs.getString("funcionario")),
                    this.searchFuncionario(rs.getString("registrador")), this.searchDependencia(rs.getString("dependencia")), rs.getString("estado"));
            solicitudes.add(s);
        }

        if (solicitudes.isEmpty()) {
            throw new Exception("No existen solicitudes");
        }

        return solicitudes;
    }

    public void setEstadoSolicitud(int codigo, String estado) throws Exception {
        String sql = "update Solicitud set estado = '%s' where numero = %d";
        sql = String.format(sql, estado, codigo);

        if (db.executeUpdate(sql) == 0) {
            throw new Exception("No se puede actualizar");
        }

    }

    public int ultimaSolicitud() throws Exception {
        String sql = "select numero from Solicitud where numero = (select max(numero) from Solicitud)";
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return rs.getInt("numero");
        } else {
            return 0;
        }
    }

    public void setRegistrador(int codigo, String registrador) throws Exception {
        String sql = "update Solicitud set registrador = '%s' where numero = %d";
        sql = String.format(sql, registrador, codigo);

        if (db.executeUpdate(sql) == 0) {
            throw new Exception("No se pudo asignar registrador");
        }
    }


}
