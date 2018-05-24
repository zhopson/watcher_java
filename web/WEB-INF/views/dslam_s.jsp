<%-- 
    Document   : dslam
    Created on : 28.02.2017, 11:42:00
    Author     : andrey-man
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="right">
    <div class="form-group" style = "padding-top:5px;">
    <table border width="100%">
        <tr>
        <td>
        <div class="form-group" style="padding: 15px 5px 0px 5px;">
            <label for="id_filter_dslam_s">Фильтр по любому полю</label>
            <input type="text" class="form-control" onkeyup="JavaScript:filter_dslam_s(this, 'res_td_dslam_s_id')" id="id_filter_dslam_s" name="filter_dslam_s" placeholder="без учета регистра">
        </div>        
        <div class="checkbox" style="margin-top:-5px;margin-left:10px">
            <label>
                <input type="checkbox" id="id_chk_pppoe_dslam_s" name="chk_pppoe_dslam_s" onclick="JavaScript:set_pppoe_dslam_s();"> Только DSLAM без PPPoE
            </label>
        </div>    
        </td>
        </tr>
    </table>
    </div>
    <div class="form-group" style="padding: 0px 5px 0px 5px;">
        <button onclick="JavaScript:new_dslam_s();" class="btn btn-primary btn-sm btn-block">Новая запись</button>
        <br>
<!--        <button onclick="JavaScript:ne_binds_dslam_s();" class="btn btn-primary btn-sm btn-block">IP-BIND <> IP ВЫДЕЛЕННЫЙ</button>        
        <button onclick="JavaScript:binds_dslam_s();" class="btn btn-primary btn-sm btn-block">Реальные и выделенные бинды</button>      -->
        <br>
        <button onclick="JavaScript:export_dslam_s();" class="btn btn-primary btn-sm btn-block">Экспорт в файл</button>      
    </div>
</div>        
<div id="main_area">
    <ul class="nav nav-tabs">
        <li role="presentation"><a href="dslam">Порты DSLAM</a></li>
        <li role="presentation" class="active"><a href="dslam_s">Сети DSLAM</a></li>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="title_block">
                        <p class="title">Информация и редактирование сетей DSLAM</p>
                    </div>
                    <div class="main_block">
                        <table border width="100%" cellspacing=1 id="res_td_dslam_s_id">
<!--                            <thead>
                                <tr bgcolor="D1EEEE">
                                    <th style = "padding-left:5px;">Изменить</th>
                                    <th style = "padding-left:5px;">Удалить</th>
                                    <th style = "padding-left:5px;">Модель</th>
                                    <th style = "padding-left:5px;">Филиал</th>
                                    <th style = "padding-left:5px;">Подключение</th>
                                    <th style = "padding-left:5px;">Хост</th>
                                    <th style = "padding-left:5px;">Монтир. емкость</th>
                                    <th style = "padding-left:5px;">Задейств. емкость</th>
                                    <th style = "padding-left:5px;">IP Сеть</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>-->
                        </table>                        
                    </div> 
                </div>
            </div>
        </div>
    </ul>    
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


<div class="modal fade" id="editModal_dslam_s" tabindex="-1" role="dialog" aria-labelledby="editModalLabel_dslam_s">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="editModalLabel_dslam_s">Редактирование</h4>
      </div>
      <div class="modal-body">
        <form>
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <label for="host_dslam_s" class="control-label">Хост:</label>
                        <input type="text" class="form-control" id="id_save_host_dslam_s" disabled>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <label for="net_dslam_s" class="control-label">Сеть:</label>
                        <input type="text" class="form-control" id="id_save_net_dslam_s">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label for="bit_dslam_s" class="control-label">Биты:</label>
                        <input type="text" class="form-control" id="id_save_bit_dslam_s">
                    </div>
                </div>
            </div>
            <label id="id_error_edit_dslam_s" style="margin-top:5px;margin-left:10px"></label>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
        <button type="button" class="btn btn-primary" onclick="JavaScript:editModal_save_dslam_s();">Сохранить</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="AddModal_dslam_s" tabindex="-1" role="dialog" aria-labelledby="addModalLabel_dslam_s">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="addModalLabel_dslam_s">Добавление сети DSLAM</h4>
      </div>
      <div class="modal-body">
        <form>
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <label for="id_add_host_dslam_s" class="control-label">Хост:</label>
                        <input type="text" class="form-control" id="id_add_host_dslam_s">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <label for="id_add_net_dslam_s" class="control-label">Сеть:</label>
                        <input type="text" class="form-control" id="id_add_net_dslam_s">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label for="id_add_bit_dslam_s" class="control-label">Биты:</label>
                        <input type="text" class="form-control" id="id_add_bit_dslam_s">
                    </div>
                </div>
            </div>
            <label id="id_error_add_dslam_s" style="margin-top:5px;margin-left:10px"></label>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
        <button type="button" class="btn btn-primary" onclick="JavaScript:addModal_add_dslam_s();">Сохранить</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="deleteModal_dslam_s" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel_dslam_s">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="deleteModalLabel_dslam_s">Удаление Сети DSLAM</h4>
      </div>
      <div class="modal-body">
          <p>Удаление Сети, Продолжить?</p>
          <label id="id_error_del_dslam_s" style="margin-top:5px;margin-left:10px"></label>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
        <button type="button" class="btn btn-primary" onclick="JavaScript:deleteModal_dslam_s();">Удалить</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="GetRepModal_dslam_s" tabindex="-1" role="dialog" aria-labelledby="GetRepModalLabel_dslam_s">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="GetRepModalLabel_dslam_s">Отчеты для DSLAM</h4>
      </div>
      <div class="modal-body">
          <p>Выберите отчет</p>
          <div class="radio">
              <label>
                  <input type="radio" name="options_rep_dslam_s" id="id_options_rep_nebinds_dslam_s" value="option_rep_nebinds_dslam_s" checked>
                  Несовпадение IP-BIND IP-ВЫДЕЛЕННЫЙ
              </label>
          </div>
          <div class="radio">
              <label>
                  <input type="radio" name="options_rep_dslam_s" id="id_options_rep_realbinds_dslam_s" value="option_rep_realbinds_dslam_s">
                  Соответствие IP-BIND IP-ВЫДЕЛЕННЫЙ
              </label>
          </div>
          <label id="id_error_rep_dslam_s" style="margin-top:5px;margin-left:10px"></label>          
          <input type="hidden" id="id_host_get_rep_dslam_s">
          <input type="hidden" id="id_net_get_rep_dslam_s">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
        <button type="button" class="btn btn-primary" onclick="JavaScript:GetRepModal_dslam_s();">Выполнить</button>
      </div>
    </div>
  </div>
</div>