<%-- 
    Document   : notifications
    Created on : 28.02.2017, 11:42:42
    Author     : andrey-man
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="right">
        <div class="form-group" style="margin-top:5px">
            <div class="input-group">
                <span class="input-group-addon" id="sizing-addon3">></span>
                <input type="datetime-local" class="form-control" id="id_s_dt_notif_r" name="s_dt_notif_r" placeholder="" aria-describedby="sizing-addon2">
            </div>            
            <div class="input-group">
                <span class="input-group-addon" id="sizing-addon3"><</span>
                <input type="datetime-local" class="form-control" id="id_e_dt_notif_r" name="e_dt_notif_r" placeholder="" aria-describedby="sizing-addon2">
            </div>            
        </div>
        <div class="checkbox" style="margin-top:-5px;margin-left:5px">
            <label>
                <input type="checkbox" id="id_chk_arch_notif_r" name="chk_arch_notif_r" onclick="JavaScript:Set_active_date_notif_r();"> Поиск в архиве
            </label>
        </div>
        <div class="form-group">
            <label for="phone_dhcpo">Фильтр по телефону</label>
            <input type="text" class="form-control" id="id_phone_notif_r" name="phone_notif_r" placeholder="89245551111">
        </div>        
        <div class="checkbox" style="margin-top:-5px;margin-left:5px">
            <label>
                <input type="checkbox" id="id_chk_ltc_notif_r" name="chk_ltc_notif_r" onclick="JavaScript:Set_active_ltc_notif_r();"> Поиск обзвона ЛТЦ
            </label>
        </div>
        <button style="margin-top:25px" onclick="JavaScript:Get_log_abon_call_notif_r();" class="btn btn-primary">Выполнить</button>        
</div>        
<div id="main_area">
    <ul class="nav nav-tabs">
        <li role="presentation"><a href="notifications">Оповещения</a></li>
        <li role="presentation" class="active"><a href="notifications_r">Результаты обзвона</a></li>
        <li role="presentation"><a href="notifications_a">Абоненты и группы</a></li>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="title_block">
                        <p class="title">Результаты обзвона</p>
                    </div>
                    <div class="main_block">
                        <table border width="100%" cellspacing=1 id="res_td_notif_r_id">
                            <tr bgcolor="D1EEEE">
                                <th>Номер п/п</th>
                                <th>ФИО</th>
                                <th>Дата</th>
                                <th>Телефон</th>
                                <th>Результат</th>
                            </tr>
                        </table>                    
                    </div>          
                </div>
            </div>
        </div>
    </ul>
</div>

