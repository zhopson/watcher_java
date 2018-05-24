/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var isIE;

var ipField_dhcpo;
var archField_dhcpo;
var sdtField_dhcpo;
var edtField_dhcpo;
var req_dhcpo;
var ResTableDhcpo;

var sdtField_syslog;
var edtField_syslog;
var selectGrField_syslog;
var selectHstField_syslog;
var msgField_syslog;
var ResTableSyslog;
var selectLevelField_syslog;
var req_syslog;
var arrHosts_syslog = [];
//var tbody;

var selectCallGrField_notif;
//var arrHosts_notif = [];
var AbonTable_notif;
var req_abon_notif;
var req_tlg_notif;
var PhoneList_notif;
var text_msg_notif;

var sdtField_notif_r;
var edtField_notif_r;
var filter_phone_notif_r;
var archField_notif_r;
var ltcField_notif_r;
var ResTable_notif_r;

var sdtField_dslam;
var edtField_dslam;
var archField_dslam;
var ResTable_dslam;
var HostField_dslam;
var PortField_dslam;
var BindField_dslam;
var AdresField_dslam;
var DomField_dslam;
var NoBindsField_dslam;

var ResTable_dslam_s;
var FilterField_dslam_s;
var noPppoeField_dslam_s;
var g_host_chg_dslam_s;
var g_net_chg_dslam_s;
var g_bit_chg_dslam_s;

var hostFieldSave_dslam_s;
var netFieldSave_dslam_s;
var bitFieldSave_dslam_s;

var hostFieldAdd_dslam_s;
var netFieldAdd_dslam_s;
var bitFieldAdd_dslam_s;

var hostFieldRep_dslam_s;
var netFieldRep_dslam_s;
var selFieldRep_dslam_s;

function init() {

var sorter = new TINY.table.sorter("sorter");

    //alert(window.location.pathname);
    if (window.location.pathname.indexOf('syslog') + 1) {
        sdtField_syslog = document.getElementById("id_s_dt_syslog");
        edtField_syslog = document.getElementById("id_e_dt_syslog");
        selectGrField_syslog = document.getElementById("id_sel_group_syslog");
        selectHstField_syslog = document.getElementById("id_sel_host_syslog");
        msgField_syslog = document.getElementById("id_ed_msg_syslog");
        selectLevelField_syslog = document.getElementById("id_sel_level_syslog");
        ResTableSyslog = document.getElementById("res_td_syslog_id");

        var now = new Date();
        //alert( new Date().getTimezoneOffset() );
        now.setHours(now.getHours() + 8);
        var strd = now.toISOString();
        strd = strd.substr(0, strd.length - 8);
        sdtField_syslog.value = strd;

        now.setHours(now.getHours() + 1);
        var strd = now.toISOString();
        strd = strd.substr(0, strd.length - 8);
        edtField_syslog.value = strd;

        // добавление в массив для фильтра и заполнения хостов по выбранной группе
        for (var i = 0; i < selectHstField_syslog.options.length; i++) {
            var option = selectHstField_syslog.options[i];
            Addhost_syslog(i, option.value, option.text);
//            if(option.value==="000" && option.selected) alert( option.text);
        }

//        var url = "syslog_get?action=init";
//        req_syslog = initRequest();
//        req_syslog.open("GET", url, true);
//        req_syslog.onreadystatechange = callback_syslog_init;
//        req_syslog.send(null);

    } else if (window.location.pathname.indexOf('dhcpo') + 1) {
        
        ipField_dhcpo = document.getElementById("id_ip_dhcpo");
        archField_dhcpo = document.getElementById("id_chk_arch_dhcpo");
        sdtField_dhcpo = document.getElementById("id_s_dt_dhcpo");
        edtField_dhcpo = document.getElementById("id_e_dt_dhcpo");
        ResTableDhcpo = document.getElementById("res_td_dhcpo_id");
        
        var now = new Date();
        now.setHours(now.getHours() + 8);
        var strd = now.toISOString();
        strd = strd.substr(0, 10);
        //alert(strd);
        sdtField_dhcpo.value = strd;

        now.setHours(now.getHours() + 1);
        var strd = now.toISOString();
        strd = strd.substr(0, 10);
        edtField_dhcpo.value = strd;        
        
    } else if (window.location.pathname.indexOf('notifications_r') + 1) {
        ResTable_notif_r = document.getElementById("res_td_notif_r_id");
        sdtField_notif_r = document.getElementById("id_s_dt_notif_r");
        edtField_notif_r = document.getElementById("id_e_dt_notif_r");
        filter_phone_notif_r = document.getElementById("id_phone_notif_r");
        archField_notif_r = document.getElementById("id_chk_arch_notif_r");
        ltcField_notif_r = document.getElementById("id_chk_ltc_notif_r");
        
        sdtField_notif_r.disabled = true; 
        edtField_notif_r.disabled = true;        
        var now = new Date();
        //alert( new Date().getTimezoneOffset() );
        now.setHours(now.getHours() + 7);
        var strd = now.toISOString();
        strd = strd.substr(0, strd.length - 8);
        sdtField_notif_r.value = strd;

        now.setHours(now.getHours() + 2);
        var strd = now.toISOString();
        strd = strd.substr(0, strd.length - 8);
        edtField_notif_r.value = strd;
    } else if (window.location.pathname.indexOf('notification') + 1) {
        selectCallGrField_notif = document.getElementById("id_sel_gr_call_notif");
        AbonTable_notif = document.getElementById("id_abon_call_td_notif");
        text_msg_notif = document.getElementById("id_text_msg_notif");
//        // добавление в массив для фильтра и заполнения хостов по выбранной группе
//        if (AbonTable_notif.getElementsByTagName("tr").length > 2) {
//            for (loop = 2; loop < AbonTable_notif.childNodes.length; loop++) {
//                AbonTable_notif.rows[loop].cells[1].innerText;
//            }
//            alert(AbonTable_notif.rows[2].cells[1].innerText);
//        }

//        for (var i = 0; i < selectCallGrField_notif.options.length; i++) {
//            var option = selectCallGrField_notif.options[i];
//            Addhost_notif(i,option.value,option.text);
////            if(option.value==="000" && option.selected) alert( option.text);
//        }  
    } else if (window.location.pathname.indexOf('dslam_s') + 1) {
        ResTable_dslam_s = document.getElementById("res_td_dslam_s_id");
        FilterField_dslam_s = document.getElementById("id_filter_dslam_s");
        noPppoeField_dslam_s = document.getElementById("id_chk_pppoe_dslam_s");
        
        hostFieldSave_dslam_s = document.getElementById("id_save_host_dslam_s");
        netFieldSave_dslam_s = document.getElementById("id_save_net_dslam_s");
        bitFieldSave_dslam_s = document.getElementById("id_save_bit_dslam_s");

        hostFieldAdd_dslam_s = document.getElementById("id_add_host_dslam_s");
        netFieldAdd_dslam_s = document.getElementById("id_add_net_dslam_s");
        bitFieldAdd_dslam_s = document.getElementById("id_add_bit_dslam_s");
        
        hostFieldRep_dslam_s = document.getElementById("id_host_get_rep_dslam_s");
        netFieldRep_dslam_s = document.getElementById("id_net_get_rep_dslam_s");

        var url = "dslam_s_get?action=get_netlist_dslam_s&chk_no_pppoe_dslam=" + escape(noPppoeField_dslam_s.checked);

        req_dslam_s = initRequest();
        req_dslam_s.open("GET", url, true);
        req_dslam_s.onreadystatechange = callback_get_netlist_dslam_s;
        req_dslam_s.send(null);

    } else if (window.location.pathname.indexOf('dslam') + 1) {
        ResTable_dslam = document.getElementById("res_td_dslam_id");
        sdtField_dslam = document.getElementById("id_s_dt_dslam");
        edtField_dslam = document.getElementById("id_e_dt_dslam");
        archField_dslam = document.getElementById("id_chk_arch_dslam");
        
        HostField_dslam = document.getElementById("id_text_ip_dslam");
        PortField_dslam = document.getElementById("id_text_port_dslam");
        BindField_dslam = document.getElementById("id_text_bind_dslam");
        AdresField_dslam = document.getElementById("id_text_adres_dslam");
        DomField_dslam = document.getElementById("id_text_dom_dslam");
        NoBindsField_dslam = document.getElementById("id_check_bind_dslam");
        
        sdtField_dslam.disabled = true; 
        edtField_dslam.disabled = true;        
        var now = new Date();
        
        now.setHours(now.getHours() + 1);
        var strd = now.toISOString();
        strd = strd.substr(0, 10);
        sdtField_dslam.value = strd;        
        edtField_dslam.value = strd;

    }

}

function Get_log_list_dhcpo() {

    var url = "dhcpo_get?ip_dhcpo=" + escape(ipField_dhcpo.value) + "&s_dt_dhcpo=" + escape(sdtField_dhcpo.value) + "&e_dt_dhcpo=" + escape(edtField_dhcpo.value) + "&chk_arch_dhcpo=" + escape(archField_dhcpo.checked);
    req_dhcpo = initRequest();
    req_dhcpo.open("GET", url, true);
    req_dhcpo.onreadystatechange = callback_dhcpo;
    req_dhcpo.send(null);
    //document.getElementById("waitModal").modal = 'show';
//        var d = document.getElementById("waitModal");
//        d.style.display='block';
    $('#DBReqModal_id').modal('show');
}

