<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>Search page</title>
</head>
<body>
<p><input id="query" oninput="f()"/></p>
<div id="res"></div>
<script type="application/javascript">
    function f() {
        if ($("#query").val().length >= 1) {
            $.ajax({
                url: "/search",
                data: {"query": $("#query").val()},
                dataType: "json",
                success: function (msg) {
                    if (msg.length > 0) {
                        $("#res").html("");
                        for (var i = 0; i < msg.length; i++) {
                            $("#res").append("<li>" + msg[i].name + "</li>");
                        }
                    } else {
                        $("#res").html("No results..");
                    }
                }
            })
        }
        else {
            $("#res").html("");
        }
    }
</script>
</body>
</html>