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

    <button class="btn btn-primary" id='id_btn_find_syslog' >Поиск</button>        
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
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="display" id="id_syslog_td"  cellspacing="0" width="100%"  style="font-size:10px;">
                        <thead>
                            <tr class="active">
<!--                                <th style="width: 280px">Наименование партнера</th>-->
                                <th>Адрес(zabbix)</th>
                                <th>Группа</th>
                                <th>Уровень</th>
                                <th>Хост</th>
                                <th>Дата</th>
                                <th>Сообщение</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>                
            </div>
        </div>  
    </div>
    
</div>

<script type="text/javascript">
    
    var table;
    
$(document).ready(function () {

$('#id_btn_find_syslog').click(function () {
    
    var ip = selectHstField_syslog.options[selectHstField_syslog.selectedIndex].text;
    ip = ip.substring(0, ip.indexOf('['));
    
            
    if (table) table.destroy();

table = $('#id_syslog_td').DataTable({
"language": {
//    "columns": [
//        null,
//        null,
//        { "type": "date" },
//        null,
//        null
//    ],
    //"url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/German.json",
    "processing": "Подождите...",
    "search": "Поиск:",
    "lengthMenu": "Показать _MENU_ записей",
    "info": "Записи с _START_ до _END_ из _TOTAL_ записей",
    "infoEmpty": "Записи с 0 до 0 из 0 записей",
    "infoFiltered": "(отфильтровано из _MAX_ записей)",
    "infoPostFix": "",
    "loadingRecords": "Загрузка записей...",
    "zeroRecords": "Записи отсутствуют.",
    "emptyTable": "В таблице отсутствуют данные",
    "paginate": {
        "first": "Первая",
        "previous": "Предыдущая",
        "next": "Следующая",
        "last": "Последняя"
    },
    "aria": {
        "sortAscending": ": активировать для сортировки столбца по возрастанию",
        "sortDescending": ": активировать для сортировки столбца по убыванию"
    }
},
"pageLength": 50,
"ajax": "syslog_get?s_dt_syslog=" + escape(sdtField_syslog.value) +
            "&e_dt_syslog=" + escape(edtField_syslog.value) +
            "&sel_group_syslog=" + escape(selectGrField_syslog.value) +
            "&sel_host_syslog=" + escape(ip) +
            "&sel_level_syslog=" + escape(selectLevelField_syslog.value) +
            "&ed_msg_syslog=" + escape(msgField_syslog.value),
"deferRender": true
});
});

//$.ajaxSetup({
//headers: {
//    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
//}
//});


table = $('#id_syslog_td').DataTable({
"language": {
//    "columns": [
//        null,
//        null,
//        { "type": "date" },
//        null,
//        null
//    ],
    //"url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/German.json",
    "processing": "Подождите...",
    "search": "Поиск:",
    "lengthMenu": "Показать _MENU_ записей",
    "info": "Записи с _START_ до _END_ из _TOTAL_ записей",
    "infoEmpty": "Записи с 0 до 0 из 0 записей",
    "infoFiltered": "(отфильтровано из _MAX_ записей)",
    "infoPostFix": "",
    "loadingRecords": "Загрузка записей...",
    "zeroRecords": "Записи отсутствуют.",
    "emptyTable": "В таблице отсутствуют данные",
    "paginate": {
        "first": "Первая",
        "previous": "Предыдущая",
        "next": "Следующая",
        "last": "Последняя"
    },
    "aria": {
        "sortAscending": ": активировать для сортировки столбца по возрастанию",
        "sortDescending": ": активировать для сортировки столбца по убыванию"
    }
},
"pageLength": 50,
//"ajax": "dhcpo_get?ip_dhcpo=" + escape(ipField_dhcpo.value) + "&s_dt_dhcpo=" + escape(sdtField_dhcpo.value) + "&e_dt_dhcpo=" + escape(edtField_dhcpo.value) + "&chk_arch_dhcpo=" + escape(archField_dhcpo.checked),
"deferRender": true
});



});
</script>