function initRequest() {
    if (window.XMLHttpRequest) {
        if (navigator.userAgent.indexOf('MSIE') != -1) {
            isIE = true;
        }
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function callback_dhcpo() {
    clearTable_dhcpo();
    if (req_dhcpo.readyState === 4) {
        if (req_dhcpo.status === 200) {
            parseMessages_dhcpo(req_dhcpo.responseXML);
        }
    }
    $('#DBReqModal_id').modal('hide');
//    $('#waitModal').on('show.bs.modal', function (e) {
//        //if (!data)
//            return e.preventDefault() // stops modal from being shown
//    })
    //document.getElementById("waitModal").modal = 'hide';
}

function appendRow_dhcpo(ptime, pheader, pmessage, pserver) {

//    var cell1, cell2, cell3, cell4;
//    var tbody = ResTableDhcpo.getElementsByTagName("tbody")[0];
//    //var tbody = document.getElementById("res_td_dhcpo_id").getElementsByTagName("tbody")[0];
//    var row;
//    if (isIE) {
//        ResTableDhcpo.style.display = 'block';
//        row = ResTableDhcpo.insertRow(ResTableDhcpo.rows.length);
//        cell1 = row.insertCell(0);
//        cell2 = row.insertCell(1);
//        cell3 = row.insertCell(2);
//        cell4 = row.insertCell(3);
//    } else {
//        //ResTableDhcpo.style.display = 'table';
//        //ResTableDhcpo.style.class = "table table-hover";
//        row = document.createElement("tr");
//        cell1 = document.createElement("td");
//        cell2 = document.createElement("td");
//        cell3 = document.createElement("td");
//        cell4 = document.createElement("td");
//        row.appendChild(cell1);
//        row.appendChild(cell2);
//        row.appendChild(cell3);
//        row.appendChild(cell4);
//        tbody.appendChild(row);
//    }
//    Elem_time = document.createTextNode(ptime);
//    cell1.appendChild(Elem_time);
//    Elem_pheader = document.createTextNode(pheader);
//    cell2.appendChild(Elem_pheader);
//    Elem_pmessage = document.createTextNode(pmessage);
//    cell3.appendChild(Elem_pmessage);
//    Elem_pserver = document.createTextNode(pserver);
//    cell4.appendChild(Elem_pserver);

    var tbody = ResTableDhcpo.getElementsByTagName("TBODY")[0];
    var row = document.createElement("tr");
    
    var td_time = document.createElement("td");
    td_time.style = "padding-left:5px;";
    td_time.appendChild(document.createTextNode(ptime));
    row.appendChild(td_time);

    var td_header = document.createElement("td");
    td_header.style = "padding-left:5px;";
    td_header.appendChild(document.createTextNode(pheader));
    row.appendChild(td_header);

    var td_message = document.createElement("td");
    td_message.style = "padding-left:5px;";
    td_message.appendChild(document.createTextNode(pmessage));
    row.appendChild(td_message);

    var td_server = document.createElement("td");
    td_server.style = "padding-left:5px;";
    td_server.appendChild(document.createTextNode(pserver));
    row.appendChild(td_server);
    
    tbody.appendChild(row);

}

function clearTable_dhcpo() {
    var tbody = ResTableDhcpo.getElementsByTagName("tbody")[0];
//    if (ResTableDhcpo.getElementsByTagName("tr").length > 0) {
    if (tbody.childNodes.length > 0) {
        //ResTableDhcpo.style.display = 'none';
        for (loop = tbody.childNodes.length - 1; loop >= 0; loop--) {
            tbody.removeChild(tbody.childNodes[loop]);
        }
    }
}

function parseMessages_dhcpo(responseXML) {
    // no matches returned
    if (responseXML == null) {
        return false;
    } else {
        var logs_dhcpo = responseXML.getElementsByTagName("logs_dhcpo")[0];
        var error_dhcpo = responseXML.getElementsByTagName("errors_dhcpo")[0];
        if (error_dhcpo != undefined) {
//            var error_row = error_dhcpo.childNodes[0];
//            var error = error_row.getElementsByTagName("error_dhcpo")[0];
            alert("Поле IP или MAC не заполнено");
        } else {
            if (logs_dhcpo.childNodes.length > 0) {
//            completeTable.setAttribute("bordercolor", "black"); ptime,pheader,pmessage,pserver
//            completeTable.setAttribute("border", "1");
                for (loop = 0; loop < logs_dhcpo.childNodes.length; loop++) {
                    var log_row = logs_dhcpo.childNodes[loop];
                    var time = log_row.getElementsByTagName("time")[0];
                    var header = log_row.getElementsByTagName("header")[0];
                    var message = log_row.getElementsByTagName("message")[0];
                    var server = log_row.getElementsByTagName("server")[0];
                    appendRow_dhcpo(time.childNodes[0].nodeValue,
                            header.childNodes[0].nodeValue,
                            message.childNodes[0].nodeValue,
                            server.childNodes[0].nodeValue);
                }
            }
        }
        sorter.head = "head";
        sorter.asc = "asc";
        sorter.desc = "desc";
        sorter.even = "evenrow";
        sorter.odd = "oddrow";
        sorter.evensel = "evenselected";
        sorter.oddsel = "oddselected";
        sorter.paginate = true;
        sorter.currentid = "currentpage";
        sorter.limitid = "pagelimit";
        sorter.initTab("res_td_dhcpo_id", 1);
        
    }
}

///////////////////////////////////////////////////////////////////////////////

//function callback_syslog_init() {
////    if (req_syslog.readyState === 4) {
////        if (req_syslog.status === 200) {
////            parseGroups_syslog(req_syslog.responseXML);
////        }
////    }
//}

//function parseGroups_syslog(responseXML) {
//    // no matches returned
//    if (responseXML == null) {
//        return false;
//    } else {
//
////        var logs_syslog = responseXML.getElementsByTagName("groups_syslog")[0];
////
////        if (logs_syslog.childNodes.length > 0) {
////            for (loop = 0; loop < logs_syslog.childNodes.length; loop++) {
////                var log_row = logs_syslog.childNodes[loop];
////                var id = log_row.getElementsByTagName("id")[0];
////                var grname = log_row.getElementsByTagName("group")[0];
////                appendGroup_syslog(id.childNodes[0].nodeValue,grname.childNodes[0].nodeValue);
////            }
////        }
//////////////////////////////////////////////////////////////////////////////////////////
//
////        logs_syslog1 = responseXML.getElementsByTagName("hosts_syslog")[0];
////
////        if (logs_syslog1.childNodes.length > 0) {
////            for (loop = 0; loop < logs_syslog1.childNodes.length; loop++) {
////                var log_row1 = logs_syslog1.childNodes[loop];
////                var host = log_row1.getElementsByTagName("host")[0];
////                var gr_id = log_row1.getElementsByTagName("grid")[0];
////                var desc = log_row1.getElementsByTagName("desc")[0];
////                appendHost_syslog(host.childNodes[0].nodeValue,gr_id.childNodes[0].nodeValue,desc.childNodes[0].nodeValue);
////            }
////        }
//    }
//}

//function appendGroup_syslog(pid,pgroup) {
//    selectGrField_syslog.options[selectGrField_syslog.options.length] = new Option(pgroup, pid);
//}
//
//function appendHost_syslog(phost,pgrid,pdesc) {
//    selectHstField_syslog.options[selectHstField_syslog.options.length] = new Option(phost+"("+pdesc+")", "");
//}

function Chg_gr_syslog() {
    var gr_id = selectGrField_syslog.options[selectGrField_syslog.selectedIndex].value;
    selectHstField_syslog.options.length = 1;
    for (var i = 0; i < arrHosts_syslog.length; i++) {
        if (arrHosts_syslog[i].grid == gr_id) {
            selectHstField_syslog.options[selectHstField_syslog.options.length] = new Option(arrHosts_syslog[i].host, "");
        }
    }
//    var opts = selectHstField_syslog.options;
//    while (opts.length > 0) {
//        opts[opts.length - 1] = null;
//    }
}

function Addhost_syslog(pid, pgrid, phost) {
    arrHosts_syslog[pid] = {
        grid: pgrid,
        host: phost
    }
}

function Get_log_list_syslog() {
    var ip = selectHstField_syslog.options[selectHstField_syslog.selectedIndex].text;
    ip = ip.substring(0, ip.indexOf('['));
    var url = "syslog_get?s_dt_syslog=" + escape(sdtField_syslog.value) +
            "&e_dt_syslog=" + escape(edtField_syslog.value) +
            "&sel_group_syslog=" + escape(selectGrField_syslog.value) +
            "&sel_host_syslog=" + escape(ip) +
            "&sel_level_syslog=" + escape(selectLevelField_syslog.value) +
            "&ed_msg_syslog=" + escape(msgField_syslog.value);
    req_syslog = initRequest();
    req_syslog.open("GET", url, true);
    req_syslog.onreadystatechange = callback_syslog;
    req_syslog.send(null);
}

function callback_syslog() {
    clearTable_syslog();
    if (req_syslog.readyState === 4) {
        if (req_syslog.status === 200) {
            parseMessages_syslog(req_syslog.responseXML);
        }
    }
}

function clearTable_syslog() {
    if (ResTableSyslog.getElementsByTagName("tr").length > 0) {
        //ResTableDhcpo.style.display = 'none';
        for (loop = ResTableSyslog.childNodes.length - 1; loop >= 2; loop--) {
            ResTableSyslog.removeChild(ResTableSyslog.childNodes[loop]);
        }
    }
}

function parseMessages_syslog(responseXML) {
    if (responseXML == null) {
        return false;
    } else {
        var logs_syslog = responseXML.getElementsByTagName("logs_syslog")[0];

        if (logs_syslog.childNodes.length > 0) {
//            completeTable.setAttribute("bordercolor", "black"); ptime,pheader,pmessage,pserver
//            completeTable.setAttribute("border", "1");
            for (loop = 0; loop < logs_syslog.childNodes.length; loop++) {
                var log_row = logs_syslog.childNodes[loop];
                var name = log_row.getElementsByTagName("name")[0];
                var facility = log_row.getElementsByTagName("facility")[0];
                var level = log_row.getElementsByTagName("level")[0];
                var host = log_row.getElementsByTagName("host")[0];
                var date = log_row.getElementsByTagName("date")[0];
                var message = log_row.getElementsByTagName("message")[0];
                appendRow_syslog(name.childNodes[0].nodeValue,
                        facility.childNodes[0].nodeValue,
                        level.childNodes[0].nodeValue,
                        host.childNodes[0].nodeValue,
                        date.childNodes[0].nodeValue,
                        message.childNodes[0].nodeValue);
            }
        }
    }
}

function appendRow_syslog(pname, pfacility, plevel, phost, pdate, pmessage) {
    var cell1, cell2, cell3, cell4, cell5, cell6;
    if (isIE) {
        ResTableSyslog.style.display = 'block';
        row = ResTableSyslog.insertRow(ResTableSyslog.rows.length);
        cell1 = row.insertCell(0);
        cell2 = row.insertCell(1);
        cell3 = row.insertCell(2);
        cell4 = row.insertCell(3);
        cell5 = row.insertCell(4);
        cell6 = row.insertCell(5);
    } else {
        ResTableSyslog.style.display = 'table';
        ResTableSyslog.style.class = "table table-hover";
        row = document.createElement("tr");
        cell1 = document.createElement("td");
        cell2 = document.createElement("td");
        cell3 = document.createElement("td");
        cell4 = document.createElement("td");
        cell5 = document.createElement("td");
        cell6 = document.createElement("td");
        row.appendChild(cell1);
        row.appendChild(cell2);
        row.appendChild(cell3);
        row.appendChild(cell4);
        row.appendChild(cell5);
        row.appendChild(cell6);
        ResTableSyslog.appendChild(row);
    }
    Elem_pname = document.createTextNode(pname);
    cell1.appendChild(Elem_pname);
    Elem_pfacility = document.createTextNode(pfacility);
    cell2.appendChild(Elem_pfacility);
    Elem_plevel = document.createTextNode(plevel);
    cell3.appendChild(Elem_plevel);
    Elem_phost = document.createTextNode(phost);
    cell4.appendChild(Elem_phost);
    Elem_pdate = document.createTextNode(pdate);
    cell5.appendChild(Elem_pdate);
    Elem_pmessage = document.createTextNode(pmessage);
    cell6.appendChild(Elem_pmessage);
}

////////////////////////////////////////////////////////////////////////////

function Chg_gr_call_nofif() {
    var gr_id = selectCallGrField_notif.options[selectCallGrField_notif.selectedIndex].value;
    //clearAbonTable_notif();
    var url = "notif_abon_call_get?action=abon_list&gr_id=" + escape(gr_id);
    req_abon_notif = initRequest();
    req_abon_notif.open("GET", url, true);
    req_abon_notif.onreadystatechange = callback_abon_notif;
    req_abon_notif.send(null);
}

function clearAbonTable_notif() {
    if (AbonTable_notif.getElementsByTagName("tr").length > 0) {
        //ResTableDhcpo.style.display = 'none';
        for (loop = AbonTable_notif.childNodes.length - 1; loop >= 2; loop--) {
            AbonTable_notif.removeChild(AbonTable_notif.childNodes[loop]);
        }
    }
}

function callback_abon_notif() {
    clearAbonTable_notif();
    if (req_abon_notif.readyState === 4) {
        if (req_abon_notif.status === 200) {
            parseMessages_notif(req_abon_notif.responseXML);
        }
    }
}

function parseMessages_notif(responseXML) {
    // no matches returned
    if (responseXML == null) {
        return false;
    } else {
        var abon_calls_notif = responseXML.getElementsByTagName("abon_calls_notif")[0];

        if (abon_calls_notif.childNodes.length > 0) {
            for (loop = 0; loop < abon_calls_notif.childNodes.length; loop++) {
                var abon_calls_row = abon_calls_notif.childNodes[loop];
                var fio = abon_calls_row.getElementsByTagName("fio")[0];
                var post = abon_calls_row.getElementsByTagName("post")[0];
                var tel = abon_calls_row.getElementsByTagName("tel")[0];
                var gr_id = abon_calls_row.getElementsByTagName("gr_id")[0];
                var call = abon_calls_row.getElementsByTagName("call")[0];
                var id = abon_calls_row.getElementsByTagName("id")[0];
                var post_fin;
                var gr_id_fin;
                var id_fin;
                if (post.childNodes[0] !== undefined)
                    post_fin = post.childNodes[0].nodeValue;
                else
                    post_fin = "";
                if (gr_id.childNodes[0] !== undefined)
                    gr_id_fin = gr_id.childNodes[0].nodeValue;
                else
                    gr_id_fin = "";
                if (id.childNodes[0] !== undefined)
                    id_fin = id.childNodes[0].nodeValue;
                else
                    id_fin = "";
                appendAbonCallRow_notif(fio.childNodes[0].nodeValue,
                        post_fin,
                        tel.childNodes[0].nodeValue,
                        gr_id_fin,
                        call.childNodes[0].nodeValue,
                        id_fin);
            }
        }
    }
}

function appendAbonCallRow_notif(pfio, ppost, ptel, pgr_id, pcall, pid) {

    var row = document.createElement("tr");

    var td_chk = document.createElement("td");
    td_chk.style = "text-align: center; vertical-align: middle; padding:5px;";
//    <input type="checkbox" id="${abon[0]}" onchange="" value="${abon[5]}">
    var c = document.createElement('input');
    c.id = ptel;//pid+"_"+ptel;
    c.type = "checkbox";
    c.value = pgr_id;
    //c.onchange = "chk_changed_abon_call_nofif('"+ptel+"');";
    c.onchange = chk_changed_abon_call_nofif;
    //c.attachEvent("onchange",chk_changed_abon_call_nofif(ptel));
    if (pcall == "true")
        c.checked = true;
    td_chk.appendChild(c);
    row.appendChild(td_chk);

    var td_fio = document.createElement("td");
    td_fio.style = "padding-left:5px;";
    td_fio.appendChild(document.createTextNode(pfio));
    row.appendChild(td_fio);

    var td_tel = document.createElement("td");
    td_tel.style = "padding-left:5px;";
    td_tel.appendChild(document.createTextNode(ptel));
    row.appendChild(td_tel);

    var td_post = document.createElement("td");
    td_post.style = "padding-left:5px;";
    td_post.appendChild(document.createTextNode(ppost));
    row.appendChild(td_post);

    AbonTable_notif.appendChild(row);
    //document.getElementById(c.id).onclick=chk_changed_abon_call_nofif(ptel);

}

function Sel_All_abon_call_nofif() {
    PhoneList_notif = "";
    if (document.getElementById("id_abon_call_chk_all_notif").checked === true)
        chk = true;
    else
        chk = false;
    if (AbonTable_notif.getElementsByTagName("tr").length > 1) {
        for (loop = 1; loop < AbonTable_notif.childNodes.length - 1; loop++) {
            var items = AbonTable_notif.rows[loop].cells[0].getElementsByTagName("input");
            items.item(0).checked = chk;
            //items.item(0).onchange();
            PhoneList_notif = PhoneList_notif + items.item(0).id + ',';
        }
        //alert(AbonTable_notif.rows[1].cells[1].innerText);
        if (PhoneList_notif !== "") {
            PhoneList_notif = PhoneList_notif.substring(0, PhoneList_notif.length - 1);
            //alert(PhoneList_notif+" checked:"+document.getElementById("id_abon_call_chk_all_notif").checked);

            var url = "notif_abon_call_get?action=set_all_abon_by_gr_call&phonelist=" + escape(PhoneList_notif) + "&on=" + escape(chk);
            req_abon_notif = initRequest();
            req_abon_notif.open("GET", url, false);
            req_abon_notif.send(null);
        }
    }
}

function chk_changed_abon_call_nofif() {
//    if (this.checked===false) alert(this.id+"checked:false");
//    else alert(this.id+"checked:true");

    var url = "notif_abon_call_get?action=set_abon_call&phone=" + escape(this.id) + "&on=" + escape(this.checked);
    req_abon_notif = initRequest();
    req_abon_notif.open("GET", url, true);
    req_abon_notif.onreadystatechange = callback_chk_changed_abon_call_notif;
    req_abon_notif.send(null);
}

function callback_chk_changed_abon_call_notif() {
    if (req_abon_notif.readyState === 4) {
        if (req_abon_notif.status === 200) {
            var error_code_notif = req_abon_notif.responseXML.getElementsByTagName("set_abon_call_notif")[0];
            if (error_code_notif != undefined) {
//            var error_row = error_dhcpo.childNodes[0];
//            var error = error_row.getElementsByTagName("error_dhcpo")[0];
                alert("Ошибка: Сбой обращения к БД при отметке абонента для обзвона");
            }
        }
    }

}

function send_telegram_msg_notif(){
    //необходимо у text_msg_notif проверять на пробелы
    if (text_msg_notif.value!=="") {
        var now = new Date();
        var options = {day: 'numeric',month: 'long',year: 'numeric', hour: 'numeric', minute: 'numeric'};
        var strd = now.toLocaleString("ru-RU",options);
        strd = strd.replace("г.","");
        var id_chk_tlg_groups=document.getElementById("id_chk_tlg_gr_notif");
        var items = id_chk_tlg_groups.getElementsByTagName("input"), len, i;
        var str_grs="";
        var msg=strd+'/'+ text_msg_notif.value;
        for (i = 0, len = items.length; i < len; i += 1) {
            // Если текущий элемент является чекбоксом...
            if (items.item(i).type && items.item(i).type === "checkbox") 
                if (items.item(i).checked) str_grs+=items.item(i).value+',';
        }
        if (str_grs !== "") {
            str_grs = str_grs.substring(0, str_grs.length - 1);

            var url = "notif_tlg_get?action=send_tlg_msg&gr_list=" + escape(str_grs) + "&msg=" +  msg;
            req_tlg_notif = initRequest();
            req_tlg_notif.open("GET", url, true);
            req_tlg_notif.onreadystatechange = callback_send_telegram_msg_notif;
            req_tlg_notif.send(null);
        }
        else alert("Не выбрана группа");
    }
}

function callback_send_telegram_msg_notif(){
        if (req_tlg_notif.readyState === 4) {
        if (req_tlg_notif.status === 200) {
            var error_code_notif = req_tlg_notif.responseXML.getElementsByTagName("send_tlg_msg_notif")[0];
            if (error_code_notif !== undefined) {
//            var error_row = error_dhcpo.childNodes[0];
//            var error = error_row.getElementsByTagName("error_dhcpo")[0];
                alert("Ошибка: Сбой обращения к БД, собщение не отправлено");
            }
        }
    }
}

function autocall_notif(){
    if (text_msg_notif.value!=="") {
        var now = new Date();
        var options = {day: 'numeric',month: 'long',year: 'numeric', hour: 'numeric', minute: 'numeric'};
        var strd = now.toLocaleString("ru-RU",options);
        strd = strd.replace("г.","");
        var msg=strd+', '+ text_msg_notif.value;
        
        var url = "notif_abon_call_get?action=autocall&msg=" + msg;
        req_tlg_notif = initRequest();
        req_tlg_notif.open("GET", url, true);
        req_tlg_notif.onreadystatechange = callback_autocall_notif;
        req_tlg_notif.send(null);
    }
    else alert("Не заполнено поле [сообщение]");
}

function callback_autocall_notif(){
        if (req_tlg_notif.readyState === 4) {
        if (req_tlg_notif.status === 200) {
            var error_code_notif = req_tlg_notif.responseXML.getElementsByTagName("send_autocall_notif")[0];
            if (error_code_notif !== undefined) {
                alert("Ошибка: Сбой обращения к серверу asterisk, автообзвон не выполнен");
            }
        }
    }
}

////////////////////////////////////////////////////////////////////////////

function Get_log_abon_call_notif_r(){
        var url = "notif_abon_call_get?action=list_call_log&s_dt_notif_r=" + escape(sdtField_notif_r.value) +
            "&e_dt_notif_r=" + escape(edtField_notif_r.value) +
            "&chk_arch_notif_r=" + escape(archField_notif_r.checked) +
            "&chk_ltc_notif_r=" + escape(ltcField_notif_r.checked) +
            "&ed_phone_notif_r=" + escape(filter_phone_notif_r.value);
    
        req_notif_r = initRequest();
        req_notif_r.open("GET", url, true);
        req_notif_r.onreadystatechange = callback_abon_call_log_notif_r;
        req_notif_r.send(null);
}

function callback_abon_call_log_notif_r(){
    clearTable_notif_r();
    if (req_notif_r.readyState === 4) {
        if (req_notif_r.status === 200) {
            parseMessages_notif_r(req_notif_r.responseXML);
        }
    }
}

function clearTable_notif_r(){
    if (ResTable_notif_r.getElementsByTagName("tr").length > 0) {
        //ResTableDhcpo.style.display = 'none';
        for (loop = ResTable_notif_r.childNodes.length - 1; loop >= 2; loop--) {
            ResTable_notif_r.removeChild(ResTable_notif_r.childNodes[loop]);
        }
    }    
}

function parseMessages_notif_r(responseXML) {
    if (responseXML == null) {
        return false;
    } else {
        var logs_notif_r = responseXML.getElementsByTagName("call_logs_notif_r")[0];

        if (logs_notif_r.childNodes.length > 0) {
//            completeTable.setAttribute("bordercolor", "black"); ptime,pheader,pmessage,pserver
//            completeTable.setAttribute("border", "1");
            for (loop = 0; loop < logs_notif_r.childNodes.length; loop++) {
                var log_row = logs_notif_r.childNodes[loop];
                var fio = log_row.getElementsByTagName("fio")[0];
                var calldate = log_row.getElementsByTagName("calldate")[0];
                var dst = log_row.getElementsByTagName("dst")[0];
                var disposition = log_row.getElementsByTagName("disposition")[0];
                appendRow_notif_r(loop+1,fio.childNodes[0].nodeValue,
                        calldate.childNodes[0].nodeValue,
                        dst.childNodes[0].nodeValue,
                        disposition.childNodes[0].nodeValue);
            }
        }
    }
}

function appendRow_notif_r(pind,pfio, pcalldate, pdst, pdisposition) {

    var row = document.createElement("tr");

    var td_num = document.createElement("td");
    td_num.style = "padding-left:5px;";
    td_num.appendChild(document.createTextNode(pind));
    row.appendChild(td_num);

    var td_fio = document.createElement("td");
    td_fio.style = "padding-left:5px;";
    td_fio.appendChild(document.createTextNode(pfio));
    row.appendChild(td_fio);

    var td_calldate = document.createElement("td");
    td_calldate.style = "padding-left:5px;";
    td_calldate.appendChild(document.createTextNode(pcalldate));
    row.appendChild(td_calldate);

    var td_dst = document.createElement("td");
    td_dst.style = "padding-left:5px;";
    td_dst.appendChild(document.createTextNode(pdst));
    row.appendChild(td_dst);

    var td_disposition = document.createElement("td");
    td_disposition.style = "padding-left:5px;";
    td_disposition.appendChild(document.createTextNode(pdisposition));
    row.appendChild(td_disposition);

    ResTable_notif_r.appendChild(row);
    //document.getElementById(c.id).onclick=chk_changed_abon_call_nofif(ptel);

}

function Set_active_date_notif_r(){
 if (archField_notif_r.checked)  {
     if (sdtField_notif_r.disabled === false) sdtField_notif_r.disabled = true;
     else sdtField_notif_r.disabled = false;
     if (edtField_notif_r.disabled === false) edtField_notif_r.disabled = true;
     else edtField_notif_r.disabled = false;
 }
 else { 
     sdtField_notif_r.disabled = true; edtField_notif_r.disabled = true;
 }
}

function Set_active_ltc_notif_r(){
    if (ltcField_notif_r.checked) {
        if (filter_phone_notif_r.disabled === true) filter_phone_notif_r.disabled = false;
        else filter_phone_notif_r.disabled = true;
    }
    else filter_phone_notif_r.disabled = false;
}

/////////////////////////////////////////////////////////////////////////////

function Set_active_date_dslam(){
 if (archField_dslam.checked)  {
     if (sdtField_dslam.disabled === false) sdtField_dslam.disabled = true;
     else sdtField_dslam.disabled = false;
     if (edtField_dslam.disabled === false) edtField_dslam.disabled = true;
     else edtField_dslam.disabled = false;
 }
 else { 
     sdtField_dslam.disabled = true; edtField_dslam.disabled = true;
 }
}

function find_dslam(){
    var url = "dslam_get?action=find_dslam_ports&s_dt_dslam=" + escape(sdtField_dslam.value) +
            "&e_dt_dslam=" + escape(edtField_dslam.value) +
            "&chk_arch_dslam=" + escape(archField_dslam.checked) +
            "&chk_no_binds_dslam=" + escape(NoBindsField_dslam.checked) +
            "&ed_host_dslam=" + escape(HostField_dslam.value) +
            "&ed_port_dslam=" + escape(PortField_dslam.value) +
            "&ed_bind_dslam=" + escape(BindField_dslam.value) +
            "&ed_adres_dslam=" + AdresField_dslam.value +
            "&ed_dom_dslam=" + escape(DomField_dslam.value); 

    req_dslam = initRequest();
    req_dslam.open("GET", url, true);
    req_dslam.onreadystatechange = callback_find_dslam;
    req_dslam.send(null);   
}

function callback_find_dslam(){
    clearTable_dslam();
    if (req_dslam.readyState === 4) {
        if (req_dslam.status === 200) {
            parseMessages_dslam(req_dslam.responseXML);
        }
    }
}

function clearTable_dslam(){
    if (ResTable_dslam.getElementsByTagName("tr").length > 0) {
        //ResTableDhcpo.style.display = 'none';
        for (loop = ResTable_dslam.childNodes.length - 1; loop >= 1; loop--) {
            ResTable_dslam.removeChild(ResTable_dslam.childNodes[loop]);
        }
    }    
}

function parseMessages_dslam(responseXML) {
    if (responseXML == null) {
        return false;
    } else {
//        var ports_dslam = responseXML.getElementsByTagName("dslam_ports_dslam")[0];
        var ports_dslam = responseXML.getElementsByTagName("dslam_ports_list")[0];

        if (ports_dslam !== undefined) {
//            completeTable.setAttribute("bordercolor", "black"); ptime,pheader,pmessage,pserver
//            completeTable.setAttribute("border", "1");
            SetColumsNames(0);
            
            for (loop = 0; loop < ports_dslam.childNodes.length; loop++) {
                var ports_row = ports_dslam.childNodes[loop];
                
                var host_name = ports_row.getElementsByTagName("host_name")[0];
                var host = ports_row.getElementsByTagName("host")[0];
                var port = ports_row.getElementsByTagName("port")[0];
                var status = ports_row.getElementsByTagName("status")[0];
                var ip_line = ports_row.getElementsByTagName("ip_line")[0];
                var profile_line = ports_row.getElementsByTagName("profile_line")[0];
                var profile_iptv = ports_row.getElementsByTagName("profile_iptv")[0];
                var vlan_line = ports_row.getElementsByTagName("vlan_line")[0];
                var pvc_line = ports_row.getElementsByTagName("pvc_line")[0];
                var vlan_iptv = ports_row.getElementsByTagName("vlan_iptv")[0];
                var pvc_iptv = ports_row.getElementsByTagName("pvc_iptv")[0];
                var ed_snr = ports_row.getElementsByTagName("ed_snr")[0];
                var eda = ports_row.getElementsByTagName("eda")[0];
                var eusnr = ports_row.getElementsByTagName("eusnr")[0];
                var eua = ports_row.getElementsByTagName("eua")[0];
                var date = ports_row.getElementsByTagName("date")[0];
                
                appendRow_ports_dslam("",
                        host_name.childNodes[0].nodeValue,
                        host.childNodes[0].nodeValue,
                        port.childNodes[0].nodeValue,
                        status.childNodes[0].nodeValue,
                        ip_line.childNodes[0].nodeValue,
                        profile_line.childNodes[0].nodeValue,
                        profile_iptv.childNodes[0]!==undefined?profile_iptv.childNodes[0].nodeValue:"",
                        vlan_line.childNodes[0].nodeValue,
                        pvc_line.childNodes[0].nodeValue,
                        vlan_iptv.childNodes[0]!==undefined?vlan_iptv.childNodes[0].nodeValue:"",
                        pvc_iptv.childNodes[0]!==undefined?pvc_iptv.childNodes[0].nodeValue:"",
                        ed_snr.childNodes[0].nodeValue,
                        eda.childNodes[0].nodeValue,
                        eusnr.childNodes[0].nodeValue,
                        eua.childNodes[0].nodeValue,
                        date.childNodes[0].nodeValue
                       );
            }
        }
        
        var stat_dslam = responseXML.getElementsByTagName("dslam_ports_stat")[0];

        if (stat_dslam !== undefined) { //stat_dslam.childNodes.length > 0
            for (loop = 0; loop < stat_dslam.childNodes.length; loop++) {
                var ports_row = stat_dslam.childNodes[loop];
                
                var total_stat = ports_row.getElementsByTagName("total_stat")[0];
                var acting_stat = ports_row.getElementsByTagName("acting_stat")[0];
                var acted_stat = ports_row.getElementsByTagName("acted_stat")[0];
                var deacted_stat = ports_row.getElementsByTagName("deacted_stat")[0];
                var nobinds_stat = ports_row.getElementsByTagName("nobinds_stat")[0];
                
                document.getElementById("id_ports_total_stat_dslam").innerText = total_stat.childNodes[0].nodeValue;
                document.getElementById("id_ports_acting_stat_dslam").innerText = acting_stat.childNodes[0].nodeValue;
                document.getElementById("id_ports_acted_stat_dslam").innerText = acted_stat.childNodes[0].nodeValue;
                document.getElementById("id_ports_deacted_stat_dslam").innerText = deacted_stat.childNodes[0].nodeValue;
                document.getElementById("id_ports_nobinds_stat_dslam").innerText = nobinds_stat.childNodes[0].nodeValue;

            }
        }

        var ports_dslam_adr = responseXML.getElementsByTagName("dslam_ports_adr_dslam")[0];

        if (ports_dslam_adr !== undefined) {
//            completeTable.setAttribute("bordercolor", "black"); ptime,pheader,pmessage,pserver
//            completeTable.setAttribute("border", "1");
            SetColumsNames(1);
            
            for (loop = 0; loop < ports_dslam_adr.childNodes.length; loop++) {
                var ports_row = ports_dslam_adr.childNodes[loop];
                
                var adr = ports_row.getElementsByTagName("adres")[0];
                var host_name = ports_row.getElementsByTagName("host_name")[0];
                var host = ports_row.getElementsByTagName("host")[0];
                var port = ports_row.getElementsByTagName("port")[0];
                var status = ports_row.getElementsByTagName("status")[0];
                var ip_line = ports_row.getElementsByTagName("ip_line")[0];
                var profile_line = ports_row.getElementsByTagName("profile_line")[0];
                var profile_iptv = ports_row.getElementsByTagName("profile_iptv")[0];
                var vlan_line = ports_row.getElementsByTagName("vlan_line")[0];
                var pvc_line = ports_row.getElementsByTagName("pvc_line")[0];
                var vlan_iptv = ports_row.getElementsByTagName("vlan_iptv")[0];
                var pvc_iptv = ports_row.getElementsByTagName("pvc_iptv")[0];
                var ed_snr = ports_row.getElementsByTagName("ed_snr")[0];
                var eda = ports_row.getElementsByTagName("eda")[0];
                var eusnr = ports_row.getElementsByTagName("eusnr")[0];
                var eua = ports_row.getElementsByTagName("eua")[0];
                var date = ports_row.getElementsByTagName("date")[0];
                
                appendRow_ports_dslam(
                        adr.childNodes[0].nodeValue!==undefined?adr.childNodes[0].nodeValue:"",
                        host_name.childNodes[0].nodeValue,
                        host.childNodes[0].nodeValue,
                        port.childNodes[0].nodeValue,
                        status.childNodes[0].nodeValue,
                        ip_line.childNodes[0].nodeValue,
                        profile_line.childNodes[0].nodeValue,
                        profile_iptv.childNodes[0]!==undefined?profile_iptv.childNodes[0].nodeValue:"",
                        vlan_line.childNodes[0].nodeValue,
                        pvc_line.childNodes[0].nodeValue,
                        vlan_iptv.childNodes[0]!==undefined?vlan_iptv.childNodes[0].nodeValue:"",
                        pvc_iptv.childNodes[0]!==undefined?pvc_iptv.childNodes[0].nodeValue:"",
                        ed_snr.childNodes[0].nodeValue,
                        eda.childNodes[0].nodeValue,
                        eusnr.childNodes[0].nodeValue,
                        eua.childNodes[0].nodeValue,
                        date.childNodes[0].nodeValue
                       );
            }
        }

        var dup_binds_dslam = responseXML.getElementsByTagName("dslam_dup_binds")[0];

        if (dup_binds_dslam !== undefined) {
//            completeTable.setAttribute("bordercolor", "black"); ptime,pheader,pmessage,pserver
//            completeTable.setAttribute("border", "1");
            SetColumsNames(2);
            
            for (loop = 0; loop < dup_binds_dslam.childNodes.length; loop++) {
                var binds_row = dup_binds_dslam.childNodes[loop];
                
                var host_name = binds_row.getElementsByTagName("host_name")[0];
                var host = binds_row.getElementsByTagName("host")[0];
                var port = binds_row.getElementsByTagName("port")[0];
                var status = binds_row.getElementsByTagName("status")[0];
                var ip_line = binds_row.getElementsByTagName("ip_line")[0];
                var date = binds_row.getElementsByTagName("date")[0];
                
                appendRow_dup_binds_dslam(
                        host_name.childNodes[0]!==undefined?host_name.childNodes[0].nodeValue:"",
                        host.childNodes[0].nodeValue,
                        port.childNodes[0].nodeValue,
                        status.childNodes[0].nodeValue,
                        ip_line.childNodes[0]!==undefined?ip_line.childNodes[0].nodeValue:"",
                        date.childNodes[0].nodeValue
                       );
            }
        }


        var svod_dslam = responseXML.getElementsByTagName("dslam_svod")[0];

        if (svod_dslam !== undefined) {
//            completeTable.setAttribute("bordercolor", "black"); ptime,pheader,pmessage,pserver
//            completeTable.setAttribute("border", "1");
            SetColumsNames(3);
            
            for (loop = 0; loop < svod_dslam.childNodes.length; loop++) {
                var svod_row = svod_dslam.childNodes[loop];
                
                var host_name = svod_row.getElementsByTagName("host_name")[0];
                var host = svod_row.getElementsByTagName("host")[0];
                var act = svod_row.getElementsByTagName("act")[0];
                var inact = svod_row.getElementsByTagName("inact")[0];
                var dis = svod_row.getElementsByTagName("dis")[0];
                var tot = svod_row.getElementsByTagName("tot")[0];
                var nob = svod_row.getElementsByTagName("nob")[0];
                
                appendRow_svod_dslam(
                        host_name.childNodes[0].nodeValue,
                        host.childNodes[0].nodeValue,
                        act.childNodes[0].nodeValue,
                        inact.childNodes[0].nodeValue,
                        dis.childNodes[0].nodeValue,
                        tot.childNodes[0].nodeValue,
                        nob.childNodes[0].nodeValue
                       );
            }
        }

    }
}

function appendRow_ports_dslam(padres,phost_name,phost, pport, pstatus, pip_line,pprofile_line,pprofile_iptv,pvlan_line,ppvc_line,pvlan_iptv,ppvc_iptv,ped_snr,peda,peusnr,peua,pdate) {

    var row = document.createElement("tr");

    if (padres!=="") {
        var td_adr = document.createElement("td");
        td_adr.style = "padding-left:5px;";
        td_adr.appendChild(document.createTextNode(padres));
        row.appendChild(td_adr);
    }
    
    var td_host_name = document.createElement("td");
    td_host_name.style = "padding-left:5px;";
    td_host_name.appendChild(document.createTextNode(phost_name));
    row.appendChild(td_host_name);

    var td_host = document.createElement("td");
    td_host.style = "padding-left:5px;";
    td_host.appendChild(document.createTextNode(phost));
    row.appendChild(td_host);

    var td_port = document.createElement("td");
    td_port.style = "padding-left:5px;";
    td_port.appendChild(document.createTextNode(pport));
    row.appendChild(td_port);

    var td_status = document.createElement("td");
    td_status.style = "padding-left:5px;";
    td_status.appendChild(document.createTextNode(pstatus));
    row.appendChild(td_status);

    var td_ip_line = document.createElement("td");
    td_ip_line.style = "padding-left:5px;";
    td_ip_line.appendChild(document.createTextNode(pip_line));
    row.appendChild(td_ip_line);

    var td_profile_line = document.createElement("td");
    td_profile_line.style = "padding-left:5px;";
    td_profile_line.appendChild(document.createTextNode(pprofile_line));
    row.appendChild(td_profile_line);

    var td_profile_iptv = document.createElement("td");
    td_profile_iptv.style = "padding-left:5px;";
    td_profile_iptv.appendChild(document.createTextNode(pprofile_iptv));
    row.appendChild(td_profile_iptv);

    var td_vlan_line = document.createElement("td");
    td_vlan_line.style = "padding-left:5px;";
    td_vlan_line.appendChild(document.createTextNode(pvlan_line));
    row.appendChild(td_vlan_line);

    var td_pvc_line = document.createElement("td");
    td_pvc_line.style = "padding-left:5px;";
    td_pvc_line.appendChild(document.createTextNode(ppvc_line));
    row.appendChild(td_pvc_line);

    var td_vlan_iptv = document.createElement("td");
    td_vlan_iptv.style = "padding-left:5px;";
    td_vlan_iptv.appendChild(document.createTextNode(pvlan_iptv));
    row.appendChild(td_vlan_iptv);

    var td_pvc_iptv = document.createElement("td");
    td_pvc_iptv.style = "padding-left:5px;";
    td_pvc_iptv.appendChild(document.createTextNode(ppvc_iptv));
    row.appendChild(td_pvc_iptv);

    var td_ed_snr = document.createElement("td");
    td_ed_snr.style = "padding-left:5px;";
    td_ed_snr.appendChild(document.createTextNode(ped_snr));
    row.appendChild(td_ed_snr);

    var td_eda = document.createElement("td");
    td_eda.style = "padding-left:5px;";
    td_eda.appendChild(document.createTextNode(peda));
    row.appendChild(td_eda);

    var td_eusnr = document.createElement("td");
    td_eusnr.style = "padding-left:5px;";
    td_eusnr.appendChild(document.createTextNode(peusnr));
    row.appendChild(td_eusnr);

    var td_eua = document.createElement("td");
    td_eua.style = "padding-left:5px;";
    td_eua.appendChild(document.createTextNode(peua));
    row.appendChild(td_eua);

    var td_date = document.createElement("td");
    td_date.style = "padding-left:5px;";
    td_date.appendChild(document.createTextNode(pdate));
    row.appendChild(td_date);

    ResTable_dslam.appendChild(row);
    //document.getElementById(c.id).onclick=chk_changed_abon_call_nofif(ptel);

}

function SetColumsNames(view){
    var row = document.createElement('tr');
    row.style.backgroundColor = "#D1EEEE";
    if (view===1) {
        var td_adres = document.createElement("th");
        td_adres.style = "padding-left:5px;";
        td_adres.appendChild(document.createTextNode("Адрес"));
        row.appendChild(td_adres);
    }
    var td_host_name = document.createElement("th");
    td_host_name.style = "padding-left:5px;";
    td_host_name.appendChild(document.createTextNode("Имя Хоста"));
    row.appendChild(td_host_name);

    var td_host = document.createElement("th");
    td_host.style = "padding-left:5px;";
    td_host.appendChild(document.createTextNode("Хост"));
    row.appendChild(td_host);

    if (view!==3) {
        var td_port = document.createElement("th");
        td_port.style = "padding-left:5px;";
        td_port.appendChild(document.createTextNode("Порт"));
        row.appendChild(td_port);

        var td_status = document.createElement("th");
        td_status.style = "padding-left:5px;";
        td_status.appendChild(document.createTextNode("Статус"));
        row.appendChild(td_status);

        var td_ip_line = document.createElement("th");
        td_ip_line.style = "padding-left:5px;";
        td_ip_line.appendChild(document.createTextNode("Бинды"));
        row.appendChild(td_ip_line);

        if (view !== 2) {
            var td_profile_line = document.createElement("th");
            td_profile_line.style = "padding-left:5px;";
            td_profile_line.appendChild(document.createTextNode("Профиль"));
            row.appendChild(td_profile_line);

            var td_profile_iptv = document.createElement("th");
            td_profile_iptv.style = "padding-left:5px;";
            td_profile_iptv.appendChild(document.createTextNode("Профили iptv"));
            row.appendChild(td_profile_iptv);

            var td_vlan_line = document.createElement("th");
            td_vlan_line.style = "padding-left:5px;";
            td_vlan_line.appendChild(document.createTextNode("VLAN линии"));
            row.appendChild(td_vlan_line);

            var td_pvc_line = document.createElement("th");
            td_pvc_line.style = "padding-left:5px;";
            td_pvc_line.appendChild(document.createTextNode("PVC линии"));
            row.appendChild(td_pvc_line);

            var td_vlan_iptv = document.createElement("th");
            td_vlan_iptv.style = "padding-left:5px;";
            td_vlan_iptv.appendChild(document.createTextNode("VLAN iptv"));
            row.appendChild(td_vlan_iptv);

            var td_pvc_iptv = document.createElement("th");
            td_pvc_iptv.style = "padding-left:5px;";
            td_pvc_iptv.appendChild(document.createTextNode("PVC iptv"));
            row.appendChild(td_pvc_iptv);

            var td_ed_snr = document.createElement("th");
            td_ed_snr.style = "padding-left:5px;";
            td_ed_snr.appendChild(document.createTextNode("Down SNR"));
            row.appendChild(td_ed_snr);

            var td_eda = document.createElement("th");
            td_eda.style = "padding-left:5px;";
            td_eda.appendChild(document.createTextNode("Down ATTEN"));
            row.appendChild(td_eda);

            var td_eusnr = document.createElement("th");
            td_eusnr.style = "padding-left:5px;";
            td_eusnr.appendChild(document.createTextNode("Up SNR"));
            row.appendChild(td_eusnr);

            var td_eua = document.createElement("th");
            td_eua.style = "padding-left:5px;";
            td_eua.appendChild(document.createTextNode("Up ATTEN"));
            row.appendChild(td_eua);
        }
        var td_date = document.createElement("th");
        td_date.style = "padding-left:5px;";
        td_date.appendChild(document.createTextNode("Дата обновления"));
        row.appendChild(td_date);
    }
    else {
        var td_act = document.createElement("th");
        td_act.style = "padding-left:5px;";
        td_act.appendChild(document.createTextNode("Кол-во активных портов"));
        row.appendChild(td_act);        
        
        var td_inact = document.createElement("th");
        td_inact.style = "padding-left:5px;";
        td_inact.appendChild(document.createTextNode("Кол-во неактивных портов"));
        row.appendChild(td_inact);        
        
        var td_dis= document.createElement("th");
        td_dis.style = "padding-left:5px;";
        td_dis.appendChild(document.createTextNode("Кол-во отключенных портов"));
        row.appendChild(td_dis);        
        
        var td_tot = document.createElement("th");
        td_tot.style = "padding-left:5px;";
        td_tot.appendChild(document.createTextNode("Общее число портов"));
        row.appendChild(td_tot);        
        
        var td_nob = document.createElement("th");
        td_nob.style = "padding-left:5px;";
        td_nob.appendChild(document.createTextNode("Портов без ip bind"));
        row.appendChild(td_nob);        
    }
    ResTable_dslam.appendChild(row);
    
}

function ip_onfocus_dslam(){
    AdresField_dslam.value = "";
}

function adres_onfocus_dslam(){
    HostField_dslam.value = "";
}

function Set_nobinds_dslam() {
    if (NoBindsField_dslam.checked) {
        if (BindField_dslam.disabled === true) {
            BindField_dslam.value = "";
            BindField_dslam.disabled = false;
        } else {
            BindField_dslam.value = "NA";
            BindField_dslam.disabled = true;
        }
    } else {
        BindField_dslam.value = "";
        BindField_dslam.disabled = false;
    }
 }
 
 function find_dup_binds_dslam() {
     
    var url = "dslam_get?action=find_dslam_dup_binds&s_dt_dslam=" + escape(sdtField_dslam.value) +
            "&e_dt_dslam=" + escape(edtField_dslam.value) +
            "&chk_arch_dslam=" + escape(archField_dslam.checked) +
            "&ed_host_dslam=" + escape(HostField_dslam.value) +
            "&ed_bind_dslam=" + escape(BindField_dslam.value);

    req_dslam = initRequest();
    req_dslam.open("GET", url, true);
    req_dslam.onreadystatechange = callback_find_dslam;
    req_dslam.send(null);   
     
}

function appendRow_dup_binds_dslam(phost_name,phost, pport, pstatus, pip_line,pdate) {

    var row = document.createElement("tr");

    var td_host_name = document.createElement("td");
    td_host_name.style = "padding-left:5px;";
    td_host_name.appendChild(document.createTextNode(phost_name));
    row.appendChild(td_host_name);

    var td_host = document.createElement("td");
    td_host.style = "padding-left:5px;";
    td_host.appendChild(document.createTextNode(phost));
    row.appendChild(td_host);

    var td_port = document.createElement("td");
    td_port.style = "padding-left:5px;";
    td_port.appendChild(document.createTextNode(pport));
    row.appendChild(td_port);

    var td_status = document.createElement("td");
    td_status.style = "padding-left:5px;";
    td_status.appendChild(document.createTextNode(pstatus));
    row.appendChild(td_status);

    var td_ip_line = document.createElement("td");
    td_ip_line.style = "padding-left:5px;";
    td_ip_line.appendChild(document.createTextNode(pip_line));
    row.appendChild(td_ip_line);

    var td_date = document.createElement("td");
    td_date.style = "padding-left:5px;";
    td_date.appendChild(document.createTextNode(pdate));
    row.appendChild(td_date);

    ResTable_dslam.appendChild(row);
    //document.getElementById(c.id).onclick=chk_changed_abon_call_nofif(ptel);

}

function svod_dslam() {
       var url = "dslam_get?action=svod_dslam&s_dt_dslam=" + escape(sdtField_dslam.value) +
            "&e_dt_dslam=" + escape(edtField_dslam.value) +
            "&chk_arch_dslam=" + escape(archField_dslam.checked);

    req_dslam = initRequest();
    req_dslam.open("GET", url, true);
    req_dslam.onreadystatechange = callback_find_dslam;
    req_dslam.send(null);    
}

function appendRow_svod_dslam(phost_name,phost,pact,pinact,pdis,ptot,pnob) {

    var row = document.createElement("tr");

    var td_host_name = document.createElement("td");
    td_host_name.style = "padding-left:5px;";
    td_host_name.appendChild(document.createTextNode(phost_name));
    row.appendChild(td_host_name);

    var td_host = document.createElement("td");
    td_host.style = "padding-left:5px;";
    td_host.appendChild(document.createTextNode(phost));
    row.appendChild(td_host);

    var td_act = document.createElement("td");
    td_act.style = "padding-left:5px;";
    td_act.appendChild(document.createTextNode(pact));
    row.appendChild(td_act);

    var td_inact = document.createElement("td");
    td_inact.style = "padding-left:5px;";
    td_inact.appendChild(document.createTextNode(pinact));
    row.appendChild(td_inact);

    var td_dis = document.createElement("td");
    td_dis.style = "padding-left:5px;";
    td_dis.appendChild(document.createTextNode(pdis));
    row.appendChild(td_dis);

    var td_tot = document.createElement("td");
    td_tot.style = "padding-left:5px;";
    td_tot.appendChild(document.createTextNode(ptot));
    row.appendChild(td_tot);

    var td_nob = document.createElement("td");
    td_nob.style = "padding-left:5px;";
    td_nob.appendChild(document.createTextNode(pnob));
    row.appendChild(td_nob);

    ResTable_dslam.appendChild(row);
    //document.getElementById(c.id).onclick=chk_changed_abon_call_nofif(ptel);

}

//////////////////////////////////////////////////////////////////////////////

function callback_get_netlist_dslam_s() {
    clearTable_dslam_s();
    if (req_dslam_s.readyState === 4) {
        if (req_dslam_s.status === 200) {
            parseMessages_dslam_s(req_dslam_s.responseXML);
        }
    }    
}

function clearTable_dslam_s() {
    if (ResTable_dslam_s.getElementsByTagName("tr").length > 0) {
        //ResTableDhcpo.style.display = 'none';
        for (loop = ResTable_dslam_s.childNodes.length - 1; loop >= 1; loop--) {
            ResTable_dslam_s.removeChild(ResTable_dslam_s.childNodes[loop]);
        }
    }
}

function SetColumnsNames_dslam_s(view) {
    var row = document.createElement('tr');
    row.style.backgroundColor = "#D1EEEE";
    if (view === 1) {
        var td_chg = document.createElement("th");
        td_chg.style = "padding-left:5px;";
        td_chg.appendChild(document.createTextNode("Изменить"));
        row.appendChild(td_chg);

        var td_del = document.createElement("th");
        td_del.style = "padding-left:5px;";
        td_del.appendChild(document.createTextNode("Удалить"));
        row.appendChild(td_del);

        var td_model = document.createElement("th");
        td_model.style = "padding-left:5px;";
        td_model.appendChild(document.createTextNode("Модель"));
        row.appendChild(td_model);
        
        var td_filifal = document.createElement("th");
        td_filifal.style = "padding-left:5px;";
        td_filifal.appendChild(document.createTextNode("Филиал"));
        row.appendChild(td_filifal);
         
        var td_conn = document.createElement("th");
        td_conn.style = "padding-left:5px;";
        td_conn.appendChild(document.createTextNode("Подключение"));
        row.appendChild(td_conn);

        var td_host = document.createElement("th");
        td_host.style = "padding-left:5px;";
        td_host.appendChild(document.createTextNode("Хост"));
        row.appendChild(td_host);

        var td_mon = document.createElement("th");
        td_mon.style = "padding-left:5px;";
        td_mon.appendChild(document.createTextNode("Монтир. емкость"));
        row.appendChild(td_mon);
        
        var td_zad = document.createElement("th");
        td_zad.style = "padding-left:5px;";
        td_zad.appendChild(document.createTextNode("Задейств. емкость"));
        row.appendChild(td_zad);
         
        var td_ipnet = document.createElement("th");
        td_ipnet.style = "padding-left:5px;";
        td_ipnet.appendChild(document.createTextNode("IP Сеть"));
        row.appendChild(td_ipnet);
        
     }
     else if (view === 2 || view === 3) {
        if (view === 3) {
            var td_num = document.createElement("th");
            td_num.style = "padding-left:5px;";
            td_num.appendChild(document.createTextNode("№"));
            row.appendChild(td_num);

            var td_model = document.createElement("th");
            td_model.style = "padding-left:5px;";
            td_model.appendChild(document.createTextNode("Модель"));
            row.appendChild(td_model);

            var td_punkt = document.createElement("th");
            td_punkt.style = "padding-left:5px;";
            td_punkt.appendChild(document.createTextNode("Нас.пункт"));
            row.appendChild(td_punkt);

            var td_hname = document.createElement("th");
            td_hname.style = "padding-left:5px;";
            td_hname.appendChild(document.createTextNode("Имя хоста"));
            row.appendChild(td_hname);
        }
        var td_host = document.createElement("th");
        td_host.style = "padding-left:5px;";
        td_host.appendChild(document.createTextNode("Хост"));
        row.appendChild(td_host);

        var td_port = document.createElement("th");
        td_port.style = "padding-left:5px;";
        td_port.appendChild(document.createTextNode("Порт"));
        row.appendChild(td_port);

        var td_status = document.createElement("th");
        td_status.style = "padding-left:5px;";
        td_status.appendChild(document.createTextNode("Статус"));
        row.appendChild(td_status);

        var td_ip_liner = document.createElement("th");
        td_ip_liner.style = "padding-left:5px;";
        td_ip_liner.appendChild(document.createTextNode("Бинды"));
        row.appendChild(td_ip_liner);

        var td_ip_linep = document.createElement("th");
        td_ip_linep.style = "padding-left:5px;";
        td_ip_linep.appendChild(document.createTextNode("Бинды по прописке"));
        row.appendChild(td_ip_linep);

        if (view === 3) {
            var td_profile_line = document.createElement("th");
            td_profile_line.style = "padding-left:5px;";
            td_profile_line.appendChild(document.createTextNode("Профиль"));
            row.appendChild(td_profile_line);

            var td_profile_iptv = document.createElement("th");
            td_profile_iptv.style = "padding-left:5px;";
            td_profile_iptv.appendChild(document.createTextNode("Профили iptv"));
            row.appendChild(td_profile_iptv);

            var td_vlan_line = document.createElement("th");
            td_vlan_line.style = "padding-left:5px;";
            td_vlan_line.appendChild(document.createTextNode("VLAN линии"));
            row.appendChild(td_vlan_line);

            var td_pvc_line = document.createElement("th");
            td_pvc_line.style = "padding-left:5px;";
            td_pvc_line.appendChild(document.createTextNode("PVC линии"));
            row.appendChild(td_pvc_line);

            var td_vlan_iptv = document.createElement("th");
            td_vlan_iptv.style = "padding-left:5px;";
            td_vlan_iptv.appendChild(document.createTextNode("VLAN iptv"));
            row.appendChild(td_vlan_iptv);

            var td_pvc_iptv = document.createElement("th");
            td_pvc_iptv.style = "padding-left:5px;";
            td_pvc_iptv.appendChild(document.createTextNode("PVC iptv"));
            row.appendChild(td_pvc_iptv);

            var td_mac = document.createElement("th");
            td_mac.style = "padding-left:5px;";
            td_mac.appendChild(document.createTextNode("mac"));
            row.appendChild(td_mac);
        }
        var td_date = document.createElement("th");
        td_date.style = "padding-left:5px;";
        td_date.appendChild(document.createTextNode("Дата обновления"));
        row.appendChild(td_date);        
     }

    ResTable_dslam_s.appendChild(row);
    
}

function parseMessages_dslam_s(responseXML) {
    if (responseXML == null) {
        return false;
    } else {
        var nets_dslam_s = responseXML.getElementsByTagName("dslam_nets_list")[0];
        var rep1_dslam_s = responseXML.getElementsByTagName("rep_binds1_dslam_s")[0];
        var rep2_dslam_s = responseXML.getElementsByTagName("rep_binds2_dslam_s")[0];
        var err_rep_dslam_s = responseXML.getElementsByTagName("dslam_nets_err_rep")[0];

        if (nets_dslam_s !== undefined) {
            SetColumnsNames_dslam_s(1);
//            completeTable.setAttribute("bordercolor", "black"); ptime,pheader,pmessage,pserver
//            completeTable.setAttribute("border", "1");
            for (loop = 0; loop < nets_dslam_s.childNodes.length; loop++) {
                var net_row = nets_dslam_s.childNodes[loop];
                var model = net_row.getElementsByTagName("model")[0];
                var filial = net_row.getElementsByTagName("uzel")[0];
                var type = net_row.getElementsByTagName("chantype")[0];
                var host = net_row.getElementsByTagName("ipaddr")[0];
                var tot_p = net_row.getElementsByTagName("num_ports")[0];
                var use_p = net_row.getElementsByTagName("used_ports")[0];
                var net = net_row.getElementsByTagName("net")[0];
                appendRow_nets_dslam_s(model.childNodes[0].nodeValue,
                        filial.childNodes[0].nodeValue,
                        type.childNodes[0].nodeValue,
                        host.childNodes[0].nodeValue,
                        tot_p.childNodes[0].nodeValue,
                        use_p.childNodes[0].nodeValue,
                        net.childNodes[0].nodeValue);
            }
        }
        else if (rep1_dslam_s !== undefined) {
            $('#GetRepModal_dslam_s').modal('hide');
            SetColumnsNames_dslam_s(2);
            for (loop = 0; loop < rep1_dslam_s.childNodes.length; loop++) {
                var rep_row = rep1_dslam_s.childNodes[loop];
                var host = rep_row.getElementsByTagName("host")[0];
                var port = rep_row.getElementsByTagName("port")[0];
                var status = rep_row.getElementsByTagName("port_status")[0];
                var ip_liner = rep_row.getElementsByTagName("ip_liner")[0];
                var ip_linep = rep_row.getElementsByTagName("ip_linep")[0];
                var date = rep_row.getElementsByTagName("date")[0];
                appendRow_rep_dslam_s(1, '', '', '', '',
                        host.childNodes[0].nodeValue,
                        port.childNodes[0].nodeValue,
                        status.childNodes[0].nodeValue,
                        ip_liner.childNodes[0].nodeValue,
                        ip_linep.childNodes[0].nodeValue, '', '', '', '', '', '', '',
                        date.childNodes[0].nodeValue);
            }
        }
        else if (rep2_dslam_s !== undefined) {
            $('#GetRepModal_dslam_s').modal('hide');
            SetColumnsNames_dslam_s(3);
            for (loop = 0; loop < rep2_dslam_s.childNodes.length; loop++) {
                var rep_row = rep2_dslam_s.childNodes[loop];
                var num = rep_row.getElementsByTagName("num")[0];
                var model = rep_row.getElementsByTagName("model")[0];
                var punkt = rep_row.getElementsByTagName("punkt")[0];
                var hostname = rep_row.getElementsByTagName("hostname")[0];
                var host = rep_row.getElementsByTagName("host")[0];
                var port = rep_row.getElementsByTagName("port")[0];
                var status = rep_row.getElementsByTagName("port_status")[0];
                var ip_liner = rep_row.getElementsByTagName("ip_liner")[0];
                var ip_linep = rep_row.getElementsByTagName("ip_linep")[0];
                var prof = rep_row.getElementsByTagName("prof")[0];
                var prof_iptv = rep_row.getElementsByTagName("prof_iptv")[0];
                var vlan = rep_row.getElementsByTagName("vlan")[0];
                var pvc = rep_row.getElementsByTagName("pvc")[0];
                var vlan_iptv = rep_row.getElementsByTagName("vlan_iptv")[0];
                var pvc_iptv = rep_row.getElementsByTagName("pvc_iptv")[0];
                var mac = rep_row.getElementsByTagName("mac")[0];
                var date = rep_row.getElementsByTagName("date")[0];
                appendRow_rep_dslam_s(2,
                        num.childNodes[0].nodeValue,
                        model.childNodes[0].nodeValue,
                        punkt.childNodes[0].nodeValue!==undefined?punkt.childNodes[0].nodeValue:"",
                        hostname.childNodes[0].nodeValue!==undefined?hostname.childNodes[0].nodeValue:"",
                        host.childNodes[0].nodeValue,
                        port.childNodes[0].nodeValue,
                        status.childNodes[0].nodeValue,
                        ip_liner.childNodes[0].nodeValue,
                        ip_linep.childNodes[0].nodeValue,
                        prof.childNodes[0].nodeValue,
                        prof_iptv.childNodes[0]!==undefined?prof_iptv.childNodes[0].nodeValue:"",
                        vlan.childNodes[0].nodeValue,
                        pvc.childNodes[0].nodeValue,
                        vlan_iptv.childNodes[0]!==undefined?vlan_iptv.childNodes[0].nodeValue:"",
                        pvc_iptv.childNodes[0]!==undefined?pvc_iptv.childNodes[0].nodeValue:"",
                        mac.childNodes[0]!==undefined?mac.childNodes[0].nodeValue:"",
                        date.childNodes[0].nodeValue);
            }
        }
        else if (err_rep_dslam_s !== undefined) {
                document.getElementById("id_error_rep_dslam_s").innerText = err_rep_dslam_s.childNodes[0].nodeValue;            
        }
    }
}

function appendRow_nets_dslam_s(pmodel, pfilial, ptype, phost, ptot_p, puse_p, pnet) {

    var row = document.createElement("tr");

    var td_chg = document.createElement("td");
    td_chg.style = "text-align: center; vertical-align: middle; padding:5px;";

    var pc = document.createElement('a');
    pc.href = "JavaScript:chg_dslam_s('"+phost+"','"+pnet+"');";
//    pc.id = phost;
//    pc.value = pnet;
    
    var c = document.createElement("img");
    c.src = "img/edit.png";
    c.width = "30";
    //c.onclick = chk_changed_abon_call_nofif;
    
    pc.appendChild(c);
    td_chg.appendChild(pc);
    row.appendChild(td_chg);


    var td_del = document.createElement("td");
    td_del.style = "text-align: center; vertical-align: middle; padding:5px;";
    
    var pc = document.createElement('a');
    pc.href = "JavaScript:del_dslam_s('"+phost+"','"+pnet+"');";
//    pc.id = phost;
//    pc.value = pnet;
    
    var c = document.createElement("img");
    c.src = "img/delete.png";
    c.width = "25";
    //c.onclick = chk_changed_abon_call_nofif;
    
    pc.appendChild(c);
    td_del.appendChild(pc);
    row.appendChild(td_del);

    var td_model = document.createElement("td");
    td_model.style = "padding-left:5px;";
    td_model.appendChild(document.createTextNode(pmodel));
    row.appendChild(td_model);

    var td_filial = document.createElement("td");
    td_filial.style = "padding-left:5px;";
    td_filial.appendChild(document.createTextNode(pfilial));
    row.appendChild(td_filial);

    var td_type = document.createElement("td");
    td_type.style = "padding-left:5px;";
    td_type.appendChild(document.createTextNode(ptype));
    row.appendChild(td_type);

    var td_host = document.createElement("td");
    td_host.style = "padding-left:5px;";
    var ppc = document.createElement('a');
    ppc.href = "JavaScript:GetRep_dslam_s('"+phost+"','"+pnet+"');";
    ppc.innerHTML = phost;
    td_host.appendChild(ppc);
//    td_host.appendChild(document.createTextNode(phost));
    row.appendChild(td_host);
    
    var td_tot_p = document.createElement("td");
    td_tot_p.style = "padding-left:5px;";
    td_tot_p.appendChild(document.createTextNode(ptot_p));
    row.appendChild(td_tot_p);
    
    var td_use_p = document.createElement("td");
    td_use_p.style = "padding-left:5px;";
    td_use_p.appendChild(document.createTextNode(puse_p));
    row.appendChild(td_use_p);
    
    var td_net = document.createElement("td");
    td_net.style = "padding-left:5px;";
    td_net.appendChild(document.createTextNode(pnet));
    row.appendChild(td_net);

    ResTable_dslam_s.appendChild(row);
    //document.getElementById(c.id).onclick=chk_changed_abon_call_nofif(ptel);

}

function filter_dslam_s (phrase, _id){
        var words = phrase.value.toLowerCase().split(" ");
        var table = document.getElementById(_id);
        var ele;
        for (var r = 1; r < table.rows.length; r++){
            ele = table.rows[r].innerHTML.replace(/<[^>]+>/g,"");
                var displayStyle = 'none';
                for (var i = 0; i < words.length; i++) {
                if (ele.toLowerCase().indexOf(words[i])>=0)
                displayStyle = '';
                else {
                displayStyle = 'none';
                break;
                }
                }
            table.rows[r].style.display = displayStyle;
        }
    }

function set_pppoe_dslam_s(){
        var url = "dslam_s_get?action=get_netlist_dslam_s&chk_no_pppoe_dslam=" + escape(noPppoeField_dslam_s.checked);

        req_dslam_s = initRequest();
        req_dslam_s.open("GET", url, true);
        req_dslam_s.onreadystatechange = callback_get_netlist_dslam_s;
        req_dslam_s.send(null);    
}

function chg_dslam_s(phost,pnet){
    
    $('#editModal_dslam_s').on('show.bs.modal', function () {
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this);
        modal.find('.modal-title').text('Изменение Сети для Хоста ' + g_host_chg_dslam_s);
        $('#id_save_host_dslam_s').val(g_host_chg_dslam_s);
        $('#id_save_net_dslam_s').val(g_net_chg_dslam_s);
        $('#id_save_bit_dslam_s').val(g_bit_chg_dslam_s);
    });

    g_host_chg_dslam_s = phost;
    var arr = pnet.split('/');
    var net_part = arr[0];
    var bit_part = arr[1];
    g_net_chg_dslam_s = net_part;
    g_bit_chg_dslam_s = bit_part;
    $('#editModal_dslam_s').modal('show');
    //alert(phost+" "+pnet);
}    

function editModal_save_dslam_s(){
    //alert('save');
    var url = "dslam_s_get?action=get_save_dslam_s&ip_save_dslam_s=" + escape(hostFieldSave_dslam_s.value) +
            "&net_save_dslam_s=" + escape(netFieldSave_dslam_s.value)+
            "&bit_save_dslam_s=" + escape(bitFieldSave_dslam_s.value);

    req_dslam_s = initRequest();
    req_dslam_s.open("GET", url, true);
    req_dslam_s.onreadystatechange = callback_get_edit_dslam_s;
    req_dslam_s.send(null);
   
}

function del_dslam_s(phost,pnet){
    
    $('#deleteModal_dslam_s').on('show.bs.modal', function () {
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this);
        modal.find('.modal-title').text('Удаление сети ' + g_net_chg_dslam_s+'/'+g_bit_chg_dslam_s+', Хост '+g_host_chg_dslam_s);
    });

    g_host_chg_dslam_s = phost;
    var arr = pnet.split('/');
    var net_part = arr[0];
    var bit_part = arr[1];
    g_net_chg_dslam_s = net_part;
    g_bit_chg_dslam_s = bit_part;
    $('#deleteModal_dslam_s').modal('show');
    //alert(phost+" "+pnet);    
}

