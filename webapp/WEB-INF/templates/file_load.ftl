<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>File upload page</title>
</head>
<body>
<form action="/saveImage" method="post" enctype="multipart/form-data">
    <p><input type="text" name="userId"/></p>
    <p><input type="file" name="photo" /></p>
    <input type="submit" value="submit"/>
</form>
</body>
</html>