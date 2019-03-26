<%-- 
    Document   : administrador
    Created on : Mar 22, 2019, 2:42:00 PM
    Author     : jaalf
--%>

<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="logic.Bien"%>
<%@page import="logic.Solicitud"%>
<%@page import="Model.ModelAdministrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" title="style"
            type="text/css" href="styleAdministrador.css">
        <title>Administrador</title>
    </head>
    <body>
        <% ModelAdministrador model = (ModelAdministrador)session.getAttribute("model"); %>
        <% ArrayList<Bien> bienes = model.getListaBienes(); %>
        <form method="post" action="bienes">
        <table id="tablaG">
            <tr>
                <td rowspan="6" class="top">
                   
                    <table id="tabla">
                        <tr class="center">
                            <td>Codigo</td>
                            <td>Fecha</td>
                            <td>Estado</td>
                        </tr>
                        <% if(model.getListaSolicitudes()!=null){ %>
                        <% for( Solicitud s : model.getListaSolicitudes()){ %>
                        <tr class="center">
                            <td>
                                <%=s.getCodigo()%>
                            </td>
                            <td>
                                <% Date d = s.getFecha(); %>
                                <%= d.getDate()%>
                                /
                                <%= d.getMonth() %>
                                /
                                <%= d.getYear() %>
                            </td>
                            <td>
                                <%=s.getEstado()%>
                            </td>
                            
                        </tr>
                        <% } %>
                        <% } %>
                        

                    </table>
                    
                    
                    
                    
                    
                    
                </td>

           
                
               
       
                <td class="right">
                    Marca:
                </td>
                <td>
                    
                    <input type="text" name="marca">
                    
                </td>
                <td class="right">
                    Modelo:
                </td>
                <td>
                    <input type="text" name="modelo">
                </td>
            </tr>
            <tr>
                <td class="right">
                    Cantidad:
                </td>
                <td>
                    <input type="text" name="cantidad">
                </td>
            
                <td class="right">
                    Descripcion:
                </td>
                <td>
                    <input type="text" name="descripcion">
                </td>
            </tr>
            <tr>
                <td class="right">
                    Precio Unitario:
                </td>
                <td>
                    <input type="text" name="precioUnitario">
                </td>
            </tr>
            <tr>
                <td colspan="4" class="submit">                   
                    <input type="submit" value="Ingresar bien" name="bien">
                </td>
            
            </tr>
            
            <tr >
                <td colspan="4" id="bienes" class="top">   
                    
                
                    
                    
                    <div id="scroll">
                    <table id="tabla">
                        <tr class="center">
                            <td>Descripcion</td> 
                            <td>Marca</td> 
                            <td>Modelo</td> 
                            <td>Cantidad</td> 
                            <td>Precio Unitario</td>
                        </tr>
                        <% if(bienes != null){ %>
                        <%for(Bien b: model.getListaBienes()){%>
                        <tr class="center">
                            <td><%= b.getDescripcion()%></td> 
                            <td><%= b.getMarca() %></td> 
                            <td><%= b.getModelo() %></td> 
                            <td><%= b.getCantidad() %></td> 
                            <td><%= b.getPrecio() %></td>
                        </tr>
                        <%}%>
                        <%}%>
                        

                    </table>
                    </div>
                    
                    
                    
                    
                    
                    
                </td>
            </tr>
            <tr>
                <td colspan="4" class="submit">
                    <input type="submit" value="Ingresar Solicitud" name="bien">
                </td>
            </tr>
            
            
        </table>
        </form> 
                                   
    </body>
</html>