function deleteModal_dslam_s(){
    //alert('delete');
    var url = "dslam_s_get?action=get_delete_dslam_s&ip_del_dslam_s=" + escape(g_host_chg_dslam_s) +
            "&net_del_dslam_s=" + escape(g_net_chg_dslam_s)+
            "&bit_del_dslam_s=" + escape(g_bit_chg_dslam_s);

    req_dslam_s = initRequest();
    req_dslam_s.open("GET", url, true);
    req_dslam_s.onreadystatechange = callback_get_edit_dslam_s;
    req_dslam_s.send(null);    
    
    //$('#deleteModal_dslam_s').modal('hide');
}

function callback_get_edit_dslam_s(){
    
    if (req_dslam_s.readyState === 4) {
        if (req_dslam_s.status === 200) {
            parseMessages_edit_dslam_s(req_dslam_s.responseXML);
        }
    }
}

function parseMessages_edit_dslam_s(responseXML) {
    if (responseXML == null) {
        return false;
    } else {
//        var ports_dslam = responseXML.getElementsByTagName("dslam_ports_dslam")[0];
        
        var errors_dslam_s = responseXML.getElementsByTagName("errors_nets")[0];
        var error_del_dslam_s = responseXML.getElementsByTagName("errors_del_nets")[0];
        var success_dslam_s = responseXML.getElementsByTagName("success_nets")[0];

        if (errors_dslam_s !== undefined) { //stat_dslam.childNodes.length > 0
                
                var incorrect_net = errors_dslam_s.getElementsByTagName("incorrect_net")[0];
                var emp_net = errors_dslam_s.getElementsByTagName("emp_net")[0];
                var emp_bits = errors_dslam_s.getElementsByTagName("emp_bits")[0];
                var incorrect_bits = errors_dslam_s.getElementsByTagName("incorrect_bits")[0];
                var overlapped_nets = errors_dslam_s.getElementsByTagName("overlapped_nets")[0];
                var dslam_not_found = errors_dslam_s.getElementsByTagName("dslam_not_found")[0];
                var low_hosts_nets = errors_dslam_s.getElementsByTagName("low_hosts_nets")[0];
                var not_updated = errors_dslam_s.getElementsByTagName("not_updated")[0];
                var msg;
                if (incorrect_net !== undefined) msg = "Неверный формат сети";
                else if (emp_net !== undefined) msg = "Сеть не указана";
                else if (emp_bits !== undefined) msg = "Биты маски не указаны";
                else if (incorrect_bits !== undefined) msg = "Неверный формат битов маски";
                else if (overlapped_nets !== undefined) msg = overlapped_nets.childNodes[0].nodeValue;
                else if (dslam_not_found !== undefined) msg = "DSLAM Отсутствует в Технограде";
                else if (low_hosts_nets !== undefined) msg = low_hosts_nets.childNodes[0].nodeValue;
                else if (not_updated !== undefined) msg = "Ошибка при обновлении БД";
                    
                document.getElementById("id_error_edit_dslam_s").innerText = msg;//total_stat.childNodes[0].nodeValue;
                //$('#id_error_edit_dslam_s').val(g_host_chg_dslam_s);
        }
        else if (error_del_dslam_s !== undefined) {
                
                var not_deleted = error_del_dslam_s.getElementsByTagName("not_deleted")[0];
                var msg;
                if (not_deleted !== undefined) msg = "Ошибка при удалении из БД";
                    
                document.getElementById("id_error_del_dslam_s").innerText = msg;//total_stat.childNodes[0].nodeValue;
        }
        else if (success_dslam_s !== undefined){
            
            $('#editModal_dslam_s').modal('hide');
            $('#deleteModal_dslam_s').modal('hide');
            //filter_dslam_s(FilterField_dslam_s.value, 'res_td_dslam_s_id');
            FilterField_dslam_s.value = "";
            var url = "dslam_s_get?action=get_netlist_dslam_s&chk_no_pppoe_dslam=" + escape(noPppoeField_dslam_s.checked);

            req_dslam_s = initRequest();
            req_dslam_s.open("GET", url, true);
            req_dslam_s.onreadystatechange = callback_get_netlist_dslam_s;
            req_dslam_s.send(null);
        }

    }

}

