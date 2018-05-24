<%-- 
    Document   : notifications
    Created on : 28.02.2017, 11:42:42
    Author     : andrey-man
--%>
<!--<?php
echo '$_SERVER["REMOTE_ADDR"]';
?>-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="right">
    <br>
    <div class="form-group">
        <p style="margin-left:10px;"><font size="3" color="black" face="Arial">Группы получателей telegram</font></p>
        <table border width="100%" >
            <tr>
                <td id="id_chk_tlg_gr_notif">
                    <c:forEach var="group_tel" items="${list_telegram_gr_notif}">
                    <div class="checkbox">
                        <label style="margin-left:10px;">
                            <input type="checkbox" id="id_chk${group_tel[1]}_tlg_notif" value="${group_tel[1]}">
                            ${group_tel[0]}
                        </label>
                    </div>
                    </c:forEach> 
                </td>
            </tr>
        </table>
    </div>

    <button type="button" class="btn btn-primary" onclick="JavaScript:send_telegram_msg_notif();" id="id_btn_send_tlg_notif">Отправить сообщение</button>

</div>        
<div id="main_area">
    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="notifications">Оповещения</a></li>
        <li role="presentation"><a href="notifications_r">Результаты обзвона</a></li>
        <li role="presentation"><a href="notifications_a">Абоненты и группы</a></li>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="title_block">
                        <p class="title">Оповещения Telegram и Автообзвон</p>
                    </div>
                    <div class="main_block">
                        <div class="form-group">
                            <label for="id_text_msg_notif">Текст оповещения/автообзвона</label>
                            <textarea class="form-control" rows="3" id="id_text_msg_notif"></textarea>                    
                        </div>
                        <div class="form-group">
                            
                            <div class="panel panel-success">
                                <div class="panel-heading">Опции обзвона</div>
                                <div class="panel-body">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <table class="table table-bordered">
                                                    <tr>
                                                        <td>
                                                            [группы]
                                                            <select onchange="JavaScript:Chg_gr_call_nofif();" id="id_sel_gr_call_notif" class="form-control" size="14">
                                                                <c:forEach var="group" items="${list_call_gr_notif}">
                                                                    <option value="${group[0]}">${group[1]}</option>
                                                                </c:forEach>                                                                  
                                                            </select>                                                            
                                                        </td>
                                                    </tr>
                                                </table>                                                
                                            </div>
                                            <div class="col-md-8">
                                                <table class="table table-bordered">
                                                    <tr>
                                                        <td>
                                                            [абоненты]
                                                            <div style="overflow:auto; height:300px">
                                                            <table border width="100%" cellspacing=1 id="id_abon_call_td_notif">
                                                                <thead>
                                                                    <tr bgcolor="D1EEEE">
                                                                        <td style="text-align: center; vertical-align: middle;">
                                                                            <input type="checkbox" id="id_abon_call_chk_all_notif" onchange="JavaScript:Sel_All_abon_call_nofif();" value="">
                                                                        </td>
                                                                        <th>ФИО</th>
                                                                        <th>Телефон</th>
                                                                        <th>Должность</th>
                                                                    </tr>
                                                                </thead>
<!--                                                                <tbody>

                                                                </tbody>-->
                                                            </table>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </table>                                                
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="margin-left:20px;" class="form-group">
                                    <button type="button" class="btn btn-primary" onclick="JavaScript:autocall_notif();" id="id_btn_autocall_notif">Автообзвон</button>
                                </div>
                            </div>                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </ul>
</div>

