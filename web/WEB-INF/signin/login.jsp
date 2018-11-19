<%-- 
    Document   : login
    Created on : 30.10.2018, 10:01:48
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

    <div class="container">

      <form class="form-signin" action='/watcher/signin' method='post' >
        <h2 class="form-signin-heading">Введите учетные данные</h2>
<!--        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>-->

        <label for="inputlogin" class="sr-only">Имя пользователя</label>
        <input type="text" id="inputlogin" name="inputlogin" class="form-control" placeholder="Username" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Запомнить меня
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
      </form>
    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="css/bootstrap/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>

<script type="text/javascript">
    
//    $(document).ready(function () {
//        $('#id_signin').click(function () {
//                $.ajax({
//                  url: "signin",  
////                headers: {
////                    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
////                },
////                    url: "alarms_get?action=add_comment&comment_alarms=" + encodeURIComponent($('#id_text_comment_alarms').val()) + "&ids_str_alarms=" + encodeURIComponent(ids_str),
//                    type: 'POST',
//                    data: {'inputlogin': escape($("#inputlogin").val()), 'inputPassword': escape($("#inputPassword").val())},
//                    dataType: 'json',
//                    success: function (result) {
//                        if (result.status === 1) {
//                            alert('yes');
//                        }
//                    },
//                    // Что-то пошло не так
//                    error: function (result) {
//                        //$('#id_call_error').css('display', 'inline');
//                        alert('no');
//                    }
//                });
//
//
//
//        });    
//    });
</script>