function new_dslam_s(){ 
    $('#AddModal_dslam_s').on('show.bs.modal', function () {
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        //var modal = $(this);
        //modal.find('.modal-title').text('Изменение Сети для Хоста ' + g_host_chg_dslam_s);
//        $('#id_save_host_dslam_s').val(g_host_chg_dslam_s);
//        $('#id_save_net_dslam_s').val(g_net_chg_dslam_s);
        $('#id_add_bit_dslam_s').val(bit);
    });
    
    var bit = "24";
    document.getElementById("id_error_add_dslam_s").innerText = "";
    $('#AddModal_dslam_s').modal('show');
    
}

function addModal_add_dslam_s(){
    //alert('save');
    var url = "dslam_s_get?action=get_add_dslam_s&ip_add_dslam_s=" + escape(hostFieldAdd_dslam_s.value) +
            "&net_add_dslam_s=" + escape(netFieldAdd_dslam_s.value)+
            "&bit_add_dslam_s=" + escape(bitFieldAdd_dslam_s.value);

    req_dslam_s = initRequest();
    req_dslam_s.open("GET", url, true);
    req_dslam_s.onreadystatechange = callback_get_add_dslam_s;
    req_dslam_s.send(null);
   
}

function callback_get_add_dslam_s(){
    
    if (req_dslam_s.readyState === 4) {
        if (req_dslam_s.status === 200) {
            parseMessages_add_dslam_s(req_dslam_s.responseXML);
        }
    }
}

