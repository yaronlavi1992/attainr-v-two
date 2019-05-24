<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main"/>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>שינוי סיסמה</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="main.css">
    <script src="main.js"></script>
</head>

<body>

<div class="container text-center">
    <div class="row">
        <div class="col-sm-12">
            <h1>שינוי סיסמה</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <p class="text-center" dir="rtl">השתמש בטופס שלהלן כדי לשנות את הסיסמה שלך. הסיסמה שלך לא יכולה להיות זהה
                לשם המשתמש שלך.</p>
            <form method="post" id="passwordForm">
                <input type="password" dir="rtl" class="input-lg form-control" name="password1" id="password1"
                       placeholder="סיסמה חדשה" autocomplete="off">
                <div class="row">
                    <div class="col-sm-6">
                        אורך 8 תווים<span id="8char" class="glyphicon glyphicon-remove"
                                          style="color:#FF0004;"></span><br>
                        לפחות אות אחת גדולה<span id="ucase" class="glyphicon glyphicon-remove"
                                                 style="color:#FF0004;"></span>
                    </div>
                    <div class="col-sm-6">
                        לפחות אות אחת קטנה<span id="lcase" class="glyphicon glyphicon-remove"
                                                style="color:#FF0004;"></span><br>
                        לפחות מספר אחד<span id="num" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span>
                    </div>
                </div>
                <input type="password" dir="rtl" class="input-lg form-control" name="password2" id="password2"
                       placeholder="סיסמה חדשה בשנית" autocomplete="off">
                <div class="row">
                    <div class="col-sm-12">
                        הסיסמאות שהוכנסו תואמות
                        <span id="pwmatch" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span>
                    </div>
                </div>
                <input type="submit" class="col-xs-12 btn btn-primary btn-load btn-lg"
                       data-loading-text="Changing Password..." value="אישור">
            </form>
        </div>
        <!--/col-sm-6-->
    </div>
    <!--/row-->
</div>
<script>
		$("input[type=password]").keyup(function () {
			var ucase = new RegExp("[A-Z]+");
			var lcase = new RegExp("[a-z]+");
			var num = new RegExp("[0-9]+");

			if ($("#password1").val().length >= 8) {
				$("#8char").removeClass("glyphicon-remove");
				$("#8char").addClass("glyphicon-ok");
				$("#8char").css("color", "#00A41E");
			} else {
				$("#8char").removeClass("glyphicon-ok");
				$("#8char").addClass("glyphicon-remove");
				$("#8char").css("color", "#FF0004");
			}

			if (ucase.test($("#password1").val())) {
				$("#ucase").removeClass("glyphicon-remove");
				$("#ucase").addClass("glyphicon-ok");
				$("#ucase").css("color", "#00A41E");
			} else {
				$("#ucase").removeClass("glyphicon-ok");
				$("#ucase").addClass("glyphicon-remove");
				$("#ucase").css("color", "#FF0004");
			}

			if (lcase.test($("#password1").val())) {
				$("#lcase").removeClass("glyphicon-remove");
				$("#lcase").addClass("glyphicon-ok");
				$("#lcase").css("color", "#00A41E");
			} else {
				$("#lcase").removeClass("glyphicon-ok");
				$("#lcase").addClass("glyphicon-remove");
				$("#lcase").css("color", "#FF0004");
			}

			if (num.test($("#password1").val())) {
				$("#num").removeClass("glyphicon-remove");
				$("#num").addClass("glyphicon-ok");
				$("#num").css("color", "#00A41E");
			} else {
				$("#num").removeClass("glyphicon-ok");
				$("#num").addClass("glyphicon-remove");
				$("#num").css("color", "#FF0004");
			}

			if ($("#password1").val() == $("#password2").val()) {
				$("#pwmatch").removeClass("glyphicon-remove");
				$("#pwmatch").addClass("glyphicon-ok");
				$("#pwmatch").css("color", "#00A41E");
			} else {
				$("#pwmatch").removeClass("glyphicon-ok");
				$("#pwmatch").addClass("glyphicon-remove");
				$("#pwmatch").css("color", "#FF0004");
			}
		});

</script>
</body>

</html>