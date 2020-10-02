<#ftl encoding="utf-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<body>
<#if message?has_content>
    <p>${message}</p>
</#if>
<form method="post" action="/login">
    <label>login input</label>
    <input type="text" name="username" placeholder="введите логин"/>
    <input type="password" name="password" placeholder="введите пароль"/>
    <input type="submit">Подтвердить</input>
</form>

</body>
</html>