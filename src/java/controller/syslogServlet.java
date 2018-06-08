/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import session.SmotrHostsViewFacade;

/**
 *
 * @author andrey-man
 */
@WebServlet(name = "syslogServlet", urlPatterns = {"/syslog_get"})
public class syslogServlet extends HttpServlet {
    @EJB
    SmotrHostsViewFacade SmotrHostsViewFacade1;
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
//        List resultList=null;
//        String action = request.getParameter("action");
//        if (action.equals("init")) {
//            
//            resultList = em.createNamedQuery("SmotrHostsView.getGroups").getResultList();
//            if (resultList != null) request.setAttribute("list_gr_syslog", resultList);
//////////////////////////////////////////////////////////////////////////////
//            resultList = em.createNamedQuery("SmotrHostsView.getHosts").getResultList();
//            if (resultList != null) request.setAttribute("list_hst_syslog", resultList);
//            //processRequest(request, response);
//            request.getRequestDispatcher("/WEB-INF/views/syslog.jsp").forward(request, response);         
//        } else if (action.equals("find")) {
//            
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
//        request.setCharacterEncoding("text/html;charset=UTF-8");

            String sel_gr = null, st_d = null, en_d = null; 
            String sel_hst = null; String sel_lev = null; String msg = null;
            Date parsingDateS = null,parsingDateE = null;
            StringBuffer sb = new StringBuffer();
            Enumeration<String> params = request.getParameterNames();
            while (params.hasMoreElements()) {
                String param = params.nextElement();
                switch (param) {
                    case "s_dt_syslog":
                        st_d = request.getParameter(param);
                        break;
                    case "e_dt_syslog":
                        en_d = request.getParameter(param);
                        break;
                    case "sel_group_syslog":
                        sel_gr = request.getParameter(param);
                        break;
                    case "sel_host_syslog":
                        sel_hst = request.getParameter(param);
                        break;
                    case "sel_level_syslog":
                        sel_lev = request.getParameter(param);
                        break;
                    case "ed_msg_syslog":
                        msg = request.getParameter(param);
                        break;
                    default:
                        break;
                }
            }

            try {
                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                    st_d=st_d.replace('T', ' ');
                    en_d=en_d.replace('T', ' ');
                    parsingDateS = format1.parse(st_d);
                    parsingDateE = format1.parse(en_d);
                
                    List resultList=null;
                    String msg1;
                    if (msg.equals("")) msg1 = "%";
                    else msg1 = "%"+msg+"%";
                    
                if ("".equals(sel_hst)) {//выберите хост...
                    if ("000".equals(sel_gr)) {//выберите группу...
                        if ("000".equals(sel_lev)) {//выберите уровень...
                            resultList = em.createNamedQuery("LogsView.findByMsg")
                                            .setParameter("date1", parsingDateS)
                                            .setParameter("date2", parsingDateE)
                                            .setParameter("msg", msg1)
                                            .setMaxResults(10000)
                                            .getResultList();
                        } else {
                            resultList = em.createNamedQuery("LogsView.findByLev")
                                            .setParameter("date1", parsingDateS)
                                            .setParameter("date2", parsingDateE)
                                            .setParameter("msg", msg1)
                                            .setParameter("level", sel_lev)
                                            .setMaxResults(10000)
                                            .getResultList();
                        }
                    }
                    else {// по группе
                        if ("000".equals(sel_lev)) {//выберите уровень...
                            resultList = em.createNamedQuery("LogsView.findByGr")
                                            .setParameter("date1", parsingDateS)
                                            .setParameter("date2", parsingDateE)
                                            .setParameter("msg", msg1)
                                            .setParameter("gr_id", Long.parseLong(sel_gr))
                                            .setMaxResults(10000)
                                            .getResultList();
                        } else {
                            resultList = em.createNamedQuery("LogsView.findByGrNLev")
                                            .setParameter("level", sel_lev)
                                            .setParameter("date1", parsingDateS)
                                            .setParameter("date2", parsingDateE)
                                            .setParameter("msg", msg1)
                                            .setParameter("gr_id", Long.parseLong(sel_gr))
                                            .setMaxResults(10000)
                                            .getResultList();
                        }
                    }
                } else {// по хосту
                        if ("000".equals(sel_lev)) {//выберите уровень...
                            resultList = em.createNamedQuery("LogsView.findByHostNMsg")
                                            .setParameter("date1", parsingDateS)
                                            .setParameter("date2", parsingDateE)
                                            .setParameter("msg", msg1)
                                            .setParameter("host", sel_hst)
                                            .setMaxResults(10000)
                                            .getResultList();
                        } else {
                            resultList = em.createNamedQuery("LogsView.findByHostNLev")
                                            .setParameter("level", sel_lev)
                                            .setParameter("date1", parsingDateS)
                                            .setParameter("date2", parsingDateE)
                                            .setParameter("msg", msg1)
                                            .setParameter("host", sel_hst)
                                            .setMaxResults(10000)
                                            .getResultList();
                        }
                }
                    boolean logsAdded=false;
                    if (resultList.size() != 0) {
                            for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                                Object[] next = (Object[]) iterator.next();
//                                sb.append("<log_raw>");
//                                sb.append("<name>" + next[0] + "</name>");
//                                sb.append("<facility>" + next[1] + "</facility>");
//                                sb.append("<level>" + next[2] + "</level>");
//                                sb.append("<host>" + next[3] + "</host>");
//                                sb.append("<date>" + next[4] + "</date>");
//                                sb.append("<message>" + next[5] + "</message>");
//                                sb.append("</log_raw>");
                                
                                sb.append("[");
                                sb.append("\"" + next[0] + "\",");
                                sb.append("\"" +  next[1] + "\",");
                                sb.append("\"" +  next[2] + "\",");
                                sb.append("\"" +  next[3] + "\",");
                                sb.append("\"" +  next[4] + "\",");
                                sb.append("\"" +  next[5] + "\"");
                                sb.append("],");                                   
                                
                                logsAdded = true;
                            }
                            sb = sb.deleteCharAt(sb.length() - 1);
                    }
                    if (logsAdded) {
                        
                            response.setContentType("application/json");
                            response.setHeader("Cache-Control", "no-cache");
                            response.getWriter().write("{\"data\":[" + sb.toString() + "]}");                        
                        
//                        response.setContentType("text/xml");
//                        response.setHeader("Cache-Control", "no-cache");
//                        response.getWriter().write("<logs_syslog>" + sb.toString() + "</logs_syslog>");
                    } else {
                        //nothing to show
                        
                            response.setContentType("application/json");
                            response.setHeader("Cache-Control", "no-cache");
                            response.getWriter().write("{\"data\":[]}");                        
                        
//                        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    }
//                    request.setAttribute("list_dhcpo", resultList);
//                    request.setAttribute("ip_attr_dhcpo", ip);
//                    if (IsArch=="true") request.setAttribute("arch_attr_dhcpo", "Checked");
//                    else request.setAttribute("arch_attr_dhcpo", "unnChecked");

            } catch (Exception e) {
                e.printStackTrace();
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
