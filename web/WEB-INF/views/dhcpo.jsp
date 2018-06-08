<%-- 
    Document   : dhcpo
    Created on : 28.02.2017, 11:41:27
    Author     : andrey-man
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--<form method="GET" action="">-->
<div id="right">
    <br>
    <div class="form-group">
        <label for="ip_dhcpo">IP или MAC</label>
        <input type="text" class="form-control" id="id_ip_dhcpo" name="ip_dhcpo" placeholder="ab:cd:ef:12:34:56 или ip">
    </div>        
    <div class="checkbox">
        <label>
            <input type="checkbox" id="id_chk_arch_dhcpo" name="chk_arch_dhcpo"> Поиск в архиве
        </label>
    </div>
    <div class="form-group">
        <div class="input-group">
            <span class="input-group-addon" id="sizing-addon3">Начало</span>
            <input type="date" class="form-control" id="id_s_dt_dhcpo" name="s_dt_dhcpo" placeholder="" aria-describedby="sizing-addon3">
        </div>            
        <div class="input-group">
            <span class="input-group-addon" id="sizing-addon3">Конец&nbsp;&nbsp;</span>
            <input type="date" class="form-control" id="id_e_dt_dhcpo" name="e_dt_dhcpo" placeholder="" aria-describedby="sizing-addon3">
        </div>            

        <!--            <input type="date" class="form-control" id="s_dt_dhcpo" placeholder="Дата начала">-->
    </div>        
    <!--<button type="submit" class="btn btn-primary">Выполнить</button>-->        
    <button class="btn btn-primary" id="id_btn_exec_dhcpo">Выполнить</button>        
</div>        
<!--  </form>-->
<div id="main_area">
    <div class="title_block">
        <p class="title">Логи DHCP Optinet</p>
    </div>
    <div class="main_block">
        <!--            <table class="table table-hover" id="res_td_dhcpo_id">-->
        <!--            <table border width="100%" cellspacing=1 id="res_td_dhcpo_id">-->
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="display" id="id_dhcpo_td"  cellspacing="0" width="100%"  style="font-size:10px;">
                        <thead>
                            <tr class="active">
<!--                                <th style="width: 280px">Наименование партнера</th>-->
                                <th>Дата</th>
                                <th>Заголовок</th>
                                <th>Сообщение</th>
                                <th>Сервер</th>
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

$('#id_btn_exec_dhcpo').click(function () {
    if ($('#id_ip_dhcpo').val() !== '') {
        //table.reset();
        //table.clear();
        //table.clear().draw();
        //table.rows().clear().draw();
        //table.clear().rows.add(document.pvm.tableContent()).draw();
        
        //table.ajax.url( "dhcpo_get?ip_dhcpo=" + escape(ipField_dhcpo.value) + "&s_dt_dhcpo=" + escape(sdtField_dhcpo.value) + "&e_dt_dhcpo=" + escape(edtField_dhcpo.value) + "&chk_arch_dhcpo=" + escape(archField_dhcpo.checked) ).load();
        //table.ajax = "dhcpo_get?ip_dhcpo=" + escape(ipField_dhcpo.value) + "&s_dt_dhcpo=" + escape(sdtField_dhcpo.value) + "&e_dt_dhcpo=" + escape(edtField_dhcpo.value) + "&chk_arch_dhcpo=" + escape(archField_dhcpo.checked);
        //table.ajax.reload();

            
if (table) table.destroy();

table = $('#id_dhcpo_td').DataTable({
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
"ajax": "dhcpo_get?ip_dhcpo=" + escape(ipField_dhcpo.value) + "&s_dt_dhcpo=" + escape(sdtField_dhcpo.value) + "&e_dt_dhcpo=" + escape(edtField_dhcpo.value) + "&chk_arch_dhcpo=" + escape(archField_dhcpo.checked),
"deferRender": true
});
        
        
        
        
        
    }
});

//$.ajaxSetup({
//headers: {
//    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
//}
//});


table = $('#id_dhcpo_td').DataTable({
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