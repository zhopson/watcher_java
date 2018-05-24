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
    <button onclick="JavaScript:Get_log_list_dhcpo();" class="btn btn-primary">Выполнить</button>        
</div>        
<!--  </form>-->
<div id="main_area">
    <div class="title_block">
        <p class="title">Логи DHCP Optinet</p>
    </div>
    <div class="main_block">
        <!--            <table class="table table-hover" id="res_td_dhcpo_id">-->
        <!--            <table border width="100%" cellspacing=1 id="res_td_dhcpo_id">-->
        <table border width="100%" cellpadding="0" cellspacing="0" border="0"  id="res_td_dhcpo_id" class="sortable">
            <thead>
                <!--                <tr bgcolor="D1EEEE">-->
                <tr>
                    <th><h3>Дата</h3></th>
                    <th><h3>Заголовок</h3></th>
                    <th><h3>Сообщение</h3></th>
                    <th><h3>Сервер</h3></th>
                </tr>
            </thead>
            <tbody>
<!--                                <tr>
                                    <td>элемент 11</td>
                                    <td>элемент 12</td>
                                    <td>элемент 13</td>
                                    <td>элемент 14</td>
                                </tr>-->
            </tbody>
        </table>
       <div id="controls">
        <div class="row">
            <div class="col-md-4">
                <div id="perpage">
                    <select onchange="sorter.size(this.value)">
                        <option value="50">50</option>
                        <option value="100">100</option>
                        <option value="200" selected="selected">200</option>
                        <option value="500">500</option>
                    </select>
                    <span>Entries Per Page</span>
                </div>
            </div>
            <div class="col-md-4">
                <div id="navigation">
                    <img src="img/first.gif" width="16" height="16" alt="First Page" onclick="sorter.move(-1, true)" />
                    <img src="img/previous.gif" width="16" height="16" alt="Previous Page" onclick="sorter.move(-1)" />
                    <img src="img/next.gif" width="16" height="16" alt="Next Page" onclick="sorter.move(1)" />
                    <img src="img/last.gif" width="16" height="16" alt="Last Page" onclick="sorter.move(1, true)" />
                </div>
            </div>
            <div class="col-md-4">
                <div id="text">Displaying Page <span id="currentpage"></span> of <span id="pagelimit"></span></div>
            </div>
        </div>
       </div>
    </div>
</div>

<script type="text/javascript" src="table.js"></script>
<script type="text/javascript">
                    var sorter = new TINY.table.sorter("sorter");
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
</script>