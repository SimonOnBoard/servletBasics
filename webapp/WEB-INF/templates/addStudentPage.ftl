<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <title>Add student page</title>
</head>
<body>
<form method="post" action="/addStudent" id="activateForm" >
    <input type="number" name="age"/>
    <input type="number" name="course"/>
    <input type="text" name="name"/>
    <input type="text" name="surname"/>
    <input type="checkbox" name="deducted" checked/>
    <input type="button" value="Send" onclick="send()"/>
</form>
<script>
    function send() {
        var form = $("#activateForm")[0];
        var formData = new FormData(form);
        var object = {};
        formData.forEach((value, key) => {object[key] = value});
        var json = JSON.stringify(object);
        $.ajax({
            type: 'POST',
            url: form.action,
            data: json,
            processData: false,
            contentType: false,
            dataType: "json",
            success: function (data) {
                //попробовать без parse и stringify извлечь содержимое из входного(data) js объекта
                var result = JSON.parse(JSON.stringify(data));
                console.log(window.location.origin);
                if(result['redirect Url'] !== null){
                    window.location.replace(window.location.origin + result['redirectUrl']);
                }
            },
            error: function (err) {
                console.log(err);
                console.log(JSON.stringify(err));
                alert(err);
            }
        });
    }
</script>
</body>
</html>