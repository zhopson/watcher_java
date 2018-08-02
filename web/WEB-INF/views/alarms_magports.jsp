<%-- 
    Document   : alarms
    Created on : 28.02.2017, 11:42:15
    Author     : andrey-man
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="right">
    Тест
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
        <li role="presentation"><a href="alarms">Активные аварии</a></li>
        <li role="presentation"><a href="alarms_bshpd">Аварии БШПД</a></li>
        <li role="presentation"><a href="alarms_bigping">Устройства с большим пингом</a></li>
        <li role="presentation" class="active"><a href="alarms_magports">Магистральные порты ШПД</a></li>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="title_block">
                        <p class="title">Известные проблемы с оборудованием</p>
                    </div>
                    <div class="main_block">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                
                            </div>
                        </div>
                    </div>      
                </div>      
            </div>      
        </div>      
    </ul>

</div>
