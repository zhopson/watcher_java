<%-- 
    Document   : dslam
    Created on : 28.02.2017, 11:42:00
    Author     : andrey-man
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="right">
        <div class="form-group" style="margin-top:10px">
            <div class="input-group">
                <span class="input-group-addon" id="sizing-addon3">Начало</span>
                <input type="date" class="form-control" id="id_s_dt_dslam" name="s_dt_dslam" placeholder="" aria-describedby="sizing-addon2">
            </div>            
            <div class="input-group">
                <span class="input-group-addon" id="sizing-addon3">Конец&nbsp;&nbsp;</span>
                <input type="date" class="form-control" id="id_e_dt_dslam" name="e_dt_dslam" placeholder="" aria-describedby="sizing-addon2">
            </div>            
        </div>
        <div class="checkbox" style="margin-top:-5px;margin-left:15px">
            <label>
                <input type="checkbox" id="id_chk_arch_dslam" name="chk_arch_dslam" onclick="JavaScript:Set_active_date_dslam();"> Поиск в архиве
            </label>
        </div>
    <div class="form-group" style="padding: 0px 5px 0px 5px;">
        <button onclick="JavaScript:find_dslam();" class="btn btn-primary btn-sm btn-block">Поиск</button>        
        <button onclick="JavaScript:find_dup_binds_dslam();" class="btn btn-primary btn-sm btn-block">Поиск дубликатов биндов</button>        
        <button onclick="JavaScript:svod_dslam();" class="btn btn-primary btn-sm btn-block">Свод по DSLAM</button>  
    </div>
        <table id="id_dslam_stat_dslam" border width="90%" style="margin-top:15px;margin-bottom:10px;margin-left:15px" >
            <tr>
                <td><label style="margin-top:5px;margin-left:10px">Количество</label></td>
                <td><label id="id_ports_total_stat_dslam" style="margin-top:5px;margin-left:10px">0</label></td>
            </tr>
            <tr>
                <td><label style="margin-top:5px;margin-left:10px">Активных</label></td>
                <td><label id="id_ports_acting_stat_dslam" style="margin-top:5px;margin-left:10px">0</label></td>
            </tr>
            <tr>
                <td><label style="margin-top:5px;margin-left:10px">Неактивных</label></td>
                <td><label id="id_ports_acted_stat_dslam" style="margin-top:5px;margin-left:10px">0</label></td>
            </tr>
            <tr>
                <td><label style="margin-top:5px;margin-left:10px">Отключенных</label></td>
                <td><label id="id_ports_deacted_stat_dslam" style="margin-top:5px;margin-left:10px">0</label></td>
            </tr>
            <tr>
                <td><label style="margin-top:5px;margin-left:10px">Без ip bind</label></td>
                <td><label id="id_ports_nobinds_stat_dslam" style="margin-top:5px;margin-left:10px">0</label></td>
            </tr>
        </table>        
        
</div>        
<div id="main_area">
    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="dslam">Порты DSLAM</a></li>
        <li role="presentation"><a href="dslam_s">Сети DSLAM</a></li>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="title_block">
                        <p class="title">Информация о портах опрошенных DSLAM</p>
                    </div>
                    <div class="main_block">
                        <div class="panel panel-success">
                            <div class="panel-heading">Опции поиска</div>
                            <div class="panel-body">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="text_ip_dslam">Хост</label>
                                                <input type="text" class="form-control" id="id_text_ip_dslam" name="text_ip_dslam" onfocus="JavaScript:ip_onfocus_dslam();" placeholder="XXX.XXX.XXX.XXX">
                                            </div>                                                
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="id_text_port_dslam">Порт</label>
                                                <input type="text" class="form-control" id="id_text_port_dslam" placeholder="0/X/XX">
                                            </div>                                                
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="id_text_bind_dslam">Бинд</label>
                                                <input type="text" class="form-control" id="id_text_bind_dslam" placeholder="XXX.XXX.XXX.XXX">
                                            </div>                                                
                                        </div>
                                        <div class="col-md-3">
                                            <div class="checkbox" style="margin-top:25px;margin-left:5px">
                                                <label>
                                                    <input type="checkbox" id="id_check_bind_dslam" onclick="JavaScript:Set_nobinds_dslam();"> Порты без биндов
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="id_text_adres_dslam">Адрес</label>
                                                <input type="text" class="form-control" id="id_text_adres_dslam" onfocus="JavaScript:adres_onfocus_dslam();" placeholder="">
                                            </div>                                                
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label for="id_text_dom_dslam">Дом</label>
                                                <input type="text" class="form-control" id="id_text_dom_dslam" placeholder="">
                                            </div>                                                
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>                            
                    </div> 
                </div>
            </div>
        </div>
    </ul>

    <table border width="100%" cellspacing=1 id="res_td_dslam_id" style="margin-top:-75px;font-size:10px;">
<!--            <tr bgcolor="D1EEEE">
                <th>Имя Хоста</th>
                <th>Хост</th>
                <th>Порт</th>
                <th>Статус</th>
                <th>Бинды</th>
                <th>Профиль</th>
                <th>Профили iptv</th>
                <th>VLAN линии</th>
                <th>PVC линии</th>
                <th>VLAN iptv</th>
                <th>PVC iptv</th>
                <th>Down SNR</th>
                <th>Down ATTEN</th>
                <th>Up SNR</th>
                <th>Up ATTEN</th>
                <th>Дата обновления</th>
            </tr>-->
    </table>
    
<!--    <div id="button_bar">
        <div class="button_block">
            <a href="#">Порты dslam ЛТЦ</a>
        </div>
        <div class="button_block">
            <a href="#">Сети dslam</a>
        </div>
    </div>
    <div class="title_block">
         <p class="title">Информация о DSLAM</p>
    </div>
    <div class="main_block">
         <h1>Главный блок</h1>
    </div>-->
</div>

