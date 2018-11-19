<%-- 
    Document   : alarms
    Created on : 28.02.2017, 11:42:15
    Author     : andrey-man
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="right">
    <div class="form-group" style="margin-top:15px;margin-left:5px">
        <label for="id_total_abons">Количество абонентов по выбранным устройствам</label>
        <input type="text" class="form-control" id="id_total_abons" name="v_total_abons" placeholder="Всего / Интернет / IPTV"  data-toggle="tooltip" title="Всего / Интернет / IPTV">
    </div>     
    <div class="checkbox" style="margin-top:15px;margin-left:5px">
        <label>
            <input type="checkbox" id="id_chk_no_comments" name="v_chk_no_comments"> Показать все без комментариев
        </label>
    </div>    
    <button style="margin-top:15px;margin-left:5px" class="btn btn-primary" id="id_btn_excel_alarms">Экспорт в Excel</button>
</div>        
<div id="main_area">
    <!--    <div id="button_bar">
            <div class="button_block">
                <a href="#">Активные аварии</a>
            </div>
            <div class="button_block">
                <a href="#">Устройства с большим откликом</a>
            </div>
            <div class="button_block">
                <a href="#">Магистральные порты</a>
            </div>
        </div>-->
    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="alarms">Активные аварии</a></li>
        <li role="presentation"><a href="alarms_bshpd">Аварии БШПД</a></li>
        <li role="presentation"><a href="alarms_bigping">Устройства с большим пингом</a></li>
        <li role="presentation"><a href="alarms_magports">Магистральные порты ШПД</a></li>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="title_block">
                        <p class="title">Текущие остановки ШПД</p>
                    </div>
                    <div class="main_block">
                        <div class="row">
                            <!--                            <div class="col-md-2"><label style="float: right" for="id_text_comment_alarms">Комментарий</label></div>-->
                            <!--                            <div class="col-md-2"><a href="#" data-toggle="tooltip" title="Причины для остановок" data-original-title="Причины для остановок">Комментарий</a></div>-->
                            <div class="col-md-2"><a href="#" data-toggle="tooltip" title="Причины для остановок">Комментарий</a></div>
                            <div class="col-md-7"><textarea class="form-control" rows="2" id="id_text_comment_alarms"></textarea></div>
                            <div class="col-md-3"><button type="button" class="btn btn-primary" id="id_btn_add_comment_alarms">Добавить</button></div>
                        </div>
                        <!--                        <div class="form-group">
                                                    <label for="id_text_comment_alarms">Комментарий</label>
                                                    <textarea class="form-control" rows="2" id="id_text_comment_alarms"></textarea>                    
                                                </div>                        -->
                        <div class="form-group"></div>
                        <div class="page-header">
                        </div>
                        <!--                        <div class="panel panel-default">
                                                    <div class="panel-body">-->
                        <div class="table-responsive">
                            <table class="display" id="id_alarms_td"  cellspacing="0" width="100%"  style="font-size:10px;">
                                <thead>
                                    <tr class="active">
                                        <!--                                <th style="width: 280px">Наименование партнера</th>-->
                                        <th><input type="checkbox" id="id_chk_all_alarms"></th>
                                        <th>Кросс</th>
                                        <th>Город</th>
                                        <th>ip</th>
                                        <th>Модель</th>
                                        <th>Наименование</th>
                                        <th>Начало</th>
                                        <th>Продолжительность</th>
                                        <th>Кол-во портов</th>
                                        <th>Кол-во инет</th>
                                        <th>Кол-во iptv</th>
                                        <th>ID</th>
                                        <th>Комментарий</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>                                  
                        <!--                            </div>
                                                </div>-->
                    </div>      
                </div>      
            </div>      
        </div>      
    </ul>

</div>

