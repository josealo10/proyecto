/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Model;
import Model.ModelAdministrador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Bien;
import logic.Funcionario;
import logic.Solicitud;

/**
 *
 * @author jaalf
 */
@WebServlet(name = "ControllerAdministrador", urlPatterns = {"/bienes"})
public class ControllerAdministrador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ModelAdministrador model = (ModelAdministrador)request.getSession().getAttribute("model");
        
       
        
        if(request.getParameter("bien").equals("Ingresar bien")){
            
            if(request.getParameter("modelo").equals("") || request.getParameter("marca").equals("") ||
                    request.getParameter("cantidad").equals("") || request.getParameter("descripcion").equals("") ||
                    request.getParameter("precioUnitario").equals("")){
                //se devuelve
           
            }
            else{
                ArrayList<Bien> bienes = new ArrayList<>();
                Bien bien=new Bien();
                
                if(Model.isNumeric(request.getParameter("cantidad"))){
                    bien.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
                }
                if(Model.isNumeric(request.getParameter("precioUnitario"))){
                    bien.setPrecio(Integer.parseInt(request.getParameter("precioUnitario")));
                }
                bien.setDescripcion(request.getParameter("descripcion"));
                bien.setModelo(request.getParameter("modelo"));
                bien.setMarca(request.getParameter("marca"));
               
                model.getListaBienes().add(bien);
        
            
            
            
            }
                
        }
        else{
            if(!model.getListaBienes().isEmpty()){
                Solicitud solicitud = new Solicitud();
                try {
                    Funcionario funcionario =model.buscarFuncionario();
                    solicitud.setFuncionario(funcionario);
                    solicitud.setDependencia(funcionario.getDependencia());
                    solicitud.setEstado("Espera");
                    model.agregarSolicitud(solicitud);
                    model.setListaBienes(new ArrayList<Bien>());
                    model.crearListaSolicitud();
                } catch (Exception ex) {
                    Logger.getLogger(ControllerAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        request.getRequestDispatcher("administrador.jsp").forward( request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
