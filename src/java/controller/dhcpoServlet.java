/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
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
import session.SmotrArchiveLogsOptinetViewFacade;
import session.SmotrLogsOptinetViewFacade;
import entity.SmotrLogsOptinetView;
import entity.SmotrArchiveLogsOptinetView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author andrey-man
 */
@WebServlet(name = "dhcpoServlet", urlPatterns = {"/dhcpo_get"})
public class dhcpoServlet extends HttpServlet {

    @EJB
    //SmotrIpvpnFacade SmotrIpvpnFacade1;
    SmotrLogsOptinetViewFacade SmotrLogsOptinetViewFacade1;
    SmotrArchiveLogsOptinetViewFacade SmotrArchiveLogsOptinetViewFacade1; 
    @PersistenceContext(unitName = "watcherPU")
    private EntityManager em;
//    private ServletContext context;
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        this.context = config.getServletContext();//To change body of generated methods, choose Tools | Templates.
//    }

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
        
            String ip = null, st_d = null, en_d = null;
            String IsArch = null;
            Date parsingDateS = null,parsingDateE = null;
            StringBuffer sb = new StringBuffer();
            Enumeration<String> params = request.getParameterNames();
            while (params.hasMoreElements()) {
                String param = params.nextElement();
                switch (param) {
                    case "ip_dhcpo":
                        ip = request.getParameter(param);
                        break;
                    case "chk_arch_dhcpo":
                        IsArch = request.getParameter(param);
                        break;
                    case "s_dt_dhcpo":
                        st_d = request.getParameter(param);
                        break;
                    case "e_dt_dhcpo":
                        en_d = request.getParameter(param);
                        break;
                    default:
                        break;
                }
            }
            try {
                if ( (ip != null && !"".equals(ip)) || (st_d != null && !"".equals(st_d)&&en_d != null && !"".equals(en_d)) ) {
                    
                    String table;
                    if ("false".equals(IsArch)) table = "SmotrLogsOptinetView";
                    else table = "SmotrArchiveLogsOptinetView";
                    
                    List resultLM=null;
                    String mac_in=null;
                    String ip_in=null;
                    Pattern p = Pattern.compile("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");
                    Matcher m = p.matcher(ip);
                    if (ip != "") {
                        if (m.matches()) { // ip // ip
                            resultLM = em.createNamedQuery(table + ".findMACByIpUser").setParameter(1, ip).getResultList();
                            if (resultLM.size() != 0) {
                                mac_in = (String) resultLM.get(0);
                            } else {
                                mac_in = " ";
                            }
                            ip_in = ip;
                        } else { // mac
                            resultLM = em.createNamedQuery(table + ".findIPByMacUser").setParameter("macUser", ip).getResultList();
//                        resultLM = (String)em.createNamedQuery(table+".findIPByMacUser").setParameter("macUser", ip).setMaxResults(1).getResultList().get(0);
                            if (resultLM.size() != 0) {//resultList.size() != 0
                                ip_in = (String) resultLM.get(0);
                            } else {
                                ip_in = "1.1.1.1";
                            }
                            mac_in = ip;
                        }
                    }
                    else {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_dhcpo>empty_ip</errors_dhcpo>");
                        return;
                    }
                    List resultList=null;
                    if (ip != null && !"".equals(ip) && st_d != null && !"".equals(st_d)) {
                       SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                       String date_s = st_d+" 00:00:00";
                       String date_e = en_d+" 23:59:59";
                       parsingDateS = format1.parse(date_s);
                       parsingDateE = format1.parse(date_e);
                       resultList = em.createNamedQuery(table+".findByIPnTimeInterval").setParameter(1, ip_in).setParameter(2, mac_in).setParameter(3, parsingDateS).setParameter(4, parsingDateE).getResultList();
                    }
                    else if (ip != null && !"".equals(ip) && (st_d == null || "".equals(st_d))) { 
//                        request.setAttribute("ip_attr_dhcpo", ip);
                          resultList = em.createNamedQuery(table+".findByIpUser").setParameter(1, ip_in).setParameter(2, mac_in).getResultList();
                    }
                    else if ((ip == null || "".equals(ip)) && (st_d != null && !"".equals(st_d))) { 
                       SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                       String date_s = st_d+" 00:00:00";
                       String date_e = en_d+" 23:59:59";
                       parsingDateS = format1.parse(date_s);
                       parsingDateE = format1.parse(date_e);
                       resultList = em.createNamedQuery(table+".findByTimeInterval").setParameter("time1", parsingDateS).setParameter("time2", parsingDateE).getResultList();
                    }
                    boolean logsAdded=false;
                    if (resultList != null) {
                        if ("false".equals(IsArch)) {
                            for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                                SmotrLogsOptinetView next = (SmotrLogsOptinetView) iterator.next();
                                sb.append("<log_raw>");
                                sb.append("<time>" + next.getTime() + "</time>");
                                sb.append("<header>" + next.getHeader() + "</header>");
                                sb.append("<message>" + next.getMessage() + "</message>");
                                sb.append("<server>" + next.getServer() + "</server>");
                                sb.append("</log_raw>");
                                logsAdded = true;
                            }
                        } else {
                            for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                                SmotrArchiveLogsOptinetView next = (SmotrArchiveLogsOptinetView) iterator.next();
                                sb.append("<log_raw>");
                                sb.append("<time>" + next.getTime() + "</time>");
                                sb.append("<header>" + next.getHeader() + "</header>");
                                sb.append("<message>" + next.getMessage() + "</message>");
                                sb.append("<server>" + next.getServer() + "</server>");
                                sb.append("</log_raw>");
                                logsAdded = true;
                            }
                        }
                    }
                    if (logsAdded) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<logs_dhcpo>" + sb.toString() + "</logs_dhcpo>");
                    } else {
                        //nothing to show
                        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    }
//                    request.setAttribute("list_dhcpo", resultList);
//                    request.setAttribute("ip_attr_dhcpo", ip);
//                    if (IsArch=="true") request.setAttribute("arch_attr_dhcpo", "Checked");
//                    else request.setAttribute("arch_attr_dhcpo", "unnChecked");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
//        processRequest(request, response);
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
