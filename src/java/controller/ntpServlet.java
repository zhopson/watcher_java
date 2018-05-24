/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.NtpManager;

/**
 *
 * @author andrey-man
 */
@WebServlet(name = "ntpServlet", urlPatterns = {"/logs_ntp"})
public class ntpServlet extends HttpServlet {

    @EJB
    NtpManager ntpManager;
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
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ntpServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ntpServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String ip = request.getParameter("ip");
        String action = request.getParameter("action");
        StringBuffer sb = new StringBuffer();

        Integer n = -1;
        
        String uuid = UUID.randomUUID().toString();
        String hash=uuid;        
        //Query query = null;
        //query = em.createNativeQuery("select watcher.c_ntp_ip_call(?1,'111')").setParameter(1, ip);
        //query = em.createNativeQuery("select * from watcher.c_ntp_ip_call(?1,'111')").setParameter(1, ip);
        //n = query.getFirstResult();
        if ("logs".equals(action)) //логи команд по ip
        {
            n = ntpManager.Run_ntp_ip_call_logs(ip, hash);
        } else // текущий статус ip
        {
            n = ntpManager.Run_ntp_ip_call_status(ip, hash);
        }

        if (n == 0) {
            Query query = em.createNativeQuery("select * from watcher.temp_ntp_ip_log where ip=?1 and usr_zabbix=?2").setParameter(1, ip).setParameter(2, hash);
            List resultList = query.getResultList();
            boolean logsAdded = false;
            if (resultList.size() != 0) {
                for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                    Object[] next = (Object[]) iterator.next();
                    sb.append("[");
                    sb.append("\"" + next[0] + "\",");
                    sb.append("\"" + next[1] + "\",");
                    sb.append("\"" + next[2] + "\",");
                    sb.append("\"" + next[3] + "\",");
                    sb.append("\"" + next[4] + "\",");
                    sb.append("\"" + next[5] + "\",");
                    sb.append("\"" + next[6] + "\",");
                    sb.append("\"" + next[7] + "\",");
                    sb.append("\"" + next[8] + "\",");
                    sb.append("\"" + next[9] + "\",");
                    sb.append("\"" + next[10] + "\"");
                    sb.append("],");
                    logsAdded = true;
                }
                sb = sb.deleteCharAt(sb.length() - 1);
            }

            if (logsAdded) {
                n = ntpManager.del_ntp_ip_temp(hash);
                response.setContentType("application/json");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("{\"data\":[" + sb.toString() + "]}");
//                        response.getWriter().write("<dslam_ports_dslam>" + sb.toString() + "</dslam_ports_dslam><dslam_stat_dslam>"+sd.toString()+"</dslam_stat_dslam>");
            } else {
                //nothing to show
                response.setContentType("application/json");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("{\"data\":[]}");
                //response.getWriter().write("{\"error\":1111}");                
                //response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } else {
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("{\"data\":[]}");
            //response.setStatus(HttpServletResponse.SC_NO_CONTENT);

        }

        //processRequest(request, response);
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