function parseMessages_add_dslam_s(responseXML) {
    if (responseXML == null) {
        return false;
    } else {
//        var ports_dslam = responseXML.getElementsByTagName("dslam_ports_dslam")[0];
        
        var errors_dslam_s = responseXML.getElementsByTagName("errors_nets")[0];
        var error_add_dslam_s = responseXML.getElementsByTagName("errors_add_nets")[0];
        var success_dslam_s = responseXML.getElementsByTagName("success_nets")[0];

        if (errors_dslam_s !== undefined) { //stat_dslam.childNodes.length > 0
                
                var incorrect_ip = errors_dslam_s.getElementsByTagName("incorrect_ip")[0];
                var emp_ip = errors_dslam_s.getElementsByTagName("emp_ip")[0];
                var incorrect_net = errors_dslam_s.getElementsByTagName("incorrect_net")[0];
                var emp_net = errors_dslam_s.getElementsByTagName("emp_net")[0];
                var emp_bits = errors_dslam_s.getElementsByTagName("emp_bits")[0];
                var incorrect_bits = errors_dslam_s.getElementsByTagName("incorrect_bits")[0];
                var overlapped_nets = errors_dslam_s.getElementsByTagName("overlapped_nets")[0];
                var dslam_not_found = errors_dslam_s.getElementsByTagName("dslam_not_found")[0];
                var low_hosts_nets = errors_dslam_s.getElementsByTagName("low_hosts_nets")[0];
                var not_added = errors_dslam_s.getElementsByTagName("not_added")[0];
                var dslam_exists = errors_dslam_s.getElementsByTagName("ipexist_nets")[0];
                var msg;
                if (incorrect_ip !== undefined) msg = "Неверный формат ip адреса";
                else if (emp_ip !== undefined) msg = "ip адрес не указан";
                else if (incorrect_net !== undefined) msg = "Неверный формат сети";
                else if (emp_net !== undefined) msg = "Сеть не указана";
                else if (emp_bits !== undefined) msg = "Биты маски не указаны";
                else if (incorrect_bits !== undefined) msg = "Неверный формат битов маски";
                else if (overlapped_nets !== undefined) msg = overlapped_nets.childNodes[0].nodeValue;
                else if (dslam_not_found !== undefined) msg = "DSLAM Отсутствует в Технограде";
                else if (low_hosts_nets !== undefined) msg = low_hosts_nets.childNodes[0].nodeValue;
                else if (not_added !== undefined) msg = "Ошибка при добавлении в БД";
                else if (dslam_exists !== undefined) msg = dslam_exists.childNodes[0].nodeValue;
                    
                document.getElementById("id_error_add_dslam_s").innerText = msg;//total_stat.childNodes[0].nodeValue;
                //$('#id_error_edit_dslam_s').val(g_host_chg_dslam_s);
        }
        else if (error_add_dslam_s !== undefined) {
                
                var not_added = error_add_dslam_s.getElementsByTagName("not_added")[0];
                var msg;
                if (not_added !== undefined) msg = "Ошибка при добавлении в БД";
                    
                document.getElementById("id_error_add_dslam_s").innerText = msg;//total_stat.childNodes[0].nodeValue;
        }
        else if (success_dslam_s !== undefined){
          
            //filter_dslam_s(FilterField_dslam_s.value, 'res_td_dslam_s_id');
            FilterField_dslam_s.value = "";
            var url = "dslam_s_get?action=get_netlist_dslam_s&chk_no_pppoe_dslam=" + escape(noPppoeField_dslam_s.checked);

            req_dslam_s = initRequest();
            req_dslam_s.open("GET", url, true);
            req_dslam_s.onreadystatechange = callback_get_netlist_dslam_s;
            req_dslam_s.send(null);
        }

    }
    $('#id_error_add_dslam_s').val("");
}
////////////////////////////////////////////////////////////////////////////

