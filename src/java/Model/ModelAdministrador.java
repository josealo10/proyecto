/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import data.Dao;
import java.util.ArrayList;
import logic.Bien;
import logic.Funcionario;
import logic.Solicitud;
import logic.Usuario;

/**
 *
 * @author jaalf
 */
public class ModelAdministrador {
    Dao db;
    private Usuario usuario;
    ArrayList<Bien> listaBienes;
    ArrayList<Solicitud> listaSolicitudes;

    public ModelAdministrador() {
        usuario=null;
        db=new Dao();
        this.listaBienes=new ArrayList<Bien>();
        this.listaSolicitudes= new ArrayList<Solicitud>();;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Bien> getListaBienes() {
        return listaBienes;
    }

    public void setListaBienes(ArrayList<Bien> listaBienes) {
        this.listaBienes = listaBienes;
    }

    public ArrayList<Solicitud> getListaSolicitudes() {
        return listaSolicitudes;
    }

    public void setListaSolicitudes(ArrayList<Solicitud> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }
    
    public void crearListaSolicitud() throws Exception{
        this.listaSolicitudes=db.searchSolicitudes("funcionario",usuario.getId());
    }
    public void agregarSolicitud(Solicitud s) throws Exception{
        db.addSolicitud(s);
    }

    public Funcionario buscarFuncionario() throws Exception {
        return db.searchFuncionario(usuario.getId());
    }
}
