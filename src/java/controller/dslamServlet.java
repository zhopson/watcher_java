/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.SmotrDslamPortsView;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.SmotrDslamPortsViewFacade;
import session.DslamNetsManager;
import utils.IPv4;
//import org.apache.commons.net.util.SubnetUtils;

/**
 *
 * @author andrey-man
 */
@WebServlet(name = "dslamServlet", urlPatterns = {"/dslam_get","/dslam_s_get"})
public class dslamServlet extends HttpServlet {
    @EJB
    SmotrDslamPortsViewFacade SmotrDslamPortsViewFacade1;    
    @PersistenceContext(unitName = "watcherPU")
    private EntityManager em;
    @EJB
    DslamNetsManager dslam_netsMgr;
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
            boolean sel_no_binds=false,sel_arch=false; String st_d = null, en_d = null; 
            String host = null,port = null,bind = null,adres = null,dom = null, action = null;
            Date parsingDateS = null,parsingDateE = null;
            StringBuffer sb = new StringBuffer();
            StringBuffer sd = new StringBuffer();
            Enumeration<String> params = request.getParameterNames();
            while (params.hasMoreElements()) {
                String param = params.nextElement();
                switch (param) {
                    case "action":
                        action = request.getParameter(param);
                        break;
                    case "s_dt_dslam":
                        st_d = request.getParameter(param);
                        break;
                    case "e_dt_dslam":
                        en_d = request.getParameter(param);
                        break;
                    case "chk_arch_dslam":
                        sel_arch = Boolean.parseBoolean(request.getParameter(param));
                        break;
                    case "chk_no_binds_dslam":
                        sel_no_binds = Boolean.parseBoolean(request.getParameter(param));
                        break;
                    case "ed_host_dslam":
                        host = request.getParameter(param);
                        break;
                    case "ed_port_dslam":
                        port = request.getParameter(param);
                        break;
                    case "ed_bind_dslam":
                        bind = request.getParameter(param);
                        break;
                    case "ed_adres_dslam":
                        adres = request.getParameter(param);
                        break;
                    case "ed_dom_dslam":
                        dom = request.getParameter(param);
                        break;
                    default:
                        break;
                }
            }
            try {
                if ("find_dslam_ports".equals(action)) {
                    adres = adres.toUpperCase();
                    //Object result = null;
                    Date result = (Date) em.createNamedQuery("SmotrDslamPortsView.findMaxDateByHost").setParameter("host", host).getSingleResult();
                    if (result != null) {
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String res = fmt.format(result);
                        res = res.substring(0, 10);
                        String date_s = res + " 00:00:00";
                        String date_e = res + " 23:59:59";
                        parsingDateS = fmt.parse(date_s);
                        parsingDateE = fmt.parse(date_e);
                    } else {
                        if (!"".equals(host)) {
                            return;//Сообщение: хост не найден
                        } else {
                            Date dd = new Date();
                            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            String dt_s = fmt.format(dd);
                            dt_s = dt_s.substring(0, 10);
                            String date_s = dt_s + " 00:00:00";
                            String date_e = dt_s + " 23:59:59";
                            parsingDateS = fmt.parse(date_s);
                            parsingDateE = fmt.parse(date_e);
                            // получение текущей даты с установкой hour,minute,second
//                        Date dd = new Date();
//                        Calendar today = Calendar.getInstance();
//                        today.set(Calendar.HOUR, 0);
//                        today.set(Calendar.MINUTE, 0);
//                        today.set(Calendar.SECOND, 0);
//                        dd = today.getTime();
//                        parsingDateS = dd;
//                        today.set(Calendar.HOUR, 23);
//                        today.set(Calendar.MINUTE, 59);
//                        today.set(Calendar.SECOND, 59);
//                        dd = today.getTime();
//                        parsingDateE = dd;
                        }
                    }
                    Date begin_date, end_date;
                    List resultList = null;

                    if (sel_arch) { // поиск в архиве
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String date_s = st_d + " 00:00:00";
                        String date_e = en_d + " 23:59:59";
                        begin_date = fmt.parse(date_s);
                        end_date = fmt.parse(date_e);
                    } else { // поиск в текущих значениях последнего опроса портов dslam
                        begin_date = parsingDateS;
                        end_date = parsingDateE;
                    }

                    bind = "%" + bind + "%";
                    if ("".equals(adres) && !"".equals(host)) { // поиск по ip
                        if (!"".equals(host) && "".equals(port)) {
                            resultList = em.createNamedQuery("SmotrDslamPortsView.findByHostDate")
                                    .setParameter("host", host)
                                    .setParameter("ipline", bind)
                                    .setParameter("date1", begin_date)
                                    .setParameter("date2", end_date)
                                    .getResultList();
                        } else if (!"".equals(host) && !"".equals(port)) {
                            resultList = em.createNamedQuery("SmotrDslamPortsView.findByHostPortDate")
                                    .setParameter("date1", begin_date)
                                    .setParameter("date2", end_date)
                                    .setParameter("host", host)
                                    .setParameter("port", port)
                                    .setParameter("ipline", bind)
                                    .getResultList();
                        }
                        Query query = em.createNativeQuery("select "
                                + "sum(Activating) as ports_activating, sum(Activated) as ports_activated,"
                                + "sum(Deactivated) as ports_deactivated, "
                                + "sum(Activating)+sum(Deactivated)+sum(Activated) as ports_total,"
                                + "sum(ipline) as ports_without_binds "
                                + "from ("
                                + "    select "
                                + "        case when port_status='Activating' then count else 0 end as Activating,"
                                + "        case when port_status='Deactivated' then count else 0 end as Deactivated,"
                                + "        case when port_status='Activated' then count else 0 end as Activated,"
                                + "        case when ip_line='NA' then c_ip_line else 0 end as ipline"
                                + "    from ("
                                + "        SELECT distinct count(port_status), port_status,count(ip_line) c_ip_line,ip_line"
                                + "        from watcher.smotr_dslam_ports_view f where (f.host=?1)"
                                + "        and f.date between ?2 and ?3"
                                + "        group by port_status, ip_line ) a ) b");

//                    Object [] res_stat = (Object []) query.setParameter(1,host).setParameter(2,b_date).setParameter(3,e_date).getSingleResult();
                        List resultListS = query.setParameter(1, host).setParameter(2, begin_date).setParameter(3, end_date).getResultList();
//                    List resultListS = query.getResultList();
                        if (resultListS != null) {
                            for (Iterator iterator = resultListS.iterator(); iterator.hasNext();) {
                                Object[] next = (Object[]) iterator.next();
                                sd.append("<dslam_stat_raw>");
                                sd.append("<total_stat>" + next[3] + "</total_stat>");
                                sd.append("<acting_stat>" + next[0] + "</acting_stat>");
                                sd.append("<acted_stat>" + next[1] + "</acted_stat>");
                                sd.append("<deacted_stat>" + next[2] + "</deacted_stat>");
                                sd.append("<nobinds_stat>" + next[4] + "</nobinds_stat>");
                                sd.append("</dslam_stat_raw>");
                            }
                        }
                        boolean logsAdded = false;
                        if (resultList != null) {
                            for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                                SmotrDslamPortsView next = (SmotrDslamPortsView) iterator.next();
                                sb.append("<dslam_ports_raw>");
                                sb.append("<host_name>" + next.getHostName() + "</host_name>");
                                sb.append("<host>" + next.getHost() + "</host>");
                                sb.append("<port>" + next.getPort() + "</port>");
                                sb.append("<status>" + next.getPortStatus() + "</status>");
                                sb.append("<ip_line>" + next.getIpLine() + "</ip_line>");
                                sb.append("<profile_line>" + next.getProfileLine() + "</profile_line>");
                                sb.append("<profile_iptv>" + next.getProfileIptv() + "</profile_iptv>");
                                sb.append("<vlan_line>" + next.getVlanLine() + "</vlan_line>");
                                sb.append("<pvc_line>" + next.getPvcLine() + "</pvc_line>");
                                sb.append("<vlan_iptv>" + next.getVlanIptv() + "</vlan_iptv>");
                                sb.append("<pvc_iptv>" + next.getPvcIptv() + "</pvc_iptv>");
                                sb.append("<ed_snr>" + next.getEdsnr() + "</ed_snr>");
                                sb.append("<eda>" + next.getEda() + "</eda>");
                                sb.append("<eusnr>" + next.getEusnr() + "</eusnr>");
                                sb.append("<eua>" + next.getEua() + "</eua>");
                                sb.append("<date>" + next.getDate() + "</date>");
                                sb.append("</dslam_ports_raw>");
                                logsAdded = true;
                            }
                        }
                        if (logsAdded) {
                            response.setContentType("text/xml");
                            response.setHeader("Cache-Control", "no-cache");
                            response.getWriter().write("<dslam_ports_dslam>"
                                    + "<dslam_ports_list>"
                                    + sb.toString()
                                    + "</dslam_ports_list>"
                                    + "<dslam_ports_stat>"
                                    + sd.toString()
                                    + "</dslam_ports_stat>"
                                    + "</dslam_ports_dslam>");
                            //response.getWriter().write("<dslam_ports_list>" + sb.toString() + "</dslam_ports_list>");
//                        response.getWriter().write("<dslam_ports_dslam>" + sb.toString() + "</dslam_ports_dslam><dslam_stat_dslam>"+sd.toString()+"</dslam_stat_dslam>");
                        } else {
                            //nothing to show
                            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                        }
                    } else if ((!"".equals(adres) || !"".equals(dom)) && "".equals(host)) {// по адресу
                        String findStringForQuery;
                        if (!"".equals(dom)) {
                            if (dom.contains("/")) {
                                List da = new ArrayList();
                                da.addAll(Arrays.asList(dom.split("/")));
                                findStringForQuery = "%" + adres + "%" + da.get(0) + "%/" + da.get(1) + "(\\s|,)%";
                            } else {
                                findStringForQuery = "%" + adres + "%" + dom + "(\\s|,)%";
                            }
                        } else {
                            findStringForQuery = "%" + adres + "%";
                        }

                        Query query = null;
                        port = "%" + port + "%";
                        if (!"".equals(dom) && "".equals(port)) {
                            query = em.createNativeQuery("select b.name as adres,a.host_name,a.host,a.port,a.port_status,a.ip_line,a.profile_line,a.profile_iptv,a.vlan_line,a.pvc_line,a.vlan_iptv,a.pvc_iptv,a.edsnr,a.eda,a.eusnr,a.eua,a.date "
                                    + "from watcher.smotr_dslam_ports_view a LEFT OUTER JOIN hosts b on b.hostid = a.host_id where a.ip_line like ?1 and a.date between ?2 and ?3 and upper(b.name) similar to ?4 order by a.port_id")
                                    .setParameter(1, bind).setParameter(2, begin_date).setParameter(3, end_date).setParameter(4, findStringForQuery);
                        } else if ((!"".equals(dom) && !"".equals(port)) || ("".equals(dom))) {
                            query = em.createNativeQuery("select b.name as adres,a.host_name,a.host,a.port,a.port_status,a.ip_line,a.profile_line,a.profile_iptv,a.vlan_line,a.pvc_line,a.vlan_iptv,a.pvc_iptv,a.edsnr,a.eda,a.eusnr,a.eua,a.date "
                                    + "from watcher.smotr_dslam_ports_view a LEFT OUTER JOIN hosts b on b.hostid = a.host_id where a.port like ?1 and a.ip_line like ?2 and a.date between ?3 and ?4 and upper(b.name) similar to ?5 order by a.port_id")
                                    .setParameter(1, port).setParameter(2, bind).setParameter(3, begin_date).setParameter(4, end_date).setParameter(5, findStringForQuery);
                        }

                        List resultList1 = query.getResultList();
                        boolean logsAdded = false;
                        if (resultList1 != null) {
                            for (Iterator iterator = resultList1.iterator(); iterator.hasNext();) {
                                Object[] next = (Object[]) iterator.next();
                                sb.append("<dslam_ports_adr_raw>");
                                sb.append("<adres>" + next[0] + "</adres>");
                                sb.append("<host_name>" + next[1] + "</host_name>");
                                sb.append("<host>" + next[2] + "</host>");
                                sb.append("<port>" + next[3] + "</port>");
                                sb.append("<status>" + next[4] + "</status>");
                                sb.append("<ip_line>" + next[5] + "</ip_line>");
                                sb.append("<profile_line>" + next[6] + "</profile_line>");
                                sb.append("<profile_iptv>" + next[7] + "</profile_iptv>");
                                sb.append("<vlan_line>" + next[8] + "</vlan_line>");
                                sb.append("<pvc_line>" + next[9] + "</pvc_line>");
                                sb.append("<vlan_iptv>" + next[10] + "</vlan_iptv>");
                                sb.append("<pvc_iptv>" + next[11] + "</pvc_iptv>");
                                sb.append("<ed_snr>" + next[12] + "</ed_snr>");
                                sb.append("<eda>" + next[13] + "</eda>");
                                sb.append("<eusnr>" + next[14] + "</eusnr>");
                                sb.append("<eua>" + next[15] + "</eua>");
                                sb.append("<date>" + next[16] + "</date>");
                                sb.append("</dslam_ports_adr_raw>");
                                logsAdded = true;
                            }
                        }
                        if (logsAdded) {
                            response.setContentType("text/xml");
                            response.setHeader("Cache-Control", "no-cache");
                            response.getWriter().write("<dslam_ports_adr_dslam>" + sb.toString() + "</dslam_ports_adr_dslam>");
                            //response.getWriter().write("<dslam_ports_list>" + sb.toString() + "</dslam_ports_list>");
//                        response.getWriter().write("<dslam_ports_dslam>" + sb.toString() + "</dslam_ports_dslam><dslam_stat_dslam>"+sd.toString()+"</dslam_stat_dslam>");
                        } else {
                            //nothing to show
                            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                        }

                    } else { // поиск всех хостов, поле хост и адрес пустые => поиск по другим полям
                        port = "%" + port + "%";
                        if (!"".equals(port)) {
                            resultList = em.createNamedQuery("SmotrDslamPortsView.findByPortDate")
                                    .setParameter("date1", begin_date)
                                    .setParameter("date2", end_date)
                                    .setParameter("port", port)
                                    .setParameter("ipline", bind)
                                    .getResultList();
                        } else {
                            resultList = em.createNamedQuery("SmotrDslamPortsView.findByDate1")
                                    .setParameter("date1", begin_date)
                                    .setParameter("date2", end_date)
                                    .setParameter("ipline", bind)
                                    .getResultList();
                        }

                        boolean logsAdded = false;
                        if (resultList != null) {
                            for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                                SmotrDslamPortsView next = (SmotrDslamPortsView) iterator.next();
                                sb.append("<dslam_ports_raw>");
                                sb.append("<host_name>" + next.getHostName() + "</host_name>");
                                sb.append("<host>" + next.getHost() + "</host>");
                                sb.append("<port>" + next.getPort() + "</port>");
                                sb.append("<status>" + next.getPortStatus() + "</status>");
                                sb.append("<ip_line>" + next.getIpLine() + "</ip_line>");
                                sb.append("<profile_line>" + next.getProfileLine() + "</profile_line>");
                                sb.append("<profile_iptv>" + next.getProfileIptv() + "</profile_iptv>");
                                sb.append("<vlan_line>" + next.getVlanLine() + "</vlan_line>");
                                sb.append("<pvc_line>" + next.getPvcLine() + "</pvc_line>");
                                sb.append("<vlan_iptv>" + next.getVlanIptv() + "</vlan_iptv>");
                                sb.append("<pvc_iptv>" + next.getPvcIptv() + "</pvc_iptv>");
                                sb.append("<ed_snr>" + next.getEdsnr() + "</ed_snr>");
                                sb.append("<eda>" + next.getEda() + "</eda>");
                                sb.append("<eusnr>" + next.getEusnr() + "</eusnr>");
                                sb.append("<eua>" + next.getEua() + "</eua>");
                                sb.append("<date>" + next.getDate() + "</date>");
                                sb.append("</dslam_ports_raw>");
                                logsAdded = true;
                            }
                        }
                        if (logsAdded) {
                            response.setContentType("text/xml");
                            response.setHeader("Cache-Control", "no-cache");
                            response.getWriter().write("<dslam_ports_list>" + sb.toString() + "</dslam_ports_list>");
//                        response.getWriter().write("<dslam_ports_dslam>" + sb.toString() + "</dslam_ports_dslam><dslam_stat_dslam>"+sd.toString()+"</dslam_stat_dslam>");
                        } else {
                            //nothing to show
                            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                        }
                    }
                }
                else if ("find_dslam_dup_binds".equals(action)) {

                    Date dd = new Date();
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String dt_s = fmt.format(dd);
                    dt_s = dt_s.substring(0, 10);
                    String date_s = dt_s + " 00:00:00";
                    String date_e = dt_s + " 23:59:59";
                    parsingDateS = fmt.parse(date_s);
                    parsingDateE = fmt.parse(date_e);

                    Date begin_date, end_date;
                    List resultList = null;

                    if (sel_arch) { // поиск в архиве
                        date_s = st_d + " 00:00:00";
                        date_e = en_d + " 23:59:59";
                        begin_date = fmt.parse(date_s);
                        end_date = fmt.parse(date_e);
                    } else { // поиск в текущих значениях последнего опроса портов dslam
                        begin_date = parsingDateS;
                        end_date = parsingDateE;
                    }

                    bind = "%" + bind + "%";
                    Query query=null;
                    //sb = new StringBuffer();
                    if ("".equals(host)) {
                            query = em.createNativeQuery("select host_name,host,port,port_status,ip_line,date from watcher.smotr_dslam_ports_view where ip_line in "
                                    + "(select ip_line from watcher.smotr_dslam_ports_view where date between date_trunc('day',now()-interval '1 day') and date_trunc('day',now()) group by ip_line having count(ip_line)>1) "
                                    + "and date between date_trunc('day',now()-interval '1 day') and date_trunc('day',now()) and ip_line!='NA' and ip_line!='' and ip_line!='1.1.1.1' order by ip_line")
                                    .setParameter(1, begin_date).setParameter(2, end_date);
                    }
                    else {
                        if (!"%%".equals(bind)) {
                            query = em.createNativeQuery("select host_name,host,port,port_status,ip_line,date from watcher.smotr_dslam_ports_view where ip_line in "
                                    + "(select ip_line from watcher.smotr_dslam_ports_view where host=?1 and date between ?2 and ?3 "
                                    + "and ip_line like ?4 group by ip_line having count(ip_line)>1) "
                                    + "and date between ?2 and ?3 and host=?1 and ip_line!='NA' and ip_line!='' and ip_line!='1.1.1.1'")
                                    .setParameter(1, host).setParameter(2, begin_date).setParameter(3, end_date).setParameter(4, bind);
                        } else {
                            query = em.createNativeQuery("select host_name,host,port,port_status,ip_line,date from watcher.smotr_dslam_ports_view where ip_line in "
                                    + "(select ip_line from watcher.smotr_dslam_ports_view where host=?1 and date between ?2 and ?3 "
                                    + "group by ip_line having count(ip_line)>1) "
                                    + "and date between ?2 and ?3 and host=?1 and ip_line!='NA' and ip_line!='' and ip_line!='1.1.1.1'")
                                    .setParameter(1, host).setParameter(2, begin_date).setParameter(3, end_date);
                        }
                    }
                    resultList = query.getResultList();

                    boolean logsAdded = false;
                    if (resultList != null) {
                        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                            Object [] next = (Object []) iterator.next();
                            sb.append("<dslam_dup_dinds_raw>");
                            sb.append("<host_name>" + next[0] + "</host_name>");
                            sb.append("<host>" + next[1] + "</host>");
                            sb.append("<port>" + next[2] + "</port>");
                            sb.append("<status>" + next[3] + "</status>");
                            sb.append("<ip_line>" + next[4] + "</ip_line>");
                            sb.append("<date>" + next[5] + "</date>");
                            sb.append("</dslam_dup_dinds_raw>");
                            logsAdded = true;
                        }
                    }
                    if (logsAdded) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<dslam_dup_binds>" + sb.toString() + "</dslam_dup_binds>");
                    } else {
                        //nothing to show
                        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    }

                }
                else if ("svod_dslam".equals(action)) {

                    Date dd = new Date();
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String dt_s = fmt.format(dd);
                    dt_s = dt_s.substring(0, 10);
                    String date_s = dt_s + " 00:00:00";
                    String date_e = dt_s + " 23:59:59";
                    parsingDateS = fmt.parse(date_s);
                    parsingDateE = fmt.parse(date_e);

                    Date begin_date, end_date;
                    List resultList = null;

                    if (sel_arch) { // поиск в архиве
                        date_s = st_d + " 00:00:00";
                        date_e = en_d + " 23:59:59";
                        begin_date = fmt.parse(date_s);
                        end_date = fmt.parse(date_e);
                    } else { // поиск в текущих значениях последнего опроса портов dslam
                        begin_date = parsingDateS;
                        end_date = parsingDateE;
                    }

                    //sb = new StringBuffer();
                    Query query = em.createNativeQuery(
                            "select name,host,sum(Activating) as Activating,sum(Activated) as Activated,sum(Deactivated) as Deactivated,"
                            + "sum(Activating)+sum(Deactivated)+sum(Activated) as total,sum(ipline) binds_na from (select name,host, "
                            + "case when port_status='Activating' then count else 0 end as Activating,"
                            + "case when port_status='Deactivated' then count else 0 end as Deactivated,"
                            + "case when port_status='Activated' then count else 0 end as Activated,"
                            + "case when ip_line='NA' then c_ip_line else 0 end as ipline from (SELECT  distinct hosts.name as name, f.host, count(port_status), "
                            + "port_status,count(ip_line) c_ip_line,ip_line from public.hosts, public.smotr_dslam_ports f where (f.host_id=hosts.hostid) "
                            + "and f.date between ?1 and ?2 group by hosts.name, f.host, port_status, ip_line ) a ) b group by name, host order by host"
                    ).setParameter(1, begin_date).setParameter(2, end_date);
                    resultList = query.getResultList();

                    boolean logsAdded = false;
                    if (resultList != null) {
                        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                            Object [] next = (Object []) iterator.next();
                            sb.append("<dslam_svod_raw>");
                            sb.append("<host_name>" + next[0] + "</host_name>");
                            sb.append("<host>" + next[1] + "</host>");
                            sb.append("<act>" + next[2] + "</act>");
                            sb.append("<inact>" + next[3] + "</inact>");
                            sb.append("<dis>" + next[4] + "</dis>");
                            sb.append("<tot>" + next[5] + "</tot>");
                            sb.append("<nob>" + next[6] + "</nob>");
                            sb.append("</dslam_svod_raw>");
                            logsAdded = true;
                        }
                    }
                    if (logsAdded) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<dslam_svod>" + sb.toString() + "</dslam_svod>");
                    } else {
                        //nothing to show
                        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    }

                }
                else if ("get_netlist_dslam_s".equals(action)) {

                    boolean noPppoe = Boolean.parseBoolean(request.getParameter("chk_no_pppoe_dslam"));

                    GetDslamNets(noPppoe,response);

                }
                else if ("get_save_dslam_s".equals(action)) {

                    String ip = request.getParameter("ip_save_dslam_s");
                    String net = request.getParameter("net_save_dslam_s");
                    String bits = request.getParameter("bit_save_dslam_s");
                    
                    Pattern p = Pattern.compile("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");
                    Matcher m = p.matcher(net);
                    if (!m.matches()) { // некорректный формат сети
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_nets><incorrect_net>1</incorrect_net></errors_nets>"); 
                        return;
                    }
                    if ("".equals(net)) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_nets><emp_net>1</emp_net></errors_nets>"); 
                        return;
                    }
                    if ("".equals(bits)) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_nets><emp_bits>1</emp_bits></errors_nets>"); 
                        return;
                    }
                    if (Byte.parseByte(bits)<16 || Byte.parseByte(bits)>32) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_nets><incorrect_bits>1</incorrect_bits></errors_nets>"); 
                        return;                        
                    }
                    List num_array = new ArrayList();
                    num_array.addAll(Arrays.asList(net.split("\\.")));             
                    String findedNet = num_array.get(0) + "." + num_array.get(1) + "." + num_array.get(2) + ".";
                    findedNet = findedNet + "%";
                    Query query = em.createNativeQuery("select * from port_info.dslam_nets where net like ?1 and ipaddr!=?2").setParameter(1, findedNet).setParameter(2, ip);
                    List resultList = query.getResultList();
                    
                    String newNet = net + "/" + bits;
                    String overlappedNets = "";
                    
                    //IPAddressString ipnetwork_new = new IPAddressString(str);
                    //SubnetUtils ipnetwork_new = new SubnetUtils(newNet);
                    
                    if (resultList.size() != 0) {
                        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                            Object[] next = (Object[]) iterator.next();
                            overlappedNets += "ip dslam: "+next[0] +"; Сеть: "+next[1]+"\n";
                        }                      
                        if (!"".equals(overlappedNets)) {
                            response.setContentType("text/xml");
                            response.setHeader("Cache-Control", "no-cache");
                            response.getWriter().write("<errors_nets><overlapped_nets>"+"Сеть "+newNet+" возможно пересекается с сетями:\n"+overlappedNets+"</overlapped_nets></errors_nets>");
                            return;                          
                        }
                    }
                    int nslots=0,nports=0;
                    int [] rng=Get_DSLAM_range(ip);
                    if (rng==null) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_nets><dslam_not_found>1</dslam_not_found></errors_nets>");
                        return;
                    }                    
                    nslots = rng[0];nports = rng[1];
                    if (nports == 17) nports--;
                    if (nslots != 16) nslots--; else nslots = 14;
                    IPv4 ipnetwork_new = new IPv4(newNet);
                    if (nslots * nports > ipnetwork_new.getNumberOfHosts()) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_nets><low_hosts_nets>" + "В сети " + newNet + ", число адресов:" + ipnetwork_new.getNumberOfHosts() + ",\nнедостаточно хостов для dslam:\n" + ip + ", количество возможных портов:" + nslots * nports 
                                                 + "</low_hosts_nets></errors_nets>");
                        return;                     
                    }
                    int RetCode = dslam_netsMgr.UpdateNet(ip, newNet);
                    if (RetCode!=0) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_nets><not_updated>1</not_updated></errors_nets>"); 
                    }
                    else {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<success_nets><updated>1</updated></success_nets>"); 
                    }