function GetRep_dslam_s(pip,pnet){
    $('#GetRepModal_dslam_s').on('show.bs.modal', function () {
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this);
        modal.find('.modal-title').text('Отчеты для DSLAM ' + pip+', сеть '+pnet);
        modal.find('#id_host_get_rep_dslam_s').val(pip);
        modal.find('#id_net_get_rep_dslam_s').val(pnet);
    });

//    g_host_chg_dslam_s = phost;
//    var arr = pnet.split('/');
//    var net_part = arr[0];
//    var bit_part = arr[1];
//    g_net_chg_dslam_s = net_part;
//    g_bit_chg_dslam_s = bit_part;
    $('#GetRepModal_dslam_s').modal('show');    
    //alert(pip);
}

function GetRepModal_dslam_s(){
    
    var radio_elms = document.getElementsByName("options_rep_dslam_s");
    if (radio_elms[0].checked) { selFieldRep_dslam_s = 1;} 
    else { selFieldRep_dslam_s = 2; }
//    alert("отчет: "+selFieldRep_dslam_s+"; ip: "+hostFieldRep_dslam_s.value+"; Сеть: "+netFieldRep_dslam_s.value);
    
    var url = "dslam_s_get?action=rep_binds_dslam_s&ip_rep_dslam_s=" + escape(hostFieldRep_dslam_s.value) +
            "&net_rep_dslam_s=" + escape(netFieldRep_dslam_s.value) +
            "&num_rep_dslam_s=" + escape(selFieldRep_dslam_s);

    req_dslam_s = initRequest();
    req_dslam_s.open("GET", url, true);
    req_dslam_s.onreadystatechange = callback_rep_binds_dslam_s;
    req_dslam_s.send(null);   
}

