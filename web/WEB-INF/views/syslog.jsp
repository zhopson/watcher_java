<%-- 
    Document   : syslog
    Created on : 28.02.2017, 11:41:46
    Author     : andrey-man
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="right">
    <br>
    <div class="form-group">
        <div class="input-group">
            <span class="input-group-addon" id="sizing-addon3">></span>
            <input type="datetime-local" class="form-control" id="id_s_dt_syslog" name="s_dt_syslog" placeholder="" aria-describedby="sizing-addon3">
        </div>            
        <div class="input-group">
            <span class="input-group-addon" id="sizing-addon3"><</span>
            <input type="datetime-local" class="form-control" id="id_e_dt_syslog" name="e_dt_syslog" placeholder="" aria-describedby="sizing-addon3">
        </div>            
    </div>
    <div class="form-group">

        <select onchange="JavaScript:Chg_gr_syslog();" class="form-control" id="id_sel_group_syslog" name="sel_group_syslog" placeholder="выберите группу">
            <option value="000">выберите группу...</option>
            <c:forEach var="group" items="${list_gr_syslog}">
                    <option value="${group[0]}">${group[1]}</option>
            </c:forEach>            
        </select>

        <select class="form-control" id="id_sel_host_syslog" name="sel_host_syslog" placeholder="выберите хост">
            <option value="000">выберите хост...</option>
            <c:forEach var="host" items="${list_hst_syslog}">
                    <option value="${host[1]}">${host[0]}[${host[2]}]</option>
            </c:forEach>            
        </select>

        <select class="form-control" id="id_sel_level_syslog" name="sel_level_syslog" placeholder="выберите уровень">
            <option value="000">выберите уровень...</option>
            <option>alert</option>
            <option>crit</option>
            <option>err</option>
            <option>warning</option>
            <option>info</option>
            <option>notice</option>
        </select>

        <!--<input type="text" class="form-control" id="id_ed_msg_syslog" name="ed_msg_syslog" placeholder="%сообщение%" aria-describedby="sizing-addon3">        -->

        <div class="input-group">
            <span class="input-group-addon">%</span>
            <input type="text" class="form-control" id="id_ed_msg_syslog" name="ed_msg_syslog" placeholder="введите сообщение" aria-label="Amount (to the nearest dollar)">
            <span class="input-group-addon">%</span>
        </div>
    </div>

    <button onclick="JavaScript:Get_log_list_syslog();" class="btn btn-primary">Поиск</button>        
</div>        
<div id="main_area">
<!--    <div id="button_bar">
        <div class="button_block">
            <a href="#">Логи устройств</a>
        </div>
        <div class="button_block">
            <a href="#">Логи DS.RT14.RU</a>
        </div>
    </div>-->
    <div class="title_block">
        <p class="title">Системные логи оборудования</p>
    </div>
    <div class="main_block">
            <table border width="100%" cellspacing=1 id="res_td_syslog_id">
                <thead>
                <tr bgcolor="D1EEEE">
                    <th>Адрес(zabbix)</th>
                    <th>Группа</th>
                    <th>Уровень</th>
                    <th>Хост</th>
                    <th>Дата</th>
                    <th>Сообщение</th>
                </tr>
                </thead>
                <tbody>
<!--                <tr>
                    <td>элемент 11</td>
                    <td>элемент 12</td>
                    <td>элемент 13</td>
                    <td>элемент 14</td>
                </tr>-->
                </tbody>
            </table>
    </div>
    
</div>