//                    boolean noPppoe = Boolean.parseBoolean(request.getParameter("chk_no_pppoe_dslam"));
//                    GetDslamNets(noPppoe,response);

                }
                else if ("get_delete_dslam_s".equals(action)) {

                    String ip = request.getParameter("ip_del_dslam_s");
                    String net = request.getParameter("net_del_dslam_s");
                    String bits = request.getParameter("bit_del_dslam_s");
                    
                    String delNet = net + "/" + bits;

                    int RetCode = dslam_netsMgr.DelNet(ip, delNet);
                    if (RetCode!=0) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_del_nets><not_deleted>1</not_deleted></errors_del_nets>"); 
                    }
                    else {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<success_nets><deleted>1</deleted></success_nets>"); 
                    }
                    
//                    boolean noPppoe = Boolean.parseBoolean(request.getParameter("chk_no_pppoe_dslam"));
//                    GetDslamNets(noPppoe,response);
                }
                else if ("get_add_dslam_s".equals(action)) {

                    String ip = request.getParameter("ip_add_dslam_s");
                    String net = request.getParameter("net_add_dslam_s");
                    String bits = request.getParameter("bit_add_dslam_s");
                    
                    Pattern p = Pattern.compile("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");
                    Matcher m = p.matcher(net);
                    Pattern p1 = Pattern.compile("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");
                    Matcher m1 = p1.matcher(ip);
                    if ("".equals(ip)) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_nets><emp_ip>1</emp_ip></errors_nets>"); 
                        return;
                    }
                    if (!m1.matches()) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_nets><incorrect_ip>1</incorrect_ip></errors_nets>"); 
                        return;
                    }
                    if (!m.matches()) { // некорректный формат сети
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_nets><incorrect_net>1</incorrect_net></errors_nets>"); 
                        return;
                    }
                    if ("".equals(net)) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_nets><emp_net>1</emp_net></errors_nets>"); 
                        return;
                    }
                    if ("".equals(bits)) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_nets><emp_bits>1</emp_bits></errors_nets>"); 
                        return;
                    }
                    if (Byte.parseByte(bits)<16 || Byte.parseByte(bits)>32) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_nets><incorrect_bits>1</incorrect_bits></errors_nets>"); 
                        return;                        
                    }
                    
                    Query query = em.createNativeQuery("select * from port_info.dslam_nets where ipaddr = ?1").setParameter(1, ip);
                    List resultList = query.getResultList();
                    if (resultList.size() != 0) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_nets><ipexist_nets>Dslam с адресом " + ip + " уже существует</ipexist_nets></errors_nets>");
                        return;
                    }
                    
                    List num_array = new ArrayList();
                    num_array.addAll(Arrays.asList(net.split("\\.")));             
                    String findedNet = num_array.get(0) + "." + num_array.get(1) + "." + num_array.get(2) + ".";
                    findedNet = findedNet + "%";
                    query = em.createNativeQuery("select * from port_info.dslam_nets where net like ?1").setParameter(1, findedNet);
                    resultList = query.getResultList();
                    
                    String newNet = net + "/" + bits;
                    String overlappedNets = "";

                    if (resultList.size() != 0) {
                        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                            Object[] next = (Object[]) iterator.next();
                            overlappedNets += "ip dslam: "+next[0] +"; Сеть: "+next[1]+"\n";
                        }                      
                        if (!"".equals(overlappedNets)) {
                            response.setContentType("text/xml");
                            response.setHeader("Cache-Control", "no-cache");
                            response.getWriter().write("<errors_nets><overlapped_nets>"+"Сеть "+newNet+" возможно пересекается с сетями:\n"+overlappedNets+"</overlapped_nets></errors_nets>");
                            return;                          
                        }
                    }
                    int nslots=0,nports=0;
                    int [] rng=Get_DSLAM_range(ip);
                    if (rng==null) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_nets><dslam_not_found>1</dslam_not_found></errors_nets>");
                        return;
                    }
                    nslots = rng[0];nports = rng[1];
                    if (nports == 17) nports--;
                    if (nslots != 16) nslots--; else nslots = 14;
                    IPv4 ipnetwork_new = new IPv4(newNet);
                    if (nslots * nports > ipnetwork_new.getNumberOfHosts()) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_nets><low_hosts_nets>" + "В сети " + newNet + ", число адресов:" + ipnetwork_new.getNumberOfHosts() + ",\nнедостаточно хостов для dslam:\n" + ip + ", количество возможных портов:" + nslots * nports 
                                                 + "</low_hosts_nets></errors_nets>");
                        return;                     
                    }
                    int RetCode = dslam_netsMgr.AddNet(ip, newNet);
                    if (RetCode!=0) {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<errors_nets><not_added>1</not_added></errors_nets>"); 
                    }
                    else {
                        response.setContentType("text/xml");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write("<success_nets><added>1</added></success_nets>"); 
                    }