function callback_rep_binds_dslam_s(){
    clearTable_dslam_s();
    if (req_dslam_s.readyState === 4) {
        if (req_dslam_s.status === 200) {
            parseMessages_dslam_s(req_dslam_s.responseXML);
        }
    }
}

function appendRow_rep_dslam_s(prep,pnum,pmodel,ppunkt,phost_name,phost,pport,pstatus,pip_liner,pip_linep,pprofile_line,pprofile_iptv,pvlan_line,ppvc_line,pvlan_iptv,ppvc_iptv,pmac,pdate) {

    var row = document.createElement("tr");

    if (prep===2) {
        var td_num = document.createElement("td");
        td_num.style = "padding-left:5px;";
        td_num.appendChild(document.createTextNode(pnum));
        row.appendChild(td_num);
        
        var td_model = document.createElement("td");
        td_model.style = "padding-left:5px;";
        td_model.appendChild(document.createTextNode(pmodel));
        row.appendChild(td_model);
    
        var td_punkt = document.createElement("td");
        td_punkt.style = "padding-left:5px;";
        td_punkt.appendChild(document.createTextNode(ppunkt));
        row.appendChild(td_punkt);

        var td_host_name = document.createElement("td");
        td_host_name.style = "padding-left:5px;";
        td_host_name.appendChild(document.createTextNode(phost_name));
        row.appendChild(td_host_name);
    }

    var td_host = document.createElement("td");
    td_host.style = "padding-left:5px;";
    td_host.appendChild(document.createTextNode(phost));
    row.appendChild(td_host);

    var td_port = document.createElement("td");
    td_port.style = "padding-left:5px;";
    td_port.appendChild(document.createTextNode(pport));
    row.appendChild(td_port);

    var td_status = document.createElement("td");
    td_status.style = "padding-left:5px;";
    td_status.appendChild(document.createTextNode(pstatus));
    row.appendChild(td_status);

    var td_ip_liner = document.createElement("td");
    td_ip_liner.style = "padding-left:5px;";
    td_ip_liner.appendChild(document.createTextNode(pip_liner));
    row.appendChild(td_ip_liner);

    var td_ip_linep = document.createElement("td");
    td_ip_linep.style = "padding-left:5px;";
    td_ip_linep.appendChild(document.createTextNode(pip_linep));
    row.appendChild(td_ip_linep);

    if (prep===2) {
        var td_profile_line = document.createElement("td");
        td_profile_line.style = "padding-left:5px;";
        td_profile_line.appendChild(document.createTextNode(pprofile_line));
        row.appendChild(td_profile_line);

        var td_profile_iptv = document.createElement("td");
        td_profile_iptv.style = "padding-left:5px;";
        td_profile_iptv.appendChild(document.createTextNode(pprofile_iptv));
        row.appendChild(td_profile_iptv);

        var td_vlan_line = document.createElement("td");
        td_vlan_line.style = "padding-left:5px;";
        td_vlan_line.appendChild(document.createTextNode(pvlan_line));
        row.appendChild(td_vlan_line);

        var td_pvc_line = document.createElement("td");
        td_pvc_line.style = "padding-left:5px;";
        td_pvc_line.appendChild(document.createTextNode(ppvc_line));
        row.appendChild(td_pvc_line);

        var td_vlan_iptv = document.createElement("td");
        td_vlan_iptv.style = "padding-left:5px;";
        td_vlan_iptv.appendChild(document.createTextNode(pvlan_iptv));
        row.appendChild(td_vlan_iptv);

        var td_pvc_iptv = document.createElement("td");
        td_pvc_iptv.style = "padding-left:5px;";
        td_pvc_iptv.appendChild(document.createTextNode(ppvc_iptv));
        row.appendChild(td_pvc_iptv);

        var td_mac = document.createElement("td");
        td_mac.style = "padding-left:5px;";
        td_mac.appendChild(document.createTextNode(pmac));
        row.appendChild(td_mac);
    }
    
    var td_date = document.createElement("td");
    td_date.style = "padding-left:5px;";
    td_date.appendChild(document.createTextNode(pdate));
    row.appendChild(td_date);

    ResTable_dslam_s.appendChild(row);
    //document.getElementById(c.id).onclick=chk_changed_abon_call_nofif(ptel);
}
//
//function clearTable_ne_binds_dslam_s(){
//    if (ResTable_dslam.getElementsByTagName("tr").length > 0) {
//        //ResTableDhcpo.style.display = 'none';
//        for (loop = ResTable_dslam.childNodes.length - 1; loop >= 1; loop--) {
//            ResTable_dslam.removeChild(ResTable_dslam.childNodes[loop]);
//        }
//    }    
//}

