/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.SmotrHostsViewFacade;
import session.SmotrCallersGroupsFacade;
import session.SmotrWhatsappGroupsFacade;
import utils.StrucUser;
//import session.SmotrCallerPeopleFacade;

/**
 *
 * @author andrey-man
 */
@WebServlet(name = "web_controller", urlPatterns = {"/alarms", "/alarms_bigping", "/alarms_bshpd", "/alarms_magports", "/dhcpo", "/dslam", "/dslam_s", "/notifications", "/notifications_r", "/notifications_a", "/ntp", "/syslog"})
public class web_controller extends HttpServlet {

    @EJB
    SmotrHostsViewFacade SmotrHostsViewFacade1;
    SmotrCallersGroupsFacade SmotrCallersGroupsFacade1;
    SmotrWhatsappGroupsFacade SmotrWhatsappGroupsFacade1;
    @PersistenceContext(unitName = "watcherPU")
    private EntityManager em;
    public StrucUser structUser;
    //private HttpSession sess = null;  

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

        //HttpSession session = request.getSession(false);
        if (getServletContext().getAttribute("session") != null) //sess = request.getSession(true);
        {

            Date createTime = (Date) getServletContext().getAttribute("createtime_session");
            Date now = new Date();

            long interval = now.getTime() - createTime.getTime(); // ms
            long SessionTimeout = 1800;//sess.getMaxInactiveInterval(); // getMaxInactiveInterval in seconds, configured in web.xml in section <session-timeout>

            if (interval > SessionTimeout * 1000) {
                getServletContext().removeAttribute("session");
                getServletContext().removeAttribute("createtime_session");
                getServletContext().removeAttribute("username");
            }
            else {
                
                boolean FlagDHCP = false,FlagSyslog = false,FlagWhatsapp = false,FlagAlarms = false,FlagNtp = false;
                structUser = StrucUser.getInstance();
                if (structUser.getDhcp_role() == 1) FlagDHCP = true;
                if (structUser.getSyslog_role() == 1) FlagSyslog = true;
                if (structUser.getWhatsapp_role() == 1) FlagWhatsapp = true;
                if (structUser.getAlarms_role() == 1) FlagAlarms = true;
                if (structUser.getNtp_role() == 1) FlagNtp = true;
                
                String login = (String)getServletContext().getAttribute("username");
                HttpSession oldsession = (HttpSession)getServletContext().getAttribute("session");
                String fio = (String)oldsession.getAttribute("fio");
                oldsession.invalidate();
                
                getServletContext().removeAttribute("session");
                getServletContext().removeAttribute("createtime_session");
                getServletContext().removeAttribute("username");
                
                HttpSession session = request.getSession(true);
                session.setAttribute("username",login);
                session.setAttribute("fio",fio);

                session.setAttribute("dhcp",FlagDHCP);
                session.setAttribute("sys",FlagSyslog);
                session.setAttribute("msg",FlagWhatsapp);
                session.setAttribute("alm",FlagAlarms);
                session.setAttribute("ntp",FlagNtp);
                
                getServletContext().setAttribute("session", session);
                getServletContext().setAttribute("username", login);
                getServletContext().setAttribute("createtime_session", now);
                
            }

        }
        
        //if (getServletContext().getAttribute("username") != null) {
        if (getServletContext().getAttribute("session") != null) {

            //getServletContext().setAttribute("session", session);
            String userPath = request.getServletPath();
            List resultList = null;
            if ("/notifications".equals(userPath) || "/notifications_r".equals(userPath) || "/notifications_a".equals(userPath)) {
                
                if (structUser.getWhatsapp_role() != 1) { response.sendRedirect("/watcher/"); return;  }

                resultList = em.createNamedQuery("SmotrCallersGroups.findAll").getResultList();
                if (resultList != null) {
                    request.setAttribute("list_call_gr_notif", resultList);
                }

                resultList = em.createNamedQuery("SmotrWhatsappGroups.findAllId").getResultList();
                if (resultList != null) {
                    request.setAttribute("list_telegram_gr_notif", resultList);
                }
                //request.setAttribute("session", session);
            } else if ("/syslog".equals(userPath)) {

                if (structUser.getSyslog_role() != 1) { response.sendRedirect("/watcher/"); return; }

                
                resultList = em.createNamedQuery("SmotrHostsView.getGroups").getResultList();
                if (resultList != null) {
                    request.setAttribute("list_gr_syslog", resultList);
                }
////////////////////////////////////////////////////////////////////////////
                resultList = em.createNamedQuery("SmotrHostsView.getHosts").getResultList();
                if (resultList != null) {
                    request.setAttribute("list_hst_syslog", resultList);
                }
                //TODO: обработка запроса
            } else if ("/dslam".equals(userPath) || "/dslam_s".equals(userPath)) {

                if (structUser.getSyslog_role() != 1) { response.sendRedirect("/watcher/");  return; }   
                
//            resultList = em.createNamedQuery("SmotrHostsView.getGroups").getResultList();
//            if (resultList != null) request.setAttribute("list_gr_syslog", resultList);
//////////////////////////////////////////////////////////////////////////////
//            resultList = em.createNamedQuery("SmotrHostsView.getHosts").getResultList();
//            if (resultList != null) request.setAttribute("list_hst_syslog", resultList);
            } else if ("/alarms".equals(userPath) || "/alarms_bigping".equals(userPath) || "/alarms_bshpd".equals(userPath) || "/alarms_magports".equals(userPath)) {
                
                if (structUser.getAlarms_role() != 1) { response.sendRedirect("/watcher/");  return; }  

            } else if ("/dhcpo".equals(userPath)) {
                
                if (structUser.getDhcp_role() != 1) { response.sendRedirect("/watcher/"); return; }
                
            } else if ("/ntp".equals(userPath)) {
                
                if (structUser.getNtp_role() != 1) { response.sendRedirect("/watcher/"); return; }   
                
            }
            //else response.sendRedirect("/watcher/");

            request.getRequestDispatcher("/WEB-INF/views" + userPath + ".jsp").forward(request, response);

        } else {
            response.sendRedirect("/watcher/login");
        }
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