//                    boolean noPppoe = Boolean.parseBoolean(request.getParameter("chk_no_pppoe_dslam"));
//                    GetDslamNets(noPppoe,response);

                }                
                else if ("rep_binds_dslam_s".equals(action)) {
                    
                    String ip = request.getParameter("ip_rep_dslam_s");
                    String net = request.getParameter("net_rep_dslam_s");
                    int repNo = Integer.parseInt(request.getParameter("num_rep_dslam_s"));
                    List resultList = em.createNamedQuery("SmotrDslamPortsView.findByHost")
                            .setParameter("host", ip)
                            .setMaxResults(1)
                            .getResultList();
                    if (resultList.size() == 0) {
                            response.setContentType("text/xml");
                            response.setHeader("Cache-Control", "no-cache");
                            response.getWriter().write("<dslam_nets_err_rep>Отсутствуют данные о портах. DSLAM не опрашивался</dslam_nets_err_rep>");
                            return;                          
                    }
                    int err_code=GetBinds4Ports(ip, net);
                    if (err_code==1) {
                            response.setContentType("text/xml");
                            response.setHeader("Cache-Control", "no-cache");
                            response.getWriter().write("<dslam_nets_err_rep>Ошибка при формировании отчета</dslam_nets_err_rep>");
                            return;                          
                    }
                    if (repNo == 1) {
                        Query query = em.createNativeQuery(
                                "select b.host,b.port,a.port_status,a.ip_line,b.ip_line,a.date "
                                + "from port_info.dslam_port_binds b "
                                + "left outer join public.smotr_dslam_ports a on (b.host=a.host and b.port=a.port) "
                                + "where b.host = ?1 and b.ip_line!=a.ip_line "
                                + "and date_trunc('day', date) = (select max(date_trunc('day', date)) from public.smotr_dslam_ports where host = ?1) "
                                + "order by b.port_id"
                        ).setParameter(1, ip);
                        resultList = query.getResultList();

                        boolean logsAdded = false;
                        if (resultList != null) {
                            for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                                Object[] next = (Object[]) iterator.next();
                                sb.append("<rep_binds_raw>");
                                sb.append("<host>" + next[0] + "</host>");
                                sb.append("<port>" + next[1] + "</port>");
                                sb.append("<port_status>" + next[2] + "</port_status>");
                                sb.append("<ip_liner>" + next[3] + "</ip_liner>");
                                sb.append("<ip_linep>" + next[4] + "</ip_linep>");
                                sb.append("<date>" + next[5] + "</date>");
                                sb.append("</rep_binds_raw>");
                                logsAdded = true;
                            }
                        }
                        if (logsAdded) {
                            response.setContentType("text/xml");
                            response.setHeader("Cache-Control", "no-cache");
                            response.getWriter().write("<rep_binds1_dslam_s>" + sb.toString() + "</rep_binds1_dslam_s>");
                        } else {
                            //nothing to show
                            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                        }
                    }
                    else {
                        Query query = em.createNativeQuery(
                                "select a.port_id,h.marka,h.uzel,a.host_name,b.host,b.port,a.port_status,a.ip_line,b.ip_line,a.profile_line,a.profile_iptv,"
                                + "a.vlan_line,a.pvc_line,a.vlan_iptv,a.pvc_iptv,a.mac,a.date from port_info.dslam_port_binds b "
                                + "left outer join port_info.c_vpn_view_smotr_dslam_ports a on (b.host=a.host and b.port=a.port) "
                                + "inner join port_info.import_technograd h on h.ipaddr=a.host where b.host = ?1 "
                                + "and date_trunc('day', date) = (select max(date_trunc('day', date)) from public.smotr_dslam_ports where host = ?1) "
                                + "order by port_id").setParameter(1, ip);
                        resultList = query.getResultList();

                        boolean logsAdded = false;
                        if (resultList != null) {
                            for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                                Object[] next = (Object[]) iterator.next();
                                sb.append("<rep_binds_raw>");
                                sb.append("<num>" + next[0] + "</num>");
                                sb.append("<model>" + next[1] + "</model>");
                                sb.append("<punkt>" + next[2] + "</punkt>");
                                sb.append("<hostname>" + next[3] + "</hostname>");
                                sb.append("<host>" + next[4] + "</host>");
                                sb.append("<port>" + next[5] + "</port>");
                                sb.append("<port_status>" + next[6] + "</port_status>");
                                sb.append("<ip_liner>" + next[7] + "</ip_liner>");
                                sb.append("<ip_linep>" + next[8] + "</ip_linep>");
                                sb.append("<prof>" + next[9] + "</prof>");
                                sb.append("<prof_iptv>" + next[10] + "</prof_iptv>");
                                sb.append("<vlan>" + next[11] + "</vlan>");
                                sb.append("<pvc>" + next[12] + "</pvc>");
                                sb.append("<vlan_iptv>" + next[13] + "</vlan_iptv>");
                                sb.append("<pvc_iptv>" + next[14] + "</pvc_iptv>");
                                sb.append("<mac>" + next[15] + "</mac>");
                                sb.append("<date>" + next[16] + "</date>");
                                sb.append("</rep_binds_raw>");
                                logsAdded = true;
                            }
                        }
                        if (logsAdded) {
                            response.setContentType("text/xml");
                            response.setHeader("Cache-Control", "no-cache");
                            response.getWriter().write("<rep_binds2_dslam_s>" + sb.toString() + "</rep_binds2_dslam_s>");
                        } else {
                            //nothing to show
                            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                        }
                    }
                }                
            } catch (Exception e) {
                e.printStackTrace();
            }        
        //processRequest(request, response);
    }

    void GetDslamNets(boolean pnopppoe,HttpServletResponse presponse) throws IOException {
        StringBuffer sb = new StringBuffer();
        List resultList = null;
        Query query;
        if (pnopppoe) 
            query = em.createNativeQuery("select b.marka,b.uzel,a.chantype,b.ipaddr,b.num_ports,b.used_ports,f.net from port_info.import_technograd b "
                                    + "LEFT OUTER join port_info.ntp_adsl_ip a on b.ipaddr=a.ip inner join port_info.dslam_nets f on f.ipaddr=b.ipaddr "
                                    + "where a.chantype!=?1 and b.uzel!=?2 and b.uzel!=?3 order by b.ipaddr")
                    .setParameter(1, "ВОЛС")
                    .setParameter(2, "ЯФ Мирный")
                    .setParameter(3, "ЯФ Ленск");
        else query = em.createNativeQuery("select b.marka,b.uzel,a.chantype,b.ipaddr,b.num_ports,b.used_ports,f.net from port_info.import_technograd b "
                                    + "LEFT OUTER join port_info.ntp_adsl_ip a on b.ipaddr=a.ip inner join port_info.dslam_nets f on f.ipaddr=b.ipaddr "
                                    + "order by b.ipaddr");
        resultList = query.getResultList();
        boolean logsAdded = false;
        if (resultList != null) {
            for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                Object [] next = (Object []) iterator.next();
                sb.append("<dslam_nets_raw>");
                sb.append("<model>" + next[0] + "</model>");
                sb.append("<uzel>" + next[1] + "</uzel>");
                sb.append("<chantype>" + next[2] + "</chantype>");
                sb.append("<ipaddr>" + next[3] + "</ipaddr>");
                sb.append("<num_ports>" + next[4] + "</num_ports>");
                sb.append("<used_ports>" + next[5] + "</used_ports>");
                sb.append("<net>" + next[6] + "</net>");
                sb.append("</dslam_nets_raw>");
                logsAdded = true;
            }
        }
        if (logsAdded) {
            presponse.setContentType("text/xml");
            presponse.setHeader("Cache-Control", "no-cache");
            presponse.getWriter().write("<dslam_nets_list>" + sb.toString() + "</dslam_nets_list>");
//                        response.getWriter().write("<dslam_ports_dslam>" + sb.toString() + "</dslam_ports_dslam><dslam_stat_dslam>"+sd.toString()+"</dslam_stat_dslam>");
        } else {
            //nothing to show
            presponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }
    
    int[] Get_DSLAM_range(String p_ip) throws IOException {
        int[] dim = {0, 0};

        String resultList = null;
        try {
            resultList = (String) em.createNamedQuery("ImportTechnograd.findMarkaByIP").setParameter("ipaddr", p_ip).getSingleResult();
            if (resultList != null) { // есть данные в технограде, определяем в зависимости от модели количество слотов/портов
                String model = resultList;
                if (model.contains("5600")) {
                    dim[0] = 16;
                    dim[1] = 32;
                } else if (model.contains("5603")) {
                    dim[0] = 7;
                    dim[1] = 32;
                } else if (model.contains("5605")) {
                    dim[0] = 5;
                    dim[1] = 17;
                } else if (model.contains("5616")) {
                    dim[0] = 5;
                    dim[1] = 32;
                } else if (model.contains("ZyXEL")) {
                    dim[0] = 1;
                    dim[1] = 49;
                } else if (model.contains("Nateks")) {
                    dim[0] = 1;
                    dim[1] = 49;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

        return dim;
    }  
    
 int GetBinds4Ports(String p_ip, String p_net) throws IOException  // заносит во временную табл. соответствие портам dslam p_ip биндов сети p_net
    {
        int nslots = 0, nports = 0;
        int stslot = 1, stport = 0;
       // EntityTransaction et = em.getTransaction();
//        Query query = em.createNativeQuery("truncate port_info.dslam_port_binds");
        //et.begin();
//        int updateCount = query.executeUpdate();
        //et.commit();
//        int deletedCount = em.createNamedQuery("DslamPortBinds.EmptyTable").executeUpdate();
//        if (deletedCount==0) return 1;
        int err_Count = dslam_netsMgr.EmptyDslamBinds();
        if (err_Count!=0) return 1;
        int[] rng = Get_DSLAM_range(p_ip);// получить диапазоны слотов и портов в зависимости от модели
        if (rng == null) {
//                        presponse.setContentType("text/xml");
//                        presponse.setHeader("Cache-Control", "no-cache");
//                        presponse.getWriter().write("<errors_nets><dslam_not_found>1</dslam_not_found></errors_nets>");
            return 1;
        }
        nslots = rng[0];
        nports = rng[1];
        IPv4 ipnetwork_new = new IPv4(p_net);

        if (nports == 17 || nports == 49) {
            stport = 1;
        }
        if (nslots == 16 || nslots == 1) {
            stslot = 0;
        }

        List<String> ips = ipnetwork_new.getAvailableIPs(Long.valueOf(ipnetwork_new.getNumberOfHosts()).intValue());
        Iterator iterator = ips.iterator();
        //class_pgsql class_pgsql1 = new class_pgsql();
        Pattern p = Pattern.compile("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.0$");

        int port_id = 0;
        for (int i = stslot; i < nslots; i++) {
            if (i == 7 || i == 8) {
                continue;
            }
            for (int k = stport; k < nports; k++) {
                String next = (String ) iterator.next();
                port_id++;
                if (next == ipnetwork_new.getBroadcastAddress().toString()) {
//                        presponse.setContentType("text/xml");
//                        presponse.setHeader("Cache-Control", "no-cache");
//                        presponse.getWriter().write("<errors_nets><dslam_not_found>1</dslam_not_found></errors_nets>");
//                    MessageBox.Show("В сети " + ipnetwork_new.Network + " недостаточно ip адресов для dslam " + p_ip);
                    return 1;
                }
                if (next.contains(".255")) {
                    next = (String ) iterator.next();
                }
                Matcher m = p.matcher(next);
                if (m.matches()) {
                    next = (String ) iterator.next();
                }
                err_Count = dslam_netsMgr.AddDslamBinds(p_ip,port_id,"0/" + i + "/" + k,next);
//                Query query = em.createNativeQuery("insert into port_info.dslam_port_binds select ?1,?2,?3,?4")
//                        .setParameter(1, p_ip)
//                        .setParameter(2, port_id)
//                        .setParameter(3, "0/" + i + "/" + k)
//                        .setParameter(4, next);
//                int updateCount = query.executeUpdate();                
//                SQL = String.Format(@"insert into port_info.dslam_port_binds select '{0}',{1},'{2}','{3}'", p_ip, port_id, "0/" + i + "/" + k, ips.Current);
            }
        }
        return 0;
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
