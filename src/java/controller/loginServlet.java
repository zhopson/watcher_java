/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author andrey-man
 */
@WebServlet(name = "loginServlet", urlPatterns = {"/login","/error"})
public class loginServlet extends HttpServlet {
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
        request.getRequestDispatcher("/WEB-INF/signin" + userPath + ".jsp").forward(request, response);

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

//        String userPath = request.getServletPath();
//        if ("/signin".equals(userPath)) {
//
//            String inputlogin = null, inputPassword = null;
//            Enumeration<String> params = request.getParameterNames();
//            while (params.hasMoreElements()) {
//                String param = params.nextElement();
//                switch (param) {
//                    case "inputlogin":
//                        inputlogin = request.getParameter(param);
//                        break;
//                    case "inputPassword":
//                        inputPassword = request.getParameter(param);
//                        break;
//                    default:
//                        break;
//                }
//            }
//            try {
//                Query query = null;
//                query = em.createNativeQuery("select * from port_info.tech_alarms_comments_view where comments=''");
//                List resultList = query.getResultList();
//                boolean logsAdded = false;
//                if (resultList.size() != 0) {
//                    for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
//                        Object[] next = (Object[]) iterator.next();
//
//                        String s = next[11].toString();
//
//                        logsAdded = true;
//                    }
//                    
//                }
//                if (logsAdded) {
//
////                    response.setContentType("application/json");
////                    response.setHeader("Cache-Control", "no-cache");
////                    response.getWriter().write("{\"status\":1}");
//
////                response.getWriter().write(
////                        "{\"status\" : "
////                        + 1
////                        + ",\"total_stat\" : "
////                        + res_stat[3]
////                        + ",\"acting_stat\" : "
////                        + res_stat[0]
////                        + ",\"acted_stat\" : "
////                        + res_stat[1]
////                        + ",\"deacted_stat\" : "
////                        + res_stat[2]
////                        + ",\"nobinds_stat\" : "
////                        + res_stat[4]
////                        + "}"
////                );                    
//                    
//
//                    HttpSession session = request.getSession(true);
//                    session.setAttribute("username",inputlogin);
////                    request.setAttribute("session", session);
//                    
//                    getServletContext().setAttribute("session", session);
//                    response.sendRedirect("/watcher/");
//                    //request.getRequestDispatcher("/").forward(request, response);
//                    //processRequest(request, response);
//                    
//                } else {
//                    //nothing to show
////                    request.setAttribute("error", 1);
////                    processRequest(request, response);
//                    response.setContentType("application/json");
//                    response.setHeader("Cache-Control", "no-cache");
//                    response.getWriter().write("{\"error\"}");
//                    //response.getWriter().write("{\"data\":[]}");
////                            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
//                }
//                
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

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
