<%-- 
    Document   : error
    Created on : 02.11.2018, 15:15:04
    Author     : andrey-man
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Watcher Вход</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/bootstrap/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/bootstrap/signin.css" rel="stylesheet">

    <script type="text/javascript" src="jquery-3.2.0.min.js"></script>

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="css/bootstrap/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

<!--    <div class="container">

      <form class="form-signin" >
        <h2 class="form-signin-heading" style='color: red;'>Неверные учетные данные!</h2>
        <div class='alert-danger'><h3 class="form-signin-heading">Неверные учетные данные</h3></div>
        <button class="btn btn-lg btn-primary btn-block" id='id_back'>Попробовать снова</button>
      </form>
    </div>  /container -->

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="page-header" style="margin-top:-18px">
                <h2 style='color:palevioletred'>Ошибка</h2>
            </div>            
        </div>
    </div>
<!--    <div class="row">
        <div class="col-md-6">
            Уровень доступа:
        </div>
    </div>-->
    <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">Информация</div>

                <div class="panel-body">
                    <div class="alert alert-warning">
                        <ul>
                            Введены неверные учетные данные!
                        </ul>
                    </div>                
                </div>
                <div class="form-group" style="margin:0 60px 30px 30px">
                    <a class="btn btn-default" href="Javascript:window.history.back()" role="button">  Назад к входу </a>
                </div>
            </div>
    </div>
</div>



    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="css/bootstrap/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>


