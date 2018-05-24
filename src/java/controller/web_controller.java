/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.SmotrHostsViewFacade;
import session.SmotrCallersGroupsFacade;
import session.SmotrWhatsappGroupsFacade;
//import session.SmotrCallerPeopleFacade;

/**
 *
 * @author andrey-man
 */
@WebServlet(name = "web_controller", urlPatterns = {"/alarms", "/dhcpo", "/dslam", "/dslam_s", "/notifications", "/notifications_r", "/notifications_a", "/ntp", "/syslog"})
public class web_controller extends HttpServlet {

    @EJB
    SmotrHostsViewFacade SmotrHostsViewFacade1;
    SmotrCallersGroupsFacade SmotrCallersGroupsFacade1;
    SmotrWhatsappGroupsFacade SmotrWhatsappGroupsFacade1;
    @PersistenceContext(unitName = "watcherPU")
    private EntityManager em;    
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
        String userPath = request.getServletPath();
        List resultList=null;
        if ("/notifications".equals(userPath)) {
           
            resultList = em.createNamedQuery("SmotrCallersGroups.findAll").getResultList();
            if (resultList != null) request.setAttribute("list_call_gr_notif", resultList);
            
            resultList = em.createNamedQuery("SmotrWhatsappGroups.findAllId").getResultList();
            if (resultList != null) request.setAttribute("list_telegram_gr_notif", resultList);
            
        } else if ("/syslog".equals(userPath)) {
            
            resultList = em.createNamedQuery("SmotrHostsView.getGroups").getResultList();
            if (resultList != null) request.setAttribute("list_gr_syslog", resultList);
////////////////////////////////////////////////////////////////////////////
            resultList = em.createNamedQuery("SmotrHostsView.getHosts").getResultList();
            if (resultList != null) request.setAttribute("list_hst_syslog", resultList);
            //TODO: обработка запроса
        } else if ("/dslam_s".equals(userPath)) {
            
//            resultList = em.createNamedQuery("SmotrHostsView.getGroups").getResultList();
//            if (resultList != null) request.setAttribute("list_gr_syslog", resultList);
//////////////////////////////////////////////////////////////////////////////
//            resultList = em.createNamedQuery("SmotrHostsView.getHosts").getResultList();
//            if (resultList != null) request.setAttribute("list_hst_syslog", resultList);
            
        }
        
        request.getRequestDispatcher("/WEB-INF/views" + userPath + ".jsp").forward(request, response);
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
