/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.update.servlet;


import com.javafx.update.action.CheckUpdateAction;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckUpdateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        try {
            HashMap<String, Object> srcParams;
            ObjectInputStream ois = new ObjectInputStream(request.getInputStream());

            srcParams = (HashMap<String, Object>) ois.readObject();
            ois.close();

            CheckUpdateAction checkUpdateAction = new CheckUpdateAction();
            checkUpdateAction.setParams(srcParams);
            HashMap<String, String> resultMap = checkUpdateAction.process();
            
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(resultMap);
            oos.flush();
            oos.close();

        } catch (Exception ex) {
            ex.printStackTrace();

            HashMap<String, Object> jsonMap = new HashMap<String, Object>();
            jsonMap.put("actionResult", "failed");
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(jsonMap);
            oos.flush();
            oos.close();
        }
    }

     // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