//function parseMessages_reports_dslam_s(responseXML) {
//    if (responseXML == null) {
//        return false;
//    } else {
////        var ports_dslam = responseXML.getElementsByTagName("dslam_ports_dslam")[0];
//        var ports_dslam = responseXML.getElementsByTagName("dslam_ports_list")[0];
//
//        if (ports_dslam !== undefined) {
////            completeTable.setAttribute("bordercolor", "black"); ptime,pheader,pmessage,pserver
////            completeTable.setAttribute("border", "1");
//            SetColumsNames(0);
//            
//            for (loop = 0; loop < ports_dslam.childNodes.length; loop++) {
//                var ports_row = ports_dslam.childNodes[loop];
//                
//                var host_name = ports_row.getElementsByTagName("host_name")[0];
//                var host = ports_row.getElementsByTagName("host")[0];
//                var port = ports_row.getElementsByTagName("port")[0];
//                var status = ports_row.getElementsByTagName("status")[0];
//                var ip_line = ports_row.getElementsByTagName("ip_line")[0];
//                var profile_line = ports_row.getElementsByTagName("profile_line")[0];
//                var profile_iptv = ports_row.getElementsByTagName("profile_iptv")[0];
//                var vlan_line = ports_row.getElementsByTagName("vlan_line")[0];
//                var pvc_line = ports_row.getElementsByTagName("pvc_line")[0];
//                var vlan_iptv = ports_row.getElementsByTagName("vlan_iptv")[0];
//                var pvc_iptv = ports_row.getElementsByTagName("pvc_iptv")[0];
//                var ed_snr = ports_row.getElementsByTagName("ed_snr")[0];
//                var eda = ports_row.getElementsByTagName("eda")[0];
//                var eusnr = ports_row.getElementsByTagName("eusnr")[0];
//                var eua = ports_row.getElementsByTagName("eua")[0];
//                var date = ports_row.getElementsByTagName("date")[0];
//                
//                appendRow_ports_dslam("",
//                        host_name.childNodes[0].nodeValue,
//                        host.childNodes[0].nodeValue,
//                        port.childNodes[0].nodeValue,
//                        status.childNodes[0].nodeValue,
//                        ip_line.childNodes[0].nodeValue,
//                        profile_line.childNodes[0].nodeValue,
//                        profile_iptv.childNodes[0]!==undefined?profile_iptv.childNodes[0].nodeValue:"",
//                        vlan_line.childNodes[0].nodeValue,
//                        pvc_line.childNodes[0].nodeValue,
//                        vlan_iptv.childNodes[0]!==undefined?vlan_iptv.childNodes[0].nodeValue:"",
//                        pvc_iptv.childNodes[0]!==undefined?pvc_iptv.childNodes[0].nodeValue:"",
//                        ed_snr.childNodes[0].nodeValue,
//                        eda.childNodes[0].nodeValue,
//                        eusnr.childNodes[0].nodeValue,
//                        eua.childNodes[0].nodeValue,
//                        date.childNodes[0].nodeValue
//                       );
//            }
//        }
//        
//        var ports_dslam_adr = responseXML.getElementsByTagName("dslam_ports_adr_dslam")[0];
//
//        if (ports_dslam_adr !== undefined) {
////            completeTable.setAttribute("bordercolor", "black"); ptime,pheader,pmessage,pserver
////            completeTable.setAttribute("border", "1");
//            SetColumsNames(1);
//            
//            for (loop = 0; loop < ports_dslam_adr.childNodes.length; loop++) {
//                var ports_row = ports_dslam_adr.childNodes[loop];
//                
//                var adr = ports_row.getElementsByTagName("adres")[0];
//                var host_name = ports_row.getElementsByTagName("host_name")[0];
//                var host = ports_row.getElementsByTagName("host")[0];
//                var port = ports_row.getElementsByTagName("port")[0];
//                var status = ports_row.getElementsByTagName("status")[0];
//                var ip_line = ports_row.getElementsByTagName("ip_line")[0];
//                var profile_line = ports_row.getElementsByTagName("profile_line")[0];
//                var profile_iptv = ports_row.getElementsByTagName("profile_iptv")[0];
//                var vlan_line = ports_row.getElementsByTagName("vlan_line")[0];
//                var pvc_line = ports_row.getElementsByTagName("pvc_line")[0];
//                var vlan_iptv = ports_row.getElementsByTagName("vlan_iptv")[0];
//                var pvc_iptv = ports_row.getElementsByTagName("pvc_iptv")[0];
//                var ed_snr = ports_row.getElementsByTagName("ed_snr")[0];
//                var eda = ports_row.getElementsByTagName("eda")[0];
//                var eusnr = ports_row.getElementsByTagName("eusnr")[0];
//                var eua = ports_row.getElementsByTagName("eua")[0];
//                var date = ports_row.getElementsByTagName("date")[0];
//                
//                appendRow_ports_dslam(
//                        adr.childNodes[0].nodeValue!==undefined?adr.childNodes[0].nodeValue:"",
//                        host_name.childNodes[0].nodeValue,
//                        host.childNodes[0].nodeValue,
//                        port.childNodes[0].nodeValue,
//                        status.childNodes[0].nodeValue,
//                        ip_line.childNodes[0].nodeValue,
//                        profile_line.childNodes[0].nodeValue,
//                        profile_iptv.childNodes[0]!==undefined?profile_iptv.childNodes[0].nodeValue:"",
//                        vlan_line.childNodes[0].nodeValue,
//                        pvc_line.childNodes[0].nodeValue,
//                        vlan_iptv.childNodes[0]!==undefined?vlan_iptv.childNodes[0].nodeValue:"",
//                        pvc_iptv.childNodes[0]!==undefined?pvc_iptv.childNodes[0].nodeValue:"",
//                        ed_snr.childNodes[0].nodeValue,
//                        eda.childNodes[0].nodeValue,
//                        eusnr.childNodes[0].nodeValue,
//                        eua.childNodes[0].nodeValue,
//                        date.childNodes[0].nodeValue
//                       );
//            }
//        }
//
//    }
//}