<script type="text/javascript">

    var table;
    var timerIds = [];
    //var alarmIds = [];
    var tPorts = 0, tInet = 0, tIptv = 0;

    var alarmIds = Object.create(null);

    function StartTimer() {
        alert('start');
        //setInterval(GetDataAlarms($('#id_chk_no_comments').is(':checked')), 60000);
        //clearInterval(timerId);
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    window.onload = function () {
        $(function () {
            // инициализации подсказок для всех элементов на странице, имеющих атрибут data-toggle="tooltip"
            $('[data-toggle="tooltip"]').tooltip();
        });
        //setTimeout(GetDataAlarms, 20000,$('#id_chk_no_comments').is(':checked'));
    };

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    $(document).ready(function () {
////////////////////////////////////////////////////////////////////////////////
        GetDataAlarms();
////////////////////////////////////////////////////////////////////////////////
        $(function () {
            // инициализации подсказок для всех элементов на странице, имеющих атрибут data-toggle="tooltip"
            $('[data-toggle="tooltip"]').tooltip();
        });
////////////////////////////////////////////////////////////////////////////////
        $('#id_chk_no_comments').click(function () {
            //alert(escape($('#id_chk_no_comments').is(':checked')));

            GetDataAlarms($('#id_chk_no_comments').is(':checked'));

        });
////////////////////////////////////////////////////////////////////////////////        
        $('#id_btn_add_comment_alarms').click(function () {
            if (!$('#id_text_comment_alarms').val()) {
                alert("Заполните поле Комментарий!");
                return;
            }
            if (!isEmpty(alarmIds)) {
                var ids_str = '';
                for (var key in alarmIds) {
                    ids_str += key + ',';
                    //delete alarmIds[key];
                }
                ids_str = ids_str.replace(/\,+$/g, "");
                //alert(ids_str);
                //ajax

                $.ajax({
//                headers: {
//                    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
//                },
                    url: "alarms_get?action=add_comment&comment_alarms=" + encodeURIComponent($('#id_text_comment_alarms').val()) + "&ids_str_alarms=" + encodeURIComponent(ids_str),
                    type: 'GET',
//                data: {'action': 'get_dslam_stat', 's_dt_dslam': escape(sdtField_dslam.value), 'e_dt_dslam': escape(edtField_dslam.value),'chk_arch_dslam': escape(archField_dslam.checked),
//                        'ed_host_dslam': escape(HostField_dslam.value)},
                    dataType: 'json',
                    success: function (result) {
                        if (result.status === 1) {

//                        $('#id_stat_error_panel').css('display', 'none');

                            GetDataAlarms($('#id_chk_no_comments').is(':checked'));
//                            $('#id_ports_total_stat_dslam').html(result.total_stat);
//                            $('#id_ports_acting_stat_dslam').html(result.acting_stat);
//                            $('#id_ports_acted_stat_dslam').html(result.acted_stat);
//                            $('#id_ports_deacted_stat_dslam').html(result.deacted_stat);
//                            $('#id_ports_nobinds_stat_dslam').html(result.nobinds_stat);

                        }
                    },
                    // Что-то пошло не так
                    error: function (result) {
                        //$('#
                        //').css('display', 'inline');
                    }
                });

            } else
                alert("Не выбраны аварии");
            //alert("комментарий: " + encodeURIComponent($('#id_text_comment_alarms').val()));
            //GetDataAlarms($('#id_chk_no_comments').is(':checked'));

        });
////////////////////////////////////////////////////////////////////////////////
        $('#id_chk_all_alarms').click(function () {
            //alert(escape($('#id_chk_all_alarms').is(':checked')));

            //PhoneList_notif = "";
            if ($('#id_chk_all_alarms').is(':checked') === true)
                chk = true;
            else
                chk = false;

            //AlarmsTable = document.getElementById("id_alarms_td");
            var AlarmsTable_tbody = document.getElementById("id_alarms_td").getElementsByTagName("tbody")[0];

//            if (AlarmsTable.getElementsByTagName("tr").length > 1) {
            if (AlarmsTable_tbody.childNodes.length > 1) {
                for (loop = 0; loop < AlarmsTable_tbody.childNodes.length - 1; loop++) {
                    var items = AlarmsTable_tbody.rows[loop].cells[0].getElementsByTagName("input");

                    if (chk) {
                        if (!items.item(0).checked) {
                            items.item(0).checked = chk;
                            items.item(0).onclick();
                        }
                    } else {
                        if (items.item(0).checked) {
                            items.item(0).checked = chk;
                            items.item(0).onclick();
                        }
                    }

                    //items.item(0).checked = chk;

//                    var ports = AlarmsTable_tbody.rows[loop].cells[8].innerHTML;
//                    var inet = AlarmsTable_tbody.rows[loop].cells[9].innerHTML;
//                    var iptv = AlarmsTable_tbody.rows[loop].cells[10].innerHTML;

//                    if (chk) {
//                        tPorts += ports;
//                        tInet += inet;
//                        tIptv += iptv;
//                    } else {
//                        tPorts -= ports;
//                        tInet -= inet;
//                        tIptv -= iptv;
//                    }
                    //items.item(0).onclick();
                    //PhoneList_notif = PhoneList_notif + items.item(0).id + ',';
                }
                //$('#id_total_abons').val(tPorts + ' / ' + tInet + ' / ' + tIptv);

//                if (tPorts < 0 || tInet < 0 || tIptv < 0) {
//                    tPorts = tInet = tIptv = 0;
//                    $('#id_total_abons').val("");
//                }
                //alert(AbonTable_notif.rows[1].cells[1].innerText);
//                if (PhoneList_notif !== "") {
//                    PhoneList_notif = PhoneList_notif.substring(0, PhoneList_notif.length - 1);
//                    //alert(PhoneList_notif+" checked:"+document.getElementById("id_chk_all_alarms").checked);
//
//                }
            }

        });
////////////////////////////////////////////////////////////////////////////////
        $('#id_text_comment_alarms').focus(function () {
            //console.log('Элемент memo получил фокус');

            for (var i = 0; i < timerIds.length; i++) {
                clearTimeout(timerIds[i]);
            }
            if (timerIds.length > 10)
                timerIds.length = 0;

            var timerId = setTimeout(GetDataAlarms, 100000, $('#id_chk_no_comments').is(':checked'));
            timerIds.push(timerId);

        });

    });

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    function isEmpty(object) {
        for (var key in object)
            if ({}.hasOwnProperty.call(object, key))
                return false;
        return true;
    }


    function SetAllSelected() {
        if (isEmpty(alarmIds)) {

            var AlarmsTable_tbody = document.getElementById("id_alarms_td").getElementsByTagName("tbody")[0];

            if (AlarmsTable_tbody.childNodes.length > 1) {
                for (loop = 0; loop < AlarmsTable_tbody.childNodes.length - 1; loop++) {
                    var items = AlarmsTable_tbody.rows[loop].cells[0].getElementsByTagName("input");
                    if (items.item(0).checked)
                        items.item(0).checked = false;
                }
            }
            $('#id_chk_all_alarms').prop('checked', false);

        }
    }

    function GetDataAlarms(pnew_alarms = false) {

        if (table)
            table.destroy();


        table = $('#id_alarms_td').on('page.dt', function () {
            SetAllSelected();
        }).DataTable({

            "columnDefs": [{
                    "searchable": false,
                    "orderable": false,
                    "targets": 0
                },
                {
                    "targets": [8],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [9],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [10],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [11],
                    "visible": false,
                    "searchable": false
                },
                {
                    "type": "date",
                    "targets": 6
                }],
            "order": [[3, 'asc']],
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
//            "ajax": 'alarms_get?action=list&no_comments=' + escape($('#id_chk_no_comments').is(':checked'))
            "ajax": 'alarms_get?action=list&no_comments=' + escape(pnew_alarms)
                    //"deferRender": true
        });
        $('[data-toggle="tooltip"]').tooltip();

        //playSound();
        //alert('get data');
        //if (timerId) clearTimeout(timerId);
        for (var i = 0; i < timerIds.length; i++) {
            clearTimeout(timerIds[i]);
        }
        if (timerIds.length > 10)
            timerIds.length = 0;

        var timerId = setTimeout(GetDataAlarms, 60000, $('#id_chk_no_comments').is(':checked'));
        timerIds.push(timerId);

        $('#id_total_abons').val("");
        tPorts = tInet = tIptv = 0;
        //alarmIds.length = 0;
        for (var key in alarmIds) {
            delete alarmIds[key];
        }
        console.log(alarmIds);

        $('#id_chk_all_alarms').prop('checked', false);

        $("input[type=search]").focus(function () {
//            console.log('Элемент search получил фокус');
            //alert('Элемент search получил фокус.');
            chk = false;

            var AlarmsTable_tbody = document.getElementById("id_alarms_td").getElementsByTagName("tbody")[0];

            if (AlarmsTable_tbody.childNodes.length > 1) {
                for (loop = 0; loop < AlarmsTable_tbody.childNodes.length - 1; loop++) {
                    var items = AlarmsTable_tbody.rows[loop].cells[0].getElementsByTagName("input");
                    items.item(0).checked = chk;
                }
            }
            $('#id_chk_all_alarms').prop('checked', false);

            tPorts = tInet = tIptv = 0;
            //alarmIds.length = 0;
            for (var key in alarmIds) {
                delete alarmIds[key];
            }
            $('#id_total_abons').val("");
            console.log(alarmIds);

        });


    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    var playSound = (function beep() {
        var snd = new Audio("data:audio/wav;base64,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qJrx4KOQjahZxWbcZgztj2c49nKmkId44S71j0c8eV9yDK6uPRzx5X18eDvjvQ6yKo9ZSS6l//8elePK/Lf//IInrOF/FvDoADYAGBMGb7FtErm5MXMlmPAJQVgWta7Zx2go+8xJ0UiCb8LHHdftWyLJE0QIAIsI+UbXu67dZMjmgDGCGl1H+vpF4NSDckSIkk7Vd+sxEhBQMRU8j/12UIRhzSaUdQ+rQU5kGeFxm+hb1oh6pWWmv3uvmReDl0UnvtapVaIzo1jZbf/pD6ElLqSX+rUmOQNpJFa/r+sa4e/pBlAABoAAAAA3CUgShLdGIxsY7AUABPRrgCABdDuQ5GC7DqPQCgbbJUAoRSUj+NIEig0YfyWUho1VBBBA//uQZB4ABZx5zfMakeAAAAmwAAAAF5F3P0w9GtAAACfAAAAAwLhMDmAYWMgVEG1U0FIGCBgXBXAtfMH10000EEEEEECUBYln03TTTdNBDZopopYvrTTdNa325mImNg3TTPV9q3pmY0xoO6bv3r00y+IDGid/9aaaZTGMuj9mpu9Mpio1dXrr5HERTZSmqU36A3CumzN/9Robv/Xx4v9ijkSRSNLQhAWumap82WRSBUqXStV/YcS+XVLnSS+WLDroqArFkMEsAS+eWmrUzrO0oEmE40RlMZ5+ODIkAyKAGUwZ3mVKmcamcJnMW26MRPgUw6j+LkhyHGVGYjSUUKNpuJUQoOIAyDvEyG8S5yfK6dhZc0Tx1KI/gviKL6qvvFs1+bWtaz58uUNnryq6kt5RzOCkPWlVqVX2a/EEBUdU1KrXLf40GoiiFXK///qpoiDXrOgqDR38JB0bw7SoL+ZB9o1RCkQjQ2CBYZKd/+VJxZRRZlqSkKiws0WFxUyCwsKiMy7hUVFhIaCrNQsKkTIsLivwKKigsj8XYlwt/WKi2N4d//uQRCSAAjURNIHpMZBGYiaQPSYyAAABLAAAAAAAACWAAAAApUF/Mg+0aohSIRobBAsMlO//Kk4soosy1JSFRYWaLC4qZBYWFRGZdwqKiwkNBVmoWFSJkWFxX4FFRQWR+LsS4W/rFRb/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////VEFHAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAU291bmRib3kuZGUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMjAwNGh0dHA6Ly93d3cuc291bmRib3kuZGUAAAAAAAAAACU=");
        return function () {
            snd.play();
        };
    })();
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    function SelectAlarms(pid, pstatus, pports, pinet, piptv) {

        if (pstatus) {
            tPorts += pports;
            tInet += pinet;
            tIptv += piptv;
            alarmIds[pid] = 1;
        } else {
            tPorts -= pports;
            tInet -= pinet;
            tIptv -= piptv;
            if ({}.hasOwnProperty.call(alarmIds, pid)) {
                delete alarmIds[pid];
            }
        }
        $('#id_total_abons').val(tPorts + ' / ' + tInet + ' / ' + tIptv);

        if (tPorts < 0 || tInet < 0 || tIptv < 0) {
            tPorts = tInet = tIptv = 0;
            $('#id_total_abons').val("");
            for (var key in alarmIds) {
                delete alarmIds[key];
            }
            //alarmIds.length = 0;
        }

        console.log(alarmIds);

        //alert('pid: '+ pid+', CHECKED:' + pstatus+', ports:'+pports+', inet'+pinet+', iptv'+piptv);

        for (var i = 0; i < timerIds.length; i++) {
            clearTimeout(timerIds[i]);
        }
        if (timerIds.length > 10)
            timerIds.length = 0;

        var timerId = setTimeout(GetDataAlarms, 100000, $('#id_chk_no_comments').is(':checked'));
        timerIds.push(timerId);
    }

</script>    
