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
        <button id='id_btn_find_dslam' class="btn btn-primary btn-sm btn-block">Поиск</button>        
        <button id='id_btn_find_dup_binds_dslam' class="btn btn-primary btn-sm btn-block">Поиск дубликатов биндов</button>        
        <button id='id_btn_svod_dslam' class="btn btn-primary btn-sm btn-block">Свод по DSLAM</button>  
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
                                                <input type="text" class="form-control input-sm" id="id_text_ip_dslam" name="text_ip_dslam" onfocus="JavaScript:ip_onfocus_dslam();" placeholder="XXX.XXX.XXX.XXX">
                                            </div>                                                
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="id_text_port_dslam">Порт</label>
                                                <input type="text" class="form-control input-sm" id="id_text_port_dslam" placeholder="0/X/XX">
                                            </div>                                                
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="id_text_bind_dslam">Бинд</label>
                                                <input type="text" class="form-control input-sm" id="id_text_bind_dslam" placeholder="XXX.XXX.XXX.XXX">
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
                                                <input type="text" class="form-control input-sm" id="id_text_adres_dslam" onfocus="JavaScript:adres_onfocus_dslam();" placeholder="">
                                            </div>                                                
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label for="id_text_dom_dslam">Дом</label>
                                                <input type="text" class="form-control input-sm" id="id_text_dom_dslam" placeholder="">
                                            </div>                                                
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> 
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="table-responsive hidden" id="id_dslam_ports_block">
                                    <table class="display" id="id_dslam_ports_td"  cellspacing="0" width="100%"  style="font-size:10px;">
                                        <thead>
                                            <tr class="active">
                                                <!--                                <th style="width: 280px">Наименование партнера</th>-->
<!--                                                <th style="display:none" id='id_th_adres'>Адрес</th>-->
                                                <th id='id_th_adres'>Адрес</th>
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
                                                <th>Дата опроса</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>                
                                <div class="table-responsive hidden" id="id_dslam_dup_binds_block">
                                    <table class="display" id="id_dslam_dup_binds_td"  cellspacing="0" width="100%"  style="font-size:10px;">
                                        <thead>
                                            <tr class="active">
                                                <th>Имя Хоста</th>
                                                <th>Хост</th>
                                                <th>Порт</th>
                                                <th>Статус</th>
                                                <th>Бинды</th>
                                                <th>Дата опроса</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>                                 
                                <div class="table-responsive hidden" id="id_svod_dslam_block">
                                    <table class="display" id="id_svod_dslam_td"  cellspacing="0" width="100%"  style="font-size:10px;">
                                        <thead>
                                            <tr class="active">
                                                <th>Имя Хоста</th>
                                                <th>Хост</th>
                                                <th>Активных</th>
                                                <th>Неактивных</th>
                                                <th>Отключенных</th>
                                                <th>Число портов</th>
                                                <th>Без ip bind</th>
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
            </div>
        </div>
    </ul>



</div>

<script type="text/javascript">
    
    var table;
    
function GetDslamStat() {
    
            $.ajax({
//                headers: {
//                    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
//                },
                url: "dslam_get?action=get_dslam_stat&s_dt_dslam=" + escape(sdtField_dslam.value) +
                "&e_dt_dslam=" + escape(edtField_dslam.value) +
                "&chk_arch_dslam=" + escape(archField_dslam.checked) +
                "&ed_host_dslam=" + escape(HostField_dslam.value),
                type: 'GET',
//                data: {'action': 'get_dslam_stat', 's_dt_dslam': escape(sdtField_dslam.value), 'e_dt_dslam': escape(edtField_dslam.value),'chk_arch_dslam': escape(archField_dslam.checked),
//                        'ed_host_dslam': escape(HostField_dslam.value)},
                dataType: 'json',
                success: function (result) {
                    if (result.status === 1) {
                        //alert(result.ddt);
                        //call_id = result.new_call_id;
                        
//                        $('#id_stat_error_panel').css('display', 'none');
                        
                        $('#id_ports_total_stat_dslam').html(result.total_stat);
                        $('#id_ports_acting_stat_dslam').html(result.acting_stat);
                        $('#id_ports_acted_stat_dslam').html(result.acted_stat);
                        $('#id_ports_deacted_stat_dslam').html(result.deacted_stat);
                        $('#id_ports_nobinds_stat_dslam').html(result.nobinds_stat);
                        
                    }
                },
                // Что-то пошло не так
                error: function (result) {
                    //$('#id_call_error').css('display', 'inline');
                }
            });     
    
}

