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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.CallersManager;
import java.util.UUID;
import utils.StrucUser;

/**
 *
 * @author andrey-man
 */
@WebServlet(name = "notifServlet", urlPatterns = {"/notif_abon_call_get","/notif_tlg_get"})
public class notifServlet extends HttpServlet {
    @EJB
    CallersManager callerManager;
//    @EJB
//    SmotrCallersFacade SmotrCallersFacade1;
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String grId = request.getParameter("gr_id");
        StringBuffer sb = new StringBuffer(); 
        
        em.setProperty("prepareThreshold", 0);
        em.setProperty("prepared_statements", false);                

        if (action.equals("abon_list")) {
            try {
                List resultList=null;
                Long GrId = Long.parseLong(grId);
                boolean namesAdded = false;
                
                if (GrId==0) {
                    resultList = em.createNamedQuery("SmotrCallerPeople.findByGr0").getResultList();
                    for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                        Object[] next = (Object[]) iterator.next();
                        sb.append("<abon_call_raw>");
                        sb.append("<fio>" + next[0] + "</fio>");
                        sb.append("<post>" + next[2] + "</post>");
                        sb.append("<tel>" + next[1] + "</tel>");
                        sb.append("<gr_id></gr_id>");
                        sb.append("<call>" + next[3] + "</call>");
                        sb.append("<id></id>");
                        sb.append("</abon_call_raw>");
                        namesAdded = true;
                    }
                }
                else {
                    resultList = em.createNamedQuery("SmotrCallerPeople.findByGroupid").setParameter("groupid", GrId).getResultList();
                    for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                        Object[] next = (Object[]) iterator.next();
                        sb.append("<abon_call_raw>");
                        sb.append("<fio>" + next[1] + "</fio>");
                        sb.append("<post>" + next[3] + "</post>");
                        sb.append("<tel>" + next[2] + "</tel>");
                        sb.append("<gr_id>" + next[5] + "</gr_id>");
                        sb.append("<call>" + next[4] + "</call>");
                        sb.append("<id>" + next[0] + "</id>");
                        sb.append("</abon_call_raw>");
                        namesAdded = true;
                    }
                }
                if (namesAdded) {
                    response.setContentType("text/xml");
                    response.setHeader("Cache-Control", "no-cache");
                    response.getWriter().write("<abon_calls_notif>" + sb.toString() + "</abon_calls_notif>");
                } else {
                    //nothing to show
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (action.equals("set_abon_call")) {
            String Phone = request.getParameter("phone");
            String Flag = request.getParameter("on");
            boolean bFlag=Boolean.parseBoolean(Flag);
            Integer codeOperation=callerManager.SetAbonCallFlag(bFlag,Phone);
            if (codeOperation != 0) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<set_abon_call_notif>" + codeOperation + "</set_abon_call_notif>");
            } else {
                //nothing to show
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }         
//            if (codeOperation!=0)
//                { request.setAttribute("code_op_set_abon_call_notif", "Код завершения операции: "+codeOperation); }
//            else
//                { request.setAttribute("code_op_set_abon_call_notif", "Операция успешна"); }          
        }
        else if (action.equals("set_all_abon_by_gr_call")) {
            String PhoneList = request.getParameter("phonelist");
            String Flag = request.getParameter("on");
            boolean bFlag=Boolean.parseBoolean(Flag);
            Integer codeOperation=callerManager.SetAllAbonCallFlagByGr(bFlag,PhoneList);
            if (codeOperation!=0)
                { request.setAttribute("code_op_set_abon_call_notif", "Код завершения операции: "+codeOperation); }
            else
                { request.setAttribute("code_op_set_abon_call_notif", "Операция успешна"); }
        }
        else if (action.equals("send_tlg_msg")) {
            String GrList = request.getParameter("gr_list");
            String Msg = request.getParameter("msg");
            String ppp = request.getRemoteAddr(); //getRemoteHost()
            structUser = StrucUser.getInstance();
            String fio = structUser.getFio();
            String finalMsg = Msg.replace("/", ppp + "\r\n" + fio + "\r\n");
            Integer codeOperation=callerManager.SendTlgMsg(ppp+';'+Msg,GrList);
            if (codeOperation != 0) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<send_tlg_msg_notif>" + codeOperation + "</send_tlg_msg_notif>");
            } else {
                //nothing to show
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } 
        }
        else if (action.equals("autocall")) {
            String Msg = request.getParameter("msg");
            Integer codeOperation=callerManager.DoAutoCall(Msg);
            if (codeOperation != 0) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<send_autocall_notif>" + codeOperation + "</send_autocall_notif>");
            } else {
                //nothing to show
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } 
        }
        else if (action.equals("list_call_log")) {

            String filter_phone = null, st_d = null, en_d = null;
            boolean IsArch=false, IsLtc=false;
            Date parsingDateS = null,parsingDateE = null;
            Enumeration<String> params = request.getParameterNames();
            while (params.hasMoreElements()) {
                String param = params.nextElement();
                switch (param) {
                    case "ed_phone_notif_r":
                        filter_phone = request.getParameter(param);
                        break;
                    case "chk_arch_notif_r":
                        IsArch = Boolean.parseBoolean(request.getParameter(param));
                        break;
                    case "chk_ltc_notif_r":
                        IsLtc = Boolean.parseBoolean(request.getParameter(param));
                        break;
                    case "s_dt_notif_r":
                        st_d = request.getParameter(param);
                        break;
                    case "e_dt_notif_r":
                        en_d = request.getParameter(param);
                        break;
                    default:
                        break;
                }
            }
            try {
                String uuid = UUID.randomUUID().toString();
                String hash=uuid;
                String oldPhone = filter_phone;
                if (IsLtc==true) filter_phone = "9999";
                Integer codeOperation = callerManager.DoMysqlAsteriskCall(filter_phone,IsArch,st_d,en_d,hash);
                if (codeOperation != 0) {
                    response.setContentType("text/xml");
                    response.setHeader("Cache-Control", "no-cache");
                    response.getWriter().write("<get_call_log_error_notif_r>" + codeOperation + "</get_call_log_error_notif_r>");
                } else {
                    sb = new StringBuffer(); 
                    List resultList=null;
                    resultList = em.createNamedQuery("TempAsterisk.findByUsrZabbix").setParameter("usrZabbix", hash).getResultList();

                    boolean logsAdded=false;
                    if (resultList != null) {
                        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                            Object[]  next = (Object[] ) iterator.next();
                            sb.append("<call_log_raw>");
                            sb.append("<fio>" + next[0] + "</fio>");
                            sb.append("<calldate>" + next[1] + "</calldate>");
                            sb.append("<dst>" + next[2] + "</dst>");
                            sb.append("<disposition>" + next[3] + "</disposition>");
                            sb.append("</call_log_raw>");
                            logsAdded = true;
                        }
                    }
                    if (logsAdded) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<call_logs_notif_r>" + sb.toString() + "</call_logs_notif_r>");
                    } else {
                        //nothing to show
                        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    }
                }
//                    request.setAttribute("list_dhcpo", resultList);
//                    request.setAttribute("ip_attr_dhcpo", ip);
//                    if (IsArch=="true") request.setAttribute("arch_attr_dhcpo", "Checked");
//                    else request.setAttribute("arch_attr_dhcpo", "unnChecked");
            } catch (Exception e) {
                e.printStackTrace();
            }

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
