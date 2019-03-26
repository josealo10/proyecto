/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import data.Dao;
import logic.Usuario;

/**
 *
 * @author jaalf
 */
public class ModelInicio {
    Dao db;

    public ModelInicio() {
        db = new Dao();
    }
    
    public boolean UsuarioEncontrado(String usuario,String clave) throws Exception{
        return db.isSearchUsuario(usuario,clave);
    }

    public Usuario crearUsuario(String usuario) throws Exception {
        return db.searchUsuario(usuario);
    }
    
    
}