function ClearStatDslam(){
        $('#id_ports_total_stat_dslam').html("0");
        $('#id_ports_acting_stat_dslam').html("0");
        $('#id_ports_acted_stat_dslam').html("0");
        $('#id_ports_deacted_stat_dslam').html("0");
        $('#id_ports_nobinds_stat_dslam').html("0");
}
    
$(document).ready(function () {

$('#id_btn_find_dslam').click(function () {
    
    $('#id_svod_dslam_block').attr('class','table-responsive hidden');
    $('#id_dslam_dup_binds_block').attr('class','table-responsive hidden');
    $('#id_dslam_ports_block').attr('class','table-responsive show');

    if ($('#id_text_ip_dslam').val() !== '') { is_visible = false; GetDslamStat(); }//$("#id_th_adres").css('display', 'none');
    else if ($('#id_text_adres_dslam').val() !== '') { is_visible = true; ClearStatDslam(); }//$("#id_th_adres").css('display', 'inline');

    //$('#id_dslam_ports_block').css('display', 'inline');
    if (table) table.destroy();

table = $('#id_dslam_ports_td').DataTable({
        "columnDefs": [
            {
                "targets": [ 0 ],
                "visible": is_visible
            }
        ],    
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
"pageLength": 10,
"ajax": "dslam_get?action=find_dslam_ports&s_dt_dslam=" + escape(sdtField_dslam.value) +
            "&e_dt_dslam=" + escape(edtField_dslam.value) +
            "&chk_arch_dslam=" + escape(archField_dslam.checked) +
            "&chk_no_binds_dslam=" + escape(NoBindsField_dslam.checked) +
            "&ed_host_dslam=" + escape(HostField_dslam.value) +
            "&ed_port_dslam=" + escape(PortField_dslam.value) +
            "&ed_bind_dslam=" + escape(BindField_dslam.value) +
            "&ed_adres_dslam=" + AdresField_dslam.value +
            "&ed_dom_dslam=" + escape(DomField_dslam.value),
"deferRender": true
});

});


$('#id_btn_find_dup_binds_dslam').click(function () {

    $('#id_svod_dslam_block').attr('class','table-responsive hidden');
    $('#id_dslam_dup_binds_block').attr('class','table-responsive show');
    $('#id_dslam_ports_block').attr('class','table-responsive hidden');
    
    ClearStatDslam();
    
    if (table) table.destroy();

table = $('#id_dslam_dup_binds_td').DataTable({
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
"ajax": "dslam_get?action=find_dslam_dup_binds&s_dt_dslam=" + escape(sdtField_dslam.value) +
            "&e_dt_dslam=" + escape(edtField_dslam.value) +
            "&chk_arch_dslam=" + escape(archField_dslam.checked) +
            "&ed_host_dslam=" + escape(HostField_dslam.value) +
            "&ed_bind_dslam=" + escape(BindField_dslam.value),
"deferRender": true
});
});


$('#id_btn_svod_dslam').click(function () {

    //$('#id_svod_dslam_block').css('display', 'inline');
    
    $('#id_svod_dslam_block').attr('class','table-responsive show');
    $('#id_dslam_dup_binds_block').attr('class','table-responsive hidden');
    $('#id_dslam_ports_block').attr('class','table-responsive hidden');
    
    ClearStatDslam();
    
    if (table) table.destroy();

table = $('#id_svod_dslam_td').DataTable({
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
"ajax": "dslam_get?action=svod_dslam&s_dt_dslam=" + escape(sdtField_dslam.value) +
            "&e_dt_dslam=" + escape(edtField_dslam.value) +
            "&chk_arch_dslam=" + escape(archField_dslam.checked),
"deferRender": true
});
});

//$.ajaxSetup({
//headers: {
//    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
//}
//});

});
</script>
