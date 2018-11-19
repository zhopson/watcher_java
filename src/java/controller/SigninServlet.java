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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.StrucUser;

/**
 *
 * @author andrey-man
 */
@WebServlet(name = "SigninServlet", urlPatterns = {"/signin","/logout"})
public class SigninServlet extends HttpServlet {
    @PersistenceContext(unitName = "watcherPU")
    private EntityManager em;    
    public StrucUser structUser;
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
        
        String userPath = request.getServletPath();
        if ("/logout".equals(userPath)) {
            
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
            
                HttpSession oldsession = (HttpSession)getServletContext().getAttribute("session");
                oldsession.invalidate();            
                
                getServletContext().removeAttribute("session");
                getServletContext().removeAttribute("createtime_session");
                getServletContext().removeAttribute("username");            
            }
            response.sendRedirect("/watcher/");
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
        
        String userPath = request.getServletPath();
        if ("/signin".equals(userPath)) {

            String inputlogin = null, inputPassword = null;
            Enumeration<String> params = request.getParameterNames();
            while (params.hasMoreElements()) {
                String param = params.nextElement();
                switch (param) {
                    case "inputlogin":
                        inputlogin = request.getParameter(param);
                        break;
                    case "inputPassword":
                        inputPassword = request.getParameter(param);
                        break;
                    default:
                        break;
                }
            }
            try {
                
                Query query = null;
                query = em.createNativeQuery("select '0' as id,alias as login,passwd as pass,'VPN' as service,right_user,search_rule,dhcp_role,syslog_role,vpn_role,raport_role,whatsapp_role,gpon_role,iptv_role,gpon_stend_role,alarms_role,export_role,mac_role,admin_role,ipvpn_role,NULL as syslog_list_group from watcher.smotr_roles_view where alias = ?1 and passwd = md5(?2)")
                                                .setParameter(1, inputlogin).setParameter(2, inputPassword);
                List resultList = query.getResultList();
                boolean logsAdded = false;
                
                boolean FlagDHCP = false,FlagSyslog = false,FlagWhatsapp = false,FlagAlarms = false,FlagNtp = false;
                if (resultList.size() != 0) { // есть юзер
                    
                    for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                        Object[] next = (Object[]) iterator.next();

                        //String id = next[0].toString();
                        String alias = next[1].toString();
                        String passwd = next[2].toString();
                        String service = next[3].toString();
                        String right_user = next[4].toString();
                        int search_rule = Integer.parseInt(next[5].toString());
                        int dhcp_role = Integer.parseInt(next[6].toString());
                        int syslog_role = Integer.parseInt(next[7].toString());
                        int vpn_role = Integer.parseInt(next[8].toString());
                        int raport_role = Integer.parseInt(next[9].toString());
                        int whatsapp_role = Integer.parseInt(next[10].toString());
                        int gpon_role = Integer.parseInt(next[11].toString());
                        int iptv_role = Integer.parseInt(next[12].toString());
                        int gpon_stend_role = Integer.parseInt(next[13].toString());
                        int alarms_role = Integer.parseInt(next[14].toString());
                        int export_role = Integer.parseInt(next[15].toString());
                        int ntp_role = Integer.parseInt(next[16].toString());
                        int admin_role = Integer.parseInt(next[17].toString());
                        int ipvpn_role = Integer.parseInt(next[18].toString());
                        //String syslog_list_group = next[19].toString();

                        structUser = new StrucUser(null,alias,passwd,service,right_user,search_rule,dhcp_role,syslog_role,vpn_role,raport_role,whatsapp_role,gpon_role,iptv_role,gpon_stend_role,alarms_role,export_role,ntp_role,admin_role,ipvpn_role);
                        logsAdded = true;
                    }

                }
                if (logsAdded) {
                    
                    query = null;
                    String fio = null;
                    query = em.createNativeQuery("select surname,name from public.users where alias= ?1").setParameter(1, inputlogin);
                    resultList = query.getResultList();
                    if (resultList.size() != 0) { 
                        Object[] obj = (Object[]) resultList.get(0);
                        fio = obj[0].toString() + " " + obj[1].toString();
                        if (fio != null) {
                            structUser.setFio(fio);
                        }
                    }
                    

//                    response.setContentType("application/json");
//                    response.setHeader("Cache-Control", "no-cache");
//                    response.getWriter().write("{\"status\":1}");

//                    HttpSession session = request.getSession(false);
//                    if (session!= null){
//                        session.invalidate();
//                    }

                    if (structUser.getDhcp_role() == 1) FlagDHCP = true;
                    if (structUser.getSyslog_role() == 1) FlagSyslog = true;
                    if (structUser.getWhatsapp_role() == 1) FlagWhatsapp = true;
                    if (structUser.getAlarms_role() == 1) FlagAlarms = true;
                    if (structUser.getNtp_role() == 1) FlagNtp = true;


                    HttpSession session = request.getSession(true);
                    session.setAttribute("username",inputlogin);
                    session.setAttribute("fio",fio);
                    session.setAttribute("dhcp",FlagDHCP);
                    session.setAttribute("sys",FlagSyslog);
                    session.setAttribute("msg",FlagWhatsapp);
                    session.setAttribute("alm",FlagAlarms);
                    session.setAttribute("ntp",FlagNtp);


                    
                    getServletContext().setAttribute("session", session);
                    Date createTime = new Date(session.getCreationTime());
                    getServletContext().setAttribute("createtime_session", createTime);
                    
                    
                    
                    getServletContext().setAttribute("username", inputlogin);
                    
                    response.sendRedirect("/watcher/index.jsp");
                    //request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
                    //processRequest(request, response);
                    
                } else {
                    //nothing to show
//                    response.setContentType("application/json");
//                    response.setHeader("Cache-Control", "no-cache");
//                    response.getWriter().write("{\"error\"}");
                    response.sendRedirect("/watcher/error");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        //processRequest(request, response);
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
