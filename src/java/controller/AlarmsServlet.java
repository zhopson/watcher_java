/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import entity.SmotrNtpWhatsapp;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
import session.AlarmsManager;
/**
 *
 * @author andrey-man
 */
@WebServlet(name = "AlarmsServlet", urlPatterns = {"/alarms_get"})
public class AlarmsServlet extends HttpServlet {

    @PersistenceContext(unitName = "watcherPU")
    private EntityManager em;
    @EJB
    AlarmsManager Alarmsmgr;
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AlarmsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AlarmsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String host = null, action = null, comment_alarms = null, ids_str_alarms = null;
        boolean new_alarms_only = false;
        StringBuffer sb = new StringBuffer();

        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            switch (param) {
                case "action":
                    action = request.getParameter(param);
                    break;
                case "no_comments":
                    new_alarms_only = Boolean.parseBoolean(request.getParameter(param));
                    break;
                case "comment_alarms":
                    comment_alarms = request.getParameter(param);
                    break;
                case "ids_str_alarms":
                    ids_str_alarms = request.getParameter(param);
                    break;
                default:
                    break;
            }
        }
        try {

            Query query = null;

            em.setProperty("prepareThreshold", 0);
            em.setProperty("prepared_statements", false);

            if ("list".equals(action)) {

                if (new_alarms_only == true) {
                    query = em.createNativeQuery("select * from port_info.tech_alarms_comments_view where comments=''");
                } else {
                    query = em.createNativeQuery("select * from port_info.tech_alarms_comments_view");
                }

                List resultList1 = query.getResultList();
                boolean logsAdded = false;
                if (resultList1.size() != 0) {
                    for (Iterator iterator = resultList1.iterator(); iterator.hasNext();) {
                        Object[] next = (Object[]) iterator.next();

                        String s = next[11].toString();

                        sb.append("[");
                        sb.append("\"<input type=\\\"checkbox\\\" id=\\\"" + next[1] + "\\\" onclick=\\\"SelectAlarms(" + next[1] + ",this.checked," + next[8] + "," + next[9] + "," + next[10] + ")\\\" style=\\\"margin-left:8px\\\">\",");
                        sb.append("\"" + next[0] + "\",");
                        sb.append("\"" + next[2] + "\",");
                        sb.append("\"" + next[3] + "\",");
                        sb.append("\"" + next[4] + "\",");
//                            sb.append("\"" + next[5] + "\",");
                        sb.append("\"<a href=\\\"#\\\" data-toggle=\\\"tooltip\\\" title=\\\"Кол-во портов: " + next[8] + ", число инет: " + next[9] + ", Число IPTV: " + next[10] + "\\\">" + next[5] + "</a>\",");
                        sb.append("\"" + next[6] + "\",");
                        sb.append("\"" + next[7] + "\",");
                        sb.append("\"" + next[8] + "\",");
                        sb.append("\"" + next[9] + "\",");
                        sb.append("\"" + next[10] + "\",");
                        sb.append("\"" + next[1] + "\",");
                        sb.append("\"" + s.replaceAll("\r|\n|\r\n", " ") + "\"");
                        sb.append("],");

//                                sb.append("<dslam_ports_adr_raw>");
//                                sb.append("<adres>" + next[0] + "</adres>");
//                                sb.append("<host_name>" + next[1] + "</host_name>");
//                                sb.append("<host>" + next[2] + "</host>");
//                                sb.append("<port>" + next[3] + "</port>");
//                                sb.append("<status>" + next[4] + "</status>");
//                                sb.append("<ip_line>" + next[5] + "</ip_line>");
//                                sb.append("<profile_line>" + next[6] + "</profile_line>");
//                                sb.append("<profile_iptv>" + next[7] + "</profile_iptv>");
//                                sb.append("<vlan_line>" + next[8] + "</vlan_line>");
//                                sb.append("<pvc_line>" + next[9] + "</pvc_line>");
//                                sb.append("<vlan_iptv>" + next[10] + "</vlan_iptv>");
//                                sb.append("<pvc_iptv>" + next[11] + "</pvc_iptv>");
//                                sb.append("<ed_snr>" + next[12] + "</ed_snr>");
//                                sb.append("<eda>" + next[13] + "</eda>");
//                                sb.append("<eusnr>" + next[14] + "</eusnr>");
//                                sb.append("<eua>" + next[15] + "</eua>");
//                                sb.append("<date>" + next[16] + "</date>");
//                                sb.append("</dslam_ports_adr_raw>");
                        logsAdded = true;
                    }
                    sb = sb.deleteCharAt(sb.length() - 1);
                }
                if (logsAdded) {

                    response.setContentType("application/json");
                    response.setHeader("Cache-Control", "no-cache");
                    response.getWriter().write("{\"data\":[" + sb.toString() + "]}");
//                            response.setContentType("text/xml");
//                            response.setHeader("Cache-Control", "no-cache");
//                            response.getWriter().write("<dslam_ports_adr_dslam>" + sb.toString() + "</dslam_ports_adr_dslam>");
                } else {
                    //nothing to show
                    response.setContentType("application/json");
                    response.setHeader("Cache-Control", "no-cache");
                    response.getWriter().write("{\"data\":[]}");
//                            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                }
            } else if ("add_comment".equals(action)) {

                List arr_ids = new ArrayList(); //Массив
                arr_ids.addAll(Arrays.asList(ids_str_alarms.split(","))); //Формирование массива из строки - список id, разделенных запятыми
                if (arr_ids.size() != 0) {
                    Integer codeOperation = 0;
                    boolean flagOperation = false;
                    for (Iterator iterator = arr_ids.iterator(); iterator.hasNext();) {//Цикл по списку ids
                        String next = (String) iterator.next();
                        //next
                        codeOperation = Alarmsmgr.AddComment(next,comment_alarms);
                        if (codeOperation != 0) flagOperation = true; // fail add comment
                    }
                    
                    if (flagOperation)
                         request.setAttribute("code_op_add_comment_alarm", "Ошибка БД, комментарий не добавлен"); 
                    else
                         request.setAttribute("code_op_add_comment_alarm", "Операция успешна"); 
                    
                }


            }

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
