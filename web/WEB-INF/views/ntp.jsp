<%-- 
    Document   : ntp
    Created on : 28.02.2017, 11:43:01
    Author     : andrey-man
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="right">
    <div class="form-group"></div>
    <div class="form-group">
        <label for="id_ip_ntp">IP АДРЕС</label>
        <input type="text" class="form-control" id="id_ip_ntp" name="v_ip_ntp" placeholder="192.168.195.123">
    </div>        
    <div class="form-group"></div>
    <div class="form-group" style="padding: 0px 55px 0px 5px;">
        <button class="btn btn-primary btn-block" id="id_btn_status_ntp">Текущий статус</button>    
        <button class="btn btn-primary btn-block" id="id_btn_logs_ntp">Логи команд</button>    
    </div>
</div>        
<div id="main_area">
    <div class="title_block">
        <p class="title">Информация с БД NTP.RT14.RU</p>
    </div>
    <div class="main_block">

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="display" id="id_ntp_td"  cellspacing="0" width="100%"  style="font-size:10px;">
                        <thead>
                            <tr class="active">
                                <!--                                <th style="width: 280px">Наименование партнера</th>-->
                                <th>Дата sql-запроса</th>
                                <th>Логин</th>
                                <th>Операция</th>
                                <th>ip адрес</th>
                                <th>Статус</th>
                                <th>timestamp</th>
                                <th>timestamp shaper1</th>
                                <th>Скорость</th>
                                <th>Service ID</th>
                                <th>timestamp shaper2</th>
                                <th>Измененные поля</th>
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

        $('#id_btn_logs_ntp').click(function () {
            if ($('#id_ip_ntp').val() !== '') {
                if (table)
                    table.destroy();

                table = $('#id_ntp_td').DataTable({
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
                    "ajax": 'logs_ntp?action=logs&ip=' + escape($('#id_ip_ntp').val()),
                    "deferRender": true
                });


                //table.ajax.url( 'logs_ntp?action=logs&ip=' + escape($('#id_ip_ntp').val()) ).load();
            }
        });

        $('#id_btn_status_ntp').click(function () {
            if ($('#id_ip_ntp').val() !== '') {
                if (table)
                    table.destroy();

                table = $('#id_ntp_td').DataTable({
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
                    "ajax": 'logs_ntp?action=status&ip=' + escape($('#id_ip_ntp').val()),
                    "deferRender": true
                });

                //table.ajax.url( 'logs_ntp?action=status&ip=' + escape($('#id_ip_ntp').val()) ).load();
            }
        });

//$.ajaxSetup({
//headers: {
//    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
//}
//});

        table = $('#id_ntp_td').DataTable({
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
//"ajax": "/ntp/json",
            "deferRender": true
        });

    });
</script>